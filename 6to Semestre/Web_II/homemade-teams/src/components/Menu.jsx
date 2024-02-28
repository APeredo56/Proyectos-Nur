import { useEffect, useState } from "react";
import { Button, Container, Nav, NavDropdown, Navbar } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

const Menu = () => {
    const [ username, setUsername] = useState(sessionStorage.getItem("username"));
    const navigate = useNavigate();

    useEffect(() => {
        // Escuchar el evento de actualización del nombre de usuario
        const handleUsernameUpdated = () => {
            setUsername(sessionStorage.getItem("username"));
        };

        window.addEventListener("usernameUpdated", handleUsernameUpdated);

        return () => {
            // Limpiar el event listener cuando el componente se desmonta
            window.removeEventListener("usernameUpdated", handleUsernameUpdated);
        };
    }, []);

    const logOut = () => {
        sessionStorage.removeItem("username");
        setUsername(null);
        const event = new Event("logout");
        window.dispatchEvent(event);
        navigate("/");
    }
    return (<Navbar data-bs-theme="dark" expand="lg" className="primary-bg">
        <Container className="mt-0 mx-3">
            <Navbar.Brand href="/" className="fw-bold secondary-color">Teams</Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
                <Nav className="me-auto">
                    <NavDropdown title={username} id="basic-nav-dropdown">
                        <Button className="dropdown-item" onClick={logOut}>Cerrar Sesión</Button>
                    </NavDropdown>
                </Nav>
            </Navbar.Collapse>
        </Container>
    </Navbar>);
}

export default Menu;