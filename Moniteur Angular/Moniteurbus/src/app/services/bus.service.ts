import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../environments/env';

@Injectable({
  providedIn: 'root'
})
export class BusService {

  private apiurl=environment.urlapi+"/bus";
  constructor(private httpclient:HttpClient) { }

  getBus(){
    return this.httpclient.get(this.apiurl);
  }
  getBusById(id:any){
    return this.httpclient.get(this.apiurl+"/"+id);
  }
  addBus(bus:any){
    return this.httpclient.post(this.apiurl,bus);
  }
  updateBus(id:any,bus:any){
    return this.httpclient.put(this.apiurl+"/"+id,bus);
  }
  deleteBus(id:any){
    return this.httpclient.delete(this.apiurl+"/"+id);
  }
}
