import { Button, ListGroup } from "react-bootstrap";
import Menu from "../../components/Menu";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./userAccommodations.css";
import InfoModal from "../../components/InfoModal";

const UserAccommodations = () => {
    const baseImageUrl = "http://localhost:8000/";
    const navigate = useNavigate();
    const [accommodations, setAccommodations] = useState([]);
    const [showInfoModal, setShowInfoModal] = useState(false);
    const [deleteError, setDeleteError] = useState('');

    useEffect(() => {
        const token = localStorage.getItem('token');
        fetchUserAccommodations(token);
    }, []);

    const fetchUserAccommodations = (token) => {
        axios.get('http://localhost:8000/api/user/accommodations', {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }).then((response) => {
            setAccommodations(response.data);
        }).catch((error) => {
            console.log(error);
        });
    }

    const deleteUserAccomodation = (id) =>{
        const token = localStorage.getItem('token');
        axios.delete('http://localhost:8000/api/accommodations/' + id, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }).then((response) => {
            fetchUserAccommodations(token);
        }).catch((error) => {
            setDeleteError(error.response.data.message);
            setShowInfoModal(true);
        });
    }

    return (<>
        <Menu />
        <div className="p-4">
            <div className="d-flex align-items-center mb-3">
                <h1 className="primary-color m-0">Mis Alojamientos</h1>
                <Button className="btn primary-border primary-color mx-3" variant="clear"
                    onClick={() => navigate('/accommodations/form')}>Nuevo Alojamiento</Button>
            </div>
            <ListGroup className="w-50">
                {accommodations.map((accommodation) => {
                    return (<ListGroup.Item key={accommodation.id}>
                        <div className="d-flex justify-content-between">
                            <div className="h-100 w-50">
                                <img src={baseImageUrl + accommodation.images[0]?.url}
                                    alt="accommodation image" className="accommodationImage" />
                            </div>
                            <div>
                                <h5 className="primary-color">Información del Alojamiento</h5>
                                <p><span className="fw-bold">Titulo:</span> {accommodation.name}</p>
                                <p><span className="fw-bold">Tipo de Propiedad:</span> {accommodation.type}</p>
                                <p><span className="fw-bold">Habitaciones:</span> {accommodation.bedrooms}</p>
                                <p><span className="fw-bold">Dirección:</span> {accommodation.address}</p>
                                <p><span className="fw-bold">Ciudad:</span> {accommodation.city.name}</p>
                                <Button className="btn primary-border primary-color d-block mb-2" variant="clear"
                                    onClick={() => navigate('/accommodations/form/' + accommodation.id)}>
                                    Editar Alojamiento</Button>
                                <Button className="btn primary-border primary-color d-block mb-2" variant="clear"
                                    onClick={() => navigate('/accommodations/' + accommodation.id + '/bookings')}>
                                    Ver Reservas</Button>
                                <Button className="btn primary-border primary-color" variant="clear"
                                    onClick={() => deleteUserAccomodation(accommodation.id)}>
                                    Eliminar Alojamiento</Button>
                            </div>
                        </div>
                    </ListGroup.Item>);
                })}
            </ListGroup>
        </div>
        <InfoModal show={showInfoModal} title="Error al eliminar el alojamiento" message={deleteError}
            onClose={() => setShowInfoModal(false)} />
    </>);
}

export default UserAccommodations;