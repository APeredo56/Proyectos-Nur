import { useEffect, useState } from "react";
import { Button, Card, CardBody, CardHeader, Typography } from "@material-tailwind/react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPen, faTrash } from "@fortawesome/free-solid-svg-icons";
import { useNavigate } from "react-router-dom";
import SearchBarComponent from "../../components/common/SearchBarComponent";
import { Routes } from "../../routes/CONSTANTS";
import DeleteConfirmModal from "../../components/common/DeleteConfirmModal";
import { WaterBody } from "../../models/WaterBody";
import { WaterBodyService } from "../../services/WaterBodyService";

const TABLE_HEAD = ["Nombre", "Tipo", "Comunidad","", " "];

const WaterBodyListPage = () => {
    const [waterBodies, setWaterBodies] = useState<WaterBody[]>([]);
    const [searchResults, setSearchResults] = useState<WaterBody[]>([]);
    const [searchTerm, setSearchTerm] = useState("");
    const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false);
    const [deleteId, setDeleteId] = useState(0);
    const navigate = useNavigate();

    useEffect(() => {
        setSearchTerm("");
        fetchWaterBodies();
    }, []);

    const fetchWaterBodies = () => {
        WaterBodyService.list().then((waterBodies) => {
            setWaterBodies(waterBodies);
            setSearchResults(waterBodies);
        });
    }

    useEffect(() => {
        if (searchTerm === "") {
            setSearchResults(waterBodies);
            return;
        }
        const results = waterBodies.filter((waterBody) =>
            waterBody.name.toLowerCase().includes(searchTerm.toLowerCase())
        );
        setSearchResults(results);
    }, [waterBodies, searchTerm]);

    const handleDelete = (id: number) => {
        setIsDeleteModalOpen(true);
        setDeleteId(id);
    }

    const confirmDelete = () => {
        WaterBodyService.delete(deleteId).then(() => {
            setIsDeleteModalOpen(false);
            setDeleteId(0);
            fetchWaterBodies();
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
                <Typography color="blue" as="h1" className="text-xl font-bold">Gestionar Cuerpos de Agua</Typography>
                <div className="flex flex-wrap sm:flex-nowrap w-full shrink-0 gap-2 md:w-max">
                    <SearchBarComponent setSearchTerm={setSearchTerm} />
                    <Button color="blue" onClick={() => navigate(Routes.WATERBODY.CREATE)}>
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
                        {searchResults.map((waterBody) => (
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
                                    <Typography as="a" href={Routes.WATERBODY.EDIT_PARAM(waterBody.id)} variant="small" color="white">
                                        <FontAwesomeIcon icon={faPen} className="h-4 w-4 rounded-full bg-blue-500 p-2" />
                                    </Typography>
                                </td>
                                <td className="p-2">
                                    <Button variant="text" color="white" onClick={() => handleDelete(waterBody.id!)}>
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
            onConfirm={confirmDelete} title="Eliminar Cuerpo de Agua"
            message="¿Está seguro que desea eliminar el cuerpo de agua?" />
    </>);
}

export default WaterBodyListPage;