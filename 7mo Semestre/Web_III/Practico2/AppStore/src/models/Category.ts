import { App } from "./App";

export interface Category{
    id?: number,
    name: string,
    apps: App[]
}