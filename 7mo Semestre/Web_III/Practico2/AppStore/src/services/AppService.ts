import axios from "axios";
import { App } from "../models/App";

export const AppService = {
    create: (data: FormData) => {
        return new Promise<App>((resolve, reject) => {
            axios.post('http://localhost:8000/api/apps/', data)
                .then(response => resolve(response.data))
                .catch(error => reject(error))
        });
    },
    list: () => {
        return new Promise<App[]>((resolve, reject) => {
            axios.get('http://localhost:8000/api/apps/')
                .then(response => resolve(response.data))
                .catch(error => reject(error))
        });
    },
    get: (id: number) => {
        return new Promise<App>((resolve, reject) => {
            axios.get(`http://localhost:8000/api/apps/${id}/`)
                .then(response => resolve(response.data))
                .catch(error => reject(error))
        });
    },
    update: (data: FormData, id: number) => {
        return new Promise<App>((resolve, reject) => {
            axios.put(`http://localhost:8000/api/apps/${id}/`, data)
                .then(response => resolve(response.data))
                .catch(error => reject(error))
        });
    },
    delete: (id: number) => {
        return new Promise((resolve, reject) => {
            axios.delete(`http://localhost:8000/api/apps/${id}/`)
                .then(response => resolve(response.data))
                .catch(error => reject(error))
        });
    },
    latest: () => {
        return new Promise<App[]>((resolve, reject) => {
            axios.get('http://localhost:8000/api/apps/latest/')
                .then(response => resolve(response.data))
                .catch(error => reject(error))
        });
    },
    topFree: () => {
        return new Promise<App[]>((resolve, reject) => {
            axios.get('http://localhost:8000/api/apps/top-free/')
                .then(response => resolve(response.data))
                .catch(error => reject(error))
        });
    },
    topPaid: () => {
        return new Promise<App[]>((resolve, reject) => {
            axios.get('http://localhost:8000/api/apps/top-paid/')
                .then(response => resolve(response.data))
                .catch(error => reject(error))
        });
    },
    addDownload: (id: number) => {
        return new Promise<App>((resolve, reject) => {
            axios.post(`http://localhost:8000/api/apps/${id}/download/`)
                .then(response => resolve(response.data))
                .catch(error => reject(error))
        });
    }
}