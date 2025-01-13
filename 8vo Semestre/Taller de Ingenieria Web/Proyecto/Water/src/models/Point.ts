import { User } from "./User";

export interface Point {
    id?: number,
    user_id: number,
    user?: User,
    level: number,
    total_points: number,
}