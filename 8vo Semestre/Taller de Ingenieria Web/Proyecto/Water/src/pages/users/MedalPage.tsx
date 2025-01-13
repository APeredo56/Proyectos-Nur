import { Typography } from "@material-tailwind/react";
import { useUser } from "../../contexts/user/useUser";
import { useEffect, useState } from "react";
import { Medal } from "../../models/Medal";
import { MedalService } from "../../services/MedalService";

const MedalPage = () => {
    const [medals, setMedals] = useState<Medal[]>([]);
    const { user, points } = useUser();

    useEffect(() => {
        MedalService.list().then((medals) => {
            setMedals(medals);
        });
    }, []);

    return (<>
        <Typography color="blue" as="h1" className="text-xl font-bold">Medallas de {user?.name}</Typography>
        <div className="grid grid-cols-3 gap-4">
            {medals.map((medal) => {
                return medal.points_value <= (points?.total_points ?? 0) ? (
                    <div key={medal.id} className="bg-white rounded-md p-4 shadow-md">
                        <Typography color="blue-gray" as="h2" className="text-lg font-bold">{medal.name}</Typography>
                        <Typography color="blue-gray" as="p" className="text-sm">Puntos necesarios: {medal.points_value}</Typography>
                    </div>
                ) : null;
            })}
        </div>
    </>);
}

export default MedalPage;