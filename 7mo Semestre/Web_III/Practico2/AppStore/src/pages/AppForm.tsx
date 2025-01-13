import { useNavigate, useParams } from "react-router-dom";
import { Routes } from "../routes/CONSTANTS";
import { Button, Col, Container, Form, Image, Row } from "react-bootstrap";
import { ChangeEvent, FormEvent, useEffect, useState } from "react";
import { AppService } from "../services/AppService";
import { Category } from "../models/Category";
import { CategoryService } from "../services/CategoryService";
import { ScreenshotService } from "../services/ScreenshotService";
import { Screenshot } from "../models/Screenshot";

const AppForm = () => {
    const { id } = useParams();
    const [name, setName] = useState("");
    const [description, setDescription] = useState("");
    const [price, setPrice] = useState(0);
    const [iconImage, setIconImage] = useState("");
    const [coverImage, setCoverImage] = useState("");
    const [iconImageFile, setIconImageFile] = useState<File>();
    const [coverImageFile, setCoverImageFile] = useState<File>();
    const [categories, setCategories] = useState<Category[]>([]);
    const [appCategories, setAppCategories] = useState<number[]>([]);
    const [appScreenshots, setAppScreenshots] = useState<Screenshot[]>([]);
    const [appScreenshotsFiles, setAppScreenshotsFiles] = useState<File[]>([]);
    const [uploadedScreenshotsAmount, setUploadedScreenshotsAmount] = useState(0);

    const navigate = useNavigate();

    useEffect(() => {
        if (id) {
            fetchApp();
        }
        fetchCategories();
    }, []);

    const fetchApp = () => {
        if (!id) return;
        AppService.get(parseInt(id)).then((response) => {
            setName(response.name);
            setDescription(response.description);
            setIconImage(response.icon);
            setCoverImage(response.cover_photo);
            setPrice(response.price);
            const appCategoriesIds = response.categories.map((category) => category.id!);
            setAppCategories(appCategoriesIds);
            setAppScreenshots(response.screenshots);
            setUploadedScreenshotsAmount(response.screenshots.length);
        });
    }

    const fetchCategories = () => {
        CategoryService.list().then((response) => {
            setCategories(response);
        });
    }

    const createApp = () => {
        if (!iconImageFile || !coverImageFile) return
        const data = new FormData();
        data.append('name', name);
        data.append('description', description);

        data.append('price', price.toString());
        if (iconImageFile) {
            data.append('icon', iconImageFile);
        }
        data.append('cover_photo', coverImageFile);
        for (const app of appCategories) {
            data.append("category_ids", app + "");
        }
        for (const screenshot of appScreenshotsFiles) {
            data.append("screenshots_files", screenshot);
        }
        AppService.create(data).then(() => {
            navigate(Routes.ADMIN);
        });
    }

    const updateCategory = () => {
        if (!id) return;
        const data = new FormData();
        data.append('name', name);
        data.append('description', description);
        data.append('price', price.toString());
        if (iconImageFile) {
            data.append('icon', iconImageFile);
        }
        if (coverImageFile) {
            data.append('cover_photo', coverImageFile);
        }
        for (const app of appCategories) {
            data.append("category_ids", app + "");
        }
        for (const screenshot of appScreenshotsFiles) {
            data.append("screenshots_files", screenshot);
        }
        AppService.update(data, parseInt(id)).then(() => {
            navigate(Routes.ADMIN);
        });
    }

    const handleIconChange = (event: ChangeEvent<HTMLInputElement>) => {
        if (!event.target.files) return;
        const image = event.target.files[0];
        setIconImageFile(image);
        const reader = new FileReader();
        reader.onload = () => {
            setIconImage(reader.result as string);
        }
        reader.readAsDataURL(image);
    };

    const handleCoverChange = (event: ChangeEvent<HTMLInputElement>) => {
        if (!event.target.files) return;
        const image = event.target.files[0];
        setCoverImageFile(image);
        const reader = new FileReader();
        reader.onload = () => {
            setCoverImage(reader.result as string);
        }
        reader.readAsDataURL(image);
    };

    const handleScreenshotChange = (event: ChangeEvent<HTMLInputElement>) => {
        if (!event.target.files) return;
        const images = event.target.files;
        const newImages: Screenshot[] = [];
        for (const image of images) {
            newImages.push({ image: URL.createObjectURL(image) } as Screenshot);
        }
        setAppScreenshotsFiles([...appScreenshotsFiles, ...images]);
        setAppScreenshots([...appScreenshots, ...newImages]);
    }

    const handleSwitchChange = (categoryId: number) => {
        if (appCategories.includes(categoryId)) {
            appCategories.splice(appCategories.indexOf(categoryId), 1);
        } else {
            appCategories.push(categoryId);
        }
        setAppCategories([...appCategories]);
    }

    const deleteScreenshot = (id: number) => {
        ScreenshotService.delete(id).then(() => {
            setAppScreenshots(appScreenshots.filter((screenshot) => screenshot.id !== id));
        });
    }

    const removeScreenshot = (index: number) => {
        setAppScreenshots(appScreenshots.filter((screenshot, i) => i !== index));
        setAppScreenshotsFiles(appScreenshotsFiles.filter((screenshot, i) => i !== index - uploadedScreenshotsAmount));
    }

    const onFormSubmit = (e: FormEvent) => {
        e.preventDefault();
        if (id) {
            updateCategory();
        } else {
            createApp();
        }
    }

    return (<div className="banner vh-100 overflow-auto">
        <div className="bg-dark p-3 ps-5 border-bottom border-2 border-secondary">
            <a href={Routes.ADMIN} className="h2 text-white text-decoration-none">Inicio</a>
        </div>
        <Container>
            <h1 className="py-2 text-white">{id ? 'Editar' : 'Crear' + ' Aplicación'}</h1>
            <Form onSubmit={(e) => onFormSubmit(e)} className="mw-50">
                <Row>
                    <Col className="form-block">
                        <Form.Group className="mb-3">
                            <Form.Label className="text-white">Nombre</Form.Label>
                            <Form.Control type="text" placeholder="Nombre de la aplicación" required
                                value={name} onChange={(e) => setName(e.target.value)} />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label className="text-white">Descripción</Form.Label>
                            <Form.Control type="text" placeholder="Descripción de la aplicación" required
                                value={description} onChange={(e) => setDescription(e.target.value)} />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label className="text-white">Precio</Form.Label>
                            <Form.Control type="number" placeholder="Precio de la aplicación" required
                                value={price} onChange={(e) => setPrice(parseInt(e.target.value))} />
                        </Form.Group>
                    </Col>
                    <Col className="form-block overflow-auto scroll-container bg-translucent rounded">
                        <Row>
                            {categories.map((category) => (
                                <Col className="col-4 p-2" key={"category-switch-" + category.id}>
                                    <div className="form-check form-switch">
                                        <input className="form-check-input" type="checkbox"
                                            checked={appCategories.includes(category.id!)}
                                            onChange={() => handleSwitchChange(category.id!)}></input>
                                        <label className="form-check-label text-white">
                                            {category.name}
                                        </label>
                                    </div>
                                </Col>
                            ))}
                        </Row>
                    </Col>
                </Row>
                <Row className="mt-1">
                    <Col>
                        <Form.Group className="mb-3">
                            <Form.Label className="text-white d-block">Ícono</Form.Label>
                            <Image src={iconImage} rounded className="preview-image-form mb-3" />
                            <Form.Control type="file" placeholder="Ícono de la aplicación"
                                onChange={handleIconChange} />
                        </Form.Group>
                    </Col>
                    <Col>
                        <Form.Group className="mb-3">
                            <Form.Label className="text-white d-block">Portada</Form.Label>
                            <Image src={coverImage} rounded className="preview-image-form mb-3" />
                            <Form.Control type="file" placeholder="Portada de la aplicación"
                                onChange={handleCoverChange} />
                        </Form.Group>
                    </Col>
                </Row>
                <Row className="mb-1">
                    <Form.Group className="mb-3">
                        <Form.Label className="text-white d-block">Capturas de Pantalla</Form.Label>
                        <div className="scroll-container d-flex flex-nowrap overflow-auto mb-2">
                            {appScreenshots.map((screenshot, index) => (
                                <div className="position-relative" key={"screenshot-" + index}>
                                    <Image src={screenshot.image} rounded
                                        className="preview-image-form mx-2 mb-1" />
                                    <Button className="btn btn-danger btn-sm rounded-circle position-absolute end-0"
                                        onClick={() => {
                                            if (screenshot.id) {
                                                deleteScreenshot(screenshot.id);
                                                return;
                                            } 
                                            removeScreenshot(index);
                                        }}>
                                        <i className="fas fa-trash-alt"></i>
                                    </Button>
                                </div>
                            ))}
                        </div>
                        <Form.Control type="file" className="w-50" onChange={handleScreenshotChange}
                            multiple />
                    </Form.Group>
                </Row>
                <Button variant="primary" type="submit">
                    Guardar
                </Button>
            </Form>
        </Container>
    </div>);
}

export default AppForm;