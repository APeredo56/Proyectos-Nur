import { Container } from "react-bootstrap";
import Menu from "../../components/Menu";
import { useState } from "react";
import LoginForm from "../../components/auth/LoginForm";
import RegisterForm from "../../components/auth/RegisterForm";

const Auth = () => {
    const [isLogin, setIsLogin] = useState(true);
    return ( <>
        <Menu />
        <Container className="w-50 p-4">
            <div className="authMethodContainer d-flex">
                <h1 className={(isLogin?'primary-color primary-border border-bottom-0': 'primary-border-bottom') + ' w-100 text-center p-2 m-0 rounded-top-4'} onClick={() => setIsLogin(true)}>Iniciar Sesión</h1>
                <h1 className={(!isLogin?'primary-color primary-border border-bottom-0': 'primary-border-bottom') + ' w-100 text-center p-2 m-0 rounded-top-4'} onClick={() => setIsLogin(false)}>Registrarse</h1>
            </div>
            <div>
                {isLogin && <LoginForm />}
                {!isLogin && <RegisterForm />}
            </div>
        </Container>
    </> );
}

export default Auth;