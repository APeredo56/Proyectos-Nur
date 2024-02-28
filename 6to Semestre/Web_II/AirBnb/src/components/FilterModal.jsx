import { useEffect, useState } from "react";
import { Form, FormControl, FormLabel, Modal } from "react-bootstrap";
import PropTypes from 'prop-types';
import { getAccommodationTypes } from "../services/AccommodationService";

const FilterModal = ({ setAdvSearchParams, isFilterOpen,
    setIsFilterOpen }) => {
    const [minPricePerNight, setMinPricePerNight] = useState(0);
    const [maxPricePerNight, setMaxPricePerNight] = useState(0);
    const [rooms, setRooms] = useState(0);
    const [bathrooms, setBathrooms] = useState(0);
    const [beds, setBeds] = useState(0);
    const [hasWifi, setHasWifi] = useState(false);
    const [accommodationType, setAccommodationType] = useState('');
    const [typesList, setTypesList] = useState([]);

    const handleClose = () => {
        setAdvSearchParams({
            minPricePerNight: parseFloat(minPricePerNight),
            maxPricePerNight: parseFloat(maxPricePerNight),
            bedrooms: parseFloat(rooms),
            bathrooms: parseFloat(bathrooms),
            beds: parseFloat(beds),
            hasWifi: Boolean(hasWifi),
            propertyType: accommodationType
        });
        setIsFilterOpen(false)
    }

    useEffect(() => {
        getAccommodationTypes().then((response) => {
            setTypesList(response);
        });
    }, []);

    return (<Modal show={isFilterOpen} centered onHide={handleClose}>
        <Modal.Header closeButton>
            <Modal.Title>Búsqueda Avanzada</Modal.Title>
        </Modal.Header>
        <Modal.Body>
            <FormLabel>Precio Mínimo por noche</FormLabel>
            <FormControl type="number" value={minPricePerNight}
                onChange={(event) => { setMinPricePerNight(event.target.value) }} />
            <FormLabel>Precio Máximo por noche</FormLabel>
            <FormControl type="number" value={maxPricePerNight}
                onChange={(event) => { setMaxPricePerNight(event.target.value) }} />
            <FormLabel>Tipo de Alojamiento</FormLabel>
            <Form.Select value={accommodationType}
                onChange={(event) => { setAccommodationType(event.target.value) }}>
                <option value="">Todos</option>
                {typesList.map((type) => (
                    <option key={'acmType-' + type.property_type} value={type.property_type}>
                        {type.property_type}
                    </option>
                ))}
            </Form.Select>
            <FormLabel>Habitaciones</FormLabel>
            <FormControl type="number" value={rooms}
                onChange={(event) => setRooms(event.target.value)} />
            <FormLabel>Baños</FormLabel>
            <FormControl type="number" value={bathrooms}
                onChange={(event) => setBathrooms(event.target.value)} />
            <FormLabel>Camas</FormLabel>
            <FormControl type="number" value={beds}
                onChange={(event) => setBeds(event.target.value)} />
            <FormLabel>¿Tiene Wifi?</FormLabel>
            <Form.Check type="switch" value={hasWifi}
                onChange={(event) => setHasWifi(event.target.value)} />
        </Modal.Body>
    </Modal>);
}

FilterModal.propTypes = {
    advSearchParams: PropTypes.shape({
        minPricePerNight: PropTypes.number.isRequired,
        maxPricePerNight: PropTypes.number.isRequired,
        bedrooms: PropTypes.number.isRequired,
        bathrooms: PropTypes.number.isRequired,
        beds: PropTypes.number.isRequired,
        hasWifi: PropTypes.bool.isRequired,
        propertyType: PropTypes.string.isRequired
    }).isRequired,
    setAdvSearchParams: PropTypes.func.isRequired,
    isFilterOpen: PropTypes.bool.isRequired,
    setIsFilterOpen: PropTypes.func.isRequired,
};


export default FilterModal;