import { ComponentFixture, TestBed } from '@angular/core/testing';
import { SendLocationPage } from './send-location.page';

describe('SendLocationPage', () => {
  let component: SendLocationPage;
  let fixture: ComponentFixture<SendLocationPage>;

  beforeEach(async(() => {
    fixture = TestBed.createComponent(SendLocationPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
