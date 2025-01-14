import { Message } from "./Message";
import { Product } from "./Product";
import { User } from "./User";

export interface Chat {
    id:         number;
    user_id:    number;
    product_id: number;
    createdAt:  Date;
    updatedAt:  Date;
    product:    Product;
    user:       User;
    lastMessage: Message;
}