import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../environments/env';

@Injectable({
  providedIn: 'root'
})
export class BusService {

  private apiurl=environment.urlapi+"/bus";
  constructor(private httpclient:HttpClient) { }
  private token=environment.tokenstring;
  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
  });
  private requestOptions = { headers: this.headers };
  getBus(id:number){
    return this.httpclient.get(this.apiurl+"/ecole/"+id, this.requestOptions);
  }
  getBusById(id:any){
    return this.httpclient.get(this.apiurl+"/"+id, this.requestOptions);
  }
  getBusByChauffeur(id:any){
    return this.httpclient.get(this.apiurl+"/chauffeur/"+id, this.requestOptions);
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
