import { Medal } from "../models/Medal";
import { MessageResponse } from "../models/responses/MessageResponse";
import { handleErrorResponse } from "../utils/axiosUtils";
import privateApi from "./privateApi";

export const MedalService = {
    list: () => {
        return new Promise<Medal[]>((resolve, reject) => {
            privateApi.get('medals')
                .then(response => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    get: (id: number) => {
        return new Promise<Medal>((resolve, reject) => {
            privateApi.get(`medals/${id}`)
                .then(response => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    create: (medal: Medal) => {
        return new Promise<Medal>((resolve, reject) => {
            privateApi.post('medals', medal)
                .then((response) => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    update: (id: number, medal: Medal) => {
        return new Promise<Medal>((resolve, reject) => {
            privateApi.put(`medals/${id}`, medal)
                .then((response) => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    delete: (id: number) => {
        return new Promise<MessageResponse>((resolve, reject) => {
            privateApi.delete(`medals/${id}`)
                .then((response) => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
}