import { useEffect, useState } from "react";
import NavbarComponent from "../components/NavbarComponent";
import { Container } from "react-bootstrap";
import { TaskService } from "../services/TaskService";
import { Task } from "../models/Task";
import { Routes } from "../routes/CONSTANTS";

const HomePage = () => {
    const [tasks, setTasks] = useState<Task[]>([]);

    useEffect(() => {
        fetchTasks();
    }, []);

    const fetchTasks = () => {
        TaskService.list().then(response => {
            setTasks(response);
        });
    }

    const handleTaskDeleteClick = (taskId: number) => () => {
        if(!confirm("¿Estás seguro de que deseas eliminar esta tarea?")){
            return;
        }
        TaskService.delete(taskId).then(() => {
            fetchTasks();
        });
    }

    return (<>
        <NavbarComponent />
        <Container>
            <div className="d-flex align-items-center">
                <h1 className="my-2 d-inline-block">Tareas Disponibles</h1>
                <a className="btn btn-primary bg-da rounded-pill ms-3 h-fit" href={Routes.TASK.CREATE}>
                    <i className="fas fa-add fs-3"></i>
                </a>
            </div>
            <div className="gap-3">
                {tasks.map(task => (
                    <div key={task.id} className="card mt-3 mx-3 d-inline-block">
                        <div className="card-body">
                            <h5 className="card-title">{task.title}</h5>
                            <a className="mt-1 btn btn-primary d-block"
                                href={Routes.TASK.DETAIL_PARAM(task.id)}>Ver Detalles</a>
                            <button className="mt-1 btn btn-danger"
                                onClick={handleTaskDeleteClick(task.id)}>Eliminar</button>
                        </div>
                    </div>
                ))}
            </div>
        </Container>
    </>);
}

export default HomePage;