import { Component, OnInit } from '@angular/core';
import {RouterLink} from "@angular/router";
import { EtudiantService } from '../../services/etudiant.service';
import { Etudiant } from '../../models/etudiant';

@Component({
  selector: 'app-etudiant',
  templateUrl: './etudiant.component.html',
  styleUrl: './etudiant.component.css'
})
export class EtudiantComponent implements OnInit{

  etudiants?:Etudiant[];
  searchetu?:'';
  constructor(private etudiantService:EtudiantService) { }

  ngOnInit(): void {
    this.retrieveEtudiants();
  }

  retrieveEtudiants(){
    this.etudiantService.getEtudiants().subscribe(
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
