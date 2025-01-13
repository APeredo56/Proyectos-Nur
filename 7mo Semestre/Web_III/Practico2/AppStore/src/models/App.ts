import { Category } from "./Category";
import { Screenshot } from './Screenshot';

export interface App{
    id?: number,
    name: string,
    description: string,
    icon: string,
    cover_photo: string,
    price: number,
    downloads: number,
    release_date: string,
    categories: Category[],
    screenshots: Screenshot[]
}