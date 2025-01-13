import { useParams } from "react-router-dom";
import NavbarComponent from "../../components/NavbarComponent";
import { useEffect, useState } from "react";
import { Task } from "../../models/Task";
import { TaskService } from "../../services/TaskService";
import { Container } from "react-bootstrap";
import AssignUserModal from "../../components/AssignUserModal";
import { Routes } from "../../routes/CONSTANTS";

const TaskDetailPage = () => {
    const { id } = useParams();
    const [task, setTask] = useState<Task>({
        id: 0,
        title: "",
        description: "",
        completed: false,
        start_date: "",
        end_date: "",
    });
    const [showAssignModal, setShowAssignModal] = useState(false);

    useEffect(() => {
        fetchTask();
    }, [id]);

    const fetchTask = () => {
        TaskService.get(Number(id)).then(response => {
            setTask(response);
        });
    }

    const formatDate = (date: string) => {
        return new Date(date).toLocaleDateString();
    }

    const handleUnassignUserClick = (userId: number) => () => {
        TaskService.unassignUser(Number(id), userId).then(() => {
            fetchTask();
        });
    }

    const completeTask = () => {
        if (!confirm("¿Estás seguro de que deseas marcar esta tarea como completada?")) return;
        TaskService.update({ ...task, completed: true }).then((response) => {
            setTask(response);
        });
    }

    return (<>
        <NavbarComponent />
        <Container>
            <div className="d-flex align-items-center">
                <h1 className="my-2 d-inline-block">{task.title}</h1>
                <a className="btn btn-primary bg-da rounded-pill ms-3 h-fit" href={Routes.TASK.EDIT_PARAM(task.id)}>
                    <i className="fas fa-pen"></i>
                </a>
            </div>
            <p>{task.description}</p>
            <p><span className="fw-bold">Fecha de Inicio:</span> {formatDate(task.start_date)}</p>
            <p><span className="fw-bold">Fecha de Fin:</span> {formatDate(task.end_date)}</p>
            <div className="d-flex align-items-center mb-3">
                <p className="mb-0"><span className="fw-bold">Completada:</span> {task.completed ? "Sí" : "No"}</p>
                {!task.completed && <button className="btn p-1 ms-3 btn-success rounded-pill" 
                    onClick={completeTask}>
                    Marcar como Completada
                </button>}
            </div>
            <h5>Usuarios Asignados:</h5>
            <div>
                <div className="card mt-3 mx-3 d-inline-block">
                    <div className="card-body cursor-pointer" onClick={() => setShowAssignModal(true)}>
                        <i className="fas fa-add fs-1"></i>
                    </div>
                </div>
                {task.users?.map(user => (
                    <div key={user.id} className="card mt-3 mx-3 d-inline-block">
                        <div className="card-body">
                            <h5 className="card-title">{user.name} {user.last_name}</h5>
                            <p className="card-text">{user.email}</p>
                            <button className="btn btn-danger rounded-pill" onClick={handleUnassignUserClick(user.id)}>
                                <i className="fas fa-trash"></i>
                            </button>
                        </div>
                    </div>
                ))}
            </div>
        </Container>
        <AssignUserModal show={showAssignModal} setShow={setShowAssignModal} taskId={Number(id)}
            assignedUsers={task.users || []} onAssign={() => fetchTask()} />
    </>);
}

export default TaskDetailPage;