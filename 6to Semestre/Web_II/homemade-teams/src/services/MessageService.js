import axios from "axios";

export const getChannelMessages = (channelId) => {
    return new Promise((resolve, reject) => {
        axios.get('http://localhost:8080/Web_II/teamsapi/channel/messages/' + channelId)
            .then((response) => {
                resolve(response.data);
            }).catch((error) => {
                console.log(error);
                reject(error);
            });
    });
}

export const deleteMessage = (messageId) => {
    return new Promise((resolve, reject) => {
        axios.delete('http://localhost:8080/Web_II/teamsapi/message/delete/' + messageId)
            .then((response) => {
                resolve(response.data);
            }).catch((error) => {
                console.log(error);
                reject(error);
            });
    });
}