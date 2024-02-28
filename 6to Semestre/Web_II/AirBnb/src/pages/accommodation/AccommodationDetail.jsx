import { useNavigate, useParams } from "react-router-dom";
import Menu from "../../components/Menu";
import { useEffect, useState } from "react";
import { getAccommodationById } from "../../services/AccommodationService";
import { Container } from "react-bootstrap";
import "./accommodationDetail.css"
import DatePicker from "react-datepicker";
import moment from "moment/moment";
import axios from "axios";
import LoginAlertModal from "../../components/auth/LoginAlertModal";
import CreditCardModal from "../../components/CreditCardModal";

const AccommodationDetail = () => {
    const navigate = useNavigate();
    const baseImgUrl = "http://localhost:8000/";
    const baseAPIUrl = import.meta.env.VITE_API_URL;
    const { id } = useParams();
    const [accommodation, setAccommodation] = useState(null);
    const [checkIn, setCheckIn] = useState(null);
    const [checkOut, setCheckOut] = useState(null);
    const [bookingNights, setBookingNights] = useState(0);
    const [serviceFee, setServiceFee] = useState(0);
    const [totalPrice, setTotalPrice] = useState(0);
    const [showLoginModal, setShowLoginModal] = useState(false);
    const [showCreditCardModal, setShowCreditCardModal] = useState(false);
    const [bookingData, setBookingData] = useState({});

    useEffect(() => {
        getAccommodationById(id).then((response) => {
            setAccommodation(response);
        });
    }, []);

    useEffect(() => {
        if (checkIn && checkOut) {
            const checkInDate = moment(checkIn).format('YYYY-MM-DD');
            const checkOutDate = moment(checkOut).format('YYYY-MM-DD');
            setBookingNights(moment(checkOutDate).diff(moment(checkInDate), 'days'));
        }
    }, [checkIn, checkOut]);

    useEffect(() => {
        if (bookingNights > 0) {
            const stayPrice = parseFloat(accommodation.price_per_night) * bookingNights;
            const serviceFeePrice = (stayPrice + parseFloat(accommodation.cleaning_fee)) * 0.14;
            setServiceFee(serviceFeePrice);
            setTotalPrice(stayPrice + parseFloat(serviceFeePrice.toFixed(2)) + parseFloat(accommodation.cleaning_fee));
        }
    }, [bookingNights]);

    const handleBooking = () => {
        const token = localStorage.getItem('token');
        if (!token) {
            setShowLoginModal(true);
            return;
        }

        const checkInDate = moment(checkIn).format('YYYY-MM-DD');
        const checkOutDate = moment(checkOut).format('YYYY-MM-DD');

        setBookingData({
            accommodation_id: accommodation.id,
            check_in: checkInDate,
            check_out: checkOutDate,
            total_price: totalPrice,
            service_fee: serviceFee,
            owner_id: accommodation.user.id,
            client_id: localStorage.getItem('myUserId'),
        });

        setShowCreditCardModal(true);
    }

    return (<>
        <Menu />
        {accommodation && <Container className="p-4">
            <h1>{accommodation.name}</h1>
            {accommodation.images.length > 0 && <div className="galleryContainer">
                {accommodation.images.map((image, index) => {
                    return <img key={'acmImg-' + index}
                        src={baseImgUrl + image.url} className="galleryImage" />
                })}
            </div>}
            <h3 className="mt-2 mb-0">{accommodation.address}</h3>
            <p>
                {accommodation.max_guests} huéspedes <span className="fw-bold">·</span> {accommodation.bedrooms} habitaciones <span className="fw-bold">·</span> {accommodation.num_beds} camas <span className="fw-bold">·</span> {accommodation.bathrooms} {accommodation.bathrooms > 1 ? 'baños' : 'baño'} <span className="fw-bold">·</span> {accommodation.has_wifi ? 'Wifi' : 'Sin Wifi'}
            </p>
            <p><span className="fw-bold">Tipo de Alojamiento: </span>{accommodation.property_type}</p>
            <h5 className="fw-bold">Descripción del Alojamiento</h5>
            <p>{accommodation.description}</p>
            <h5 className="fw-bold">Nombre del Anfitrión</h5>
            <p>{accommodation.user.name}</p>
            <h2 className="fw-bold primary-color">Realizar una Reserva</h2>
            <h5 className="text-center">{accommodation.price_per_night} Bs por noche</h5>

            <div className="bookingContainer primary-border rounded-4 p-3 w-75 mx-auto">
                <div className="d-flex mb-3">
                    <DatePicker
                        selectsStart
                        selected={checkIn}
                        startDate={checkIn}
                        endDate={checkOut}
                        onChange={(date) => setCheckIn(date)}
                        minDate={new Date()}
                        placeholderText="Fecha de Inicio"
                        showMonthDropdown
                        className="me-2 border p-2 rounded"
                    />
                    <DatePicker
                        selectsEnd
                        selected={checkOut}
                        startDate={checkIn}
                        endDate={checkOut}
                        minDate={moment().add(1, 'days').toDate()}
                        onChange={(date) => setCheckOut(date)}
                        placeholderText="Fecha de Fin"
                        showMonthDropdown
                        className="mx-2 border p-2 rounded"
                    />
                </div>
                {(!checkIn || !checkOut) && <p className="primary-color">
                    Seleccione una fecha de inicio y una fecha de fin para ver los detalles de la reserva</p>}
                {checkIn && checkOut && <div className="d-flex justify-content-between">
                    <div>
                        <p>{accommodation.price_per_night} Bs X {bookingNights} noches</p>
                        <p>Tarifa de limpieza</p>
                        <p>Tarifa por servicio</p>
                        <p className="fw-bold">Total</p>
                    </div>
                    <div>
                        <p>{accommodation.price_per_night * bookingNights} Bs</p>
                        <p>{accommodation.cleaning_fee} Bs</p>
                        <p>{serviceFee.toFixed(2)} Bs</p>
                        <p className="fw-bold">{totalPrice} Bs</p>
                    </div>
                </div>}
                {checkIn && checkOut && <div className="d-flex justify-content-end">
                    <button className="btn primary-bg primary-border text-white" onClick={handleBooking}
                    >Reservar</button>
                </div>}
            </div>
        </Container>}
        <LoginAlertModal isOpen={showLoginModal} setIsOpen={setShowLoginModal} />
        <CreditCardModal isOpen={showCreditCardModal} setIsOpen={setShowCreditCardModal}
            bookingData={bookingData} />

    </>);
}

export default AccommodationDetail;