import { Button, Card, Input, Typography } from "@material-tailwind/react";
import NavbarComponent from "../../Components/NavbarComponent";
import { Routes } from "../../routes/CONSTANTS";
import { AuthService } from "../../services/AuthService";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import useUserInfo from "../../hooks/useUserInfo";

const LoginPage = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();
    const [error, setError] = useState("")
    const { getUserInfo } = useUserInfo();

    const onFormSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        AuthService.login({ username: email, password: password })
            .then(response => {
                localStorage.setItem('access_token', response.access);
                localStorage.setItem('refresh_token', response.refresh);
                getUserInfo();
                navigate(Routes.HOME);
            }).catch(() => {
                setError("Credenciales incorrectas");
            });
    }

    return (<>
        <NavbarComponent />
        <Card color="gray" shadow={false} className="mx-auto p-6 mt-5 w-fit">
            <Typography variant="h4" color="white">
                Iniciar Sesión
            </Typography>
            <form className="mt-4 mb-2 w-80 max-w-screen-lg sm:w-96" onSubmit={(e)=> onFormSubmit(e)}>
                <div className="mb-1 flex flex-col gap-6">
                    <Typography variant="h6" color="red" className="-mb-3">
                        {error}
                    </Typography>
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
                        value={password} onChange={(e) => setPassword(e.target.value)} required/>
                </div>
                <Button className="mt-6 bg-dark-blue" fullWidth type="submit">
                    Iniciar Sesión
                </Button>
                <Typography color="gray" className="mt-4 text-center font-normal text-blue-gray-200">
                    No tienes una cuenta?{" "}
                    <a href={Routes.AUTH.REGISTER} className="font-medium text-blue-600">
                        Registrarse
                    </a>
                </Typography>
            </form>
        </Card>
    </>);
}

export default LoginPage;