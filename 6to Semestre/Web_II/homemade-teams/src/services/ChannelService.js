import axios from "axios";

export const getTeamChannelsList = (teamId) => {
    return new Promise((resolve, reject) => {
    axios.get('http://localhost:8080/Web_II/teamsapi/team/channels/' + teamId)
        .then((response) => {
            resolve(response.data);
        }).catch((error) => {
            console.log(error);
            reject(error);
        });
    });
}

export const getChannelById = (channelId) => {
    return new Promise((resolve, reject) => {
    axios.get('http://localhost:8080/Web_II/teamsapi/channel/detail/' + channelId)
        .then((response) => {
            resolve(response.data);
        }).catch((error) => {
            console.log(error);
            reject(error);
        });
    });
}

export const deleteChannel = (channelId) => {
    return new Promise((resolve, reject) => {
    axios.delete('http://localhost:8080/Web_II/teamsapi/channel/delete/' + channelId)
        .then((response) => {
            resolve(response.data);
        }).catch((error) => {
            console.log(error);
            reject(error);
        });
    });
}