import { SamplingAnalysis } from "../models/SamplingAnalysis";
import { MessageResponse } from "../models/responses/MessageResponse";
import { handleErrorResponse } from "../utils/axiosUtils";
import privateApi from "./privateApi";

export const SamplingAnalysisService = {
    list: () => {
        return new Promise<SamplingAnalysis[]>((resolve, reject) => {
            privateApi.get('sampling-analyses')
                .then(response => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    get: (id: number) => {
        return new Promise<SamplingAnalysis>((resolve, reject) => {
            privateApi.get(`sampling-analyses/${id}`)
                .then(response => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    create: (samplingAnalysis: SamplingAnalysis) => {
        return new Promise<SamplingAnalysis>((resolve, reject) => {
            privateApi.post('sampling-analyses', samplingAnalysis)
                .then((response) => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    update: (id: number, samplingAnalysis: SamplingAnalysis) => {
        return new Promise<SamplingAnalysis>((resolve, reject) => {
            privateApi.put(`sampling-analyses/${id}`, samplingAnalysis)
                .then((response) => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    delete: (id: number) => {
        return new Promise<MessageResponse>((resolve, reject) => {
            privateApi.delete(`sampling-analyses/${id}`)
                .then((response) => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    getByFieldTripAndUser: (fieldTripId: number, userId: number) => {
        return new Promise<SamplingAnalysis[]>((resolve, reject) => {
            privateApi.get(`sampling-analyses/field-trips/${fieldTripId}/users/${userId}`)
                .then(response => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    }
}