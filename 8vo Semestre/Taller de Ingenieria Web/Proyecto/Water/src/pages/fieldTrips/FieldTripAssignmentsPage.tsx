import { useEffect, useState } from "react";
import { Button, Card, CardBody, CardHeader, Typography } from "@material-tailwind/react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrash } from "@fortawesome/free-solid-svg-icons";
import { useParams } from "react-router-dom";
import DeleteConfirmModal from "../../components/common/DeleteConfirmModal";
import { FieldTrip } from "../../models/FieldTrip";
import { FieldTripService } from "../../services/FieldTripService";
import AssignUserModal from "../../components/AssignUserModal";
import AssignWaterBodyModal from "../../components/AssignWaterBodyModal";

const USER_TABLE_HEAD = ["Nombre", "Correo", ""];
const WATER_BODY_TABLE_HEAD = ["Nombre", "Tipo", "Comunidad", ""];

const FieldTripAssignmentsPage = () => {
    const [fieldTrip, setFieldTrip] = useState<FieldTrip>();
    const [isDeleteUserModalOpen, setIsDeleteUserModalOpen] = useState(false);
    const [isDeleteWaterBodyModalOpen, setIsDeleteWaterBodyModalOpen] = useState(false);
    const [deleteUserId, setDeleteUserId] = useState(0);
    const [deleteWaterBodyId, setDeleteWaterBodyId] = useState(0);
    const [isAssignUserModalOpen, setIsAssignUserModalOpen] = useState(false);
    const [isAssignWaterBodyModalOpen, setIsAssignWaterBodyModalOpen] = useState(false);
    const { id } = useParams();

    useEffect(() => {
        fetchFieldTrip();
    }, []);

    const fetchFieldTrip = () => {
        if (!id) return
        FieldTripService.get(parseInt(id)).then((fieldTrip) => {
            setFieldTrip(fieldTrip);
        });
    }

    const handleDeleteUser = (id: number) => {
        setIsDeleteUserModalOpen(true);
        setDeleteUserId(id);
    }

    const confirmDeleteUser = () => {
        if (!id) return;
        FieldTripService.unassignUser(parseInt(id), deleteUserId).then(() => {
            setIsDeleteUserModalOpen(false);
            setDeleteUserId(0);
            fetchFieldTrip();
        });
    }

    const cancelDeleteUser = () => {
        setIsDeleteUserModalOpen(false);
        setDeleteUserId(0);
    }

    const handleDeleteWaterBody = (id: number) => {
        setIsDeleteWaterBodyModalOpen(true);
        setDeleteWaterBodyId(id);
    }

    const confirmDeleteWaterBody = () => {
        if (!id) return;
        FieldTripService.unassignWaterBody(parseInt(id), deleteWaterBodyId).then(() => {
            setIsDeleteWaterBodyModalOpen(false);
            setDeleteWaterBodyId(0);
            fetchFieldTrip();
        });
    }

    const cancelDeleteWaterBody = () => {
        setIsDeleteWaterBodyModalOpen(false);
        setDeleteWaterBodyId(0);
    }

    const handleConfirmAssignUser = (assignedUserId: number) => {
        if (!id) return;
        FieldTripService.assignUser(parseInt(id), assignedUserId).then(() => {
            setIsAssignUserModalOpen(false);
            fetchFieldTrip();
        });
    }

    const handleCancelAssignUser = () => {
        setIsAssignUserModalOpen(false);
    }

    const handleConfirmAssignWaterBody = (assignedWaterBodyId: number) => {
        if (!id) return;
        FieldTripService.assignWaterBody(parseInt(id), assignedWaterBodyId).then(() => {
            setIsAssignWaterBodyModalOpen(false);
            fetchFieldTrip();
        });
    }

    const handleCancelAssignWaterBody = () => {
        setIsAssignWaterBodyModalOpen(false);
    }

    return (<>
        <Typography color="blue" as="h1" className="text-xl font-bold">Gestionar salida de campo</Typography>
        <div className="grid grid-cols-1 md:grid-cols-2 gap-5">
            <Card className="w-full my-8 !shadow-full-sm max-h-[75vh]">
                <CardHeader className="rounded-none p-4 m-0 flex flex-col justify-between md:flex-row md:items-center"
                    floated={false} shadow={false}>
                    <Typography color="blue" as="h1" className="text-xl font-bold">Usuarios</Typography>
                    <Button color="blue" onClick={() => setIsAssignUserModalOpen(true)}>
                        Agregar
                    </Button>
                </CardHeader>
                <CardBody className="p-0 overflow-y-auto scroll-container">
                    <table className="w-full min-w-max table-auto text-left">
                        <thead>
                            <tr>
                                {USER_TABLE_HEAD.map((head) => (
                                    <th key={head} className="border-b border-blue-gray-100 bg-blue-gray-50 p-4">
                                        <Typography variant="small" color="blue-gray"
                                            className="font-normal leading-none opacity-70">
                                            {head}
                                        </Typography>
                                    </th>
                                ))}
                            </tr>
                        </thead>
                        <tbody>
                            {fieldTrip?.users?.map((user) => (
                                <tr key={user.id} className="even:bg-blue-gray-50/50">
                                    <td className="p-2">
                                        <Typography variant="small" color="blue-gray" className="font-normal">
                                            {user.name}
                                        </Typography>
                                    </td>
                                    <td className="p-2">
                                        <Typography variant="small" color="blue-gray" className="font-normal">
                                            {user.email}
                                        </Typography>
                                    </td>
                                    <td className="p-2">
                                        <Button variant="text" color="white" onClick={() => handleDeleteUser(user.id!)}>
                                            <FontAwesomeIcon icon={faTrash} className="h-4 w-4 rounded-full bg-red-500 p-2" />
                                        </Button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </CardBody>
            </Card>

            <Card className="w-full my-8 !shadow-full-sm max-h-[75vh]">
                <CardHeader className="rounded-none p-4 m-0 flex flex-col justify-between md:flex-row md:items-center"
                    floated={false} shadow={false}>
                    <Typography color="blue" as="h1" className="text-xl font-bold">Cuerpos de Agua</Typography>
                    <Button color="blue" onClick={() => setIsAssignWaterBodyModalOpen(true)}>
                        Agregar
                    </Button>
                </CardHeader>
                <CardBody className="p-0 overflow-y-auto scroll-container">
                    <table className="w-full min-w-max table-auto text-left">
                        <thead>
                            <tr>
                                {WATER_BODY_TABLE_HEAD.map((head) => (
                                    <th key={head} className="border-b border-blue-gray-100 bg-blue-gray-50 p-4">
                                        <Typography variant="small" color="blue-gray"
                                            className="font-normal leading-none opacity-70">
                                            {head}
                                        </Typography>
                                    </th>
                                ))}
                            </tr>
                        </thead>
                        <tbody>
                            {fieldTrip?.water_bodies?.map((waterBody) => (
                                <tr key={waterBody.id} className="even:bg-blue-gray-50/50">
                                    <td className="p-2">
                                        <Typography variant="small" color="blue-gray" className="font-normal">
                                            {waterBody.name}
                                        </Typography>
                                    </td>
                                    <td className="p-2">
                                        <Typography variant="small" color="blue-gray" className="font-normal">
                                            {waterBody.water_body_type}
                                        </Typography>
                                    </td>
                                    <td className="p-2">
                                        <Typography variant="small" color="blue-gray" className="font-normal">
                                            {waterBody.community?.name}
                                        </Typography>
                                    </td>
                                    <td className="p-2">
                                        <Button variant="text" color="white" onClick={() => handleDeleteWaterBody(waterBody.id!)}>
                                            <FontAwesomeIcon icon={faTrash} className="h-4 w-4 rounded-full bg-red-500 p-2" />
                                        </Button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </CardBody>
            </Card>
        </div>

        <DeleteConfirmModal isOpen={isDeleteUserModalOpen} onCancel={cancelDeleteUser}
            onConfirm={confirmDeleteUser} title="Desasignar usuario"
            message="¿Está seguro que desea desasignar el usuario?" />
        <DeleteConfirmModal isOpen={isDeleteWaterBodyModalOpen} onCancel={cancelDeleteWaterBody}
            onConfirm={confirmDeleteWaterBody} title="Desasignar cuerpo de agua"
            message="¿Está seguro que desea desasignar el cuerpo de agua?" />
        <AssignUserModal isOpen={isAssignUserModalOpen} assignedUsers={fieldTrip?.users ?? []}
            onCancel={handleCancelAssignUser} onConfirm={handleConfirmAssignUser} />
        <AssignWaterBodyModal isOpen={isAssignWaterBodyModalOpen} assignedWaterBodies={fieldTrip?.water_bodies ?? []}
            onCancel={handleCancelAssignWaterBody} onConfirm={handleConfirmAssignWaterBody} />
    </>);
}

export default FieldTripAssignmentsPage;