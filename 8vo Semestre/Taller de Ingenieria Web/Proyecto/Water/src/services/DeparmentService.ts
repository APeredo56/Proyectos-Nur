import { Department } from "../models/Department";
import { MessageResponse } from "../models/responses/MessageResponse";
import { handleErrorResponse } from "../utils/axiosUtils";
import privateApi from "./privateApi";

export const DepartmentService = {
    list: () => {
        return new Promise<Department[]>((resolve, reject) => {
            privateApi.get('departments')
                .then(response => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    get: (id: number) => {
        return new Promise<Department>((resolve, reject) => {
            privateApi.get(`departments/${id}`)
                .then(response => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    create: (department: Department) => {
        return new Promise<Department>((resolve, reject) => {
            privateApi.post('departments', department)
                .then((response) => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    update: (id: number, department: Department) => {
        return new Promise<Department>((resolve, reject) => {
            privateApi.put(`departments/${id}`, department)
                .then((response) => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    delete: (id: number) => {
        return new Promise<MessageResponse>((resolve, reject) => {
            privateApi.delete(`departments/${id}`)
                .then((response) => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
}