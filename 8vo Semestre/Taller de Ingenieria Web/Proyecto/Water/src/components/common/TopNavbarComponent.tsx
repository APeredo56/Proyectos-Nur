import { useEffect, useState } from "react";
import {
    Navbar,
    Collapse,
    IconButton,
    Typography,
    Menu,
    MenuHandler,
    ListItem,
    MenuList,
} from "@material-tailwind/react";

import { Routes } from '../../routes/CONSTANTS';
import { useUser } from "../../contexts/user/useUser";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBars, faChevronDown, faXmark } from "@fortawesome/free-solid-svg-icons";
import { RoleEnum } from "../../models/enums/RoleEnum";

const links = (userRoles: Set<RoleEnum>) => {
    return (<ul className="my-2 flex flex-col gap-2 lg:mb-0 lg:mt-0 lg:flex-row lg:items-center lg:gap-6">
        {userRoles.has(RoleEnum.ADMINISTRATIVO) && <li>
            <Typography as={"a"} href={Routes.USER.LIST}>Usuarios</Typography>
        </li>}
        {userRoles.has(RoleEnum.ADMINISTRATIVO) && <li>
            <Typography as={"a"} href={Routes.DEPARTMENT.LIST}>Departamentos</Typography>
        </li>}
        {userRoles.has(RoleEnum.ADMINISTRATIVO) && <li>
            <Typography as={"a"} href={Routes.MUNICIPALITY.LIST}>Municipios</Typography>
        </li>}
        {userRoles.has(RoleEnum.ADMINISTRATIVO) && <li>
            <Typography as={"a"} href={Routes.COMMUNITY.LIST}>Comunidades</Typography>
        </li>}
        {userRoles.has(RoleEnum.ADMINISTRATIVO) && <li>
            <Typography as={"a"} href={Routes.WATERBODY.LIST}>Cuerpos de agua</Typography>
        </li>}
        {userRoles.has(RoleEnum.ADMINISTRATIVO) && <li>
            <Typography as={"a"} href={Routes.FIELDTRIP.LIST}>Salidas de Campo</Typography>
        </li>}
        {userRoles.has(RoleEnum.TECNICO) && <li>
            <Typography as={"a"} href={Routes.FIELDTRIP.OWN}>Mis salidas de campo</Typography>
        </li>}
    </ul>);
}

const TopNavbarComponent = () => {
    const [openNav, setOpenNav] = useState(false);
    const [isMenuOpen, setIsMenuOpen] = useState(false);
    const { user, userRoles, signOut, points } = useUser();

    const handleWindowResize = () =>
        window.innerWidth >= 960 && setOpenNav(false);

    useEffect(() => {
        window.addEventListener("resize", handleWindowResize);

        return () => {
            window.removeEventListener("resize", handleWindowResize);
        };
    }, []);

    return (
        <Navbar className="top-0 z-[100] p-0 rounded-none bg-primary" color="blue" fullWidth>
            <div className="max-w-screen-2xl m-auto p-5 flex items-center justify-between min-h-10">
                <a href={Routes.HOME} className="z-10 font-bold text-2xl">
                    AquaTrace
                </a>
                <div className="hidden lg:flex">
                    <div className="mr-4 hidden lg:flex">
                        {user && links(userRoles)}
                        {user && <Menu open={isMenuOpen} handler={setIsMenuOpen} placement="bottom" allowHover={true}>
                            <MenuHandler>
                                <Typography className="text-md hidden lg:block" as="div">
                                    <ListItem className="flex items-center gap-2 py-2 pr-4" selected={isMenuOpen}
                                        onClick={() => setIsMenuOpen((cur) => !cur)}>
                                        {user.name}
                                        <FontAwesomeIcon icon={faChevronDown}
                                            className={`h-3 w-3 transition-transform ${isMenuOpen ? "rotate-180" : ""}`} />
                                    </ListItem>
                                </Typography>
                            </MenuHandler>
                            <MenuList className="hidden rounded-xl lg:block">
                                <Typography as="p" className="py-2">Nivel: {points?.level}</Typography>
                                <Typography as="p" className="py-2">Puntos: {points?.total_points}</Typography>
                                <Typography as="a" href={Routes.USER.RANKING} className="py-2">Ver Ranking</Typography>
                                <Typography as="a" href={Routes.MEDAL.MY_MEDALS} className="py-2">Ver mis Medallas</Typography>
                                <Typography as="button" className="ms-6" onClick={signOut}>Cerrar Sesión</Typography>
                            </MenuList>
                        </Menu>}
                    </div>
                </div>
                <IconButton
                    variant="text"
                    className="ml-auto h-6 w-6 text-inherit hover:bg-transparent focus:bg-transparent active:bg-transparent lg:hidden"
                    ripple={false}
                    onClick={() => setOpenNav(!openNav)}
                >
                    {openNav ? (
                        <FontAwesomeIcon icon={faXmark} className="h-5 w-5" />
                    ) : (
                        <FontAwesomeIcon icon={faBars} className="h-5 w-5" />
                    )}
                </IconButton>
            </div>
            <Collapse open={openNav} className="px-8 max-h-72 overflow-scroll">
                {user && links(userRoles)}
                {user && <Typography as="button" className="pb-3" onClick={signOut}>Cerrar Sesión</Typography>}
            </Collapse>
        </Navbar>
    );
}

export default TopNavbarComponent;