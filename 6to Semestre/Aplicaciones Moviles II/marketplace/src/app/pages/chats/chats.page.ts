import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Chat } from 'src/app/models/Chat';
import { AuthService } from 'src/app/services/auth.service';
import { ChatService } from 'src/app/services/chat.service';
import { SocketService } from 'src/app/services/socket.service';

@Component({
  selector: 'app-chats',
  templateUrl: './chats.page.html',
  styleUrls: ['./chats.page.scss'],
})
export class ChatsPage implements OnInit {
  chats: Chat[] = [];
  token: string = '';
  productId: number = 0;
  notifications: Map<number, number> = new Map<number, number>();
  messageSubscription: Subscription | undefined;

  constructor(
    private chatService: ChatService, 
    private authService: AuthService, 
    private router: Router,
    private route: ActivatedRoute,
    private socket: SocketService) { }

    ionViewDidEnter(){
      const userId = this.authService.myUser?.id!;
      this.socket.identifyUser(userId);
      if (this.messageSubscription?.closed || !this.messageSubscription){
        this.messageSubscription = this.socket.getMessages().subscribe(message => {
          console.log(message);
          const chat = this.chats.find(chat => chat.id == message.chat_id);
          if (!chat){
            if (message.user_id_sender != userId){
              this.notifications.set(message.chat_id, 1);
            } else{
              this.notifications.set(message.chat_id, 0);
            }
            this.chatService.saveNotifications(this.notifications);
            this.fetchChats();
            return;
          }
          chat.lastMessage = message;
          chat.updatedAt = message.createdAt;
          this.sortChatsByDate();
          this.notifications.set(chat.id, this.notifications.get(chat.id)! + 1);
          this.chatService.saveNotifications(this.notifications);
        });
      }
    }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      if(params['productId']) {
        this.productId = params['productId'];
      }
      this.authService.getToken().then(token => {
        this.token = token!;
        this.fetchChats();
      });
    });
    
  }

  async fetchChats(){
    if(this.productId === 0){
      this.fetchChatsByUser();
      return;
    }
    this.fetchChatsByProduct();
  }

  fetchNotifications(){
    this.chatService.getNotifications().then(notifications => {
      if (notifications && notifications.size > 0){
        this.notifications = notifications;
        return;
      }
      for(let chat of this.chats){
        this.notifications.set(chat.id, 0);
      }
      this.chatService.saveNotifications(this.notifications);
    });
  }

  fetchChatsByUser(){
    this.chatService.getUserChats(this.token).subscribe(chats => {
      this.chats = chats;
      this.sortChatsByDate();
      this.fetchNotifications();
    });
  }

  fetchChatsByProduct(){
    this.chatService.getProductChats(this.productId, this.token).subscribe(chats => {
      this.chats = chats;
      this.sortChatsByDate();
      this.fetchNotifications();
    });
  }

  goToChatDetail(chat: Chat){
    this.router.navigate(['/chats/' + chat.id, {
      "productId": chat.product.id,
      "userId": this.authService.myUser?.id
    }]);
  }

  sortChatsByDate(){
    this.chats.sort((a, b) => {
      return new Date(b.lastMessage.updatedAt).getTime() - new Date(a.lastMessage.updatedAt).getTime();
    });
  }

  getChatNotifications(chatId: number){
    const notificationsAmount = this.notifications.get(chatId);
    if(typeof(notificationsAmount) != 'number'){
      return "";
    }
    return notificationsAmount!==0? notificationsAmount+"" : "";
  }

}