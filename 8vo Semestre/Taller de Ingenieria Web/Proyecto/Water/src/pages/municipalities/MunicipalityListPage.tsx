import { useEffect, useState } from "react";
import { Button, Card, CardBody, CardHeader, Typography } from "@material-tailwind/react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPen, faTrash } from "@fortawesome/free-solid-svg-icons";
import { useNavigate } from "react-router-dom";
import SearchBarComponent from "../../components/common/SearchBarComponent";
import { Routes } from "../../routes/CONSTANTS";
import DeleteConfirmModal from "../../components/common/DeleteConfirmModal";
import { Municipality } from "../../models/Municipality";
import { MunicipalityService } from "../../services/MunicipalityService";

const TABLE_HEAD = ["Nombre", "Departamento","", " "];

const MunicipalityListPage = () => {
    const [municipalities, setMunicipalities] = useState<Municipality[]>([]);
    const [searchResults, setSearchResults] = useState<Municipality[]>([]);
    const [searchTerm, setSearchTerm] = useState("");
    const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false);
    const [deleteId, setDeleteId] = useState(0);
    const navigate = useNavigate();

    useEffect(() => {
        setSearchTerm("");
        fetchMunicipalities();
    }, []);

    const fetchMunicipalities = () => {
        MunicipalityService.list().then((municipalities) => {
            setMunicipalities(municipalities);
            setSearchResults(municipalities);
        });
    }

    useEffect(() => {
        if (searchTerm === "") {
            setSearchResults(municipalities);
            return;
        }
        const results = municipalities.filter((municipality) =>
            municipality.name.toLowerCase().includes(searchTerm.toLowerCase())
        );
        setSearchResults(results);
    }, [municipalities, searchTerm]);

    const handleDelete = (id: number) => {
        setIsDeleteModalOpen(true);
        setDeleteId(id);
    }

    const confirmDelete = () => {
        MunicipalityService.delete(deleteId).then(() => {
            setIsDeleteModalOpen(false);
            setDeleteId(0);
            fetchMunicipalities();
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
                <Typography color="blue" as="h1" className="text-xl font-bold">Gestionar Municipios</Typography>
                <div className="flex flex-wrap sm:flex-nowrap w-full shrink-0 gap-2 md:w-max">
                    <SearchBarComponent setSearchTerm={setSearchTerm} />
                    <Button color="blue" onClick={() => navigate(Routes.MUNICIPALITY.CREATE)}>
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
                        {searchResults.map((municipality) => (
                            <tr key={municipality.id} className="even:bg-blue-gray-50/50">
                                <td className="p-2">
                                    <Typography variant="small" color="blue-gray" className="font-normal">
                                        {municipality.name}
                                    </Typography>
                                </td>
                                <td className="p-2">
                                    <Typography variant="small" color="blue-gray" className="font-normal">
                                        {municipality.department?.name}
                                    </Typography>
                                </td>
                                <td className="p-2">
                                    <Typography as="a" href={Routes.MUNICIPALITY.EDIT_PARAM(municipality.id)} variant="small" color="white">
                                        <FontAwesomeIcon icon={faPen} className="h-4 w-4 rounded-full bg-blue-500 p-2" />
                                    </Typography>
                                </td>
                                <td className="p-2">
                                    <Button variant="text" color="white" onClick={() => handleDelete(municipality.id!)}>
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
            onConfirm={confirmDelete} title="Eliminar Municipio"
            message="¿Está seguro que desea eliminar el municipio?" />
    </>);
}

export default MunicipalityListPage;