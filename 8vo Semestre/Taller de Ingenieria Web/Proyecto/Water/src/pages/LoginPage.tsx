import { Button, Card, CardBody, CardFooter, CardHeader, Input, Typography } from "@material-tailwind/react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { AuthService } from "../services/AuthService";
import { Routes } from "../routes/CONSTANTS";

const LoginPage = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const [formValidated, setFormValidated] = useState(false);
    const navigate = useNavigate();

    const handleFormSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        event.stopPropagation();
        const form = event.currentTarget;
        if (form.checkValidity() === false) {
            setFormValidated(true);
            return;
        }
        AuthService.login(email, password)
            .then((response) => {
                const { access_token } = response as { access_token: string };
                sessionStorage.setItem('token', access_token);
                navigate(Routes.HOME);
            })
            .catch(error => setError(error.message));
    }

    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        setFormValidated(false);
        if (name === "email") {
            setEmail(value);
        } else if (name === "password") {
            setPassword(value);
        }
    }

    return (<Card className="w-64 sm:w-96 mx-auto my-16">
        <form onSubmit={handleFormSubmit} noValidate>
            <CardHeader color="blue" className="mb-4 grid h-24 place-items-center">
                <Typography variant="h3" color="white">
                    Iniciar Sesión
                </Typography>
            </CardHeader>
            <CardBody className="flex flex-col gap-4">
                {error && <Typography color="red">{error}</Typography>}
                <Input type="text" label="Email" size="lg" name="email" id="email" required
                    value={email} onChange={(e) => handleInputChange(e)} error={formValidated && email === ""}/>
                <Input type="password" label="Contraseña" size="lg" name="password" id="password" required
                    value={password} onChange={(e) => handleInputChange(e)} error={formValidated && password === ""}/>
            </CardBody>
            <CardFooter className="pt-0">
                <Button color="blue" fullWidth type="submit">
                    Iniciar Sesión
                </Button>
            </CardFooter>
        </form>
    </Card>);
}

export default LoginPage;