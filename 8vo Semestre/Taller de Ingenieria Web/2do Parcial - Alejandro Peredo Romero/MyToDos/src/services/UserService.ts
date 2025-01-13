import { User } from "../models/User";
import api from "./Api"

export const UserService = {
    list: () => {
        return new Promise<User[]>((resolve, reject) => {
            api.get('users/')
            .then(response => resolve(response.data))
            .catch(error => reject(error))
        });
    },
    get: (id: number) => {
        return new Promise<User>((resolve, reject) => {
            api.get(`users/${id}`)
            .then(response => resolve(response.data))
            .catch(error => reject(error))
        });
    },
    create: (user: User) => {
        return new Promise<User>((resolve, reject) => {
            api.post('users/', user)
            .then(response => resolve(response.data))
            .catch(error => reject(error))
        });
    },
    update: (user: User) => {
        return new Promise<User>((resolve, reject) => {
            api.put(`users/${user.id}/`, user)
            .then(response => resolve(response.data))
            .catch(error => reject(error))
        });
    },
    delete: (id: number) => {
        return new Promise<User>((resolve, reject) => {
            api.delete(`users/${id}/`)
            .then(response => resolve(response.data))
            .catch(error => reject(error))
        });
    },
}