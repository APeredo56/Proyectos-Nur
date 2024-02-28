import { useEffect } from "react";
import { Modal } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import PropTypes from 'prop-types';

const LoginAlertModal = ({ isOpen, setIsOpen }) => {
    const navigate = useNavigate();
    const onLoginClick = () => {
        setIsOpen(false);
        navigate('/auth');
    }

    return (<>
        <Modal show={isOpen} onHide={() => setIsOpen(false)}>
            <Modal.Header closeButton>
                <Modal.Title className="primary-color fw-bold">Iniciar Sesión</Modal.Title>
            </Modal.Header>
            <Modal.Body className="primary-border border-end-0 border-start-0">
                Debes iniciar sesión para realizar una reserva
            </Modal.Body>
            <Modal.Footer>
                <button className="btn primary-bg primary-border text-white" 
                    onClick={() => onLoginClick()}>Aceptar</button>
            </Modal.Footer>
        </Modal>
    </>);
}

LoginAlertModal.propTypes = {
    isOpen: PropTypes.bool.isRequired,
    setIsOpen: PropTypes.func.isRequired
}

export default LoginAlertModal;