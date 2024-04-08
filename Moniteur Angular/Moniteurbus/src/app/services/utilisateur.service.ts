import { Injectable } from '@angular/core';
import { environment } from '../environments/env';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Role } from '../enum/role';

@Injectable({
  providedIn: 'root'
})
export class UtilisateurService {

  

  private apiurl=environment.urlapi+"/utilisateur";
  private token=environment.tokenstring;
  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
  });
  private requestOptions = { headers: this.headers };
  constructor(private httpclient:HttpClient) { }

  getutilisateurs(role:Role){
    return this.httpclient.get(this.apiurl+"/role/"+role,this.requestOptions);
  }
  createutilisateur(data:any){
    return this.httpclient.post(this.apiurl,data,this.requestOptions);
  }
  getutilisateur(id:any){
    return this.httpclient.get(this.apiurl+"/"+id,this.requestOptions);
  }
  updateutilisateur(id:any,data:any){
    return this.httpclient.put(this.apiurl+"/"+id,data,this.requestOptions);
  }
  deleteutilisateur(id:any){
    return this.httpclient.delete(this.apiurl+"/"+id,this.requestOptions);
  }
}
