import { FieldTrip } from "../models/FieldTrip";
import { MessageResponse } from "../models/responses/MessageResponse";
import { handleErrorResponse } from "../utils/axiosUtils";
import privateApi from "./privateApi";

export const FieldTripService = {
    list: () => {
        return new Promise<FieldTrip[]>((resolve, reject) => {
            privateApi.get('field-trips')
                .then(response => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    get: (id: number) => {
        return new Promise<FieldTrip>((resolve, reject) => {
            privateApi.get(`field-trips/${id}`)
                .then(response => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    create: (fieldTrip: FieldTrip) => {
        return new Promise<FieldTrip>((resolve, reject) => {
            privateApi.post('field-trips', fieldTrip)
                .then((response) => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    update: (id: number, fieldTrip: FieldTrip) => {
        return new Promise<FieldTrip>((resolve, reject) => {
            privateApi.put(`field-trips/${id}`, fieldTrip)
                .then((response) => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    delete: (id: number) => {
        return new Promise<MessageResponse>((resolve, reject) => {
            privateApi.delete(`field-trips/${id}`)
                .then((response) => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    assignUser: (fieldTripId: number, userId: number) => {
        return new Promise<MessageResponse>((resolve, reject) => {
            privateApi.post(`field-trips/${fieldTripId}/users/${userId}`)
                .then(response => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    unassignUser: (fieldTripId: number, userId: number) => {
        return new Promise<MessageResponse>((resolve, reject) => {
            privateApi.delete(`field-trips/${fieldTripId}/users/${userId}`)
                .then(response => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    assignWaterBody: (fieldTripId: number, waterBodyId: number) => {
        return new Promise<MessageResponse>((resolve, reject) => {
            privateApi.post(`field-trips/${fieldTripId}/water-bodies/${waterBodyId}`)
                .then(response => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    unassignWaterBody: (fieldTripId: number, waterBodyId: number) => {
        return new Promise<MessageResponse>((resolve, reject) => {
            privateApi.delete(`field-trips/${fieldTripId}/water-bodies/${waterBodyId}`)
                .then(response => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    getByUser: (userId: number) => {
        return new Promise<FieldTrip[]>((resolve, reject) => {
            privateApi.get(`users/${userId}/field-trips`)
                .then(response => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    }
}