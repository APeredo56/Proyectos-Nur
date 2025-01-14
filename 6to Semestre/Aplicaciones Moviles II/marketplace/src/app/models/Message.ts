import { User } from "./User";

export interface Message {
    image_url:        string;
    id:               number;
    chat_id:          number;
    type:             number;
    message:          null;
    latitude:         null;
    longitude:        null;
    image_extension:  string;
    user_id_sender:   number;
    user_id_receiver: number;
    createdAt:        Date;
    updatedAt:        Date;
    user_sender:      User;
    user_receiver:    User;
}