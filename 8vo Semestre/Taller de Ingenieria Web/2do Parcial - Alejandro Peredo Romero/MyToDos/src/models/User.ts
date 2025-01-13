import { Task } from "./Task";

export interface User {
    id: number;
    name: string;
    last_name: string;
    email: string;
    password?: string;
    phone: string;
    date_of_birth: string;
    tasks?: Task[];
}