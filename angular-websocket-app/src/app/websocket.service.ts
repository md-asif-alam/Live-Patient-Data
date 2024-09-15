import { Injectable } from '@angular/core';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  private $socket: WebSocketSubject<any>;

  constructor() {
    this.$socket = webSocket('ws://localhost:8080/app/ws/patient-data');
  }

  getMessages() {
    console.log(this.$socket);
    return this.$socket.asObservable();
  }
}


