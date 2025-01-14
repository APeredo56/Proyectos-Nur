export interface User {
    id?:        number;
    fullname:      string;
    email:     string;
    password?:  string;
    createdAt?: Date;
    updatedAt?: Date;
}