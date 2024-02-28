import axios from "axios";
const baseAPIUrl = import.meta.env.VITE_API_URL;

export const getAccommodationTypes = () => {
    return new Promise((resolve, reject) => {
        axios.get(baseAPIUrl+ 'accommodations/types')
            .then((response) => {
                resolve(response.data);
            }).catch((error) => {
                console.log(error);
                reject(error);
            });
    });
}

export const getAccommodationById = (id) => {
    return new Promise((resolve, reject) => {
        axios.get(baseAPIUrl+ 'accommodations/' + id)
            .then((response) => {
                resolve(response.data);
            }).catch((error) => {
                console.log(error);
                reject(error);
            });
    });
}