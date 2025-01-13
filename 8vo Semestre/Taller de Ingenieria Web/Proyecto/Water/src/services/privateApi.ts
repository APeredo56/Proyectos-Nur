import axios from "axios";
import { handleErrorResponse } from "../utils/axiosUtils";
import { Routes } from "../routes/CONSTANTS";

const privateApi = axios.create({
    baseURL: 'http://localhost:8000/api/',
    timeout: 5000,
});

privateApi.interceptors.request.use(
    (config) => {
        // Obtén el token desde sessionStorage
        const token = sessionStorage.getItem('token');

        // Si existe el token, agrégalo a los encabezados de la solicitud
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }

        return config;
    },
    (error) => {
        // Manejo de errores antes de enviar la solicitud
        return Promise.reject(handleErrorResponse(error));
    }
);

privateApi.interceptors.response.use(
    (response) => response,
    async (error) => {
        handleErrorResponse(error);
        console.log('error', error.message);
        if (error.response?.status === 401 && error.message.includes("Unauthenticated")) {
            if (window.location.pathname !== Routes.LOGIN) {
                window.location.href = Routes.LOGIN;
            }
        }

        return Promise.reject(handleErrorResponse(error));
    });

export default privateApi;