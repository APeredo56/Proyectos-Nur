import { GoogleMap, Marker, useJsApiLoader } from "@react-google-maps/api";
import { useCallback, useEffect, useState } from "react";
import { Button, FormControl, Modal } from "react-bootstrap";
import axios from "axios";

const CityModal = ({show, setShow, onConfirm}) => {
    const baseAPIUrl = import.meta.env.VITE_API_URL;
    const [cityName, setCityName] = useState('');
    const [latitude, setLatitude] = useState('');
    const [longitude, setLongitude] = useState('');
    const [mapLatitude, setMapLatitude] = useState('');
    const [mapLongitude, setMapLongitude] = useState('');
    const [markers, setMarkers] = useState([]);

    const { isLoaded } = useJsApiLoader({
        id: 'google-map-script',
        googleMapsApiKey: import.meta.env.VITE_MAP_KEY
    });

    const handleMapClick = (event) => {
        const { latLng } = event;
        const lat = latLng.lat();
        const lng = latLng.lng();
        setLatitude(lat);
        setLongitude(lng);
        setMapLatitude(lat);
        setMapLongitude(lng);
        const newMarker = { lat, lng };
        setMarkers([newMarker]);
    };

    const createCity = () => {
        if (!cityName || !latitude || !longitude) {
            return;
        }
        console.log('createCity');
        axios.post(baseAPIUrl + 'cities', {
            name: cityName,
            latitude: latitude,
            longitude: longitude
        }, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`,
            },
        }).then((response) => {
            onConfirm(response.data);
            setShow(false);
        }).catch((error) => {
            console.log(error);
        });

    }

    useEffect(() => {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(
                (position) => {
                    const latitude = position.coords.latitude;
                    const longitude = position.coords.longitude;
                    setMapLatitude(latitude);
                    setMapLongitude(longitude);
                },
                (error) => {
                    console.error(`Error al obtener la ubicación: ${error.message}`);
                }
            );
        } else {
            console.error('La geolocalización no es compatible con este navegador.');
        }
    }, []);

    return (<>
        <Modal show={show} centered onHide={() => setShow(false)}>
            <Modal.Header closeButton>
                <Modal.Title className="primary-color">Agregar una Ciudad</Modal.Title>
            </Modal.Header>
            <Modal.Body className="primary-border border-start-0 border-end-0">
                {mapLatitude && isLoaded && <div className="mapContainer w-100" 
                    style={{height:50+'vh'}}>
                    <GoogleMap
                        center={{
                            lat: parseFloat(mapLatitude),
                            lng: parseFloat(mapLongitude)
                        }}
                        options={{
                            zoomControl: false,
                            scaleControl: false,
                            streetViewControl: false,
                            mapTypeControl: false,
                        }}
                        zoom={12}
                        onClick={handleMapClick}
                    >
                        {markers.map((marker, index) => (
                            <Marker key={index} position={{ lat: marker.lat, lng: marker.lng }} />
                        ))}
                    </GoogleMap>
                </div>}
            </Modal.Body>
            <Modal.Footer>
                <div className="d-flex">
                    <FormControl type="text" placeholder="Nombre de la Ciudad" className="me-2" 
                        value={cityName} onChange={(e) => setCityName(e.target.value)} />
                    <Button className="btn primary-border primary-color" variant="clear" onClick={createCity}>
                        Agregar</Button>
                </div>
            </Modal.Footer>
        </Modal>
    </>);
}

export default CityModal;