import { Container, Nav, Navbar } from "react-bootstrap";
import { Routes } from '../routes/CONSTANTS';

const NavbarComponent = () => {

    return (<Navbar bg="dark" data-bs-theme="dark">
        <Container>
            <Navbar.Brand href={Routes.HOME}>MyToDos</Navbar.Brand>
            <Nav className="me-auto">
                <Nav.Link href={Routes.USER.LIST}>Usuarios</Nav.Link>
            </Nav>
        </Container>
    </Navbar>);
}

export default NavbarComponent;