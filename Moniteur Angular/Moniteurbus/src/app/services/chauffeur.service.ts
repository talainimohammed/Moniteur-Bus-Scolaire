import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../environments/env';
import { Role } from '../enum/role';

@Injectable({
  providedIn: 'root'
})
export class ChauffeurService {


  private apiurl=environment.urlapi+"/utilisateur";
  private token=environment.tokenstring;
  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
  });
  private requestOptions = { headers: this.headers };
  constructor(private httpclient:HttpClient) { }

  getChauffeurs(id:number){
    return this.httpclient.get(this.apiurl+"/role/"+Role.CHAUFFEUR+"/ecole/"+id,this.requestOptions);
  }
  createChauffeur(data:any){
    return this.httpclient.post(this.apiurl,data,this.requestOptions);
  }
  getChauffeur(id:any){
    return this.httpclient.get(this.apiurl+"/"+id,this.requestOptions);
  }
  updateChauffeur(id:any,data:any){
    return this.httpclient.put(this.apiurl+"/"+id,data,this.requestOptions);
  }
  deleteChauffeur(id:any){
    return this.httpclient.delete(this.apiurl+"/"+id,this.requestOptions);
  }
}
