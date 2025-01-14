import { ComponentFixture, TestBed } from '@angular/core/testing';
import { UserProductsPage } from './user-products.page';

describe('UserProductsPage', () => {
  let component: UserProductsPage;
  let fixture: ComponentFixture<UserProductsPage>;

  beforeEach(async(() => {
    fixture = TestBed.createComponent(UserProductsPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
