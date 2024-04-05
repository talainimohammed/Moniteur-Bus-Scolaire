import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../environments/env';

@Injectable({
  providedIn: 'root'
})
export class BusService {

  private apiurl=environment.urlapi+"/bus";
  constructor(private httpclient:HttpClient) { }
  private token="eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQ0hBVUZGRVVSIiwiaWQiOjMsInN1YiI6InF3ZXJ0eTIiLCJpYXQiOjE3MTIyMzk4NTYsImV4cCI6MTcxMjI0MTY1Nn0.HlOHPDDRdXV1T-HcithDG9VprZikdPYuxm-vhCfkIyE";
  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': `Bearer ${this.token}`
  });
  private requestOptions = { headers: this.headers };
  getBus(){
    return this.httpclient.get(this.apiurl, this.requestOptions);
  }
  getBusById(id:any){
    return this.httpclient.get(this.apiurl+"/"+id, this.requestOptions);
  }
  addBus(bus:any){
    return this.httpclient.post(this.apiurl,bus, this.requestOptions);
  }
  updateBus(id:any,bus:any){
    return this.httpclient.put(this.apiurl+"/"+id,bus, this.requestOptions);
  }
  deleteBus(id:any){
    return this.httpclient.delete(this.apiurl+"/"+id, this.requestOptions);
  }
}
