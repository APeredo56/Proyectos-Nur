import { Component, Input, OnInit } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { Product } from 'src/app/models/Product';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-product-info-modal',
  templateUrl: './product-info-modal.component.html',
  styleUrls: ['./product-info-modal.component.scss'],
})
export class ProductInfoModalComponent  implements OnInit {
  @Input() product!: Product;
  baseAPIUrl: string = environment.baseAPIUrl;

  constructor(private modalController: ModalController) { }

  ngOnInit() {}

  getStaticMapUrl(){
    const apiKey = environment.mapsKey;
    const lat = this.product?.latitude;
    const lng = this.product?.longitude;
    return "https://maps.googleapis.com/maps/api/staticmap?center="+lat+","+lng+"&zoom=17&size=600x300&maptype=roadmap&markers=color:red%7Clabel:C%7C"+lat+","+lng+"&key="+apiKey;
  }

  dismiss(){
    this.modalController.dismiss();
  }
}
