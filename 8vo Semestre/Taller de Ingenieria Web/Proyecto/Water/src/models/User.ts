import { Role } from "./Role";

export interface User {
    id?: number;
    name: string;
    email: string;
    password?: string;
    role?: string;
    roles?: Role[];
    status?: boolean;
}