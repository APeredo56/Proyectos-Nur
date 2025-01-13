import { User } from "./User";

export interface Task {
    id: number;
    title: string;
    description: string;
    start_date: string;
    end_date: string;
    completed: boolean;
    users?: User[];
}