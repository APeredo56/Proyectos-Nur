import { Button, Typography } from "@material-tailwind/react";
import NavbarComponent from "../../Components/NavbarComponent";
import { useNavigate, useParams } from "react-router-dom";
import { Routes } from "../../routes/CONSTANTS";
import { useEffect, useState } from "react";
import { Raffle } from "../../models/objects/Raffle";
import { RaffleService } from "../../services/RaffleService";
import { getStatusDisplay } from "../../utilities/RaffleUtils";
import { ParticipatingUser } from "../../models/objects/ParticipatingUser";
import AddParticipantModal from "../../Components/modals/AddParticipantModal";
import WinnersModal from "../../Components/modals/WinnersModal";
import useUserInfo from "../../hooks/useUserInfo";

const RaffleDetailPage = () => {
    const navigate = useNavigate();
    const { id } = useParams();
    const [raffle, setRaffle] = useState<Raffle | undefined>();
    const [participatingUsers, setParticipatingUsers] = useState<ParticipatingUser[]>([]);
    const participatingUsersHeaders = ["Nombre", "Correo", "Teléfono", "Ticket"];
    const [isAddModalOpen, setIsAddModalOpen] = useState(false);
    const [isWinnersModalOpen, setIsWinnersModalOpen] = useState(false);
    const { userId } = useUserInfo()

    const fetchRaffle = () => {
        RaffleService.getById(parseInt(id!)).then((raffle) => {
            setRaffle(raffle);
        });
    }

    const fetchParticipatingUsers = () => {
        RaffleService.participating(parseInt(id!)).then((users) => {
            setParticipatingUsers(users);
        });
    }

    useEffect(() => {
        fetchRaffle();
        fetchParticipatingUsers();
    }, []);

    return (<>
        <NavbarComponent />
        <div className="p-5 container mx-auto">
            <div className="flex justify-between">
                <Typography color="white" variant="h1">
                    Detalle de la rifa
                </Typography>
                {userId === raffle?.created_by.id && <Button className="h-fit w-fit p-3 capitalize" size="lg" color="light-blue"
                    onClick={() => navigate(Routes.RAFFLES.EDIT_PARAM(parseInt(id!)))}>
                    Editar rifa
                </Button>}
            </div>
            <div className="mt-3">
                {raffle && <div className="flex justify-between">
                    <Typography color="white" variant="h2">
                        {raffle.name}
                    </Typography>
                    {raffle.status !== 0 && userId === raffle.created_by.id && <Button className="h-fit w-fit p-3 capitalize" size="md" color="light-blue"
                        onClick={() => navigate(Routes.RAFFLES.RAFFLE_DRAW_PARAM(raffle.id))}>
                        Sortear Rifa
                    </Button>}
                </div>}
                <table className="mt-3 w-full text-center">
                    <thead>
                        <tr>
                            <th className="w-1/3">
                                <Typography color="white" className="font-bold text-light-blue-400">
                                    Código de la rifa
                                </Typography>
                            </th>
                            <th className="w-1/3">
                                <Typography color="white" className="font-bold text-light-blue-400">
                                    Tickets
                                </Typography>
                            </th>
                            <th className="w-1/3">
                                <Typography color="white" className="font-bold text-light-blue-400">
                                    Estado
                                </Typography>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                {raffle && <Typography color="white">
                                    {raffle.ticket_code}
                                </Typography>}
                            </td>
                            <td>
                                {raffle && <Typography color="white">
                                    {raffle.ticket_amount - raffle.users.length + " / " + raffle.ticket_amount}
                                </Typography>}
                            </td>
                            <td>
                                {raffle && <Typography color="white">
                                    {getStatusDisplay(raffle.status)}
                                </Typography>}
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div className="mt-5">
                <div className="flex justify-between">
                    <Typography color="white" variant="h4">
                        Lista de participantes
                    </Typography>
                    {userId === raffle?.created_by.id && raffle?.status !== 0 &&
                        <Button className="h-fit w-fit p-3 capitalize" size="md" color="light-blue"
                            onClick={() => setIsAddModalOpen(true)}>
                            Agregar participantes
                        </Button>}
                    {raffle?.status !== 1 &&
                        <Button className="h-fit w-fit p-3 capitalize" size="md" color="light-blue"
                            onClick={() => setIsWinnersModalOpen(true)}>
                            Ver Ganadores
                        </Button>}
                </div>
                <div className="mt-5">
                    <table className="w-full min-w-max table-auto text-left">
                        <thead>
                            <tr className="border-b border-blue-gray-100">
                                {participatingUsersHeaders.map((head, index) => {
                                    if (head === "Teléfono" && userId !== raffle?.created_by.id) return null;
                                    return (
                                        <th key={head}
                                            className={(index !== participatingUsersHeaders.length - 1 ?
                                                'border-r' : '') + ' p-4'}>
                                            <Typography className="font-bold leading-none text-light-blue-400">
                                                {head}
                                            </Typography>
                                        </th>)
                                })}
                            </tr>
                        </thead>
                        <tbody>
                            {participatingUsers.map((participation, index) => (
                                <tr key={'user' + index}>
                                    <td className="border-r ps-4 pt-2">
                                        <Typography color="white">
                                            {participation.user.first_name + " " +
                                                participation.user.last_name}
                                        </Typography>
                                    </td>
                                    <td className="border-r ps-4 pt-2">
                                        <Typography color="white">
                                            {participation.user.email}
                                        </Typography>
                                    </td>
                                    {userId === raffle?.created_by.id &&
                                        <td className="border-r ps-4 pt-2">
                                            <Typography color="white">
                                                {participation.user.phone}
                                            </Typography>
                                        </td>}
                                    <td className="ps-4 pt-2">
                                        <Typography color="white">
                                            {participation.ticket_number}
                                        </Typography>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <AddParticipantModal isOpen={isAddModalOpen} onConfirm={(newParticipantsId) => {
            const requests = newParticipantsId.map((newId) =>
                RaffleService.participate(parseInt(id!), newId)
            );
            Promise.all(requests).then(() => {
                fetchParticipatingUsers();
                setIsAddModalOpen(false);
            });
        }} handleOpen={() => setIsAddModalOpen(false)} participants={participatingUsers}
            maxTickets={raffle?.ticket_amount??0} />
        <WinnersModal isOpen={isWinnersModalOpen} handleOpen={() => setIsWinnersModalOpen(false)}
            raffleId={parseInt(id!)} showPhone={userId === raffle?.created_by.id} />
    </>);
}

export default RaffleDetailPage;