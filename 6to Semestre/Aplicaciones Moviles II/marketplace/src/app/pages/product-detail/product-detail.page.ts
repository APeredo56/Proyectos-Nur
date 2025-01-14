import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Product } from 'src/app/models/Product';
import { AuthService } from 'src/app/services/auth.service';
import { ChatService } from 'src/app/services/chat.service';
import { ProductService } from 'src/app/services/product.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.page.html',
  styleUrls: ['./product-detail.page.scss'],
})
export class ProductDetailPage implements OnInit {
  id: number = 0;
  product?: Product;
  baseAPIUrl: string = environment.baseAPIUrl;
  isLoggedIn : boolean = false;
  authSubscription: Subscription = new Subscription;

  constructor(private route: ActivatedRoute, private authService: AuthService, 
      private productService: ProductService, private router: Router, private chatService: ChatService) {
        
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      if (params["id"]){
        this.id = params["id"];
      }
    });
    this.productService.getById(this.id).subscribe(product => {
      this.product = product;
    });

    this.authSubscription = this.authService.isLoggedIn.subscribe((res) => {
      this.isLoggedIn = res;
    });
  }

  getStaticMapUrl(){
    const apiKey = environment.mapsKey;
    const lat = this.product?.latitude;
    const lng = this.product?.longitude;
    return "https://maps.googleapis.com/maps/api/staticmap?center="+lat+","+lng+"&zoom=17&size=600x300&maptype=roadmap&markers=color:red%7Clabel:C%7C"+lat+","+lng+"&key="+apiKey;
  }

  goToChat(){
    this.authService.getToken().then(token => {
      this.chatService.getUserChats(token!).subscribe(chats => {
        const chat = chats.find(chat => chat.product_id === this.product?.id);
        if(chat){
          this.router.navigate(['/chats/' + chat.id, {
            "productId": this.product!.id,
            "userId": this.authService.myUser!.id
          }]);
          return;
        }
        this.router.navigate(['/chats/' + 0, {
          "productId": this.product!.id,
          "userId": this.authService.myUser!.id
        }]);
      });
    });
    
  }

  openMapsApp(){
    window.open("https://www.google.com/maps/search/?api=1&query="+this.product!.latitude+","+this.product!.longitude, '_system');
  }

}
