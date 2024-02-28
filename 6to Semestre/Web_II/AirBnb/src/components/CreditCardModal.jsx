import { Button, Form, FormGroup, Modal } from "react-bootstrap";
import Proptypes from 'prop-types';
import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import moment from "moment";

const CreditCardModal = ({ isOpen, setIsOpen, bookingData }) => {
    const baseAPIUrl = import.meta.env.VITE_API_URL;
    const navigate = useNavigate();
    const [validated, setValidated] = useState(false);
    const [name, setName] = useState('');
    const [number, setNumber] = useState('');
    const [expiration, setExpiration] = useState('');
    const [cvv, setCvv] = useState('');
    const [error, setError] = useState(null);

    const handleClose = (e) => {
        const form = e.currentTarget;
        e.preventDefault();
        e.stopPropagation();

        const isValid = form.checkValidity();
        setValidated(true);
        if (!isValid) {
            return;
        }
        bookingData.credit_card_name = name;
        bookingData.credit_card_number = number;
        bookingData.credit_card_expiry = moment(expiration).format('YYYY-MM-DD');
        bookingData.credit_card_cvv = cvv;
        saveBooking();
    }

    const saveBooking = () => {
        axios.post(baseAPIUrl + 'bookings/', bookingData, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`,
            },
        }).then(() => {
            navigate('/bookings');
            setIsOpen(false);
        }).catch((error) => {
            console.log(error.response);
            if(error.response.data.message === 'Accommodation already booked for this period'){
                setError('El alojamiento ya se encuentra reservado para este período');
            }else{
                setError('Ocurrió un error al procesar la reserva');
            }
        });
    }

    return (<Modal show={isOpen} onHide={() => setIsOpen(false)}>
        <Form noValidate validated={validated} onSubmit={handleClose}>
            <Modal.Header closeButton>
                <Modal.Title className="primary-color">Ingresa los datos de tu tarjeta</Modal.Title>
            </Modal.Header>
            <Modal.Body className="primary-border border-start-0 border-end-0">
                {error && <p className="text-danger">{error}</p>}
                <FormGroup className="mb-3">
                    <Form.Label>Nombre</Form.Label>
                    <Form.Control type="text" placeholder="Nombre" value={name}
                        onChange={(e) => setName(e.target.value)} required />
                </FormGroup>
                <FormGroup className="mb-3">
                    <Form.Label>Número</Form.Label>
                    <Form.Control type="text" placeholder="Número" value={number}
                        onChange={(e) => setNumber(e.target.value)} required />
                </FormGroup>
                <FormGroup className="mb-3">
                    <Form.Label>Fecha de Expiración</Form.Label>
                    <Form.Control type="date" placeholder="MM/AA" value={expiration}
                        onChange={(e) => setExpiration(e.target.value)} required />
                </FormGroup>
                <FormGroup>
                    <Form.Label>CVV</Form.Label>
                    <Form.Control type="number" placeholder="CVV" value={cvv}
                        onChange={(e) => setCvv(e.target.value)} required />
                </FormGroup>
            </Modal.Body>
            <Modal.Footer>
                <Button type="submit" className="btn primary-bg primary-border">Confirmar Reserva</Button>
            </Modal.Footer>
        </Form>
    </Modal>);
}

CreditCardModal.propTypes = {
    isOpen: Proptypes.bool.isRequired,
    setIsOpen: Proptypes.func.isRequired,
    bookingData: Proptypes.object.isRequired,
}

export default CreditCardModal;