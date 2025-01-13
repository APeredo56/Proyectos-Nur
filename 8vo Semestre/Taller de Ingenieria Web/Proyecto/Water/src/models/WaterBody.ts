import { Community } from "./Community";
import { WaterBodyType } from "./enums/WaterBodyType";
import { WaterBodyImage } from "./WaterBodyImage";

export interface WaterBody {
    id?: number;
    name: string;
    water_body_type: WaterBodyType;
    latitude: number;
    longitude: number;
    community_id: number;
    community?: Community;
    water_body_images?: WaterBodyImage[];
}