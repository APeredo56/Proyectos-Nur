import { useEffect, useState } from "react";
import { Card, CardBody, CardHeader, Typography } from "@material-tailwind/react";
import { Point } from "../../models/Point";
import { PointService } from "../../services/PointService";

const TABLE_HEAD = ["Nombre", "Nivel", "Puntos Totales"];

const UserRankingPage = () => {
    const [ranking, setRanking] = useState<Point[]>([]);

    useEffect(() => {
        fetchRanking();
    }, []);

    const fetchRanking = () => {
        PointService.ranking().then((ranking) => {
            setRanking(ranking);
        });
    }

    return (<>
        <Card className="w-full my-8 !shadow-full-sm max-h-[75vh]">
            <CardHeader className="rounded-none p-4 m-0 flex flex-col justify-between md:flex-row md:items-center"
                floated={false} shadow={false}>
                <Typography color="blue" as="h1" className="text-xl font-bold">Ranking de Usuarios</Typography>
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
                        {ranking.map((points) => (
                            <tr key={points.id} className="even:bg-blue-gray-50/50">
                                <td className="p-2">
                                    <Typography variant="small" color="blue-gray" className="font-normal">
                                        {points.user?.name}
                                    </Typography>
                                </td>
                                <td className="p-2">
                                    <Typography variant="small" color="blue-gray" className="font-normal">
                                        {points.level}
                                    </Typography>
                                </td>
                                <td className="p-2">
                                    <Typography variant="small" color="blue-gray" className="font-normal">
                                        {points.total_points}
                                    </Typography>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </CardBody>
        </Card>
    </>);
}

export default UserRankingPage;