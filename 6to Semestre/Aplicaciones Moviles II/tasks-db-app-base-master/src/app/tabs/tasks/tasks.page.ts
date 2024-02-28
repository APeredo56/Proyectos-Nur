import {Component, OnInit} from '@angular/core';
import {AlertController} from "@ionic/angular";
import {Task} from "../../services/sqlite/tasks/models/task";
import {TasksService} from "../../services/sqlite/tasks/tasks.service";

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.page.html',
  styleUrls: ['./tasks.page.scss'],
})
export class TasksPage implements OnInit {

  tasks: Task[] = [];

  constructor(private alertController: AlertController, private tasksService: TasksService) {
  }

  ngOnInit() {
    this.loadTasks();
  }

  loadTasks() {
    this.tasksService.getAll().then((tasks) => {
      this.tasks = tasks;
    })
  }

  async presentTaskForm() {
    const alert = await this.alertController.create({
      header: 'Add a new task',
      buttons: [
        {
          text: 'Cancel',
          role: 'cancel',
        },
        {
          text: 'Ok',
          role: 'confirm',
          handler: (alertData) => {
            if (alertData.title && alertData.description) {
              this.tasksService.insert(alertData.title, alertData.description);
              this.loadTasks();
              return true;
            } else {
              return false;
            }
          },
        },
      ],
      inputs: [
        {
          name: 'title',
          type: 'text',
          placeholder: 'title',
        },
        {
          name: 'description',
          type: 'textarea',
          placeholder: 'Description',
        },
      ],
    });

    await alert.present();
  }


}
