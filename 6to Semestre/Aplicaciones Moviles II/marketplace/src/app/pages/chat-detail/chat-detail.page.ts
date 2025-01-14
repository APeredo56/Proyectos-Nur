import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoadingController, ModalController } from '@ionic/angular';
import { Subscription } from 'rxjs';
import { ImageViewModalComponent } from 'src/app/components/modals/image-view-modal/image-view-modal.component';
import { SendImageModalComponent } from 'src/app/components/modals/send-image-modal/send-image-modal.component';
import { ProductInfoModalComponent } from 'src/app/components/product-info-modal/product-info-modal.component';
import { Message } from 'src/app/models/Message';
import { Product } from 'src/app/models/Product';
import { AuthService } from 'src/app/services/auth.service';
import { ChatService } from 'src/app/services/chat.service';
import { ProductService } from 'src/app/services/product.service';
import { SocketService } from 'src/app/services/socket.service';
import { environment } from 'src/environments/environment';


@Component({
  selector: 'app-chat-detail',
  templateUrl: './chat-detail.page.html',
  styleUrls: ['./chat-detail.page.scss'],
})
export class ChatDetailPage implements OnInit {
  @ViewChild('main__content', {static: true}) mainContainer!: ElementRef;
  chatId: number = 0;
  sellerId: number = 0;
  product: Product | undefined;
  userId: number = 0;
  messages: Message[] = [];
  token = '';
  message: string = '';
  baseAPIUrl: string = environment.baseAPIUrl;
  isPopOverOpen: boolean = false;
  private messageSubscription: Subscription | undefined;
  loading: HTMLIonLoadingElement | undefined;

  constructor(private route: ActivatedRoute, 
              private chatService: ChatService, 
              private productService: ProductService,
              private authService: AuthService,
              private modalCtrl: ModalController,
              private socket: SocketService,
              private router: Router,
              private loadingCtrl: LoadingController) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.chatId = params['id'];
      this.authService.getToken().then(token => {
        this.token = token!;
        this.userId = params['userId'];
        this.productService.getById(params['productId']).subscribe(product => {
          this.product = product;
          this.sellerId = product.user_id!;
          if(this.chatId != 0){
            this.fetchMessages(this.chatId);
            this.chatService.markChatAsRead(this.chatId);
            return;
          }
        });
      });
    });
    this.messageSubscription = this.socket.getMessages().subscribe((data) =>{
      const newMessage: Message = {
        id: data.id,
        chat_id: data.chat_id,
        type: data.type,
        user_id_sender: data.user_id_sender,
        user_id_receiver: data.user_id_receiver,
        message: data.message,
        image_url: data.image_url,
        latitude: data.latitude,
        longitude: data.longitude,
        image_extension: data.image_extension,
        createdAt: data.createdAt,
        updatedAt: data.updatedAt,
        user_sender: data.user_sender,
        user_receiver: data.user_receiver,
      };
      this.messages.push(newMessage);
      setTimeout(() => {
        this.scrollToBottom();
      }, 100);
    });
    this.chatService.locationSent.subscribe(async () => {
      if(this.chatId == 0){
        await this.createChat();
      }
      this.chatService.sendLocationMessage(
        this.chatId, 
        this.chatService.msgLatitude!,
        this.chatService.msgLongitude!, 
        this.token).subscribe(message => {
          this.messages.push(message);
          setTimeout(() => {
            this.scrollToBottom();
          }, 100);
      });
    });
  }

  fetchMessages(chatId: number){
    this.chatService.getChatMessages(chatId, this.token).subscribe(messages => {
      this.messages = messages;
      setTimeout(() => {
        this.scrollToBottom();
      }, 100);
    });
  }

  scrollToBottom() {
    const container = this.mainContainer.nativeElement;
    container.scrollTop = container.scrollHeight;
  }

  async createChat(): Promise<number> {
    return new Promise<number>((resolve) => {
      this.chatService.createChat(this.product!.id, this.token).subscribe(chat => {
        this.chatId = chat.id;
        resolve(this.chatId);
      });
    });
  }

  async sendTextMessage(){
    if(this.message.trim() == ''){
      return;
    }
    if(this.chatId == 0){
      await this.createChat();
    }
    this.chatService.sendTextMessage(this.chatId, this.message, this.token).subscribe(message => {
      this.messages.push(message);
      this.message = '';
      setTimeout(() => {
        this.scrollToBottom();
      }, 100);
    });
  }

  sellProduct(){
    if(this.product!.sold == 1){
      return;
    }
    this.productService.sellProduct(this.product!.id, this.token).subscribe(product => {
      this.product!.sold = 1;
    });
  }

  async openInfoModal(){
    const modal = await this.modalCtrl.create({
      component: ProductInfoModalComponent,
      componentProps: {
        product: this.product!,
      },
      cssClass: 'product__info__modal'
    });
    return await modal.present();
  }

  async openSendImageModal(){
    const modal = await this.modalCtrl.create({
      component: SendImageModalComponent,
      cssClass: 'send__img__modal'
    });
    this.isPopOverOpen = false;
    modal.onDidDismiss().then(async (res) => {
      if(!res.data){
        return
      }
      if(this.chatId == 0){
        await this.createChat();
      }
      const imageBlob = res.data[1];
      this.showLoading();
      this.chatService.sendImageMessage(this.chatId, imageBlob, this.token).subscribe(message => {
        this.messages.push(message);
        this.loading?.dismiss();
        setTimeout(() => {
          this.scrollToBottom();
        }, 100);
      });
    });
    return await modal.present();
  }

  private async getBlobFromUri(uri: string): Promise<Blob> {
    const response = await fetch(uri);
    return response.blob();
  }

  getStaticMapUrl(lat: number, lng: number){
    const apiKey = environment.mapsKey;
    return "https://maps.googleapis.com/maps/api/staticmap?center="+lat+","+lng+"&zoom=17&size=600x300&maptype=roadmap&markers=color:red%7Clabel:C%7C"+lat+","+lng+"&key="+apiKey;
  }

  openMapsApp(lat: number, lng: number){
    window.open("https://www.google.com/maps/search/?api=1&query="+lat+","+lng, '_system');
  }

  openImageViewModal(image: string, sender: string){
    this.modalCtrl.create({
      component: ImageViewModalComponent,
      componentProps: {
        image: image,
        sender: sender,
      },
      cssClass: 'image__view__modal'
    }).then(modal => {
      modal.present();
    });
  }

  goToSendLocationPage(){
    this.isPopOverOpen = false;
    setTimeout(() => {
      this.router.navigateByUrl('/sendLocation');
    }, 100);
  }

  async showLoading() {
    this.loading = await this.loadingCtrl.create({
      message: 'Enviando la imagen...'
    });

    this.loading.present();
  }

  ngOnDestroy() {
    if(this.messageSubscription){
      this.messageSubscription.unsubscribe();
    }
  }
}
