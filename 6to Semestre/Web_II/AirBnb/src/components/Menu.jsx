import { useEffect, useState } from "react";
import { Container, Nav, NavDropdown, Navbar } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import { useUser } from '../utilities/UserContext.jsx';

const Menu = () => {
    const navigate = useNavigate();
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [name, setName] = useState(null);
    const authorizedPages = ['/auth', '/', '/accommodation/:id'];
    const { username, logout } = useUser();

    const onLogOutClick = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('name');
        setIsLoggedIn(false);
        setName(null);
        logout();
        navigate('/');
    }
    useEffect(() => {
        const token = localStorage.getItem('token');
        setIsLoggedIn(token ? true : false);
        setName(localStorage.getItem('name'));
        if (!token && !authorizedPages.some(page => {
            const regex = new RegExp('^' + page.replace(/:[^/]+/g, '([^/]+)') + '$');
            return regex.test(window.location.pathname);
        })) {
            navigate('/auth');
        }
    }, [])

    useEffect(() => {
        if (username) {
            setName(username);
            setIsLoggedIn(true);
        }
    }, [username])

    return (<Navbar expand="lg" className="bg-white border">
        <Container>
            <Navbar.Brand href="/" className="fw-bold primary-color">
                <img
                    alt=""
                    src="/img/logo.png"
                    width="30"
                    height="30"
                    className="d-inline-block align-top"
                />{' '}airbnb
            </Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
                <Nav className="w-100">
                    {!isLoggedIn && <Link className="nav-link primary-color fw-bold ms-auto" to="/auth">Iniciar Sesión</Link>}
                    {isLoggedIn && <><NavDropdown title={name} id="basic-nav-dropdown" className="ms-auto">
                        <Link to="/user/accommodations" className="dropdown-item">Mis Alojamientos</Link>
                        <Link to="/bookings" className="dropdown-item">Mis Reservas</Link>
                        <button className="dropdown-item text-start" onClick={onLogOutClick}>Cerrar sesión</button>
                    </NavDropdown></>}
                </Nav>
            </Navbar.Collapse>
        </Container>
    </Navbar>);
}

export default Menu;