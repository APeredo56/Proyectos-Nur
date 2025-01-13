import { useEffect, useState } from "react";
import { Card, CardBody, CardHeader, Typography } from "@material-tailwind/react";
import SearchBarComponent from "../../components/common/SearchBarComponent";
import { Routes } from "../../routes/CONSTANTS";
import { FieldTrip } from "../../models/FieldTrip";
import { FieldTripService } from "../../services/FieldTripService";
import { useUser } from "../../contexts/user/useUser";

const TABLE_HEAD = ["Id", "Descripcion", "Fecha de Inicio", "Fecha de Fin", "Analisis",];

const OwnFieldTripsPage = () => {
    const [fieldTrips, setFieldTrips] = useState<FieldTrip[]>([]);
    const [searchResults, setSearchResults] = useState<FieldTrip[]>([]);
    const [searchTerm, setSearchTerm] = useState("");
    const { user } = useUser();

    useEffect(() => {
        setSearchTerm("");
        fetchFieldTrips();
    }, [user]);

    const fetchFieldTrips = () => {
        if (!user) return;
        FieldTripService.getByUser(user.id!).then((fieldTrips) => {
            setFieldTrips(fieldTrips);
            setSearchResults(fieldTrips);
        });
    }

    useEffect(() => {
        if (searchTerm === "") {
            setSearchResults(fieldTrips);
            return;
        }
        const results = fieldTrips.filter((fieldTrips) =>
            fieldTrips.description.toLowerCase().includes(searchTerm.toLowerCase())
        );
        setSearchResults(results);
    }, [fieldTrips, searchTerm]);

    const getFormattedDate = (date: string) => {
        const dateObj = new Date(date);
        return dateObj.toLocaleDateString();
    }

    return (<Card className="w-full my-8 !shadow-full-sm max-h-[75vh]">
        <CardHeader className="rounded-none p-4 m-0 flex flex-col justify-between md:flex-row md:items-center"
            floated={false} shadow={false}>
            <Typography color="blue" as="h1" className="text-xl font-bold">Mis Salidas de Campo</Typography>
            <SearchBarComponent setSearchTerm={setSearchTerm} />
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
                    {searchResults.map((fieldTrip) => (
                        <tr key={fieldTrip.id} className="even:bg-blue-gray-50/50">
                            <td className="p-2">
                                <Typography variant="small" color="blue-gray" className="font-normal">
                                    {fieldTrip.id}
                                </Typography>
                            </td>
                            <td className="p-2">
                                <Typography variant="small" color="blue-gray" className="font-normal">
                                    {fieldTrip.description}
                                </Typography>
                            </td>
                            <td className="p-2">
                                <Typography variant="small" color="blue-gray" className="font-normal">
                                    {getFormattedDate(fieldTrip.start_date)}
                                </Typography>
                            </td>
                            <td className="p-2">
                                <Typography variant="small" color="blue-gray" className="font-normal">
                                    {getFormattedDate(fieldTrip.end_date)}
                                </Typography>
                            </td>
                            <td className="p-2">
                                <Typography as="a" href={Routes.FIELDTRIP.ANALYSES_PARAM(fieldTrip.id)}
                                    color="white" className="bg-blue-500 rounded-full p-1 px-2 w-fit">
                                    Analisis
                                </Typography>
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
    </Card>);
}

export default OwnFieldTripsPage;