import { Button, Modal } from "react-bootstrap";

const InfoModal = ({show, title, message, onClose}) => {
    return (<Modal show={show} centered onHide={onClose}>
        <Modal.Header>
            <Modal.Title className="primary-color">{title}</Modal.Title>
        </Modal.Header>
        <Modal.Body className="primary-border border-start-0 border-end-0">{message}</Modal.Body>
        <Modal.Footer>
            <Button className="primary-color primary-border" variant="clear" onClick={onClose}>
                Aceptar
            </Button>
        </Modal.Footer>
    </Modal>);
}
 
export default InfoModal;