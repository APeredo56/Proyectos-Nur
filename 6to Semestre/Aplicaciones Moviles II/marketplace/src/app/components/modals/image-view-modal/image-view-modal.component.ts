import { Component, Input, OnInit } from '@angular/core';
import { ModalController } from '@ionic/angular';

@Component({
  selector: 'app-image-view-modal',
  templateUrl: './image-view-modal.component.html',
  styleUrls: ['./image-view-modal.component.scss'],
})
export class ImageViewModalComponent  implements OnInit {
  @Input() image: string = '';
  @Input() sender: string = '';

  constructor(private modalController: ModalController) { }

  ngOnInit() {}

  dismiss(){
    this.modalController.dismiss();
  }
  
}
