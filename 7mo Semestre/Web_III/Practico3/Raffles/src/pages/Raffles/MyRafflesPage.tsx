import { useEffect, useState } from "react";
import NavbarComponent from "../../Components/NavbarComponent";
import { Raffle } from "../../models/objects/Raffle";
import { RaffleService } from "../../services/RaffleService";
import { Button, Typography } from "@material-tailwind/react";
import { useNavigate } from "react-router-dom";
import { Routes } from "../../routes/CONSTANTS";
import AddIcon from "../../Components/svgs/AddIcon";
import DeleteIcon from "../../Components/svgs/DeleteIcon";
import DeleteModal from "../../Components/modals/DeleteModal";
import { getStatusDisplay } from "../../utilities/RaffleUtils";
import { UserService } from "../../services/UserService";
import useUserInfo from "../../hooks/useUserInfo";

const MyRafflesPage = () => {
    const [raffles, setRaffles] = useState<Raffle[]>([]);
    const navigate = useNavigate();
    const [openDeleteModal, setOpenDeleteModal] = useState(false);
    const [idForDelete, setIdForDelete] = useState(0);
    const { userId } = useUserInfo()

    const fetchRaffles = () => {
        UserService.createdRaffles(userId).then((raffles) => {
            setRaffles(raffles);
        })
    }

    const deleteRaffle = () => {
        RaffleService.delete(idForDelete).then(() => {
            setOpenDeleteModal(!openDeleteModal);
            fetchRaffles();
        }).catch((error) => {
            console.error(error)
        })
    }

    const handleDeleteClick = (raffleId: number) => {
        setIdForDelete(raffleId);
        setOpenDeleteModal(true);
    }

    useEffect(() => {
        if (!userId) return
        fetchRaffles()
    }, [userId])

    return (<>
        <NavbarComponent />
        <div className="p-5 container mx-auto">
            <div className="flex items-center">
                <Typography color="white" variant="h1">
                    Mis Rifas
                </Typography>
                <Button className="ml-3 capitalize h-fit w-fit p-3 rounded-full"
                    size="md" color="light-blue"
                    onClick={() => navigate(Routes.RAFFLES.CREATE)}>
                    <AddIcon width={20} height={20} />
                </Button>
            </div>
            <div className="flex flex-wrap gap-5 justify-center">
                {raffles.length === 0 &&
                    <Typography color="white" className="mt-4 text-center font-normal text-blue-gray-200">
                        No tienes rifas
                    </Typography>}
                {raffles.map((raffle) => {
                    return <div key={raffle.id} className="bg-cyan-900 p-4 rounded-lg mt-4 w-[250px] border border-white flex flex-col">
                        <div className="flex justify-between">
                            <Typography color="white" className="text-xl font-bold text-light-blue-400">
                                {raffle.name}
                            </Typography>
                            <Button className="bg-red-900 w-fit h-fit p-2 rounded-full"
                                onClick={() => handleDeleteClick(raffle.id!)}>
                                <DeleteIcon width={25} height={25} className="cursor-pointer text-white" />
                            </Button>
                        </div>
                        <Typography color="white" className="text-sm mt-2">
                            <span className="font-bold text-light-blue-400">Cantidad de tickets: </span>
                            {raffle.ticket_amount}
                        </Typography>
                        <Typography color="white" className="text-sm">
                            <span className="font-bold text-light-blue-400">Estado: </span>
                            {getStatusDisplay(raffle.status)}
                        </Typography>
                        <Button className="bg-dark-blue mt-4 mx-auto"
                            onClick={() => navigate(Routes.RAFFLES.DETAIL_PARAM(raffle.id))}>
                            Ver rifa
                        </Button>
                    </div>
                })}
            </div>
        </div>
        <DeleteModal isOpen={openDeleteModal} handleOpen={()=> setOpenDeleteModal(!openDeleteModal)} 
            onConfirm={() => deleteRaffle()} title="Eliminar Rifa" 
            message="¿Desea eliminar esta rifa?"/>
    </>);
}

export default MyRafflesPage;