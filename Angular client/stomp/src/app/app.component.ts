import { Component, OnInit } from '@angular/core';
import { StompService } from './stomp.service';

@Component({
  moduleId: module.id,
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.css'],
  providers: [StompService]
})
export class AppComponent implements OnInit {
  public inputField = '';
  public serverResponse: string;

  constructor(private _stompService: StompService) {
  }

  public ngOnInit(): void {
    this._stompService.connect('ws://localhost:8080/interest-websocket');
    this._stompService.getObservable().subscribe(payload => {
      this.serverResponse = payload.outputField;
    });
  }

  public send(): void {
    this._stompService.send(this.inputField);
  }
}