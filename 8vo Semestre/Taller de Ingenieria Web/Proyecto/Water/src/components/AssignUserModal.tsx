import { Button, Dialog, DialogBody, DialogFooter, DialogHeader } from "@material-tailwind/react";
import { useEffect, useState } from "react";
import { faPlus, faX } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { User } from "../models/User";
import { UserService } from "../services/UserService";
import SearchBarComponent from "./common/SearchBarComponent";

type Props = {
    isOpen: boolean,
    onCancel: () => void,
    onConfirm: (assignedUserId: number) => void,
    assignedUsers: User[]
}

const AssignUserModal = ({ isOpen, onCancel, onConfirm, assignedUsers }: Props) => {
    const [availableUsers, setAvailableUsers] = useState<User[]>([]);
    const [selectedUserId, setSelectedUserId] = useState(0);
    const [searchTerm, setSearchTerm] = useState("");
    const [isSaving, setIsSaving] = useState(false);

    const selectedUser = availableUsers.find((contacto) => contacto.id === selectedUserId);

    useEffect(() => {
        if (!isOpen) {
            handleClose();
            return;
        }
        fetchUsers();
    }, [isOpen]);

    const fetchUsers = () => {
        UserService.getTecnicos().then((users) => {
            setAvailableUsers(filterUsers(users));
        });
    }

    const filterUsers = (contactos: User[]) => {
        return contactos.filter((user) => {
            return !assignedUsers.some((c) => c.id === user.id);
        });
    }

    const handleClose = () => {
        setSelectedUserId(0);
        setSearchTerm("");
        setAvailableUsers([]);
        setIsSaving(false);
    }

    const handleConfirm = () => {
        if (!selectedUserId) return;
        setIsSaving(true);
        onConfirm(selectedUserId);
    }

    return (<Dialog open={isOpen} handler={onCancel} className="border">
        <DialogHeader className="text-blue-500 border-0 border-b">Asignar Usuarios</DialogHeader>
        <DialogBody>
            <SearchBarComponent setSearchTerm={setSearchTerm} containerClassNames="mx-auto mb-4" />
            <div className="max-h-[40vh] overflow-y-auto scroll-container">
                {availableUsers.map((user) => (
                    (user.name.toLowerCase().includes(searchTerm) ||
                        user.email.toLowerCase().includes(searchTerm)) &&
                    <div key={user.id} className="mb-3 border-b-2 border-blue-500 flex items-center justify-between">
                        <p className="text-gray-500">
                            {user.name} | {user.email}
                        </p>
                        {selectedUserId === user.id ?
                            <Button variant="text" color="white" onClick={() => setSelectedUserId(0)} className="p-0">
                                <FontAwesomeIcon color="red" icon={faX} className="h-4 w-4 p-2 m-0" />
                            </Button> :
                            <Button variant="text" color="white" onClick={() => setSelectedUserId(user.id!)} className="p-0">
                                <FontAwesomeIcon color="blue" icon={faPlus} className="h-4 w-4 p-2 m-0" />
                            </Button>}
                    </div>
                ))}
            </div>
        </DialogBody>
        <DialogFooter className="border-0 border-t">
            <Button variant="outlined" onClick={onCancel} className="mr-1">
                Cancelar
            </Button>
            <Button color="blue" onClick={handleConfirm} disabled={!selectedUser || isSaving}>
                {isSaving ? "Guardando..." : "Confirmar"}
            </Button>
        </DialogFooter>
    </Dialog>);
}

export default AssignUserModal;