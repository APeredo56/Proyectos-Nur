import { useEffect, useState } from "react";
import NavbarComponent from "../../components/NavbarComponent";
import { User } from "../../models/User";
import { UserService } from "../../services/UserService";
import { Button, Container } from "react-bootstrap";
import { Routes } from "../../routes/CONSTANTS";

const UserListPage = () => {
    const [users, setUsers] = useState<User[]>([]);

    useEffect(() => {
        fetchTasks();
    }, []);

    const fetchTasks = () => {
        UserService.list().then(response => {
            setUsers(response);
        });
    }

    const handleTaskDeleteClick = (taskId: number) => () => {
        if(!confirm("¿Estás seguro de que deseas eliminar este usuario?")){
            return;
        }
        UserService.delete(taskId).then(() => {
            fetchTasks();
        });
    }

    return (<>
        <NavbarComponent />
        <Container>
            <div className="d-flex align-items-center">
                <h1 className="my-2 d-inline-block">Lista de Usuarios</h1>
                <a className="btn btn-primary bg-da rounded-pill ms-3 h-fit" href={Routes.USER.CREATE}>
                    <i className="fas fa-add fs-3"></i>
                </a>
            </div>
            <div className="gap-3">
                {users.map(user => (
                    <div key={user.id} className="card mt-3 mx-3 d-inline-block">
                        <div className="card-body">
                            <h5 className="card-title">{user.name + " " + user.last_name}</h5>
                            <p className="card-text">{user.email}</p>
                            <div className="flex">
                                <Button className="mt-1 btn btn-danger"
                                    onClick={handleTaskDeleteClick(user.id)}>Eliminar</Button>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </Container>
    </>);
}

export default UserListPage;