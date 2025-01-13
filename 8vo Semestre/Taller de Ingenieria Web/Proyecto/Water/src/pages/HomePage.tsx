import { Typography } from "@material-tailwind/react";
import { useUser } from "../contexts/user/useUser";

const HomePage = () => {
    const { user } = useUser();
    return (<>
        <Typography color="blue" as="h1" className="text-xl font-bold">Bienvenido</Typography>
        <p>{user?.name}</p>
    </>);
}

export default HomePage;