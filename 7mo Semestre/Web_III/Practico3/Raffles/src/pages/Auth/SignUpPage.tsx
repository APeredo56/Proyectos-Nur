import { Button, Card, Input, Typography } from "@material-tailwind/react";
import NavbarComponent from "../../Components/NavbarComponent";
import { Routes } from "../../routes/CONSTANTS";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { AuthService } from "../../services/AuthService";
import useUserInfo from "../../hooks/useUserInfo";

const SignUpPage = () => {
    const [name, setName] = useState("");
    const [lastName, setLastName] = useState("");
    const [phone, setPhone] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();
    const [error, setError] = useState("");
    const { getUserInfo } = useUserInfo();

    const onFormSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        AuthService.signUp({
            first_name: name, last_name: lastName, username: email, phone, email, password
        }).then(() => {
            AuthService.login({ username: email, password: password })
            .then(response => {
                localStorage.setItem('access_token', response.access);
                localStorage.setItem('refresh_token', response.refresh);
                getUserInfo();
                navigate(Routes.HOME);
            })
        }).catch((response) => {
            response.response.data.detail ? setError(response.response.data.detail) :
                setError("Ocurrió un error al registrarse");
        });
    }

    return (<>
        <NavbarComponent />
        <Card color="gray" shadow={false} className="mx-auto p-6 mt-5 w-fit">
            <Typography variant="h4" color="white">
                Registrarse
            </Typography>
            <form className="mt-4 mb-2 w-80 max-w-screen-lg sm:w-96" onSubmit={(e) => onFormSubmit(e)}>
                <div className="mb-1 flex flex-col gap-6">
                    <Typography variant="h6" color="red" className="-mb-3">
                        {error}
                    </Typography>
                    <Input
                        type="text"
                        size="lg" color="white"
                        label="Nombre"
                        placeholder="Escribe tu nombre"
                        crossOrigin={undefined}
                        value={name} onChange={(e) => setName(e.target.value)} required />
                    <Input
                        type="text"
                        size="lg" color="white"
                        label="Apellidos"
                        placeholder="Escribe tus apellidos"
                        crossOrigin={undefined}
                        value={lastName} onChange={(e) => setLastName(e.target.value)} required />
                    <Input
                        type="number"
                        size="lg" color="white"
                        label="Teléfono"
                        placeholder="Escribe tu teléfono"
                        crossOrigin={undefined}
                        value={phone} onChange={(e) => setPhone(e.target.value)} required />
                    <Input
                        type="email"
                        size="lg" color="white"
                        label="Correo Electrónico"
                        placeholder="Escribe tu correo electrónico"
                        crossOrigin={undefined}
                        value={email} onChange={(e) => setEmail(e.target.value)} required />
                    <Input
                        type="password"
                        size="lg" color="white"
                        label="Contraseña"
                        placeholder="Escribe tu contraseña"
                        crossOrigin={undefined}
                        value={password} onChange={(e) => setPassword(e.target.value)} required />
                </div>
                <Button className="mt-6 bg-dark-blue" fullWidth type="submit">
                    Registrarse
                </Button>
                <Typography color="gray" className="mt-4 text-center font-normal text-blue-gray-200">
                    Ya tienes una cuenta?{" "}
                    <a href={Routes.AUTH.LOGIN} className="font-medium text-blue-600">
                        Iniciar Sesión
                    </a>
                </Typography>
            </form>
        </Card>
    </>);
}

export default SignUpPage;