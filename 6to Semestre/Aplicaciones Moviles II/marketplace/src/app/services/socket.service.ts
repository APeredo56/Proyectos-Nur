import { Injectable } from '@angular/core';
import { Socket } from 'ngx-socket-io';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SocketService {

  constructor(private socket: Socket) { 
    this.socket.on('connect', () => {
      console.log('Conectado al servidor');
    });
  }

  sendDebugger(userId: number){
    this.socket.emit('debugger', {id: userId});
  }

  receiveDebugger(){
    return this.socket.fromEvent('debugger').pipe(map((data: any) => data));
  }

  sendEmitDebugger(userId: number){
    this.socket.emit('emitDebugger', {id: userId});
  }

  receiveEmitDebugger(){
    return this.socket.fromEvent('emitDebugger').pipe(map((data: any) => data));
  }

  identifyUser(userId: number){
    this.socket.emit('identificarUsuario', {id: userId});
  }

  getMessages(){
    return this.socket.fromEvent('mensajeRecibido').pipe(map((data: any) => data));
  }

}
