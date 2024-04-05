import { Injectable } from '@angular/core';
import { environment } from '../environments/env';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { SuiviBus } from '../models/suivi-bus';

@Injectable({
  providedIn: 'root'
})
export class SuiviBusService {

  private apiurl=environment.urlapi+"/realtimeloc";
  private token="eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQ0hBVUZGRVVSIiwiaWQiOjEsInN1YiI6InF3ZXJ0eTEiLCJpYXQiOjE3MTIyMzEyNjgsImV4cCI6MTcxMjIzMzA2OH0.YPAveaUB8KrAi2kQYycjx4Kc1wd03vswAoDeLtgJSVU";
  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': `Bearer ${this.token}`
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
