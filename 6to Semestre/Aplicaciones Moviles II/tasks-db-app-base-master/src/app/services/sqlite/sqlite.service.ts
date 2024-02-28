import { Injectable } from '@angular/core';
import {SQLite, SQLiteObject} from "@awesome-cordova-plugins/sqlite/ngx";

@Injectable({
  providedIn: 'root'
})
export class SqliteService {

  db: SQLiteObject;

  constructor(private sqlite: SQLite) { }

  createDataBase(){
    this.sqlite.create({
      name: 'tasks.db',
      location: 'default'
    }).then((db: SQLiteObject) => {
      this.db = db;
      this.createTable();
    })
  }

  private createTable(){
    this.db.executeSql('create table if not exists tasks(id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR(30), description VARCHAR(200))', [])
      .then(() => console.log('tasks table created successfully'))
      .catch( e => console.log("ERROR CREATE TASKS TABLE", e))
  }
}
