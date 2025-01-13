import { useEffect, useState } from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import { Button, Card, CardBody, CardFooter, CardHeader, Input, Option, Select, Typography } from "@material-tailwind/react";
import { Routes } from "../../routes/CONSTANTS";
import { SamplingAnalysisService } from "../../services/SamplingAnalysisService";
import { SamplingAnalysis } from "../../models/SamplingAnalysis";
import { useUser } from "../../contexts/user/useUser";
import { FieldTrip } from "../../models/FieldTrip";
import { FieldTripService } from "../../services/FieldTripService";
import { SamplingAnalysisImage } from "../../models/SamplingAnalysisImage";
import { SamplingAnalysisImageService } from "../../services/SamplingAnalysisImageService";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrash } from "@fortawesome/free-solid-svg-icons";

type FormErrors = Partial<Record<keyof SamplingAnalysis, boolean>>;

const SamplingAnalysisFormPage = () => {
    const { user } = useUser();
    const fieldTripId = new URLSearchParams(useLocation().search).get("field_trip_id");
    const [form, setForm] = useState<SamplingAnalysis>({
        turbidity: 0,
        water_velocity: 0,
        width: 0,
        average_depth: 0,
        flow_rate: 0,
        user_id: user?.id ?? 0,
        water_body_id: 0,
        field_trip_id: fieldTripId ? parseInt(fieldTripId) : 0,
    });
    const [formErrors, setFormErrors] = useState<FormErrors>({});
    const [fieldTrip, setFieldTrip] = useState<FieldTrip>();
    const [newImages, setNewImages] = useState<SamplingAnalysisImage[]>([]);
    const [oldImages, setOldImages] = useState<SamplingAnalysisImage[]>([]);
    const [requestError, setRequestError] = useState("");
    const [isSending, setIsSending] = useState(false);
    const navigate = useNavigate();
    const { id } = useParams();
    const baseImageUrl = import.meta.env.VITE_BASE_IMG_URL;

    useEffect(() => {
        if (id) fetchAnalysis()
    }, [id]);

    useEffect(() => {
        if (fieldTripId) fetchFieldTrip()
    }, [fieldTripId]);

    const fetchAnalysis = () => {
        if (!id) return
        SamplingAnalysisService.get(parseInt(id)).then((analysis) => {
            setForm({
                turbidity: analysis.turbidity,
                water_velocity: analysis.water_velocity,
                width: analysis.width,
                average_depth: analysis.average_depth,
                flow_rate: analysis.flow_rate ?? 0,
                user_id: analysis.user_id,
                water_body_id: analysis.water_body_id,
                field_trip_id: analysis.field_trip_id,
            });
            setOldImages(analysis.sampling_analysis_images ?? []);
        }).catch((error) => setRequestError(error.message));
    }

    const fetchFieldTrip = () => {
        if (!fieldTripId) return
        FieldTripService.get(parseInt(fieldTripId)).then((fieldTrip) => {
            setFieldTrip(fieldTrip);
        }).catch((error) => setRequestError(error.message));
    }

    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const { name, value } = event.target;
        setForm((prevForm) => ({ ...prevForm, [name]: value }));
        if (formErrors[name as keyof SamplingAnalysis]) {
            setFormErrors({ ...formErrors, [name]: false });
        }
    }

    const handleFormSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        event.stopPropagation();

        const errors = validateForm(form);
        setFormErrors(errors);
        if (Object.values(errors).some(err => err)) return;
        setForm({ ...form, user_id: user?.id ?? 0 });
        setIsSending(true);
        if (!id) {
            createAnalysis();
        } else {
            updateAnalysis();
        }
    }

    const validateForm = (form: SamplingAnalysis): FormErrors => {
        const errors: FormErrors = {};
        if (form.turbidity === 0) errors.turbidity = true;
        if (form.water_velocity === 0) errors.water_velocity = true;
        if (form.width === 0) errors.width = true;
        if (form.average_depth === 0) errors.average_depth = true;
        if (form.water_body_id === 0) errors.water_body_id = true;
        if (newImages.length === 0 && oldImages.length === 0) errors.sampling_analysis_images = true;
        return errors;
    }

    const createAnalysis = () => {
        SamplingAnalysisService.create(form)
            .then((analysis) => saveImages(analysis.id!))
            .catch(error => {
                setIsSending(false);
                setRequestError(error.message)
            });
    }

    const updateAnalysis = () => {
        if (!id) return
        SamplingAnalysisService.update(parseInt(id), form)
            .then((analysis) => saveImages(analysis.id!))
            .catch(error => {
                setIsSending(false);
                setRequestError(error.message)
            });
    }

    const handleImageChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        if (!event.target.files) return;
        const image = event.target.files[0];
        const newImage: SamplingAnalysisImage = {
            image,
            sampling_analysis_id: 0,
        }
        const reader = new FileReader();
        reader.onload = () => {
            setNewImages([...newImages, { ...newImage, image_url: reader.result as string }]);
        }
        reader.readAsDataURL(image);
    };

    const handleOldImageDelete = (id: number) => {
        SamplingAnalysisImageService.delete(id).then(() => {
            setOldImages(oldImages.filter(image => image.id !== id));
        }).catch((error) => setRequestError(error.message));
    }

    const saveImages = (samplingAnalysisId: number) => {
        const promises = newImages.map((image) => SamplingAnalysisImageService.create({ ...image, sampling_analysis_id: samplingAnalysisId }));
        Promise.all(promises).then(() => navigate(Routes.FIELDTRIP.ANALYSES_PARAM(parseInt(fieldTripId ?? ""))))
            .catch((error) => {
                setIsSending(false);
                setRequestError(error.message);
            });
    }

    return (<Card className="lg:w-160 mx-auto my-8">
        <form onSubmit={handleFormSubmit} noValidate>
            <CardHeader floated={false} className="mb-4" shadow={false}>
                <Typography variant="h3" color="blue">
                    {id ? "Editar" : "Crear"} Muestreo
                </Typography>
            </CardHeader>
            <CardBody className="flex flex-col gap-4">
                <Input type="number" label="Turbidez" size="lg" name="turbidity" id="turbidity" required
                    value={form.turbidity != 0 ? form.turbidity : ""}
                    onChange={(e) => handleInputChange(e)} error={formErrors.turbidity} />

                <Input type="number" label="Velocidad media del agua" size="lg" name="water_velocity" id="water_velocity" required
                    value={form.water_velocity != 0 ? form.water_velocity : ""}
                    onChange={(e) => handleInputChange(e)} error={formErrors.water_velocity} />

                <Input type="number" label="Ancho Transversal" size="lg" name="width" id="width" required
                    value={form.width != 0 ? form.width : ""}
                    onChange={(e) => handleInputChange(e)} error={formErrors.width} />

                <Input type="number" label="Profundidad Promedio" size="lg" name="average_depth" id="average_depth" required
                    value={form.average_depth != 0 ? form.average_depth : ""}
                    onChange={(e) => handleInputChange(e)} error={formErrors.average_depth} />

                {fieldTrip && <Select label="Cuerpo de agua" value={form.water_body_id + ""} error={formErrors.water_body_id}
                    onChange={(value) => handleInputChange({ target: { name: "water_body_id", value } } as React.ChangeEvent<HTMLInputElement>)}>
                    {[{ id: "0", name: "Seleccionar una opción" }, ...(fieldTrip?.water_bodies || [])].map((waterBody) => (
                        <Option key={waterBody.id} value={waterBody.id + ""} disabled={waterBody.id === "0"}>
                            {waterBody.name}
                        </Option>
                    ))}
                </Select>}

                <Input type="file" label="Imagen" size="lg" name="imagen" id="imagen"
                    onChange={(e) => handleImageChange(e)} error={formErrors.sampling_analysis_images} />
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

export default SamplingAnalysisFormPage;