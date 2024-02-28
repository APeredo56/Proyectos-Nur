import { useNavigate, useParams } from "react-router-dom";
import Menu from "../../components/Menu";
import { useEffect, useState } from "react";
import axios from "axios";
import { Button, Form } from "react-bootstrap";
import CityModal from "../../components/CityModal";
import { GoogleMap, Marker, useJsApiLoader } from "@react-google-maps/api";
import DeleteModal from "../../components/DeleteModal";

const AccommodationForm = () => {
    const baseAPIUrl = import.meta.env.VITE_API_URL;
    const baseImageUrl = "http://localhost:8000/";
    const navigate = useNavigate();
    const id = useParams().id;
    const [cities, setCities] = useState([]);
    const [accommodationTypes, setAccommodationTypes] = useState([]);
    const [images, setImages] = useState([]);
    const [name, setName] = useState('');
    const [bedrooms, setBedrooms] = useState('');
    const [bathrooms, setBathrooms] = useState('');
    const [maxGuests, setMaxGuests] = useState('');
    const [pricePerNight, setPricePerNight] = useState('');
    const [address, setAddress] = useState('');
    const [description, setDescription] = useState('');
    const [cleaningFee, setCleaningFee] = useState('');
    const [hasWifi, setHasWifi] = useState(false);
    const [type, setType] = useState('');
    const [beds, setBeds] = useState('');
    const [latitude, setLatitude] = useState('');
    const [longitude, setLongitude] = useState('');
    const userId = localStorage.getItem('myUserId');
    const [cityId, setCityId] = useState('');
    const [validated, setValidated] = useState(false);
    const [showCityModal, setShowCityModal] = useState(false);
    const [mapLatitude, setMapLatitude] = useState('');
    const [mapLongitude, setMapLongitude] = useState('');
    const [markers, setMarkers] = useState([]);
    const [showDeleteModal, setShowDeleteModal] = useState(false);
    const [imageToDelete, setImageToDelete] = useState(null);

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

    useEffect(() => {
        const token = localStorage.getItem('token');
        if (id) {
            fetchAccommodation(token);
        }
        fetchCities(token);
        fetchAccommodationTypes(token);
        if (navigator.geolocation) {
            if(!id){
                return;
            }
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

    const fetchAccommodation = (token) => {
        axios.get(baseAPIUrl + 'accommodations/' + id, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }).then((response) => {
            setName(response.data.name);
            setBedrooms(response.data.bedrooms);
            setBathrooms(response.data.bathrooms);
            setMaxGuests(response.data.max_guests);
            setPricePerNight(response.data.price_per_night);
            setAddress(response.data.address);
            setDescription(response.data.description);
            setCleaningFee(response.data.cleaning_fee);
            setHasWifi(response.data.has_wifi);
            setType(response.data.property_type);
            setBeds(response.data.num_beds);
            setLatitude(response.data.latitude);
            setLongitude(response.data.longitude);
            setMapLatitude(response.data.latitude);
            setMapLongitude(response.data.longitude);
            setMarkers([{ lat: parseFloat(response.data.latitude), 
                lng: parseFloat(response.data.longitude) }]);
            setCityId(response.data.city_id);
            setImages(response.data.images);
        }).catch((error) => {
            console.log(error);
        });
    }

    const fetchCities = (token) => {
        axios.get(baseAPIUrl + 'cities', {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }).then((response) => {
            setCities(response.data);
        }).catch((error) => {
            console.log(error);
        });
    }

    const fetchAccommodationTypes = (token) => {
        axios.get(baseAPIUrl + 'accommodations/types', {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }).then((response) => {
            setAccommodationTypes(response.data);
        }).catch((error) => {
            console.log(error);
        });
    }

    const onFormSubmit = (e) => {
        const form = e.currentTarget;
        e.preventDefault();
        e.stopPropagation();
        const isValid = form.checkValidity();
        setValidated(true);
        if (!isValid) {
            return;
        }
        if (id) {
            updateAccommodation();
        } else {
            createAccommodation();
        }
    }

    const createAccommodation = () => {
        const token = localStorage.getItem('token');
        axios.post(baseAPIUrl + 'accommodations', {
            name: name,
            bedrooms: bedrooms,
            bathrooms: bathrooms,
            max_guests: maxGuests,
            price_per_night: pricePerNight,
            address: address,
            description: description,
            cleaning_fee: cleaningFee,
            has_wifi: hasWifi,
            property_type: type,
            num_beds: beds,
            latitude: latitude,
            longitude: longitude,
            city_id: cityId,
            user_id: userId
        }, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }).then((response) => {
            const accommodationId = response.data.id;
            if (accommodationId) {
                uploadImages(accommodationId);
            }
        }).catch((error) => {
            console.log(error);
        });
    }

    const updateAccommodation = () => {
        const token = localStorage.getItem('token');
        axios.put(baseAPIUrl + 'accommodations/' + id, {
            name: name,
            bedrooms: bedrooms,
            bathrooms: bathrooms,
            max_guests: maxGuests,
            price_per_night: pricePerNight,
            address: address,
            description: description,
            cleaning_fee: cleaningFee,
            has_wifi: hasWifi,
            property_type: type,
            num_beds: beds,
            latitude: latitude,
            longitude: longitude,
            city_id: cityId,
            user_id: userId
        }, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }).then((response) => {
            const accommodationId = response.data.id;
            if (accommodationId) {
                uploadImages(accommodationId);
            }
        }).catch((error) => {
            console.log(error);
        });
    }

    const uploadImages = (accommodationId) => {
        const token = localStorage.getItem('token');
        for(const image of images){
            if(!image.file){
                continue;
            }
            const formData = new FormData();
            formData.append('accommodation_id', accommodationId);
            formData.append('image', image.file);
            axios.post(baseAPIUrl + 'accommodations/image', formData, {
                headers: {
                    Authorization: `Bearer ${token}`,
                    'Content-Type': 'multipart/form-data'
                }
            }).then(() => {
            }).catch((error) => {
                console.log(error);
            });
        }
        navigate('/user/accommodations');
    }

    const handleFileChange = (event) => {
        const selectedFiles = event.target.files;

        const newImages = Array.from(selectedFiles).map((file) => {
            const path = URL.createObjectURL(file);
            return { file, path };
        });
        setImages((prevImages) => [...prevImages, ...newImages]);
    };

    const handleImageDelete = (index) => {
        setImageToDelete(images[index]);
        setShowDeleteModal(true);
    }

    const deleteImage = () => {
        if(imageToDelete.file){
            const newImages = images.filter((image) => image.file !== imageToDelete.file);
            setImages(newImages);
            setShowDeleteModal(false);
            return;
        } else {
            const token = localStorage.getItem('token');
            axios.delete(baseAPIUrl + 'images/' + imageToDelete.id, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            }).then(() => {
                const newImages = images.filter((image) => image.id !== imageToDelete.id);
                setImages(newImages);
                setShowDeleteModal(false);
            }).catch((error) => {
                console.log(error);
            });
        }
    };

    return (<>
        <Menu />
        <div className="p-4">
            <h1 className="primary-color mb-3">{id === undefined ? 'Crear' : 'Actualizar'} Alojamiento</h1>
            <Form className="w-50" noValidate validated={validated} onSubmit={(e)=>(onFormSubmit(e))}>
                <Form.Group className="mb-3 fs-5" controlId="formBasicName">
                    <Form.Label className="fw-bold">Nombre</Form.Label>
                    <Form.Control type="text" placeholder="Nombre" value={name} onChange={(e) => setName(e.target.value)} required />
                    <Form.Control.Feedback type="invalid">
                        Escribe un nombre válido.
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3 fs-5" controlId="formBasicBedrooms">
                    <Form.Label className="fw-bold">Habitaciones</Form.Label>
                    <Form.Control type="number" placeholder="Habitaciones" value={bedrooms} onChange={(e) => setBedrooms(e.target.value)} required />
                    <Form.Control.Feedback type="invalid">
                        Escribe un número válido.
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3 fs-5" controlId="formBasicBathrooms">
                    <Form.Label className="fw-bold">Baños</Form.Label>
                    <Form.Control type="number" placeholder="Baños" value={bathrooms} onChange={(e) => setBathrooms(e.target.value)} required />
                    <Form.Control.Feedback type="invalid">
                        Escribe un número válido.
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3 fs-5" controlId="formBasicMaxGuests">
                    <Form.Label className="fw-bold">Máximo de Huéspedes</Form.Label>
                    <Form.Control type="number" placeholder="Huespedes" value={maxGuests} onChange={(e) => setMaxGuests(e.target.value)} required />
                    <Form.Control.Feedback type="invalid">
                        Escribe un número válido.
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3 fs-5" controlId="formBasicPricePerNight">
                    <Form.Label className="fw-bold">Precio por Noche</Form.Label>
                    <Form.Control type="number" placeholder="Precio por Noche" value={pricePerNight} onChange={(e) => setPricePerNight(e.target.value)} required />
                    <Form.Control.Feedback type="invalid">
                        Escribe un número válido.
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3 fs-5" controlId="formBasicAddress">
                    <Form.Label className="fw-bold">Dirección</Form.Label>
                    <Form.Control type="text" placeholder="Dirección" value={address} onChange={(e) => setAddress(e.target.value)} required />
                    <Form.Control.Feedback type="invalid">
                        Escribe una dirección válida.
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3 fs-5" controlId="formBasicDescription">
                    <Form.Label className="fw-bold">Descripción</Form.Label>
                    <Form.Control type="text" placeholder="Descripción" value={description} onChange={(e) => setDescription(e.target.value)} required />
                    <Form.Control.Feedback type="invalid">
                        Escribe una descripción válida.
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3 fs-5" controlId="formBasicCleaningFee">
                    <Form.Label className="fw-bold">Costo de Limpieza</Form.Label>
                    <Form.Control type="number" placeholder="Costo de Limpieza" value={cleaningFee} onChange={(e) => setCleaningFee(e.target.value)} required />
                    <Form.Control.Feedback type="invalid">
                        Escribe un número válido.
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3 fs-5" controlId="formBasicHasWifi">
                    <Form.Check type="switch" label="¿Tiene Wifi?" checked={hasWifi} onChange={(e) => setHasWifi(e.target.checked)} required />
                </Form.Group>
                <Form.Group className="mb-3 fs-5" controlId="formBasicType">
                    <Form.Label className="fw-bold">Tipo de Propiedad</Form.Label>
                    <Form.Select value={type}
                        onChange={(event) => { setType(event.target.value) }} required>
                        <option value="">Selecciona un Tipo</option>
                        {accommodationTypes.map((type) => (
                            <option key={'acmType-' + type.property_type} value={type.property_type}>
                                {type.property_type}
                            </option>
                        ))}
                    </Form.Select>
                    <Form.Control.Feedback type="invalid">
                        Selecciona un tipo de propiedad.
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3 fs-5" controlId="formBasicBeds">
                    <Form.Label className="fw-bold">Camas</Form.Label>
                    <Form.Control type="number" placeholder="Camas" value={beds} onChange={(e) => setBeds(e.target.value)} required />
                    <Form.Control.Feedback type="invalid">
                        Escribe un número válido.
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3 fs-5" controlId="formBasicType">
                    <Form.Label className="fw-bold">Ciudad
                        <span className=" ms-3 text-decoration-underline fs-6 text-secondary cursor-pointer"
                            onClick={() => setShowCityModal(true)}>
                            ¿No encuentras tu ciudad? Agrega una nueva
                        </span>
                    </Form.Label>
                    <Form.Select value={cityId}
                        onChange={(event) => { setCityId(event.target.value) }} required>
                        <option value="">Selecciona una ciudad</option>
                        {cities.map((city) => (
                            <option key={'acmType-' + city.id} value={city.id}>
                                {city.name}
                            </option>
                        ))}
                    </Form.Select>
                    <Form.Control.Feedback type="invalid">
                        Selecciona una ciudad.
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3 fs-5" controlId="formBasicLocation">
                    <Form.Label className="fw-bold">Ubicación</Form.Label>
                    <Form.Control type="number" value={latitude} required style={{ display: 'none' }}
                        onChange={() => { }} />
                    {mapLatitude && isLoaded && <div className="mapContainer w-100"
                        style={{ height: 50 + 'vh' }}>
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
                    <Form.Control.Feedback type="invalid">
                        Selecciona una ubicación en el mapa.
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3 fs-5" controlId="formBasicBeds">
                    <Form.Label className="fw-bold">Imágenes</Form.Label>
                    <div className="galleryContainer mb-3">
                    {images.length > 0 && images.map((image, index) => (
                        <div key={"image-" + index} className="imageWrapper position-relative">
                            <img alt={`Image ${index}`} src={image.file?image.path:baseImageUrl+image.url} 
                                className="accommodationImage"/>
                            <Button className="btn primary-color position-absolute end-0" variant="clear"
                                onClick={() => handleImageDelete(index)}>
                                <i className="fa fa-trash"></i>
                            </Button>
                        </div>
                    ))}
                    </div>
                    <Form.Control type="file" placeholder="Imagen" multiple
                        onChange={handleFileChange} required />
                    <Form.Control.Feedback type="invalid">
                        Selecciona una imagen para el producto.
                    </Form.Control.Feedback>
                </Form.Group>
                <Button className="btn primary-bg primary-border" type="submit"
                    onClick={(e) => onFormSubmit(e)}>{id === undefined ? 'Crear' : 'Actualizar'} Alojamiento
                </Button>
            </Form>
        </div>
        <CityModal show={showCityModal} setShow={setShowCityModal}
            onConfirm={(city) => cities.push(city)} />
        <DeleteModal show={showDeleteModal} title="Eliminar imagen" onConfirm={deleteImage}
            message="¿Desea eliminar la imagen?" onCancel={() => setShowDeleteModal(false)}/>
    </>);
}

export default AccommodationForm;