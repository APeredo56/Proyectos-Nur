import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { Button, Card, CardBody, CardFooter, CardHeader, Input, Typography } from "@material-tailwind/react";
import { Department } from "../../models/Department";
import { DepartmentService } from "../../services/DeparmentService";
import { Routes } from "../../routes/CONSTANTS";

type FormErrors = Partial<Record<keyof Department, boolean>>;

const DepartmentFormPage = () => {
    const [form, setForm] = useState<Department>({
        name: "",
    });
    const [formErrors, setFormErrors] = useState<FormErrors>({});
    const [requestError, setRequestError] = useState("");
    const [isSending, setIsSending] = useState(false);
    const navigate = useNavigate();
    const { id } = useParams();

    useEffect(() => {
        if (id) fetchDepartment()
    }, [id]);

    const fetchDepartment = () => {
        if (!id) return
        DepartmentService.get(parseInt(id)).then((department) => {
            setForm({
                name: department.name,
            });
        }).catch((error) => setRequestError(error.message));
    }

    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const { name, value } = event.target;
        setForm((prevForm) => ({ ...prevForm, [name]: value }));
        if (formErrors[name as keyof Department]) {
            setFormErrors({ ...formErrors, [name]: false });
        }
    }

    const handleFormSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        event.stopPropagation();
        validateForm();
        if (Object.values(formErrors).some(err => err)) return;
        setIsSending(true);
        if (!id) {
            createDepartment();
        } else {
            updateDepartment();
        }
    }

    const validateForm = () => {
        const errors: FormErrors = {};
        if (form.name === "") errors.name = true;
        setFormErrors(errors);
    }

    const createDepartment = () => {
        DepartmentService.create(form)
            .then(() => navigate(Routes.DEPARTMENT.LIST))
            .catch(error => {
                setIsSending(false);
                setRequestError(error.message)
            });
    }

    const updateDepartment = () => {
        if (!id) return
        DepartmentService.update(parseInt(id), form)
            .then(() => navigate(Routes.DEPARTMENT.LIST))
            .catch(error => {
                setIsSending(false);
                setRequestError(error.message)
            });
    }

    return (<Card className="lg:w-160 mx-auto my-8">
        <form onSubmit={handleFormSubmit} noValidate>
            <CardHeader floated={false} className="mb-4" shadow={false}>
                <Typography variant="h3" color="blue">
                    {id ? "Editar" : "Crear"} Departamento
                </Typography>
            </CardHeader>
            <CardBody className="flex flex-col gap-4">
                <Input type="text" label="Nombre" size="lg" name="name" id="name" required
                    value={form.name} onChange={(e) => handleInputChange(e)} error={formErrors.name} />

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

export default DepartmentFormPage;