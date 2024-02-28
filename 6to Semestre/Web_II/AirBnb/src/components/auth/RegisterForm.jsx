import { useState } from "react";
import { Button, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { useUser } from "../../utilities/UserContext";
import axios from 'axios';

const RegisterForm = () => {
    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [name, setName] = useState(null);
    const [validated, setValidated] = useState(false);
    const [error, setError] = useState(null);
    const { login } = useUser();

    const onRegisterClick = (e) => {
        const form = e.currentTarget;
        e.preventDefault();
        e.stopPropagation();

        const isValid = form.checkValidity();
        setValidated(true);
        if (!isValid) {
            return;
        }
        doRegister();
    }

    const doRegister = () => {
        axios.post('http://localhost:8000/api/auth/register', {
            name: name,
            email: email,
            password: password
        }).then(() => {
            doLogin();
        }).catch((error) => {
            console.log(error);
            setError('Usuario o contraseña incorrectos');
        });
    }

    const doLogin = () => {
        axios.post('http://localhost:8000/api/auth/login', {
            email: email,
            password: password
        }).then((response) => {
            const token = response.data.access_token;
            if(token){
                localStorage.setItem('token', token);
                fetchUserInfo(token);
                navigate('/');
            }else{
                setError('Usuario o contraseña incorrectos');
            }
        }).catch((error) => {
            console.log(error);
            setError('Usuario o contraseña incorrectos');
        });
        
    }

    const fetchUserInfo = (token) => {
        axios.get('http://localhost:8000/api/auth/me', {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }).then((response) => {
            const name = response.data.name;
            localStorage.setItem('name', name);
            localStorage.setItem('myUserId', response.data.id);
            login(name);
        }).catch((error) => {
            console.log(error);
        });
    }

    return (
        <div>
            <Form className="p-3 primary-border border-top-0 rounded-bottom-4" noValidate validated={validated}>
                {error && <Form.Label className="text-danger error">{error}</Form.Label>}
                <Form.Group className="mb-3" controlId="formBasicName">
                    <Form.Label className="primary-color fw-bold">Nombre</Form.Label>
                    <Form.Control type="text" placeholder="Ingrese su nombre completo"
                        value={name} onChange={(e) => setName(e.target.value)} required />
                    <Form.Control.Feedback type="invalid">
                        Escribe un nombre.
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label className="primary-color fw-bold">Correo Electrónico</Form.Label>
                    <Form.Control type="email" placeholder="Ingrese su correo electrónico"
                        value={email} onChange={(e) => setEmail(e.target.value)} required />
                    <Form.Control.Feedback type="invalid">
                        Escribe un correo electrónico válido.
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label className="primary-color fw-bold">Contraseña</Form.Label>
                    <Form.Control type="password" placeholder="Contraseña" value={password}
                        onChange={(e) => setPassword(e.target.value)} required />
                    <Form.Control.Feedback type="invalid">
                        Escribe una contraseña válida.
                    </Form.Control.Feedback>
                </Form.Group>
                <Button className="btn primary-bg primary-border" type="submit"
                    onClick={(e) => onRegisterClick(e)}>Registrarse</Button>
            </Form>
        </div>
    );
}

export default RegisterForm;