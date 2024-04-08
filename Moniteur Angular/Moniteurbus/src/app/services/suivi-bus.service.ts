import { Injectable } from '@angular/core';
import { environment } from '../environments/env';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { SuiviBus } from '../models/suivi-bus';

@Injectable({
  providedIn: 'root'
})
export class SuiviBusService {

  private apiurl=environment.urlapi+"/realtimeloc";
  private token=environment.tokenstring;
  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
  });
  private requestOptions = { headers: this.headers };
  constructor(private httpclient:HttpClient) { }

  getRealTimeLoc(){
    return this.httpclient.get(this.apiurl,this.requestOptions);
  }
  addRealTimeLoc(data:SuiviBus){
    return this.httpclient.post(this.apiurl,data,this.requestOptions);
  }
  getRealTimeLocByBusId(id:number){
    return this.httpclient.get(this.apiurl+"/bus/"+id,this.requestOptions);
  }
}
