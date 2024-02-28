import { Button, Modal } from "react-bootstrap";
import PropTypes from 'prop-types';

const DeleteModal = ({ show, title, message, onConfirm, onCancel }) => {
    return ( <Modal show={show} centered onHide={onCancel}>
        <Modal.Header>
            <Modal.Title className="primary-color">{title}</Modal.Title>
        </Modal.Header>
        <Modal.Body className="primary-border border-start-0 border-end-0">{message}</Modal.Body>
        <Modal.Footer>
            <Button className="primary-color primary-border" variant="clear" onClick={onCancel}>
                Cancelar
            </Button>
            <Button className="primary-bg text-white primary-border" onClick={onConfirm}>
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