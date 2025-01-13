import { MessageResponse } from "../models/responses/MessageResponse";
import { WaterBody } from "../models/WaterBody";
import { handleErrorResponse } from "../utils/axiosUtils";
import privateApi from "./privateApi";

export const WaterBodyService = {
    list: () => {
        return new Promise<WaterBody[]>((resolve, reject) => {
            privateApi.get('water-bodies')
                .then(response => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    get: (id: number) => {
        return new Promise<WaterBody>((resolve, reject) => {
            privateApi.get(`water-bodies/${id}`)
                .then(response => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    create: (waterBody: WaterBody) => {
        return new Promise<WaterBody>((resolve, reject) => {
            privateApi.post('water-bodies', waterBody)
                .then((response) => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    update: (id: number, waterBody: WaterBody) => {
        return new Promise<WaterBody>((resolve, reject) => {
            privateApi.put(`water-bodies/${id}`, waterBody)
                .then((response) => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    delete: (id: number) => {
        return new Promise<MessageResponse>((resolve, reject) => {
            privateApi.delete(`water-bodies/${id}`)
                .then((response) => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
}