import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Camera, CameraResultType, CameraSource } from '@capacitor/camera';
import { Geolocation } from '@capacitor/geolocation';
import { GoogleMap, Marker } from '@capacitor/google-maps';
import { Category } from 'src/app/models/Category';
import { CategoryService } from 'src/app/services/category.service';
import { ProductService } from 'src/app/services/product.service';
import { environment } from 'src/environments/environment';
import { Product, ProductImage } from '../../models/Product';
import { AuthService } from 'src/app/services/auth.service';
import { LoadingController, ModalController, NavController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';
import { DeleteModalComponent } from 'src/app/components/modals/delete-modal/delete-modal.component';
import { ImageService } from 'src/app/services/image.service';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.page.html',
  styleUrls: ['./product-form.page.scss'],
})
export class ProductFormPage implements OnInit {
  productForm: FormGroup = new FormGroup({
    name: new FormControl('', Validators.required),
    description: new FormControl('', Validators.required),
    price: new FormControl('', Validators.required),
    category: new FormControl('0', [Validators.required, Validators.min(1)]),
    latitude: new FormControl('', Validators.required),
    longitude: new FormControl('', Validators.required),
    images: new FormControl([] as ProductImage[], Validators.minLength(1)),
  });
  categories: Category[] = [];
  @ViewChild('swiper') swiperRef: ElementRef | undefined;
  @ViewChild('map', { static: true }) mapRef!: ElementRef<HTMLElement>;
  googleMap!: GoogleMap;
  marker: string | undefined;
  token: string = '';
  id: number = 0;
  baseAPIUrl: string = environment.baseAPIUrl;
  loading: HTMLIonLoadingElement | undefined;

  constructor(private categoryService: CategoryService,
              private productService: ProductService,
              private authService: AuthService,
              private navController: NavController,
              private route: ActivatedRoute,
              private modalCtrl: ModalController,
              private imageService: ImageService,
              private loadingCtrl: LoadingController) { }

  ngOnInit() {
    this.categoryService.getCategories().subscribe((res) => {
      this.categories = res;
    });
    this.authService.getToken().then((token) => {
      if(token){
        this.token = token;
      }
    });
    this.route.params.subscribe(params => {
      if (!params["id"]){
        this.getCurrentLocation().then((location) => {
          this.createMap(location.latitude, location.longitude);
        });
        return;
      }
      
      this.id = params["id"];
      this.productService.getById(this.id).subscribe(product => {
        this.productForm.controls['name'].setValue(product.name);
        this.productForm.controls['description'].setValue(product.description);
        this.productForm.controls['price'].setValue(product.price);
        this.productForm.controls['category'].setValue(product.category_id);
        this.productForm.controls['latitude'].setValue(product.latitude);
        this.productForm.controls['longitude'].setValue(product.longitude);
        this.updateSwiper();
        this.createMap(parseFloat(product.latitude), parseFloat(product.longitude)).then(() => {
          this.addMarker(parseFloat(product.latitude), parseFloat(product.longitude));
        });
        const imagesWithApiUrl = product.productimages.map((image) => {
          image.url = this.baseAPIUrl + image.url;
          return image;
        });
        this.productForm.controls['images'].setValue(imagesWithApiUrl);
      });
      
    });
  }

  async showLoading() {
    this.loading = await this.loadingCtrl.create({
      message: 'Guardando el producto...'
    });

    this.loading.present();
  }

  takePicture = async () => {
    const image = await Camera.getPhoto({
      quality: 90,
      allowEditing: true,
      resultType: CameraResultType.Uri,
      source: CameraSource.Camera,
    });  

    if (image == null) {
      return;
    }
    const imageObj: ProductImage = {
      url: image.webPath!,
      product_id: 0,
    };

    this.productForm.controls["images"].setValue([...this.productForm.controls["images"].value, imageObj]);
    this.updateSwiper();
  };

  openGallery = async () => {
    const images = await Camera.pickImages({
      quality: 90,
    });
    for (let image of images.photos) {
      const imageObj: ProductImage = {
        url: image.webPath,
        product_id: 0,
      };
      this.productForm.controls["images"].value.push(imageObj);
      this.updateSwiper();
    }
  };

  async getCurrentLocation() {
    const coordinates = await Geolocation.getCurrentPosition();
    const latitude = coordinates.coords.latitude;
    const longitude = coordinates.coords.longitude;
    return {latitude: latitude, longitude: longitude};
  }

