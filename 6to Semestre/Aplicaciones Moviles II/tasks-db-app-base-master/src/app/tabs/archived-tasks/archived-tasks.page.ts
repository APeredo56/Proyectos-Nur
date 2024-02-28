import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-archived-tasks',
  templateUrl: './archived-tasks.page.html',
  styleUrls: ['./archived-tasks.page.scss'],
})
export class ArchivedTasksPage implements OnInit {

  tasks: Task[] = [];
  constructor() { }

  ngOnInit() {
  }

}
