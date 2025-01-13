import { useEffect, useState, ReactNode, useMemo, useCallback } from "react";
import { AuthService } from "../../services/AuthService";
import { UserContext } from "./UserContext";
import { Routes } from "../../routes/CONSTANTS";
import { useLocation, useNavigate } from "react-router-dom";
import { RoleEnum } from "../../models/enums/RoleEnum";
import { User } from "../../models/User";
import { Point } from "../../models/Point";
import { PointService } from "../../services/PointService";

export const UserProvider = ({ children }: { children: ReactNode }) => {
    const [user, setUser] = useState<User | undefined>();
    const [userRoles, setUserRoles] = useState<Set<RoleEnum>>(new Set<RoleEnum>());
    const [userPoints, setUserPoints] = useState<Point | undefined>();
    const navigate = useNavigate();
    const location = useLocation();

    useEffect(() => {
        if (window.location.pathname === Routes.LOGIN) return;
        AuthService.me().then((user) => {
            setUser(user);
            setUserRoles(new Set(user.roles?.map(role => role.name)));
            PointService.getByUser(user.id!).then((point) => setUserPoints(point));
        });
    }, [location]);

    const signOut = useCallback(() => {
        sessionStorage.removeItem('token');
        setUser(undefined);
        setUserRoles(new Set());
        setUserPoints(undefined);
        navigate(Routes.LOGIN);
    }, [navigate]);

    const value = useMemo(
        () => ({
            user,
            userRoles: userRoles,
            signOut,
            points: userPoints,
        }),
        [user, userRoles, signOut, userPoints]
    );

    return (
        <UserContext.Provider value={value}>
            {children}
        </UserContext.Provider>
    );
};