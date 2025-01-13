import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { Button, Card, CardBody, CardFooter, CardHeader, Input, Typography } from "@material-tailwind/react";

import { Routes } from "../../routes/CONSTANTS";
import { Municipality } from "../../models/Municipality";
import { MunicipalityService } from "../../services/MunicipalityService";
import { Department } from "../../models/Department";
import { ReactSearchAutocomplete } from 'react-search-autocomplete'
import { DepartmentService } from "../../services/DeparmentService";

type FormErrors = Partial<Record<keyof Municipality, boolean>>;

const MunicipalityFormPage = () => {
    const [form, setForm] = useState<Municipality>({
        name: "",
        department_id: 0,
    });
    const [departments, setDepartments] = useState<Department[]>([]);
    const [formErrors, setFormErrors] = useState<FormErrors>({});
    const [requestError, setRequestError] = useState("");
    const [isSending, setIsSending] = useState(false);
    const navigate = useNavigate();
    const { id } = useParams();

    useEffect(() => {
        if (id) fetchMunicipality()
        fetchDepartments();
    }, [id]);

    const fetchMunicipality = () => {
        if (!id) return
        MunicipalityService.get(parseInt(id)).then((municipality) => {
            setForm({
                name: municipality.name,
                department_id: municipality.department_id,
            });
        }).catch((error) => setRequestError(error.message));
    }

    const fetchDepartments = () => {
        DepartmentService.list().then((departments) => {
            setDepartments(departments);
        }).catch((error) => setRequestError(error.message));
    }

    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const { name, value } = event.target;
        setForm((prevForm) => ({ ...prevForm, [name]: value }));
        if (formErrors[name as keyof Municipality]) {
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
            createMunicipality();
        } else {
            updateMunicipality();
        }
    }

    const validateForm = (form: Municipality): FormErrors => {
        const errors: FormErrors = {};
        if (form.name === "") errors.name = true;
        if (form.department_id === 0) errors.department_id = true;
        return errors;
    }

    const createMunicipality = () => {
        MunicipalityService.create(form)
            .then(() => navigate(Routes.MUNICIPALITY.LIST))
            .catch(error => {
                setIsSending(false);
                setRequestError(error.message)
            });
    }

    const updateMunicipality = () => {
        if (!id) return
        MunicipalityService.update(parseInt(id), form)
            .then(() => navigate(Routes.MUNICIPALITY.LIST))
            .catch(error => {
                setIsSending(false);
                setRequestError(error.message)
            });
    }

    const resetSelectedDepartment = () => {
        setForm((prevForm) => ({ ...prevForm, department_id: 0 }));
    }

    const handleOnSelect = (department: Department) => {
        setForm((prevForm) => ({ ...prevForm, department_id: department.id! }));
        if (formErrors.department_id) {
            setFormErrors({ ...formErrors, department_id: false });
        }
    }

    const handleOnSearch = () => {
        resetSelectedDepartment();
    }

    return (<Card className="lg:w-160 mx-auto my-8">
        <form onSubmit={handleFormSubmit} noValidate>
            <CardHeader floated={false} className="mb-4" shadow={false}>
                <Typography variant="h3" color="blue">
                    {id ? "Editar" : "Crear"} Municipio
                </Typography>
            </CardHeader>
            <CardBody className="flex flex-col gap-4">
                <Input type="text" label="Nombre" size="lg" name="name" id="name" required
                    value={form.name} onChange={(e) => handleInputChange(e)} error={formErrors.name} />
                <ReactSearchAutocomplete
                    items={departments}
                    onSelect={handleOnSelect}
                    onClear={resetSelectedDepartment}
                    onSearch={handleOnSearch}
                    placeholder="Buscar departamento..."
                    showItemsOnFocus
                    maxResults={5}
                    
                    styling={formErrors.department_id?{border: '1px solid #FF0000'}:{}}
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

export default MunicipalityFormPage;