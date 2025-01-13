import { Button, Container, Row } from "react-bootstrap";
import { Routes } from "../routes/CONSTANTS";
import { useEffect, useState } from "react";
import { Category } from "../models/Category";
import { App } from '../models/App';
import { CategoryService } from "../services/CategoryService";
import { AppService } from "../services/AppService";

const AdminPage = () => {
    const [categories, setCategories] = useState<Category[]>([]);
    const [apps, setApps] = useState<App[]>([]);
    const [searchTerm, setSearchTerm] = useState("");

    const fetchCategories = () => {
        CategoryService.list().then((response) => {
            setCategories(response);
        });
    }

    const fetchApps = () => {
        AppService.list().then((response) => {
            setApps(response);
        });
    }

    const deleteCategory = (id: number) => {
        if (!window.confirm("¿Estás seguro de eliminar esta categoría?")) return;
        if (!id) return;
        CategoryService.delete(id).then(() => {
            setCategories(categories.filter((category) => category.id !== id));
        });
    }

    const deleteApp = (id: number) => {
        if (!window.confirm("¿Estás seguro de eliminar esta aplicación?")) return;
        if (!id) return;
        AppService.delete(id).then(() => {
            setApps(apps.filter((app) => app.id !== id));
        });
    }

    useEffect(() => {
        fetchCategories();
        fetchApps();
    }
        , []);

    return (<div className="banner vh-100">
        <Container className="h-100">
            <h1 className="p-3 text-white">Panel de Administrador</h1>
            <Row className="m-0">
                <div className="col-6 py-3">
                    <div className="d-flex flex-wrap align-items-center pb-3">
                        <h2 className="text-white">Categorías</h2>
                        <a href={Routes.CATEGORIES.CREATE} className="btn">
                            <i className="fas fa-plus-circle fs-3 text-success"></i>
                        </a>
                    </div>
                    <ul className="list-unstyled pe-3 overflow-auto admin-panel-element scroll-container">
                        {categories.map((category) => (
                            <li className="pb-2 px-0" key={"category-" + category.id}>
                                <div className="text-white bg-dark p-2 rounded border d-flex justify-content-between">
                                    <div>{category.name}</div>
                                    <div>
                                        <a href={Routes.CATEGORIES.EDIT_PARAM(category.id)}
                                            className="btn btn-primary btn-sm rounded-circle me-2">
                                            <i className="fas fa-pencil-alt"></i>
                                        </a>
                                        <button onClick={() => deleteCategory(category.id!)}
                                            className="btn btn-danger btn-sm rounded-circle">
                                            <i className="fas fa-trash-alt"></i>
                                        </button>
                                    </div>
                                </div>
                            </li>
                        ))}
                    </ul>
                </div>
                <div className="col-6 py-3">
                    <div className="d-flex flex-wrap align-items-center pb-3">
                        <h2 className="text-white">Aplicaciones</h2>
                        <a href={Routes.APPS.CREATE} className="btn">
                            <i className="fas fa-plus-circle fs-3 text-success"></i>
                        </a>
                        <div className="d-flex">
                            <input type="text" className="form-control"
                                placeholder="Busca una aplicación" value={searchTerm}
                                onChange={(e) => setSearchTerm(e.target.value)}></input>
                        </div>
                    </div>
                    <ul className="list-unstyled pe-3 overflow-auto admin-panel-element scroll-container">
                        {apps.map((app) => (
                            <li className="pb-2 px-0" key={"category-" + app.id}>
                                {app.name.includes(searchTerm) && <div className="text-white bg-dark p-2 rounded border d-flex justify-content-between">
                                    <a className="text-decoration-none text-white"
                                        href={Routes.APPS.DETAIL_PARAM(app.id)}>
                                        {app.name}
                                    </a>
                                    <div>
                                        <a href={Routes.APPS.EDIT_PARAM(app.id)}
                                            className="btn btn-primary btn-sm rounded-circle me-2">
                                            <i className="fas fa-pencil-alt"></i>
                                        </a>
                                        <Button onClick={() => deleteApp(app.id!)}
                                            className="btn btn-danger btn-sm rounded-circle">
                                            <i className="fas fa-trash-alt"></i>
                                        </Button>
                                    </div>
                                </div>}
                            </li>
                        ))}
                    </ul>
                </div>
            </Row>
        </Container>
    </div>);
}

export default AdminPage;