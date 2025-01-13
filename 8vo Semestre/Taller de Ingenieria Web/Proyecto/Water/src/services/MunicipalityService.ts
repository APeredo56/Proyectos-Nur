import { Municipality } from "../models/Municipality";
import { MessageResponse } from "../models/responses/MessageResponse";
import { handleErrorResponse } from "../utils/axiosUtils";
import privateApi from "./privateApi";

export const MunicipalityService = {
    list: () => {
        return new Promise<Municipality[]>((resolve, reject) => {
            privateApi.get('municipalities')
                .then(response => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    get: (id: number) => {
        return new Promise<Municipality>((resolve, reject) => {
            privateApi.get(`municipalities/${id}`)
                .then(response => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    create: (municipality: Municipality) => {
        return new Promise<Municipality>((resolve, reject) => {
            privateApi.post('municipalities', municipality)
                .then((response) => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    update: (id: number, municipality: Municipality) => {
        return new Promise<Municipality>((resolve, reject) => {
            privateApi.put(`municipalities/${id}`, municipality)
                .then((response) => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    delete: (id: number) => {
        return new Promise<MessageResponse>((resolve, reject) => {
            privateApi.delete(`municipalities/${id}`)
                .then((response) => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
}