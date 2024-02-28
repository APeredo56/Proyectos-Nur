import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ArchivedTasksPage } from './archived-tasks.page';

describe('ArchivedTasksPage', () => {
  let component: ArchivedTasksPage;
  let fixture: ComponentFixture<ArchivedTasksPage>;

  beforeEach(async(() => {
    fixture = TestBed.createComponent(ArchivedTasksPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
