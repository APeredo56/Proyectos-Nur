import { Component, OnInit } from '@angular/core';
import { Camera, CameraResultType, CameraSource } from '@capacitor/camera';
import { LoadingController, ModalController } from '@ionic/angular';
import { ImageService } from 'src/app/services/image.service';

@Component({
  selector: 'app-send-image-modal',
  templateUrl: './send-image-modal.component.html',
  styleUrls: ['./send-image-modal.component.scss'],
})
export class SendImageModalComponent  implements OnInit {
  imageUrl: string = '';
  imageFile: any = null;
  loading: HTMLIonLoadingElement | undefined;

  constructor(private modalController: ModalController, private imageService: ImageService,
    private loadingCtrl: LoadingController) { }

  ngOnInit() {}

  cancel(){
    this.modalController.dismiss();
  }

  confirm(){
    this.modalController.dismiss([this.imageUrl, this.imageFile]);
  }

  openGallery = async () => {
    this.showLoading();
    const image = await Camera.getPhoto({
      quality: 50,
      promptLabelPhoto: "Abrir Galería",
      promptLabelPicture: "Abrir la Cámara",
      allowEditing: true,
      resultType: CameraResultType.DataUrl,
      source: CameraSource.Prompt,
    });
    this.imageUrl = image.dataUrl!;
    const blob = this.imageService.dataURLToBlob(image.dataUrl!);
    this.imageService.compressImage(blob).then(compressedImage => {
      this.imageFile = compressedImage;
      this.loading?.dismiss();
    });
  }

  async showLoading() {
    this.loading = await this.loadingCtrl.create({
      message: 'Cargando imagen...'
    });
    this.loading.present();
  }
  
}
