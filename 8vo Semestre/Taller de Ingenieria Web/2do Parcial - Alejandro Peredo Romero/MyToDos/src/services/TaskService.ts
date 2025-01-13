import { Task } from "../models/Task";
import api from "./Api"

export const TaskService = {
    list: () => {
        return new Promise<Task[]>((resolve, reject) => {
            api.get('tasks/')
            .then(response => resolve(response.data))
            .catch(error => reject(error))
        });
    },
    get: (id: number) => {
        return new Promise<Task>((resolve, reject) => {
            api.get(`tasks/${id}`)
            .then(response => resolve(response.data))
            .catch(error => reject(error))
        });
    },
    create: (Task: Task) => {
        return new Promise<Task>((resolve, reject) => {
            api.post('tasks/', Task)
            .then(response => resolve(response.data))
            .catch(error => reject(error))
        });
    },
    update: (Task: Task) => {
        return new Promise<Task>((resolve, reject) => {
            api.put(`tasks/${Task.id}/`, Task)
            .then(response => resolve(response.data))
            .catch(error => reject(error))
        });
    },
    delete: (id: number) => {
        return new Promise<Task>((resolve, reject) => {
            api.delete(`tasks/${id}/`)
            .then(response => resolve(response.data))
            .catch(error => reject(error))
        });
    },
    assignUser: (taskId: number, userId: number) => {
        return new Promise<Task>((resolve, reject) => {
            api.post(`tasks/${taskId}/users/${userId}`)
            .then(response => resolve(response.data))
            .catch(error => reject(error))
        });
    },
    unassignUser: (taskId: number, userId: number) => {
        return new Promise<Task>((resolve, reject) => {
            api.delete(`tasks/${taskId}/users/${userId}`)
            .then(response => resolve(response.data))
            .catch(error => reject(error))
        });
    },
}