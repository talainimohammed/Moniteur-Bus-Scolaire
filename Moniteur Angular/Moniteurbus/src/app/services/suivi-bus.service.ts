import { Injectable } from '@angular/core';
import { environment } from '../environments/env';
import { HttpClient } from '@angular/common/http';
import { SuiviBus } from '../models/suivi-bus';

@Injectable({
  providedIn: 'root'
})
export class SuiviBusService {

  private apiurl=environment.urlapi+"/realtimeloc";
  constructor(private httpclient:HttpClient) { }

  getRealTimeLoc(){
    return this.httpclient.get(this.apiurl);
  }
  addRealTimeLoc(data:SuiviBus){
    return this.httpclient.post(this.apiurl,data);
  }
  getRealTimeLocByBusId(id:number){
    return this.httpclient.get(this.apiurl+"/bus/"+id);
  }
}
