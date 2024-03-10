import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Etudiant } from '../models/etudiant';
import { environment } from '../environments/env';

@Injectable({
  providedIn: 'root'
})
export class EtudiantService {

  private apiurl = environment.urlapi + "/etudiants";

  constructor(private httpclient: HttpClient) { }

  getEtudiants(): Observable<Etudiant[]> {
    return this.httpclient.get<Etudiant[]>(this.apiurl);
  }
  createEtudiant(data: any) {
    return this.httpclient.post(this.apiurl, data);
  }
  getEtudiant(id: any): Observable<Etudiant> {
    return this.httpclient.get<Etudiant>(this.apiurl + "/" + id);
  }
  updateEtudiant(id: any, data: any) {
    return this.httpclient.put(this.apiurl + "/" + id, data);
  }
  deleteEtudiant(id: any) {
    return this.httpclient.delete(this.apiurl + "/" + id);
  }

}
