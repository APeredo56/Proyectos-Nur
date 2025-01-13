import { MessageResponse } from "../models/responses/MessageResponse";
import { handleErrorResponse } from "../utils/axiosUtils";
import privateApi from "./privateApi";
import { Community } from '../models/Community';

export const CommunityService = {
    list: () => {
        return new Promise<Community[]>((resolve, reject) => {
            privateApi.get('communities')
                .then(response => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    get: (id: number) => {
        return new Promise<Community>((resolve, reject) => {
            privateApi.get(`communities/${id}`)
                .then(response => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    create: (community: Community) => {
        return new Promise<Community>((resolve, reject) => {
            privateApi.post('communities', community)
                .then((response) => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    update: (id: number, community: Community) => {
        return new Promise<Community>((resolve, reject) => {
            privateApi.put(`communities/${id}`, community)
                .then((response) => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    delete: (id: number) => {
        return new Promise<MessageResponse>((resolve, reject) => {
            privateApi.delete(`communities/${id}`)
                .then((response) => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
}