import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { Button, Card, CardBody, CardFooter, CardHeader, Input, Switch, Typography } from "@material-tailwind/react";

import { Routes } from "../../routes/CONSTANTS";
import { User } from "../../models/User";
import { UserService } from "../../services/UserService";
import { RoleEnum } from "../../models/enums/RoleEnum";

type FormErrors = Partial<Record<keyof User, boolean>>;

const UserFormPage = () => {
    const [form, setForm] = useState<User>({
        name: "",
        email: "",
        password: "",
        role: "",
    });
    const [formErrors, setFormErrors] = useState<FormErrors>({});
    const [requestError, setRequestError] = useState("");
    const [isSending, setIsSending] = useState(false);
    const navigate = useNavigate();
    const { id } = useParams();

    useEffect(() => {
        if (id) fetchUser()
    }, [id]);

    const fetchUser = () => {
        if (!id) return
        UserService.get(parseInt(id)).then((user) => {
            setForm({
                name: user.name,
                email: user.email,
                role: user.role,
                status: user.status,
            });
        }).catch((error) => setRequestError(error.message));
    }

    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const { name, value } = event.target;
        setForm((prevForm) => ({ ...prevForm, [name]: value }));
        if (formErrors[name as keyof User]) {
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
            createUser();
        } else {
            updateUser();
        }
    }

    const validateForm = (form: User): FormErrors => {
        const errors: FormErrors = {};
        if (form.name === "") errors.name = true;
        if (form.email === "") errors.email = true;
        if (form.password === "" && !id) errors.password = true;
        if (form.role === "") errors.role = true;
        return errors;
    }

    const createUser = () => {
        UserService.create(form)
            .then(() => navigate(Routes.USER.LIST))
            .catch(error => {
                setIsSending(false);
                setRequestError(error.message)
            });
    }

    const updateUser = () => {
        if (!id) return
        UserService.update(parseInt(id), form)
            .then(() => navigate(Routes.USER.LIST))
            .catch(error => {
                setIsSending(false);
                setRequestError(error.message)
            });
    }

    const handleRoleChange = (role: string) => {
        setForm((prevForm) => ({ ...prevForm, role }));
    }

    return (<Card className="lg:w-160 mx-auto my-8">
        <form onSubmit={handleFormSubmit} noValidate>
            <CardHeader floated={false} className="mb-4" shadow={false}>
                <Typography variant="h3" color="blue">
                    {id ? "Editar" : "Crear"} Usuario
                </Typography>
            </CardHeader>
            <CardBody className="flex flex-col gap-4">
                <Input type="text" label="Nombre" size="lg" name="name" id="name" required
                    value={form.name} onChange={(e) => handleInputChange(e)} error={formErrors.name} />

                <Input type="email" label="Correo" size="lg" name="email" id="email" required
                    value={form.email} onChange={(e) => handleInputChange(e)} error={formErrors.email} />

                {!id && <Input type="password" label="Contraseña" size="lg" name="password" id="password" required
                    value={form.password} onChange={(e) => handleInputChange(e)} error={formErrors.password} />}

                <Typography color="gray">Rol</Typography>
                <div className="flex flex-wrap gap-5">
                    {Object.values(RoleEnum).map((role) => (
                        <Switch key={role} label={role} color="blue" labelProps={{ className: "capitalize" }}
                            onChange={() => handleRoleChange(role)}
                            checked={form.role === role} />
                    ))}
                </div>
                {formErrors.role && <Typography color="red">Seleccione un rol</Typography>}

                <Switch label="Estado" color="blue" labelProps={{ className: "capitalize" }}
                    onChange={(e) => setForm({ ...form, status: e.target.checked })}
                    checked={form.status} />

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

export default UserFormPage;