  async createMap(inititalLatitude: number, initialLongitude: number) {
    this.googleMap = await GoogleMap.create({
      id: 'my-cool-map',
      element: this.mapRef.nativeElement,
      apiKey: environment.mapsKey,
      config: {
        center: {
          lat: inititalLatitude,
          lng: initialLongitude,
        },
        zoom: 15,
        disableDefaultUI: true,
      },
    });
    this.googleMap.setOnMapClickListener((event) => {
      if (this.marker) {
        this.googleMap.removeMarker(this.marker);
      } 
      this.addMarker(event.latitude, event.longitude);
    });
  }

  updateSwiper(){
    setTimeout(() => {  
      this.swiperRef?.nativeElement.swiper.update();
    }, 50);
  }
  
  async addMarker(latitude: number, longitude: number) {
    this.marker = await this.googleMap.addMarker({
      coordinate: {
        lat: latitude,
        lng: longitude,
      },
    });
    this.productForm.controls['latitude'].setValue(latitude);
    this.productForm.controls['longitude'].setValue(longitude);
  }

  async submitForm() {
    const product: Product = {
      id: this.id,
      name: this.productForm.controls['name'].value,
      description: this.productForm.controls['description'].value,
      price: this.productForm.controls['price'].value,
      category_id: this.productForm.controls['category'].value,
      latitude: this.productForm.controls['latitude'].value,
      longitude: this.productForm.controls['longitude'].value,
      status: 1,
      sold: 0,
      productimages: [],
      distance: 0,
    };
    this.showLoading();
    if (this.id === 0){
      this.createProduct(product);
    } else{
      this.updateProduct(product);
    }
    
  }

  createProduct(product: Product){
    this.productService.addProduct(product, this.token).subscribe(async (res) => {
      const imagesPromises = this.productForm.controls['images'].value.map(async (image: any) => {
        const blob = await this.getBlobFromUri(image.url);
        const compressedBlob = await this.imageService.compressImage(blob);
        return this.productService.addImage(res.id, compressedBlob!, this.token).toPromise();
      });
      await Promise.all(imagesPromises);
      this.loading?.dismiss();
      this.navController.back();
    });
  }

  private async getBlobFromUri(uri: string): Promise<Blob> {
    const response = await fetch(uri);
    return response.blob();
  }

  updateProduct(product: Product){
    this.productService.updateProduct(product, this.token).subscribe(async (res) => {
      const imagesPromises = this.productForm.controls['images'].value.map(async (image: any) => {
        if (image.product_id !== 0 && image.url.startsWith(this.baseAPIUrl)) {
          return; 
        }
        const blob = await this.getBlobFromUri(image.url);
        const compressedBlob = await this.imageService.compressImage(blob);
        return this.productService.addImage(res.id, compressedBlob!, this.token).toPromise();
      });
      await Promise.all(imagesPromises);
      this.loading?.dismiss();
      this.navController.back();
    });
  }

  async openDeleteProductModal(){
    const modal = await this.modalCtrl.create({
      component: DeleteModalComponent,
      componentProps: {
        title: 'Eliminar Producto',
        message: '¿Deseas eliminar este producto?',
      },
      cssClass: 'delete__modal'
    });
    modal.onDidDismiss().then((res) => {
      if (res.data){
        this.deleteProduct();
      }
    });
    return await modal.present();
  }

  async openDeleteImgModal(image: ProductImage){
    const modal = await this.modalCtrl.create({
      component: DeleteModalComponent,
      componentProps: {
        title: 'Eliminar Imagen',
        message: '¿Deseas eliminar esta imagen?',
      },
      cssClass: 'delete__modal'
    });
    modal.onDidDismiss().then((res) => {
      if (res.data){
        this.deleteImage(image);
      }
    });
    return await modal.present();
  }

  deleteProduct(){
    this.productService.deleteProduct(this.id, this.token).subscribe(() => {
      this.navController.back();
    });
  }

  deleteImage(image: ProductImage){
    const index = this.productForm.controls['images'].value.indexOf(image);
    this.productForm.controls['images'].value.splice(index, 1);;
    this.updateSwiper();
    if (image.product_id !== 0 && image.url.startsWith(this.baseAPIUrl)){
      this.productService.deleteImage(image.id!, this.token).subscribe(() => { });
    }
  }

  ngOnDestroy() {
    if (this.googleMap) {
      this.googleMap.destroy();
    }
    this.marker = undefined;
  }
}
