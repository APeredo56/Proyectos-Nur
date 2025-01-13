import axios from "axios";

export const ScreenshotService = {
    delete: (id: number) => {
        return new Promise((resolve, reject) => {
            axios.delete(`http://localhost:8000/api/screenshots/${id}/`)
                .then(response => resolve(response.data))
                .catch(error => reject(error))
        });
    },
}