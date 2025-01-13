import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { Button, Card, CardBody, CardFooter, CardHeader, Input, Option, Select, Typography } from "@material-tailwind/react";

import { Routes } from "../../routes/CONSTANTS";
import { ReactSearchAutocomplete } from 'react-search-autocomplete'
import { GoogleMap, MarkerF, useJsApiLoader } from "@react-google-maps/api";
import { WaterBody } from "../../models/WaterBody";
import { WaterBodyType } from "../../models/enums/WaterBodyType";
import { Community } from "../../models/Community";
import { WaterBodyService } from "../../services/WaterBodyService";
import { CommunityService } from "../../services/CommunityService";
import { WaterBodyImage } from "../../models/WaterBodyImage";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrash } from "@fortawesome/free-solid-svg-icons";
import { WaterBodyImageService } from "../../services/WaterBodyImageService";

type FormErrors = Partial<Record<keyof WaterBody, boolean>>;

const WaterBodyFormPage = () => {
    const [form, setForm] = useState<WaterBody>({
        name: "",
        latitude: 0,
        longitude: 0,
        water_body_type: WaterBodyType.UNSET,
        community_id: 0,
    });
    const [newImages, setNewImages] = useState<WaterBodyImage[]>([]);
    const [oldImages, setOldImages] = useState<WaterBodyImage[]>([]);
    const [communities, setCommunities] = useState<Community[]>([]);
    const [formErrors, setFormErrors] = useState<FormErrors>({});
    const [requestError, setRequestError] = useState("");
    const [isSending, setIsSending] = useState(false);
    const navigate = useNavigate();
    const { id } = useParams();
    const baseImageUrl = import.meta.env.VITE_BASE_IMG_URL;

    const [mapLatitude, setMapLatitude] = useState("");
    const [mapLongitude, setMapLongitude] = useState("");
    const mapsApiKey = import.meta.env.VITE_MAP_KEY;
    const { isLoaded } = useJsApiLoader({
        id: 'google-map-script',
        googleMapsApiKey: mapsApiKey
    });

    useEffect(() => {
        if (!navigator.geolocation) {
            console.error('La geolocalización no es compatible con este navegador.');
        }
        navigator.geolocation.getCurrentPosition(
            (position) => {
                setMapLatitude(position.coords.latitude + "");
                setMapLongitude(position.coords.longitude + "");
            },
            (error) => {
                console.error(`Error al obtener la ubicación: ${error.message}`);
            }
        );
    }, []);

    useEffect(() => {
        if (id) fetchWaterBody()
        fetchCommunities();
    }, [id]);

    const fetchWaterBody = () => {
        if (!id) return
        WaterBodyService.get(parseInt(id)).then((waterBody) => {
            setForm({
                name: waterBody.name,
                latitude: waterBody.latitude,
                longitude: waterBody.longitude,
                water_body_type: waterBody.water_body_type,
                community_id: waterBody.community_id,
            });
            setMapLatitude(waterBody.latitude + "");
            setMapLongitude(waterBody.longitude + "");
            setOldImages(waterBody.water_body_images ?? []);
        }).catch((error) => setRequestError(error.message));
    }

    const fetchCommunities = () => {
        CommunityService.list().then((communities) => {
            setCommunities(communities);
        }).catch((error) => setRequestError(error.message));
    }

    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const { name, value } = event.target;
        setForm((prevForm) => ({ ...prevForm, [name]: value }));
        if (formErrors[name as keyof WaterBody]) {
            setFormErrors({ ...formErrors, [name]: false });
        }
    }

    const handleFormSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        event.stopPropagation();

        const errors = validateForm(form);
        setFormErrors(errors);
        if (Object.values(errors).some(err => err)) return;

        setIsSending(true);
        if (!id) {
            createWaterBody();
        } else {
            updateWaterBody();
        }
    }

    const validateForm = (form: WaterBody): FormErrors => {
        const errors: FormErrors = {};
        if (form.name === "") errors.name = true;
        if (form.latitude === 0) errors.latitude = true;
        if (form.longitude === 0) errors.longitude = true;
        if (form.water_body_type === WaterBodyType.UNSET) errors.water_body_type = true;
        if (newImages.length === 0 && oldImages.length === 0) errors.water_body_images = true;
        if (form.community_id === 0) errors.community_id = true;
        return errors;
    }

    const createWaterBody = () => {
        WaterBodyService.create(form)
            .then((waterBody) => saveImages(waterBody.id!))
            .catch(error => {
                setIsSending(false);
                setRequestError(error.message)
            });
    }

    const updateWaterBody = () => {
        if (!id) return
        WaterBodyService.update(parseInt(id), form)
            .then((waterBody) => saveImages(waterBody.id!))
            .catch(error => {
                setIsSending(false);
                setRequestError(error.message)
            });
    }

    const resetSelectedDepartment = () => {
        setForm((prevForm) => ({ ...prevForm, department_id: 0 }));
    }

    const handleOnSelect = (community: Community) => {
        setForm((prevForm) => ({ ...prevForm, community_id: community.id! }));
        if (formErrors.community_id) {
            setFormErrors({ ...formErrors, community_id: false });
        }
    }

    const handleOnSearch = () => {
        resetSelectedDepartment();
    }

    const handleMapClick = (event: google.maps.MapMouseEvent) => {
        setMapLatitude(event.latLng?.lat() + "");
        setMapLongitude(event.latLng?.lng() + "");
        setForm((prevForm) => ({ ...prevForm, latitude: event.latLng?.lat() ?? 0, longitude: event.latLng?.lng() ?? 0 }));
    }

    const handleImageChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        if (!event.target.files) return;
        const image = event.target.files[0];
        const newImage: WaterBodyImage = {
            image,
            water_body_id: 0,
        }
        const reader = new FileReader();
        reader.onload = () => {
            setNewImages([...newImages, { ...newImage, image_url: reader.result as string }]);
        }
        reader.readAsDataURL(image);
    };

    const handleOldImageDelete = (id: number) => {
        WaterBodyImageService.delete(id).then(() => {
            setOldImages(oldImages.filter(image => image.id !== id));
        }).catch((error) => setRequestError(error.message));
    }

    const saveImages = (waterBodyId: number) => {
        const promises = newImages.map((image) => WaterBodyImageService.create({ ...image, water_body_id: waterBodyId }));
        Promise.all(promises).then(() => navigate(Routes.WATERBODY.LIST))
            .catch((error) => {
                setIsSending(false);
                setRequestError(error.message);
            });
    }

    return (<Card className="lg:w-160 mx-auto my-8">
        <form onSubmit={handleFormSubmit} noValidate>
            <CardHeader floated={false} className="mb-4" shadow={false}>
                <Typography variant="h3" color="blue">
                    {id ? "Editar" : "Crear"} Cuerpo de Agua
                </Typography>
            </CardHeader>
            <CardBody className="flex flex-col gap-4">

                <Input type="text" label="Nombre" size="lg" name="name" id="name" required
                    value={form.name} onChange={(e) => handleInputChange(e)} error={formErrors.name} />

                {mapLatitude !== "" && isLoaded && <div className="w-full h-[70vh]">
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
                        onClick={handleMapClick}
                        zoom={11}
                        mapContainerStyle={{ width: '100%', height: '100%' }}
                    >
                        <MarkerF
                            position={{
                                lat: parseFloat(mapLatitude),
                                lng: parseFloat(mapLongitude)
                            }}
                        />
                    </GoogleMap>
                </div>}

                <ReactSearchAutocomplete
                    items={communities}
                    onSelect={handleOnSelect}
                    onClear={resetSelectedDepartment}
                    onSearch={handleOnSearch}
                    placeholder="Buscar departamento..."
                    showItemsOnFocus
                    maxResults={5}

                    styling={formErrors.community_id ? { border: '1px solid #FF0000' } : {}}
                />

                <Select label="Tipo" value={form.water_body_type} error={formErrors.water_body_type}
                    onChange={(value) => handleInputChange({ target: { name: "water_body_type", value } } as React.ChangeEvent<HTMLInputElement>)}>
                    <Option value={WaterBodyType.UNSET}>{WaterBodyType.UNSET}</Option>
                    <Option value={WaterBodyType.RIVER}>{WaterBodyType.RIVER}</Option>
                    <Option value={WaterBodyType.STREAM}>{WaterBodyType.STREAM}</Option>
                    <Option value={WaterBodyType.LAKE}>{WaterBodyType.LAKE}</Option>
                    <Option value={WaterBodyType.WETLAND}>{WaterBodyType.WETLAND}</Option>
                </Select>

                <Input type="file" label="Imagen" size="lg" name="imagen" id="imagen"
                    onChange={(e) => handleImageChange(e)} error={formErrors.water_body_images} />
                <div className="scroll-container overflow-x-auto w-full flex">
                    {newImages.map((image, index) => (
                        <div key={index} className="relative w-fit h-48 mr-4 shrink-0">
                            <img src={image.image_url} alt="Imagen" className="h-48 w-auto object-cover" />
                            <FontAwesomeIcon icon={faTrash} size="sm" className="absolute top-1 right-1 bg-red-500 text-white rounded-full p-2 cursor-pointer"
                                onClick={() => setNewImages(newImages.filter((_, i) => i !== index))} />
                        </div>))
                    }
                    {oldImages.map((image, index) => (
                        <div key={index} className="relative w-fit h-48 mr-4 shrink-0">
                            <img src={baseImageUrl + image.image_url} alt="Imagen" className="h-48 w-auto object-cover" />
                            <FontAwesomeIcon icon={faTrash} size="sm" className="absolute top-1 right-1 bg-red-500 text-white rounded-full p-2 cursor-pointer"
                                onClick={() => handleOldImageDelete(image.id!)} />
                        </div>))
                    }
                </div>

                {requestError && <Typography color="red">{requestError}</Typography>}
            </CardBody>
            <CardFooter className="pt-0">
                <Button color="blue" fullWidth type="submit" disabled={isSending}>
                    {isSending ? "Guardando..." : "Guardar"}
                </Button>
            </CardFooter>
        </form>
    </Card>);
}

export default WaterBodyFormPage;