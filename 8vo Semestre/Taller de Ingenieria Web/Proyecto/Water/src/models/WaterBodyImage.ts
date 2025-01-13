import { WaterBody } from "./WaterBody";

export interface WaterBodyImage {
    id?: number;
    image?: File;
    image_url?: string;
    water_body_id: number;
    water_body?: WaterBody;
}