import { useEffect, useState } from "react";
import Menu from "../../components/Menu";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { Button, ListGroup } from "react-bootstrap";
import moment from "moment";
import "./userBookings.css";

const UserBookings = () => {
    const baseAPIUrl = import.meta.env.VITE_API_URL;
    const baseImageUrl = "http://localhost:8000/";
    const navigate = useNavigate();
    const [userBookings, setUserBookings] = useState([]);

    useEffect(() => {
        const token = localStorage.getItem('token');
        fetchUserBookings(token);
    }, []);

    const fetchUserBookings = (token) => {
        axios.get(baseAPIUrl + 'user/bookings', {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }).then((response) => {
            setUserBookings(response.data);
        }).catch((error) => {
            console.log(error);
        });
    }

    return (<>
        <Menu />
        <div className="p-4">
            <h1 className="primary-color ">Mis Reservas Realizadas</h1>
            <ListGroup className="w-75">
                {userBookings.map((booking) => {
                    return (<ListGroup.Item key={booking.id}>
                        <div className="d-flex justify-content-between">
                            <div className="h-100 w-25 bg-dark">
                                <img src={baseImageUrl + booking.accommodation.images[0]?.url} 
                                    alt="accommodation image" className="bookingAcmImage"/>
                            </div>
                            <div>
                                <h5 className="primary-color">Información del Alojamiento</h5>
                                <p><span className="fw-bold">Titulo:</span> {booking.accommodation.name}</p>
                                <p><span className="fw-bold">Tipo de Propiedad:</span> {booking.accommodation.type}</p>
                                <p><span className="fw-bold">Habitaciones:</span> {booking.accommodation.bedrooms}</p>
                                <p><span className="fw-bold">Dirección:</span> {booking.accommodation.address}</p>
                                <p><span className="fw-bold">Ciudad:</span> {booking.accommodation.city.name}</p>
                                <Button className="btn primary-border primary-color" variant="clear" 
                                    onClick={() => navigate('/accommodation/' + booking.accommodation.id)}>
                                    Ver Alojamiento</Button>
                            </div>
                            <div>
                                <h5 className="primary-color">Información de la Reserva</h5>
                                <p><span className="fw-bold">Inicio:</span> {booking.check_in}</p>
                                <p><span className="fw-bold">Fin:</span> {booking.check_out}</p>
                                <p><span className="fw-bold">Noches:</span> {moment(booking.check_out).diff(moment(booking.check_in), 'days')}</p>
                                <p><span className="fw-bold">Precio por noche:</span> ${booking.accommodation.price_per_night}</p>
                                <p><span className="fw-bold">Precio total:</span> ${booking.total_price}</p>
                            </div>
                        </div>
                    </ListGroup.Item>);
                })}
            </ListGroup>
        </div>
    </>);
}

export default UserBookings;