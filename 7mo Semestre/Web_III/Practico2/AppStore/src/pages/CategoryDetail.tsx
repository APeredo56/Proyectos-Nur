import { useParams } from "react-router-dom";
import NavbarComponent from "../components/NavbarComponent";
import { useEffect, useState } from "react";
import { CategoryService } from "../services/CategoryService";
import { App } from "../models/App";
import { Container } from "react-bootstrap";
import { Routes } from "../routes/CONSTANTS";

const CategoryDetail = () => {
    const { id } = useParams();
    const [categoryName, setCategoryName] = useState("");
    const [categoryApps, setCategoryApps] = useState<App[]>([])

    const fetchCategory = () => {
        if (!id) return;
        CategoryService.get(parseInt(id)).then((response) => {
            setCategoryName(response.name);
            setCategoryApps(response.apps);
        });
    }

    useEffect(() => {
        fetchCategory();
    }, []);

    return (<div className="banner vh-100 overflow-auto">
        <NavbarComponent />
        <Container>
            <h1 className="p-3 text-white">Aplicaciones de {categoryName}</h1>
            <div className="m-0 d-flex flex-wrap">
                {categoryApps.map((app) => (
                    <div className="px-3 mb-3 d-flex justify-content-center" key={"top-paid-app-" + app.id}>
                        <a className="card text-decoration-none"
                            href={Routes.APPS.DETAIL_PARAM(app.id)}>
                            <img src={app.icon} alt={app.name}
                                className="card-img-top preview-image" />
                            <div className="card-body">
                                <div className="d-flex justify-content-between w-100">
                                    <h5 className="card-title">{app.name}</h5>
                                    <h5>{app.price} $</h5>
                                </div>
                                <p className="card-text">{app.description}</p>
                            </div>
                        </a>
                    </div>
                ))}
            </div>
        </Container>
    </div>);
}

export default CategoryDetail;