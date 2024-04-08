import { Component, OnInit } from '@angular/core';
import {RouterLink} from "@angular/router";
import { EtudiantService } from '../../services/etudiant.service';
import { Etudiant } from '../../models/etudiant';
import { Userdata } from '../../models/userdata';

@Component({
  selector: 'app-etudiant',
  templateUrl: './etudiant.component.html',
  styleUrl: './etudiant.component.css'
})
export class EtudiantComponent implements OnInit{

  etudiants?:Etudiant[];
  searchetu?:'';
  userData:Userdata=new Userdata();
  id_ecole:number=0;
  constructor(private etudiantService:EtudiantService) { }

  ngOnInit(): void {
    this.userData= JSON.parse(localStorage.getItem('userData') as string);
    this.id_ecole = this.userData.idecole ?? 0;
    this.retrieveEtudiants();
  }

  retrieveEtudiants(){
    this.etudiantService.getEtudiants(this.id_ecole).subscribe(
      data=>{
        console.log(data);
        this.etudiants=data;
      },
      error=>{
        console.log(error);
      }
    );
  }

  deleteEtudiant(id:number){
      if(confirm("Voulez vous supprimer ce Etudiant?")){
        this.etudiantService.deleteEtudiant(id).subscribe(
          data=>{
            console.log(data);
            this.retrieveEtudiants();
          },
          error=>{
            console.log(error);
          }
        );
    }
  }


}
