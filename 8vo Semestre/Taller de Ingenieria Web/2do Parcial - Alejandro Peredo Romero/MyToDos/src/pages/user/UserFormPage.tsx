import { useNavigate, useParams } from "react-router-dom";
import NavbarComponent from "../../components/NavbarComponent";
import { FormEvent, useEffect, useState } from "react";
import { User } from "../../models/User";
import { UserService } from "../../services/UserService";
import { Routes } from "../../routes/CONSTANTS";
import { Button, Container, Form } from "react-bootstrap";
import DatePicker from "react-datepicker";

const UserFormPage = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const [user, setUser] = useState<User>(
        {
            id: 0,
            name: "",
            last_name: "",
            email: "",
            password: "",
            phone: "",
            date_of_birth: ""
        }
    );
    const [error, setError] = useState("");
    const [birthDateError, setBirthDateError] = useState("");
    const [validated, setValidated] = useState(false);

    useEffect(() => {
        if (!id) return;
        fetchUser();
    }, [id]);

    const fetchUser = () => {
        UserService.get(Number(id)).then(response => {
            setUser(response);
        });
    }

    const onSave = (e: FormEvent<HTMLFormElement>) => {
        const form = e.currentTarget;
        e.preventDefault();
        e.stopPropagation();
        setBirthDateError("");
        
        const isValid = form.checkValidity();
        const isDateValid = validateDate();
        
        setValidated(true);
        if (!isValid || !isDateValid) return

        saveUser();
    }

    const validateDate = () => {
        let valid = true;
        if (!user.date_of_birth) {
            setBirthDateError("La fecha de nacimiento no debe estar vacia.");
            valid = false;
        }
        return valid;
    }

    const saveUser = () => {
        if (id) {
            UserService.update(user).then(() => {
                navigate(Routes.USER.LIST);
            }).catch(() => setError("Ocurrio un error al actualizar el usuario."));
        } else {
            UserService.create(user).then(() => {
                navigate(Routes.USER.LIST);
            }).catch(() => setError("Ocurrio un error al crear el usuario."));
        }
    }

    const changeValue = (e: React.ChangeEvent<HTMLInputElement>) => {
        setUser({
            ...user,
            [e.target.name]: e.target.value
        });
    }


    const handleBirthDateChange = (date: Date | null) => {
        if (!date) return;
        setUser({
            ...user,
            date_of_birth: date.toISOString()
        });
    }

    const getDateObject = (date: string) => {
        if (!date) return null;
        return new Date(date);
    }

    return (<>
        <NavbarComponent />
        <Container>
            <h1 className="mt-3">{id ? 'Editar' : 'Agregar'} Usuario</h1>
            <Form className="p-3 primary-border border-top-0 rounded-bottom-4" noValidate validated={validated}
                onSubmit={onSave}>
                {error && <Form.Label className="text-danger error">{error}</Form.Label>}
                <Form.Group className="mb-3" controlId="name">
                    <Form.Label className="primary-color fw-bold">Nombre</Form.Label>
                    <Form.Control type="text" placeholder="Ingrese el nombre" name="name"
                        value={user.name} onChange={changeValue} required />
                    <Form.Control.Feedback type="invalid">
                        El nombre no debe estar vacio.
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3" controlId="last_name">
                    <Form.Label className="primary-color fw-bold">Apellido</Form.Label>
                    <Form.Control type="text" placeholder="Ingrese el apellido" name="last_name"
                        value={user.last_name} onChange={changeValue} required />
                    <Form.Control.Feedback type="invalid">
                        El apellido no debe estar vacio.
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3" controlId="email">
                    <Form.Label className="primary-color fw-bold">Email</Form.Label>
                    <Form.Control type="email" placeholder="Ingrese el email" value={user.email}
                        name="email" onChange={changeValue} required/>
                    <Form.Control.Feedback type="invalid">
                        El email no debe estar vacio.
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3" controlId="email">
                    <Form.Label className="primary-color fw-bold">Contraseña</Form.Label>
                    <Form.Control type="password" placeholder="Ingrese la contraseña" value={user.password}
                        name="password" onChange={changeValue} required/>
                    <Form.Control.Feedback type="invalid">
                        La contraseña no debe estar vacia.
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3" controlId="phone">
                    <Form.Label className="primary-color fw-bold">Telefono</Form.Label>
                    <Form.Control type="text" placeholder="Ingrese el telefono" value={user.phone}
                        name="phone" onChange={changeValue} required/>
                    <Form.Control.Feedback type="invalid">
                        El telefono no debe estar vacio.
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3" controlId="end_date">
                    <Form.Label className="primary-color fw-bold d-block">Fecha de Nacimiento</Form.Label>
                    <DatePicker selected={getDateObject(user.date_of_birth)} dateFormat={"dd/MM/yyyy"}
                        onChange={(date) => handleBirthDateChange(date)} showYearDropdown={true} />
                    {birthDateError && <Form.Label className="text-danger error">{birthDateError}</Form.Label>}
                </Form.Group>

                <Button className="btn primary-bg primary-border" type="submit">Guardar</Button>
            </Form>
        </Container>
    </>);
}

export default UserFormPage;