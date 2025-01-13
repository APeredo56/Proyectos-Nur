import { Button, Typography } from "@material-tailwind/react";
import NavbarComponent from "../../Components/NavbarComponent";
import { useEffect, useState } from "react";
import { Raffle } from "../../models/objects/Raffle";
import { RaffleService } from "../../services/RaffleService";
import { useNavigate } from "react-router-dom";
import { Routes } from "../../routes/CONSTANTS";
import useUserInfo from "../../hooks/useUserInfo";

const HomePage = () => {
    const [raffles, setRaffles] = useState<Raffle[]>([])
    const navigate = useNavigate()
    const { userId } = useUserInfo()

    const fetchRaffles = () => {
        if (!userId) return
        RaffleService.actives(userId).then((raffles) => {
            setRaffles(raffles)
        }).catch((error) => {
            console.error(error)
        })
    }

    const joinRaffle = (raffleId: number) => {
        if (!userId) return
        RaffleService.participate(raffleId, userId).then((raffle) => {
            const updatedRaffles = raffles.map((oldRaffle) => oldRaffle.id === raffle.id ? raffle : oldRaffle)
            setRaffles(updatedRaffles);
        }).catch((error) => {
            console.error(error)
        })
    }

    useEffect(() => {
        fetchRaffles()
    }, [userId])

    return (<>
        <NavbarComponent />
        <div className="container mx-auto p-5">
            <Typography color="white" variant="h1">
                Rifas Disponibles
            </Typography>
            <div className="flex flex-wrap gap-5 justify-center">
                {raffles.length === 0 && <Typography color="white" className="text-xl mt-4">
                    No hay rifas disponibles
                </Typography>}
                {raffles.map((raffle) => {
                    return <div key={raffle.id} className="bg-cyan-900 p-4 rounded-lg mt-4 w-[250px] border border-white">
                        <Typography color="white" className="text-xl font-bold text-light-blue-400">
                            {raffle.name}
                        </Typography>
                        <Typography color="white" className="text-sm mt-2">
                            <span className="font-bold text-light-blue-400">Cantidad de tickets: </span>
                            {raffle.ticket_amount}
                        </Typography>
                        <div className="flex justify-between">
                            <Button className="bg-dark-blue mt-4 mx-auto" size="sm"
                                onClick={() => navigate(Routes.RAFFLES.DETAIL_PARAM(raffle.id))}>
                                Ver rifa
                            </Button>
                            <Button className="bg-dark-blue mt-4 mx-auto" size="sm"
                                onClick={() => {
                                    if (raffle.users.find((user) => user.id === userId)) return
                                    joinRaffle(raffle.id!)
                                }}>
                                {raffle.users.find((user) => user.id === userId) ? 'Participando' : 'Participar'}
                            </Button>
                        </div>
                    </div>
                })}
            </div>
        </div>
    </>);
}

export default HomePage;    