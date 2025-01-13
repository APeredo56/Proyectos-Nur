import { FieldTrip } from "./FieldTrip";
import { SamplingAnalysisImage } from "./SamplingAnalysisImage";
import { User } from "./User";
import { WaterBody } from "./WaterBody";

export interface SamplingAnalysis {
    id?: number;
    turbidity: number;
    water_velocity: number;
    width: number;
    average_depth: number;
    flow_rate?: number;
    water_body_id: number;
    water_body?: WaterBody;
    user_id: number;
    user?: User;
    field_trip_id: number;
    field_trip?: FieldTrip;
    sampling_analysis_images?: SamplingAnalysisImage[];
}