import { Component, OnInit, Output, EventEmitter, Input, ViewChild } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { Category } from 'src/app/models/Category';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-category-modal',
  templateUrl: './category-modal.component.html',
  styleUrls: ['./category-modal.component.scss'],
})
export class CategoryModalComponent  implements OnInit {
  selectedCategoryId: number = 0;
  categories: Category[] = [];
  @Input() triggerId: string = '';
  @Output() categoryEmitter: EventEmitter<number> = new EventEmitter<number>();

  constructor(private api: CategoryService, private modalController: ModalController) { }

  ngOnInit() {
    this.api.getCategories().subscribe((categories: Category[]) => {
      this.categories = categories;
    }); 
  }

  selectCategory(newId: number) {
    this.selectedCategoryId = newId;
    this.confirm();
  }

  cancel(){
    this.selectedCategoryId = 0;
    this.modalController.dismiss();
  }

  confirm(){
    this.emitCategory();
    this.selectedCategoryId = 0;
    this.modalController.dismiss();
  }

  emitCategory() {
    this.categoryEmitter.emit(this.selectedCategoryId);
  }

}
