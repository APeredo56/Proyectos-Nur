import axios from "axios";
const baseAPIUrl = import.meta.env.VITE_API_URL;

export const getCityList = () => {
    return new Promise((resolve, reject) => {
    axios.get(baseAPIUrl + 'cities')
        .then((response) => {
            resolve(response.data);
        }).catch((error) => {
            console.log(error);
            reject(error);
        });
    });
}