import { Community } from "./Community";
import { Department } from "./Department";

export interface Municipality {
    id?: number;
    name: string;
    department_id: number;
    department?: Department;
    communities?: Community[];
}