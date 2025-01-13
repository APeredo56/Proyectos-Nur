import { useEffect, useState } from "react";
import { Button, Form, Modal, Table } from "react-bootstrap";
import { User } from "../models/User";
import { UserService } from "../services/UserService";
import { TaskService } from "../services/TaskService";

interface AssignUserModalProps {
    show: boolean;
    setShow: (show: boolean) => void;
    taskId: number;
    assignedUsers: User[];
    onAssign: () => void;
}

const AssignUserModal = ({ show, setShow, taskId, assignedUsers, onAssign }: AssignUserModalProps) => {
    const [selectedUserId, setSelectedUserId] = useState(0);
    const [users, setUsers] = useState<User[]>([]);

    useEffect(() => {
        if (!show) return;
        fetchUsers();
    }, [show]);

    const fetchUsers = () => {
        UserService.list().then(response => {
            const filteredUsers = response.filter(user => !assignedUsers.some(assignedUser => assignedUser.id === user.id));
            setUsers(filteredUsers);
        });
    }

    const handleConfirmClick = () => {
        TaskService.assignUser(taskId, selectedUserId).then(() => {
            setShow(false);
            onAssign();
            setSelectedUserId(0);
        });
    }

    return (<>
        <Modal show={show} centered onHide={() => setShow(false)}>
            <Modal.Header closeButton>
                <Modal.Title className="primary-color">Asignar un Usuario</Modal.Title>
            </Modal.Header>
            <Modal.Body className="primary-border border-start-0 border-end-0">
                <h5>Usuarios Disponibles</h5>
                <div>
                    <Table>
                        <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Apellido</th>
                                <th>Email</th>
                                <th>Seleccionado</th>
                            </tr>
                        </thead>
                        <tbody>
                            {users.map(user => (
                                <tr key={user.id}>
                                    <td>{user.name}</td>
                                    <td>{user.last_name}</td>
                                    <td>{user.email}</td>
                                    <td>
                                        <Form.Check type="switch" checked={selectedUserId===user.id}
                                            onChange={() => setSelectedUserId(user.id)} />
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </Table>
                </div>
            </Modal.Body>
            <Modal.Footer>
                <div className="d-flex">
                    <Button className="btn btn-primary" onClick={handleConfirmClick} disabled={selectedUserId===0}>
                        Agregar</Button>
                </div>
            </Modal.Footer>
        </Modal>
    </>);
}

export default AssignUserModal;