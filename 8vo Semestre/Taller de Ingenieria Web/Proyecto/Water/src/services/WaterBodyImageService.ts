import { MessageResponse } from "../models/responses/MessageResponse";
import { WaterBodyImage } from "../models/WaterBodyImage";
import { handleErrorResponse } from "../utils/axiosUtils";
import privateApi from "./privateApi";

export const WaterBodyImageService = {
    list: () => {
        return new Promise<WaterBodyImage[]>((resolve, reject) => {
            privateApi.get('water-bodies-images')
                .then(response => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    get: (id: number) => {
        return new Promise<WaterBodyImage>((resolve, reject) => {
            privateApi.get(`water-bodies-images/${id}`)
                .then(response => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    create: (waterBodyImage: WaterBodyImage) => {
        const formData = new FormData();
        formData.append('image', waterBodyImage.image as Blob);
        formData.append('water_body_id', waterBodyImage.water_body_id.toString());
        return new Promise<WaterBodyImage>((resolve, reject) => {
            privateApi.post('water-bodies-images', formData)
                .then((response) => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    update: (id: number, waterBodyImage: WaterBodyImage) => {
        const formData = new FormData();
        formData.append('image', waterBodyImage.image as Blob);
        formData.append('water_body_id', waterBodyImage.water_body_id.toString());
        return new Promise<WaterBodyImage>((resolve, reject) => {
            privateApi.put(`water-bodies-images/${id}`, formData)
                .then((response) => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
    delete: (id: number) => {
        return new Promise<MessageResponse>((resolve, reject) => {
            privateApi.delete(`water-bodies-images/${id}`)
                .then((response) => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
}