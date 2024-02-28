import { Button, Modal } from "react-bootstrap";
import PropTypes from 'prop-types';

const DeleteModal = ({ show, title, message, onConfirm, onCancel }) => {
    return ( <Modal show={show} centered backdrop="static">
        <Modal.Header className="primary-bg secondary-border">
            <Modal.Title className="text-white">{title}</Modal.Title>
        </Modal.Header>
        <Modal.Body className="primary-variant-bg text-white">{message}</Modal.Body>
        <Modal.Footer className="primary-bg secondary-border">
            <Button className="bg-danger text-white" onClick={onCancel}>
                Cancelar
            </Button>
            <Button className="secondary-bg text-white" onClick={onConfirm}>
                Eliminar
            </Button>
        </Modal.Footer>
    </Modal> );
}

DeleteModal.propTypes = {
    show: PropTypes.bool.isRequired,
    title: PropTypes.string.isRequired,
    message: PropTypes.string.isRequired,
    objectId: PropTypes.number,
    onConfirm: PropTypes.func.isRequired,
    onCancel: PropTypes.func.isRequired
};

export default DeleteModal;