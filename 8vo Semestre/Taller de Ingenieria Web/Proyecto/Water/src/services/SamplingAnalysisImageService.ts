import { MessageResponse } from "../models/responses/MessageResponse";
import { SamplingAnalysisImage } from "../models/SamplingAnalysisImage";
import { handleErrorResponse } from "../utils/axiosUtils";
import privateApi from "./privateApi";

export const SamplingAnalysisImageService = {
    list: () => {
        return new Promise<SamplingAnalysisImage[]>((resolve, reject) => {
            privateApi.get('sampling-analyses-images')
                .then(response => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    get: (id: number) => {
        return new Promise<SamplingAnalysisImage>((resolve, reject) => {
            privateApi.get(`sampling-analyses-images/${id}`)
                .then(response => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    create: (samplingAnalysisImage: SamplingAnalysisImage) => {
        const formData = new FormData();
        formData.append('image', samplingAnalysisImage.image as Blob);
        formData.append('sampling_analysis_id', samplingAnalysisImage.sampling_analysis_id.toString());
        return new Promise<SamplingAnalysisImage>((resolve, reject) => {
            privateApi.post('sampling-analyses-images', formData)
                .then((response) => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    update: (id: number, samplingAnalysisImage: SamplingAnalysisImage) => {
        const formData = new FormData();
        formData.append('image', samplingAnalysisImage.image as Blob);
        formData.append('sampling_analysis_id', samplingAnalysisImage.sampling_analysis_id.toString());
        return new Promise<SamplingAnalysisImage>((resolve, reject) => {
            privateApi.put(`sampling-analyses-images/${id}`, formData)
                .then((response) => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    delete: (id: number) => {
        return new Promise<MessageResponse>((resolve, reject) => {
            privateApi.delete(`sampling-analyses-images/${id}`)
                .then((response) => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
}