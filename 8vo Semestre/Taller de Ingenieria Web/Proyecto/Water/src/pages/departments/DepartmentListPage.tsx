import { useEffect, useState } from "react";
import { Button, Card, CardBody, CardHeader, Typography } from "@material-tailwind/react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPen, faTrash } from "@fortawesome/free-solid-svg-icons";
import { useNavigate } from "react-router-dom";
import { Department } from "../../models/Department";
import { DepartmentService } from "../../services/DeparmentService";
import SearchBarComponent from "../../components/common/SearchBarComponent";
import { Routes } from "../../routes/CONSTANTS";
import DeleteConfirmModal from "../../components/common/DeleteConfirmModal";

const TABLE_HEAD = ["Nombre", "", " "];

const DepartmentListPage = () => {
    const [departments, setDepartments] = useState<Department[]>([]);
    const [searchResults, setSearchResults] = useState<Department[]>([]);
    const [searchTerm, setSearchTerm] = useState("");
    const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false);
    const [deleteId, setDeleteId] = useState(0);
    const navigate = useNavigate();

    useEffect(() => {
        setSearchTerm("");
        fetchDepartments();
    }, []);

    const fetchDepartments = () => {
        DepartmentService.list().then((departments) => {
            setDepartments(departments);
            setSearchResults(departments);
        });
    }

    useEffect(() => {
        if (searchTerm === "") {
            setSearchResults(departments);
            return;
        }
        const results = departments.filter((department) =>
            department.name.toLowerCase().includes(searchTerm.toLowerCase())
        );
        setSearchResults(results);
    }, [departments, searchTerm]);

    const handleDelete = (id: number) => {
        setIsDeleteModalOpen(true);
        setDeleteId(id);
    }

    const confirmDelete = () => {
        DepartmentService.delete(deleteId).then(() => {
            setIsDeleteModalOpen(false);
            setDeleteId(0);
            fetchDepartments();
        });
    }

    const cancelDelete = () => {
        setIsDeleteModalOpen(false);
        setDeleteId(0);
    }

    return (<>
        <Card className="w-full my-8 !shadow-full-sm max-h-[75vh]">
            <CardHeader className="rounded-none p-4 m-0 flex flex-col justify-between md:flex-row md:items-center"
                floated={false} shadow={false}>
                <Typography color="blue" as="h1" className="text-xl font-bold">Gestionar Departamentos</Typography>
                <div className="flex flex-wrap sm:flex-nowrap w-full shrink-0 gap-2 md:w-max">
                    <SearchBarComponent setSearchTerm={setSearchTerm} />
                    <Button color="blue" onClick={() => navigate(Routes.DEPARTMENT.CREATE)}>
                        Agregar
                    </Button>
                </div>
            </CardHeader>
            <CardBody className="p-0 overflow-y-auto scroll-container">
                <table className="w-full min-w-max table-auto text-left">
                    <thead>
                        <tr>
                            {TABLE_HEAD.map((head) => (
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
                        {searchResults.map((department) => (
                            <tr key={department.id} className="even:bg-blue-gray-50/50">
                                <td className="p-2">
                                    <Typography variant="small" color="blue-gray" className="font-normal">
                                        {department.name}
                                    </Typography>
                                </td>
                                <td className="p-2">
                                    <Typography as="a" href={Routes.DEPARTMENT.EDIT_PARAM(department.id)} variant="small" color="white">
                                        <FontAwesomeIcon icon={faPen} className="h-4 w-4 rounded-full bg-blue-500 p-2" />
                                    </Typography>
                                </td>
                                <td className="p-2">
                                    <Button variant="text" color="white" onClick={() => handleDelete(department.id!)}>
                                        <FontAwesomeIcon icon={faTrash} className="h-4 w-4 rounded-full bg-red-500 p-2" />
                                    </Button>
                                </td>
                            </tr>
                        ))}
                        {searchResults.length === 0 && (
                            <tr>
                                <td colSpan={5} className="p-4">
                                    <Typography color="blue-gray" className="font-normal">
                                        No se encontraron resultados
                                    </Typography>
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </CardBody>
        </Card>
        <DeleteConfirmModal isOpen={isDeleteModalOpen} onCancel={cancelDelete}
            onConfirm={confirmDelete} title="Eliminar Departamento"
            message="¿Está seguro que desea eliminar el departamento?" />
    </>);
}

export default DepartmentListPage;