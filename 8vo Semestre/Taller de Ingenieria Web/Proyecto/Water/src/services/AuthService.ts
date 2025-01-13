import { User } from "../models/User";
import { handleErrorResponse } from "../utils/axiosUtils";
import privateApi from "./privateApi";

export const AuthService = {
    login: (email: string, password: string) => {
        return new Promise((resolve, reject) => {
            privateApi.post('auth/login', {
                email: email,
                password: password
            }).then(response => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)));
        });
    },
    me: () => {
        return new Promise<User>((resolve, reject) => {
            privateApi.get('auth/me')
                .then(response => resolve(response.data))
                .catch(error => reject(handleErrorResponse(error)))
        });
    },
}