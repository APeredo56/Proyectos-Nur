import { useCallback, useEffect, useRef, useState } from "react";
import Menu from "../../components/Menu";
import 'react-bootstrap-typeahead/css/Typeahead.css';
import SearchBar from "../../components/SearchBar";
import PropTypes from 'prop-types';
import { GoogleMap, InfoWindow, Marker, useJsApiLoader } from '@react-google-maps/api';

import './home.css';
import { Card } from "react-bootstrap";
import AccommodationSlider from "../../components/AccommodationSlider";
import { useNavigate } from "react-router-dom";

const Home = () => {
    const navigate = useNavigate();
    const [accommodationList, setAccommodationList] = useState([]);
    const [selectedCity, setSelectedCity] = useState(null);
    const [mapLatitude, setMapLatitude] = useState(null);
    const [mapLongitude, setMapLongitude] = useState(null);
    const [map, setMap] = useState(null);
    const [markers, setMarkers] = useState([]);
    const [activeMarker, setActiveMarker] = useState(null);
    const [showInfoWindow, setShowInfoWindow] = useState(false);
    const [infoMsg, setInfoMsg] = useState('Busca alojamientos segun tus necesidades');

    const baseImgUrl = "http://localhost:8000/";

    const { isLoaded } = useJsApiLoader({
        id: 'google-map-script',
        googleMapsApiKey: import.meta.env.VITE_MAP_KEY
    });

    const onUnmount = useCallback(function callback() {
        setMap(null)
    }, []);

    const onMarkerClick = (markerId) => {
        const marker = markers.find((marker) => marker.id === markerId);
        setActiveMarker(marker);
        setShowInfoWindow(true);
    }

    const onInfoWindowClose = () => {
        setActiveMarker(null);
        setShowInfoWindow(false);
    }

    useEffect(() => {
        if (selectedCity) {
            setMapLatitude(parseFloat(selectedCity?.latitude));
            setMapLongitude(parseFloat(selectedCity?.longitude));
        }
    }, [selectedCity]);

    useEffect(() => {
        if (selectedCity) {
            if (accommodationList.length === 0) {
                setInfoMsg('No se encontraron alojamientos en esta ciudad');
                return;
            }
            setMarkers(
                accommodationList.map((accommodation) => {
                    return {
                        id: accommodation.id,
                        name: accommodation.name,
                        description: accommodation.description,
                        price: accommodation.price_per_night,
                        position: {
                            lat: parseFloat(accommodation.latitude),
                            lng: parseFloat(accommodation.longitude)
                        },
                        imageUrl: accommodation.images[0]?.url
                    }
                })
            );
        }
    }, [accommodationList, selectedCity]);

    return (<>
        <Menu />
        <SearchBar setAccommodationList={setAccommodationList} setSelectedCity={setSelectedCity} />
        <div className="d-flex flex-grow-1 w-100">
            <div className="d-flex flex-grow-1 flex-wrap align-items-center">
                <div className="accommodationsWrapper w-100 d-flex flex-wrap p-3">
                    {accommodationList.length === 0 &&
                        <h2 className="primary-color p-3">{infoMsg}</h2>}
                    {accommodationList.map((accommodation) => (
                        <div key={'acmDetail-' + accommodation.id} className="p-3">
                            <Card className="accommodationCard" 
                                onClick={()=>navigate('/accommodation/'+accommodation.id)}>
                                <AccommodationSlider images={accommodation.images ?? []} baseURL={baseImgUrl} />
                                <Card.Body>
                                    <Card.Title>{accommodation.name}</Card.Title>
                                    <p className="accommodationDescription mb-1">{accommodation.description}</p>
                                    <Card.Text className="mb-1">{accommodation.address}</Card.Text>
                                    <Card.Text className="mb-1">{accommodation.num_beds} Camas</Card.Text>
                                    <Card.Text>
                                        {accommodation.price_per_night} Bs
                                    </Card.Text>
                                </Card.Body>
                            </Card>
                        </div>
                    ))}
                </div>
            </div>
            {mapLatitude && isLoaded && <div className="mapContainer" >
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
                    onUnmount={onUnmount}
                >
                    {markers.map((marker) => (
                        <Marker
                            key={marker.id}
                            position={marker.position}
                            onClick={() => onMarkerClick(marker.id)}
                        />
                    ))}
                    {showInfoWindow && activeMarker && <InfoWindow onCloseClick={onInfoWindowClose}
                        position={activeMarker.position}>
                        <div onClick={()=>navigate('/accommodation/'+activeMarker.id)}>
                            <h2 className="m-0">{activeMarker.name}</h2>
                            <div>
                                <img className='imgInfoWindow' src={baseImgUrl + activeMarker.imageUrl} />
                            </div>
                            <p className="m-0">{activeMarker.description}</p>
                            <div>
                                <h4 className="m-0 p-0">{activeMarker.price} Bs</h4>
                            </div>
                        </div>
                    </InfoWindow>}
                </GoogleMap>
            </div>}
        </div>
    </>);
}

export default Home;
