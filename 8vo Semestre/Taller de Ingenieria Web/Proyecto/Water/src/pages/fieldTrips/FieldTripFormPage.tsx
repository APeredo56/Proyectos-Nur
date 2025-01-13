import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { Button, Card, CardBody, CardFooter, CardHeader, Input, Typography } from "@material-tailwind/react";

import { Routes } from "../../routes/CONSTANTS";
import { FieldTrip } from "../../models/FieldTrip";
import { FieldTripService } from "../../services/FieldTripService";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";


type FormErrors = Partial<Record<keyof FieldTrip, boolean>>;

const FieldTripFormPage = () => {
    const [form, setForm] = useState<FieldTrip>({
        description: "",
        end_date: "",
        start_date: "",
    });
    const [formErrors, setFormErrors] = useState<FormErrors>({});
    const [requestError, setRequestError] = useState("");
    const [isSending, setIsSending] = useState(false);
    const navigate = useNavigate();
    const { id } = useParams();

    useEffect(() => {
        if (id) fetchFieldTrip()
    }, [id]);

    const fetchFieldTrip = () => {
        if (!id) return
        FieldTripService.get(parseInt(id)).then((fieldTrip) => {
            setForm({
                description: fieldTrip.description,
                start_date: fieldTrip.start_date,
                end_date: fieldTrip.end_date,
            });
        }).catch((error) => setRequestError(error.message));
    }

    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const { name, value } = event.target;
        setForm((prevForm) => ({ ...prevForm, [name]: value }));
        if (formErrors[name as keyof FieldTrip]) {
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
            createFieldTrip();
        } else {
            updateMunicipality();
        }
    }

    const validateForm = (form: FieldTrip): FormErrors => {
        const errors: FormErrors = {};
        if (form.description === "") errors.description = true;
        if (form.start_date === "") errors.start_date = true;
        if (form.end_date === "") errors.end_date = true;
        if (form.start_date > form.end_date) {
            errors.start_date = true;
            errors.end_date = true;
        }
        return errors;
    }

    const createFieldTrip = () => {
        FieldTripService.create(form)
            .then(() => navigate(Routes.FIELDTRIP.LIST))
            .catch(error => {
                setIsSending(false);
                setRequestError(error.message)
            });
    }

    const updateMunicipality = () => {
        if (!id) return
        FieldTripService.update(parseInt(id), form)
            .then(() => navigate(Routes.FIELDTRIP.LIST))
            .catch(error => {
                setIsSending(false);
                setRequestError(error.message)
            });
    }

    const handleStartDateChange = (date: Date | null) => {
        if (!date) return;
        setForm({
            ...form,
            start_date: date.toISOString()
        });
    }

    const handleEndDateChange = (date: Date | null) => {
        if (!date) return;
        setForm({
            ...form,
            end_date: date.toISOString()
        });
        if (formErrors.end_date) {
            setFormErrors({ ...formErrors, end_date: false });
        }
    }

    const getDateObject = (date: string) => {
        if (!date) return null;
        return new Date(date);
    }

    return (<Card className="lg:w-160 mx-auto my-8">
        <form onSubmit={handleFormSubmit} noValidate>
            <CardHeader floated={false} className="mb-4" shadow={false}>
                <Typography variant="h3" color="blue">
                    {id ? "Editar" : "Crear"} Salida de Campo
                </Typography>
            </CardHeader>
            <CardBody className="flex flex-col gap-4">
                <Input type="text" label="Descripcion" size="lg" name="description" id="description" required
                    value={form.description} onChange={(e) => handleInputChange(e)} error={formErrors.description} />

                <DatePicker selected={getDateObject(form.start_date)} dateFormat={"dd/MM/yyyy"}
                    onChange={(date) => handleStartDateChange(date)} showYearDropdown={true}
                    className={`w-full p-2 border border-blue-gray-200 rounded-md ${formErrors.start_date ? "border-red-500" : ""}`}
                    placeholderText="Ingresa una fecha de Inicio" />

                <DatePicker selected={getDateObject(form.end_date)} dateFormat={"dd/MM/yyyy"}
                    onChange={(date) => handleEndDateChange(date)} showYearDropdown={true}
                    className={`w-full p-2 border border-blue-gray-200 rounded-md ${formErrors.end_date ? "border-red-500" : ""}`}
                    placeholderText="Ingresa una fecha de fin" />
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

export default FieldTripFormPage;