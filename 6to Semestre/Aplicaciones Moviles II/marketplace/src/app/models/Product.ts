import { Category } from "./Category";
import { User } from "./User";

export interface Product {
    id:            number;
    name:          string;
    description:   string;
    price:         number;
    latitude:      string;
    longitude:     string;
    category_id:   number;
    status:        number;
    sold:          number;
    distance:      number;
    user_id?:        number;
    productimages: ProductImage[];
    category?:      Category;
    user?: User;
}

export interface ProductImage {
    url:        string;
    id?:         number;
    product_id: number;
    extension?:  string;
    createdAt?:  Date;
    updatedAt?:  Date;
}