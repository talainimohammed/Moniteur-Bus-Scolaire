import { Injectable } from '@angular/core';
import {environment} from "../environments/env";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Ecole} from "../models/ecole";

@Injectable({
  providedIn: 'root'
})
export class EcoleService {

  private apiurl=environment.urlapi+"/ecole";

  constructor(private http:HttpClient) { }

  getEcoles():Observable<Ecole[]>{
    return this.http.get<Ecole[]>(this.apiurl);
  }
  createEcole(data:any){
    return this.http.post(this.apiurl,data);
  }
  getEcole(id:any):Observable<Ecole>{
    return this.http.get<Ecole>(this.apiurl+"/"+id);
  }
  updateEcole(id:any,data:any){
    return this.http.put(this.apiurl+"/"+id,data);
  }
  deleteEcole(id:any){
    return this.http.delete(this.apiurl+"/"+id);
  }
}
