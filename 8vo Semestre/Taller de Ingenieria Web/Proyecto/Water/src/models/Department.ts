import { Municipality } from "./Municipality";

export interface Department {
    id?: number;
    name: string;
    municipalities?: Municipality[];
}