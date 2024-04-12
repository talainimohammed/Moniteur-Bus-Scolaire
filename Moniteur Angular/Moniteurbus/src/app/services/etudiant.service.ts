import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Etudiant } from '../models/etudiant';
import { environment } from '../environments/env';

@Injectable({
  providedIn: 'root'
})
export class EtudiantService {

  private apiurl = environment.urlapi + "/etudiant";
  private token=environment.tokenstring;
  private headers = new HttpHeaders({
    'Content-Type': 'application/json'
  });
  private requestOptions = { headers: this.headers };
  constructor(private httpclient: HttpClient) { }

  getEtudiants(id:number): Observable<Etudiant[]> {
    return this.httpclient.get<Etudiant[]>(this.apiurl+ "/ecole/" + id,this.requestOptions);
  }
  createEtudiant(data: any) {
    return this.httpclient.post(this.apiurl, data,this.requestOptions);
  }
  getEtudiant(id: any): Observable<Etudiant> {
    return this.httpclient.get<Etudiant>(this.apiurl + "/" + id,this.requestOptions);
  }
  getEtudiantByemail(email: string): Observable<Etudiant> {
    return this.httpclient.get<Etudiant>(this.apiurl + "/email/" + email,this.requestOptions);
  }
  updateEtudiant(id: any, data: any) {
    return this.httpclient.put(this.apiurl + "/" + id, data,this.requestOptions);
  }
  deleteEtudiant(id: any) {
    return this.httpclient.delete(this.apiurl + "/" + id,this.requestOptions);
  }

}
