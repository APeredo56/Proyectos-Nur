import { User } from "../models/User";
import { MessageResponse } from "../models/responses/MessageResponse";
import { handleErrorResponse } from "../utils/axiosUtils";
import privateApi from "./privateApi";

export const UserService = {
    list: () => {
        return new Promise<User[]>((resolve, reject) => {
            privateApi.get('users')
                .then(response => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    get: (id: number) => {
        return new Promise<User>((resolve, reject) => {
            privateApi.get(`users/${id}`)
                .then(response => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    create: (user: User) => {
        return new Promise<User>((resolve, reject) => {
            privateApi.post('users', user)
                .then((response) => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    update: (id: number, user: User) => {
        return new Promise<User>((resolve, reject) => {
            privateApi.put(`users/${id}`, user)
                .then((response) => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    delete: (id: number) => {
        return new Promise<MessageResponse>((resolve, reject) => {
            privateApi.delete(`users/${id}`)
                .then((response) => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    getTecnicos: () => {
        return new Promise<User[]>((resolve, reject) => {
            privateApi.get('users/tecnicos')
                .then(response => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
}