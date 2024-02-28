import { useEffect, useState } from "react";
import Menu from "../../components/Menu";
import axios from "axios";
import { useParams } from "react-router-dom";
import moment from "moment/moment";
import { Button } from "react-bootstrap";
import DeleteModal from "../../components/DeleteModal";

const AccommodationBookings = () => {
    const accommodationId = useParams().id;
    const baseAPIUrl = import.meta.env.VITE_API_URL;
    const [bookings, setBookings] = useState([]);
    const [selectedBooking, setSelectedBooking] = useState(null);
    const [showDeleteModal, setShowDeleteModal] = useState(false);

    useEffect(() => {
        const token = localStorage.getItem('token');
        fetchAccommodationBookings(token);
    }, []);

    const fetchAccommodationBookings = (token) => {
        axios.get(baseAPIUrl + 'bookings/accommodation/' + accommodationId, {
            headers: {
                Authorization: 'Bearer ' + token
            }
        }).then((response) => {
            setBookings(response.data);
        }).catch((error) => {
            console.log(error);
        });
    }

    const handleBookingCancel = (bookingId) => {
        setSelectedBooking(bookingId);
        setShowDeleteModal(true);
    }

    const deleteBooking = (bookingId) => {
        const token = localStorage.getItem('token');
        axios.delete(baseAPIUrl + 'bookings/' + bookingId, {
            headers: {
                Authorization: 'Bearer ' + token
            }
        }).then(() => {
            setShowDeleteModal(false);
            fetchAccommodationBookings(token);
        }).catch((error) => {
            console.log(error);
        });
    }

    return (<>
        <Menu />
        <div className="p-4">
            <h1 className="primary-color ">Reservas del Alojamiento</h1>
            <div className="w-50">
                {bookings.length > 0 && bookings.map((booking) => {
                    return (<div key={booking.id} className="border p-2 mb-2">
                        <div className="d-flex justify-content-between">
                            <div>
                                <h5 className="primary-color">Información del Cliente</h5>
                                <p><span className="fw-bold">Nombre:</span> {booking.client.name}</p>
                                <p><span className="fw-bold">Email:</span> {booking.client.email}</p>
                            </div>
                            {moment().isBefore(moment(booking.check_in)) &&<div>
                                <Button className="btn primary-border primary-color" variant="clear"
                                    onClick={() => handleBookingCancel(booking.id)}>
                                    Cancelar Reserva</Button>
                            </div>}
                            <div>
                                <h5 className="primary-color">Información de la Reserva</h5>
                                <p><span className="fw-bold">Inicio:</span> {booking.check_in}</p>
                                <p><span className="fw-bold">Fin:</span> {booking.check_out}</p>
                                <p><span className="fw-bold">Noches:</span> {moment(booking.check_out).diff(moment(booking.check_in), 'days')}</p>
                                <p><span className="fw-bold">Precio por noche:</span> ${booking.accommodation.price_per_night}</p>
                                <p><span className="fw-bold">Precio total:</span> ${booking.total_price}</p>
                            </div>
                        </div>
                    </div>);
                })}
            </div>
        </div>
        <DeleteModal show={showDeleteModal} title="Eliminar Reserva" onCancel={() => setShowDeleteModal(false)}
            message="¿Está seguro que desea eliminar la reserva?" onConfirm={() => deleteBooking(selectedBooking)} />
    </>);
}

export default AccommodationBookings;