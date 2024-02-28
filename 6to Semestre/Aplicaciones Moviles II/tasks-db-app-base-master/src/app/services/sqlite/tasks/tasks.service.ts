import {Injectable} from '@angular/core';
import {SqliteService} from "../sqlite.service";
import {Task} from "./models/task";

@Injectable({
  providedIn: 'root'
})
export class TasksService {

  constructor(private sqliteService: SqliteService) {
  }

  async insert(title: string, description: string): Promise<boolean> {
    let result = false;
    this.sqliteService.db.executeSql('insert into tasks(title, description) values(?,?)', [title, description])
      .then(() => result = true)
      .catch(e => console.log(e));
    return result;
  }

  async getAll(): Promise<Task[]> {
    const tasks: Task[] = [];
    this.sqliteService.db.executeSql('select * from tasks', [])
      .then((data) => {
        if (data.rows.length > 0) {
          for (let i = 0; i < data.rows.length; i++) {
            tasks.push(data.rows.item(i));
          }
        }
      })
      .catch(e => console.log(e));
    return tasks;
  }

  async getById(id: string): Promise<Task> {
    let task: Task;
    this.sqliteService.db.executeSql('select * from tasks where id = ?', [id])
      .then((data) => {
        if (data.rows.length > 0) {
          task = data.rows.item(0);
        }
      })
      .catch(e => console.log(e));
    return task;
  }
}
