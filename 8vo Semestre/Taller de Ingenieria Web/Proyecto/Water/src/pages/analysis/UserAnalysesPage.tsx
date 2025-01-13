import { useEffect, useState } from "react";
import { Card, CardBody, CardHeader, Typography } from "@material-tailwind/react";
import { Routes } from "../../routes/CONSTANTS";
import { useUser } from "../../contexts/user/useUser";
import { Link, useParams } from "react-router-dom";
import { SamplingAnalysis } from "../../models/SamplingAnalysis";
import { SamplingAnalysisService } from "../../services/SamplingAnalysisService";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPen } from "@fortawesome/free-solid-svg-icons";

const TABLE_HEAD = ["Turbidez", "Velocidad del Agua", "Ancho", "Profundidad Promedio", "Caudal", "Cuerpo de Agua", ""];

const UserAnalysesPage = () => {
    const [analyses, setAnalyses] = useState<SamplingAnalysis[]>([]);
    const { user } = useUser();
    const { fieldTripId } = useParams();

    useEffect(() => {
        fetchFieldTrips();
    }, [user]);

    const fetchFieldTrips = () => {
        if (!user || !fieldTripId) return;
        SamplingAnalysisService.getByFieldTripAndUser(parseInt(fieldTripId), user.id!).then((analyses) => {
            setAnalyses(analyses);
        });
    }

    return (<Card className="w-full my-8 !shadow-full-sm max-h-[75vh]">
        <CardHeader className="rounded-none p-4 m-0 flex flex-col justify-between md:flex-row md:items-center"
            floated={false} shadow={false}>
            <Typography color="blue" as="h1" className="text-xl font-bold">Mis Muestreos</Typography>
            <Link to={{ pathname: Routes.ANALYSIS.CREATE, search: `?field_trip_id=${fieldTripId}` }}
                className="bg-blue-500 text-white rounded-full py-2 px-3">
                Agregar
            </Link>
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
                    {analyses.map((samplingAnalysis) => (
                        <tr key={samplingAnalysis.id} className="even:bg-blue-gray-50/50">
                            <td className="p-2">
                                <Typography variant="small" color="blue-gray" className="font-normal">
                                    {samplingAnalysis.turbidity}
                                </Typography>
                            </td>
                            <td className="p-2">
                                <Typography variant="small" color="blue-gray" className="font-normal">
                                    {samplingAnalysis.water_velocity}
                                </Typography>
                            </td>
                            <td className="p-2">
                                <Typography variant="small" color="blue-gray" className="font-normal">
                                    {samplingAnalysis.width}
                                </Typography>
                            </td>
                            <td className="p-2">
                                <Typography variant="small" color="blue-gray" className="font-normal">
                                    {samplingAnalysis.average_depth}
                                </Typography>
                            </td>
                            <td className="p-2">
                                <Typography variant="small" color="blue-gray" className="font-normal">
                                    {samplingAnalysis.flow_rate}
                                </Typography>
                            </td>
                            <td className="p-2">
                                <Typography variant="small" color="blue-gray" className="font-normal">
                                    {samplingAnalysis.water_body?.name}
                                </Typography>
                            </td>
                            <td className="p-2">
                                <Link to={{ pathname: Routes.ANALYSIS.EDIT_PARAM(samplingAnalysis.id), search: `?field_trip_id=${fieldTripId}` }}>
                                    <FontAwesomeIcon icon={faPen} className="h-4 w-4 rounded-full bg-blue-500 p-2 text-white" />
                                </Link>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </CardBody>
    </Card>);
}

export default UserAnalysesPage;