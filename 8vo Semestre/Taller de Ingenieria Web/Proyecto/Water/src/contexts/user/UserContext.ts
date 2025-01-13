import { createContext } from "react";
import { User } from "../../models/User";
import { RoleEnum } from "../../models/enums/RoleEnum";
import { Point } from "../../models/Point";

interface UserContextProps {
    user: User | undefined,
    userRoles: Set<RoleEnum>,
    signOut: () => void,
    points: Point | undefined,
}

export const UserContext = createContext<UserContextProps>(
    {
        user: undefined,
        userRoles: new Set<RoleEnum>(),
        signOut: () => { throw new Error("No se implemento el metodo de cerrar sesión") },
        points: undefined,
    });