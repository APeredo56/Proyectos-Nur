import { useParams } from "react-router-dom";
import NavbarComponent from "../components/NavbarComponent";
import { Col, Container, Row } from "react-bootstrap";
import { useEffect, useState } from "react";
import { AppService } from "../services/AppService";
import { Category } from "../models/Category";
import { Screenshot } from '../models/Screenshot';

const AppDetail = () => {
    const { id } = useParams();
    const [name, setName] = useState("Cargando...");
    const [description, setDescription] = useState("");
    const [coverPhoto, setCoverPhoto] = useState("");
    const [downloads, setDownloads] = useState(0);
    const [releaseDate, setReleaseDate] = useState("");
    const [price, setPrice] = useState(0);
    const [appCategories, setAppCategories] = useState<Category[]>([])
    const [appScreenshots, setAppScreenshots] = useState<Screenshot[]>([])

    const fetchApp = () => {
        if (!id) return;
        AppService.get(parseInt(id)).then((response) => {
            setName(response.name);
            setDescription(response.description);
            setCoverPhoto(response.cover_photo);
            setDownloads(response.downloads);
            setReleaseDate(response.release_date);
            setPrice(response.price);
            setAppCategories(response.categories);
            setAppScreenshots(response.screenshots);
        });
    }

    const addDownload = () => {
        if (!id) return;
        AppService.addDownload(parseInt(id)).then((response) => {
            setDownloads(response.downloads);
        });
    }

    useEffect(() => {
        fetchApp();
    }, []);

    return (<div className="banner vh-100 overflow-auto">
        <NavbarComponent />
        <Container>
            <h1 className="py-3 text-white">{name}</h1>
            <Row>
                <Col className="col-6">
                    <img src={coverPhoto} alt={name} className="img-fluid" />
                </Col>
                <Col className="col-6">
                    <div>
                        <h2 className="text-white">Descripción:</h2>
                        <p className="text-white">{description}</p>
                    </div>
                </Col>
            </Row>
            <Row className="pt-3">
                <Col>
                    <h2 className="text-white">Descargas:</h2>
                    <p className="text-white">{downloads}</p>
                </Col>
                <Col>
                    <h2 className="text-white">Fecha de lanzamiento:</h2>
                    <p className="text-white">{releaseDate}</p>
                </Col>
                <Col>
                    <h2 className="text-white">Precio:</h2>
                    <p className="text-white">{price == 0 ? 'Gratis' : price + ' $'}</p>
                </Col>
                <Col>
                    <a href={'/files/download.txt'} download="aplicacion" className="btn btn-primary"
                        onClick={() => addDownload()}>
                        Descargar
                    </a>
                </Col>
            </Row>
            <Row>
                <h3 className="text-white">Categorías</h3>
            </Row>
            <Row className="gap-3 my-3">
                {appCategories.map((category) => (
                    <Col className="col-2 text-white p-2 rounded bg-dark text-center"
                        key={"category-col-" + category.id}>
                        {category.name}
                    </Col>
                ))}
            </Row>
            <Row>
                <h3 className="text-white">Capturas de Pantalla</h3>
            </Row>
            <Row className="gap-3 my-3 align-items-center">
                {appScreenshots.map((screenshot) => (
                    <Col className="col-3 text-white p-2 text-center"
                        key={"screenshot-col-" + screenshot.id}>
                        <img src={screenshot.image} alt={"Screenshot " + screenshot.id}
                            className="img-fluid" />
                    </Col>
                ))}
            </Row>
        </Container>
    </div>);
}

export default AppDetail;