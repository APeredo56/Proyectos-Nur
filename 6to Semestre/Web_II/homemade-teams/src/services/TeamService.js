import axios from "axios";

export const getTeamsList = () => {
    return new Promise((resolve, reject) => {
    axios.get('http://localhost:8080/Web_II/teamsapi/team/list/')
        .then((response) => {
            resolve(response.data);
        }).catch((error) => {
            console.log(error);
            reject(error);
        });
    });
}

export const getTeamById = (id) => {
    return new Promise((resolve, reject) => {
    axios.get('http://localhost:8080/Web_II/teamsapi/team/detail/' + id)
        .then((response) => {
            resolve(response.data);
        }).catch((error) => {
            console.log(error);
            reject(error);
        });
    });
}