import { useNavigate, useParams } from "react-router-dom";
import { Routes } from "../routes/CONSTANTS";
import { Button, Container, Form } from "react-bootstrap";
import { FormEvent, useEffect, useState } from "react";
import { CategoryService } from "../services/CategoryService";
import { Category } from "../models/Category";

const CategoryForm = () => {
    const { id } = useParams();
    const [name, setName] = useState("");
    const navigate = useNavigate();

    useEffect(() => {
        if (id) {
            fetchCategory();
        }
    }, []);

    const fetchCategory = () => {
        if (!id) return;
        CategoryService.get(parseInt(id)).then((response) => {
            setName(response.name);
        });
    }

    const createCategory = () => {
        const category: Category = {
            name: name,
            apps: []
        }
        CategoryService.create(category).then(() => {
            navigate(Routes.ADMIN);
        });
    }

    const updateCategory = () => {
        if (!id) return;
        const category: Category = {
            id: parseInt(id),
            name: name,
            apps: []
        }
        CategoryService.update(category).then(() => {
            navigate(Routes.ADMIN);
        });
    }

    const onFormSubmit = (e: FormEvent) => {
        e.preventDefault();
        if (id) {
            updateCategory();
        } else {
            createCategory();
        }
    }

    return (<div className="banner vh-100 overflow-auto">
        <div className="bg-dark p-3 ps-5 border-bottom border-2 border-secondary">
            <a href={Routes.ADMIN} className="h2 text-white text-decoration-none">Inicio</a>
        </div>
        <Container>
            <h1 className="py-3 text-white">{id ? 'Editar' : 'Crear' + ' Categoría'}</h1>
            <Form className="w-50" onSubmit={(e) => onFormSubmit(e)}>
                <Form.Group className="mb-3">
                    <Form.Label className="text-white">Nombre</Form.Label>
                    <Form.Control type="text" placeholder="Nombre de la categoría" value={name} onChange={(e) => setName(e.target.value)} />
                </Form.Group>
                <Button variant="primary" type="submit">
                    Guardar
                </Button>
            </Form>
        </Container>
    </div>);
}

export default CategoryForm;