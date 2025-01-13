import { useNavigate, useParams } from "react-router-dom";
import NavbarComponent from "../../Components/NavbarComponent";
import { Button, Card, Input, Typography } from "@material-tailwind/react";
import { useEffect, useState } from "react";
import { RaffleService } from "../../services/RaffleService";
import { Routes } from "../../routes/CONSTANTS";
import { RaffleSaveRequest } from "../../models/requests/RaffleSaveRequest";
import useUserInfo from "../../hooks/useUserInfo";

const RaffleFormPage = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const [error, setError] = useState("");
    const [name, setName] = useState("");
    const [ticketAmount, setTicketAmount] = useState("");
    const [ticketCode, setTicketCode] = useState("");
    const { userId } = useUserInfo()

    const fetchRaffle = () => {
        if (!id) return;
        RaffleService.getById(parseInt(id)).then((raffle) => {
            setName(raffle.name);
            setTicketAmount(raffle.ticket_amount + "");
            setTicketCode(raffle.ticket_code);
        });
    }

    const createRaffle = (request: RaffleSaveRequest) => {
        RaffleService.create(request).then(() => {
            navigate(Routes.RAFFLES.MY_RAFFLES);
        }).catch(() => {
            setError("Ocurrió un error al crear la rifa");
        });
    }

    const updateRaffle = (request: RaffleSaveRequest) => {
        RaffleService.update(request).then(() => {
            navigate(Routes.RAFFLES.DETAIL_PARAM(parseInt(id!)));
        }).catch(() => {
            setError("Ocurrió un error al crear la rifa");
        });
    }

    const onFormSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        setError("");
        const request: RaffleSaveRequest = {
            name: name,
            ticket_amount: parseInt(ticketAmount),
            ticket_code: ticketCode,
            created_by_id: userId
        }
        if (!id) {
            createRaffle(request);
        } else {
            request.id = parseInt(id);
            updateRaffle(request);
        }
    }

    useEffect(() => {
        fetchRaffle();
    }, []);

    return (<>
        <NavbarComponent />
        <Card color="gray" shadow={false} className="mx-auto p-6 mt-5 w-fit">
            <Typography variant="h4" color="white">
                {id ? "Editar Rifa" : "Crear Rifa"}
            </Typography>
            <form className="mt-4 mb-2 w-80 max-w-screen-lg sm:w-96" onSubmit={(e) => onFormSubmit(e)}>
                <div className="mb-1 flex flex-col gap-6">
                    <Typography variant="h6" color="red" className="-mb-3">
                        {error}
                    </Typography>
                    <Input
                        type="text" size="lg" color="white" label="Nombre de la rifa"
                        placeholder="Escribe el nombre de la rifa" value={name}
                        crossOrigin={undefined} required
                        onChange={(e) => setName(e.target.value)} />
                    <Input
                        type="number" size="lg" color="white" label="Número de tickets"
                        placeholder="Escribe el número de tickets" value={ticketAmount}
                        crossOrigin={undefined} required min={1}
                        onChange={(e) => {
                            setTicketAmount(e.target.value);
                        }} />
                    <Input
                        type="text" size="lg" color="white" label="Código de los tickets"
                        placeholder="Escribe el código de lis tickets" value={ticketCode}
                        crossOrigin={undefined} required
                        onChange={(e) => setTicketCode(e.target.value)} />
                </div>
                <Button className="mt-6 bg-dark-blue" fullWidth type="submit">
                    Guardar
                </Button>
            </form>
        </Card>
    </>);
}

export default RaffleFormPage;