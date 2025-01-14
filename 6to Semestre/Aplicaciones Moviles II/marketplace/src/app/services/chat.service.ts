import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Chat } from '../models/Chat';
import { environment } from 'src/environments/environment';
import { Message } from '../models/Message';
import { Preferences } from '@capacitor/preferences';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  msgLatitude: number | undefined;
  msgLongitude: number | undefined;
  locationSentObject = new Subject;
  locationSent = this.locationSentObject.asObservable();

  constructor(private http: HttpClient) { }

  getUserChats(token: string){
    const options = this.getAuthHeader(token);
    return this.http.get<Chat[]>(environment.baseAPIUrl + "/api/chats", options);
  }

  getProductChats(productId: number, token: string){
    const options = this.getAuthHeader(token);
    return this.http.get<Chat[]>(environment.baseAPIUrl + "/api/products/" + productId + "/chats", options);
  }

  getChatMessages(chatId: number, token: string){
    const options = this.getAuthHeader(token);
    return this.http.get<Message[]>(environment.baseAPIUrl + "/api/chats/" + chatId + "/messages", options);
  }

  private getAuthHeader(token: string){
    const headers = new HttpHeaders({
      'Authorization': 'Bearer ' + token
    });
    const options = {
      headers: headers
    };
    return options;
  }

  createChat(productId: number, token: string){
    const options = this.getAuthHeader(token);
    return this.http.post<Chat>(environment.baseAPIUrl + "/api/chats", {"product_id": productId}, options);
  }

  sendTextMessage(chatId: number, message: string, token: string){
    const options = this.getAuthHeader(token);
    const formData = new FormData();
    formData.append("message", message);
    formData.append("chat_id", chatId.toString());
    formData.append("type", "1");
    return this.http.post<Message>(environment.baseAPIUrl + "/api/messages", formData, options);
  }

  sendImageMessage(chatId: number, imageBlob: Blob, token: string){
    const options = this.getAuthHeader(token);
    const formData = new FormData();
    formData.append("image", imageBlob);
    formData.append("chat_id", chatId.toString());
    formData.append("type", "2");
    return this.http.post<Message>(environment.baseAPIUrl + "/api/messages", formData, options);
  }

  sendLocationMessage(chatId: number, latitude: number, longitude: number, token: string){
    const options = this.getAuthHeader(token);
    const formData = new FormData();
    formData.append("latitude", latitude.toString());
    formData.append("longitude", longitude.toString());
    formData.append("chat_id", chatId.toString());
    formData.append("type", "3");
    return this.http.post<Message>(environment.baseAPIUrl + "/api/messages", formData, options);
  }

  async getNotifications(){
    return await Preferences.get({key: 'notifications'}).then((res) => {
      if(res.value && res.value.length > 0){
        const storedNotificacionsObject = Array.from(JSON.parse(res.value)) as [string, string][];
        const storedNotificationsMap = new Map<number, number>();
        for (const item of storedNotificacionsObject) {
          storedNotificationsMap.set(parseInt(item[0]), parseInt(item[1]));
        }
        return storedNotificationsMap;
      } else {
        return null;
      }
    });
  }

  saveNotifications(notifications: Map<number, number>){
    Preferences.set({
      key: "notifications",
      value: JSON.stringify(Array.from(notifications)),
    })
  }

  markChatAsRead(chatId: number){
    this.getNotifications().then(notifications => {
      if(notifications && notifications.size > 0){
        notifications.set(chatId, 0);
        this.saveNotifications(notifications);
      }
    });
  }
}
