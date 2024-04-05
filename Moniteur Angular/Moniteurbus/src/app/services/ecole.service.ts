import { Injectable } from '@angular/core';
import {environment} from "../environments/env";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Ecole} from "../models/ecole";

@Injectable({
  providedIn: 'root'
})
export class EcoleService {

  private apiurl=environment.urlapi+"/ecole";
  private token="eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQ0hBVUZGRVVSIiwiaWQiOjEsInN1YiI6InF3ZXJ0eTEiLCJpYXQiOjE3MTIyMzEyNjgsImV4cCI6MTcxMjIzMzA2OH0.YPAveaUB8KrAi2kQYycjx4Kc1wd03vswAoDeLtgJSVU";
  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': `Bearer ${this.token}`
  });
  private requestOptions = { headers: this.headers };
  constructor(private http:HttpClient) { }

  getEcoles():Observable<Ecole[]>{
    return this.http.get<Ecole[]>(this.apiurl,this.requestOptions);
  }
  createEcole(data:any){
    return this.http.post(this.apiurl,data,this.requestOptions);
  }
  getEcole(id:any):Observable<Ecole>{
    return this.http.get<Ecole>(this.apiurl+"/"+id,this.requestOptions);
  }
  updateEcole(id:any,data:any){
    return this.http.put(this.apiurl+"/"+id,data,this.requestOptions);
  }
  deleteEcole(id:any){
    return this.http.delete(this.apiurl+"/"+id),this.requestOptions;
  }
}
