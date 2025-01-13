import { User } from "./User";
import { WaterBody } from "./WaterBody";

export interface FieldTrip {
    id?: number;
    description: string;
    start_date: string;
    end_date: string;
    users?: User[];
    water_bodies?: WaterBody[];
}