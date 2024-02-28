import { Button, FormControl, Modal } from "react-bootstrap";
import { useEffect, useState } from "react";
const UsernameModal = () => {
    const [username, setUsername] = useState();
    const [show, setShow] = useState(false);

    useEffect(() => {
        if (sessionStorage.getItem("username") === null) {
            setShow(true);
        } else {
            setShow(false);
        }

        const handleLogOut = () => {
            setShow(true);
        };
        window.addEventListener("logout", handleLogOut);
        return () => {
            window.removeEventListener("logout", handleLogOut);
        };
    }, []);

    const closeModal = () => {
        if (username.trim() === "") {
            return;
        }
        sessionStorage.setItem("username", username);
        const event = new Event("usernameUpdated");
        window.dispatchEvent(event);
        setShow(false);
    }
    
    return (<Modal show={show} centered backdrop="static">
        <Modal.Header className="primary-bg secondary-border">
                <Modal.Title className="text-white">Ingresa tu nombre de usuario</Modal.Title>
            </Modal.Header>
            <Modal.Body className="primary-variant-bg text-white">
                <FormControl type="text" placeholder="Nombre de Usuario" 
                    onChange={(e) => setUsername(e.target.value)} required />
            </Modal.Body>
            <Modal.Footer className="primary-bg secondary-border">
                <Button className="secondary-bg text-white" onClick={closeModal}>Aceptar</Button>
            </Modal.Footer>
    </Modal> );
}

export default UsernameModal;