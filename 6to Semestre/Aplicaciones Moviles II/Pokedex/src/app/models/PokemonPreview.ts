export interface PokemonPreview {
    id: number;
    name: string;
    height: number;
    weight: number;
    typeDetail: TypeDetail[];
    species: Species;
}

export interface TypeDetail {
    type: Type;
}

export interface Type {
    name: string;
}

export interface Species{
    id: number;
    generation: Generation;
}

export interface Generation{
    name: string;
}