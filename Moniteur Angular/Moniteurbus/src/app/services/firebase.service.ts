import { Injectable } from '@angular/core';
import { environment } from '../environments/env';
import { HttpClient } from '@angular/common/http';
import { SuiviBus } from '../models/suivi-bus';

@Injectable({
  providedIn: 'root'
})
export class FirebaseService {

  private apiurl="https://monitor-bus-default-rtdb.firebaseio.com/loc/bus.json";
  constructor(private httpclient:HttpClient) { }

  getRealTimeLoc(){
    return this.httpclient.get(this.apiurl);
  }
  addRealTimeLoc(data:any){
    return this.httpclient.put(this.apiurl, data);
  }
  getRealTimeLocByBusId(){
    return this.httpclient.get(this.apiurl);
  }
}
