import { Point } from "../models/Point";
import { handleErrorResponse } from "../utils/axiosUtils";
import privateApi from "./privateApi";

export const PointService = {
    getByUser: (id: number) => {
        return new Promise<Point>((resolve, reject) => {
            privateApi.get(`users/${id}/points`)
                .then(response => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    ranking: () => {
        return new Promise<Point[]>((resolve, reject) => {
            privateApi.get('points/ranking')
                .then((response) => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
}