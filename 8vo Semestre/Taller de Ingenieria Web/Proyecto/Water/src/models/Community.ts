import { Municipality } from "./Municipality";
import { WaterBody } from "./WaterBody";

export interface Community {
    id?: number;
    name: string;
    municipality_id: number;
    municipality?: Municipality;
    water_bodies?: WaterBody[];
}