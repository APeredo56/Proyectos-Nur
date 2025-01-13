import axios from "axios";
import { Category } from '../models/Category';

export const CategoryService = {
    create: (category: Category) => {
        return new Promise<Category>((resolve, reject) => {
            axios.post('http://localhost:8000/api/categories/', category)
                .then(response => resolve(response.data))
                .catch(error => reject(error))
        });
    },
    list: () => {
        return new Promise<Category[]>((resolve, reject) => {
            axios.get('http://localhost:8000/api/categories/')
                .then(response => resolve(response.data))
                .catch(error => reject(error))
        });
    },
    get: (id: number) => {
        return new Promise<Category>((resolve, reject) => {
            axios.get(`http://localhost:8000/api/categories/${id}/`)
                .then(response => resolve(response.data))
                .catch(error => reject(error))
        });
    },
    update: (category: Category) => {
        return new Promise<Category>((resolve, reject) => {
            axios.put(`http://localhost:8000/api/categories/${category.id}/`, category)
                .then(response => resolve(response.data))
                .catch(error => reject(error))
        });
    },
    delete: (id: number) => {
        return new Promise((resolve, reject) => {
            axios.delete(`http://localhost:8000/api/categories/${id}/`)
                .then(response => resolve(response.data))
                .catch(error => reject(error))
        });
    },
}