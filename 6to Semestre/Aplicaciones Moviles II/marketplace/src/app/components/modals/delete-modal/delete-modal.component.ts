import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { ModalController } from '@ionic/angular';

@Component({
  selector: 'app-delete-modal',
  templateUrl: './delete-modal.component.html',
  styleUrls: ['./delete-modal.component.scss'],
})
export class DeleteModalComponent  implements OnInit {
  @Input() title: string = '';
  @Input() message: string = '';

  constructor(private modalController: ModalController) { }

  ngOnInit() {}


  dismiss(confirmed: boolean){
    this.modalController.dismiss(confirmed);
  }

}
