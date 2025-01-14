import { Component, ViewChild } from '@angular/core';
import { Geolocation } from '@capacitor/geolocation';
import { IonSearchbar } from '@ionic/angular';
import { Product } from 'src/app/models/Product';
import { ProductService } from 'src/app/services/product.service';
import { environment } from 'src/environments/environment';
import { AuthService } from 'src/app/services/auth.service';
import { Subscription } from 'rxjs';


@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage {
  @ViewChild('mySearchBar') searchbar!: IonSearchbar;
  productList: Product[] = [];
  baseAPIUrl: string = environment.baseAPIUrl;
  searchBarVisible: boolean = false;
  searchTerm: string = '';
  loggedIn: boolean = false;
  authSubscription : Subscription;
  locationSubscription: Subscription;
  searchCategoryId: number = 0;

  constructor(
    private productService: ProductService, 
    private auth: AuthService) {
    this.authSubscription = this.auth.isLoggedIn.subscribe((res) => {
      this.loggedIn = res;
    });
    this.locationSubscription = this.productService.locationChange.subscribe(() => {
      if(this.searchCategoryId === 0){
        this.fetchProducts();
        return;
      }
      this.searchWithCategory(this.searchCategoryId);
    });
  }

  ionViewWillEnter(){
    this.getCurrentLocation().then(() => {
      this.fetchProducts();
    });
  }

  async getCurrentLocation() {
    const coordinates = await Geolocation.getCurrentPosition();
    this.productService.searchLatitude = coordinates.coords.latitude;
    this.productService.searchLongitude = coordinates.coords.longitude;
  }

  fetchProducts() {
    this.productService.searchProducts(
      this.productService.searchLatitude!, 
      this.productService.searchLongitude!, 
      this.productService.searchRadius).subscribe((res) => {
        this.productList = res;
      }
    );
  } 

  showSearchBar() {
    this.searchBarVisible = true;
    setTimeout(() => {  
      this.searchbar.setFocus();
  }, 50);
  }

  containsSearchTerm(product: Product) {
    if (this.searchTerm === '') {
      return true;
    }
    return product.name.toLowerCase().includes(this.searchTerm.toLowerCase());
  }

  searchWithCategory(category: number) {
    this.searchCategoryId = category;
    if (category === 0) {
      this.fetchProducts();
      return;
    } 
    this.productService.searchProducts(
      this.productService.searchLatitude!, 
      this.productService.searchLongitude!,  
      this.productService.searchRadius).subscribe((res: Product[]) => {
        this.productList = res.filter((product: Product) => {
          return product.category_id === category;
        });
      }
    );
  }

  logOut(){
    this.auth.logout().then(() => {
      this.auth.setLoggedIn(false);
    });
  }

  ngOnDestroy() {
    this.authSubscription.unsubscribe();
  }

  
  

}
