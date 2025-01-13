import { AxiosError } from "axios";

export const handleErrorResponse = (error: AxiosError) => {
    if (error.response?.data) {
        error.message = (error.response.data as { message: string }).message;
    } else if (error.request) {
        error.message = error.request.message || error.request.statusText;
    } else {
        error.message = "Error desconocido";
    }
    return error;
}