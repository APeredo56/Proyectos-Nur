import axios from "axios"

export const ClientService = {
    create: async (client: Cliente) => {
        return new Promise((resolve, reject) => {
            axios.post('http://localhost:3001/clients', client)
    }
}