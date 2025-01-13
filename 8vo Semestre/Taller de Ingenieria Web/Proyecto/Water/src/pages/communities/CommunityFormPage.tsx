import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { Button, Card, CardBody, CardFooter, CardHeader, Input, Typography } from "@material-tailwind/react";

import { Routes } from "../../routes/CONSTANTS";
import { Municipality } from "../../models/Municipality";
import { MunicipalityService } from "../../services/MunicipalityService";
import { ReactSearchAutocomplete } from 'react-search-autocomplete'
import { Community } from "../../models/Community";
import { CommunityService } from "../../services/CommunityService";

type FormErrors = Partial<Record<keyof Community, boolean>>;

const CommunityFormPage = () => {
    const [form, setForm] = useState<Community>({
        name: "",
        municipality_id: 0,
    });
    const [municipalities, setMunicipalities] = useState<Municipality[]>([]);
    const [formErrors, setFormErrors] = useState<FormErrors>({});
    const [requestError, setRequestError] = useState("");
    const [isSending, setIsSending] = useState(false);
    const navigate = useNavigate();
    const { id } = useParams();

    useEffect(() => {
        if (id) fetchCommunity()
        fetchMunicipalities();
    }, [id]);

    const fetchCommunity = () => {
        if (!id) return
        CommunityService.get(parseInt(id)).then((community) => {
            setForm({
                name: community.name,
                municipality_id: community.municipality_id,
            });
        }).catch((error) => setRequestError(error.message));
    }

    const fetchMunicipalities = () => {
        MunicipalityService.list().then((municipalities) => {
            setMunicipalities(municipalities);
        }).catch((error) => setRequestError(error.message));
    }

    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const { name, value } = event.target;
        setForm((prevForm) => ({ ...prevForm, [name]: value }));
        if (formErrors[name as keyof Community]) {
            setFormErrors({ ...formErrors, [name]: false });
        }
    }

    const handleFormSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        event.stopPropagation();
        
        const errors = validateForm(form);
        setFormErrors(errors);
        if (Object.values(errors).some(err => err)) return;

        setIsSending(true);
        if (!id) {
            createCommunity();
        } else {
            updateCommunity();
        }
    }

    const validateForm = (form: Community): FormErrors => {
        const errors: FormErrors = {};
        if (form.name === "") errors.name = true;
        if (form.municipality_id === 0) errors.municipality_id = true;
        return errors;
    }

    const createCommunity = () => {
        CommunityService.create(form)
            .then(() => navigate(Routes.COMMUNITY.LIST))
            .catch(error => {
                setIsSending(false);
                setRequestError(error.message)
            });
    }

    const updateCommunity = () => {
        if (!id) return
        CommunityService.update(parseInt(id), form)
            .then(() => navigate(Routes.COMMUNITY.LIST))
            .catch(error => {
                setIsSending(false);
                setRequestError(error.message)
            });
    }

    const resetSelectedDepartment = () => {
        setForm((prevForm) => ({ ...prevForm, department_id: 0 }));
    }

    const handleOnSelect = (municipality: Municipality) => {
        setForm((prevForm) => ({ ...prevForm, municipality_id: municipality.id! }));
        if (formErrors.municipality_id) {
            setFormErrors({ ...formErrors, municipality_id: false });
        }
    }

    const handleOnSearch = () => {
        resetSelectedDepartment();
    }

    return (<Card className="lg:w-160 mx-auto my-8">
        <form onSubmit={handleFormSubmit} noValidate>
            <CardHeader floated={false} className="mb-4" shadow={false}>
                <Typography variant="h3" color="blue">
                    {id ? "Editar" : "Crear"} Comunidad
                </Typography>
            </CardHeader>
            <CardBody className="flex flex-col gap-4">
                <Input type="text" label="Nombre" size="lg" name="name" id="name" required
                    value={form.name} onChange={(e) => handleInputChange(e)} error={formErrors.name} />
                <ReactSearchAutocomplete
                    items={municipalities}
                    onSelect={handleOnSelect}
                    onClear={resetSelectedDepartment}
                    onSearch={handleOnSearch}
                    placeholder="Buscar municipio..."
                    showItemsOnFocus
                    maxResults={5}
                    
                    styling={formErrors.municipality_id?{border: '1px solid #FF0000'}:{}}
                />

                {requestError && <Typography color="red">{requestError}</Typography>}
            </CardBody>
            <CardFooter className="pt-0">
                <Button color="blue" fullWidth type="submit" disabled={isSending}>
                    {isSending ? "Guardando..." : "Guardar"}
                </Button>
            </CardFooter>
        </form>
    </Card>);
}

export default CommunityFormPage;