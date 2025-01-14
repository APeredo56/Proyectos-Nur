import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from 'src/app/models/Product';
import { AuthService } from 'src/app/services/auth.service';
import { ProductService } from 'src/app/services/product.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-user-products',
  templateUrl: './user-products.page.html',
  styleUrls: ['./user-products.page.scss'],
})
export class UserProductsPage implements OnInit {
  products: Product[] = [];
  baseAPIUrl: string = environment.baseAPIUrl;

  constructor(private authService: AuthService, private productService: ProductService, private router: Router) { }

  ngOnInit() { }

  ionViewWillEnter(){
    this.authService.getToken().then(token => {
      this.productService.getProductsByUser(token!).subscribe(products => {
        this.products = products;
      });
    });
  }

  goToProductDetail(productId:number){
    this.router.navigateByUrl('/tabs/account/productList/' + productId);
  }

  openChat(event: MouseEvent, productId: number) {
    event.stopPropagation();
    this.router.navigate(['/product/' + productId + '/chats'], { queryParams: { productId: productId } })
  }

}
