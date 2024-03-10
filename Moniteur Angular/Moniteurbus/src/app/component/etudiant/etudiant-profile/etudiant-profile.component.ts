import { Component, OnInit } from '@angular/core';
import { EtudiantService } from '../../../services/etudiant.service';
import { Etudiant } from '../../../models/etudiant';

@Component({
  selector: 'app-etudiant-profile',
  templateUrl: './etudiant-profile.component.html',
  styleUrl: './etudiant-profile.component.css'
})
export class EtudiantProfileComponent implements OnInit{
  etudiant:Etudiant={
    etudiantId:0,
    nom:"",
    prenom:"",
    adresse:"",
    dateNaissance:"",
    tel:"",
    niveau:"",
    busId:0,
    locationId:0,
    latitude:0,
    longtitude:0,
    ecoleId:0
  };
  submitted=false;
  constructor(private etudiantservice:EtudiantService) { }

  ngOnInit(): void {
   // this.retrieveEtudiant(1);
  }

  retrieveEtudiant(id:any){
    this.etudiantservice.getEtudiant(id).subscribe(
      data=>{
        console.log(data);
      },
      error=>{
        console.log(error);
      }
    );
  }
  saveEtudiant(){
    const data={
      nom:this.etudiant.nom,
      prenom:this.etudiant.prenom,
      adresse:this.etudiant.adresse,
      dateNaissance:this.etudiant.dateNaissance,
      tel:this.etudiant.tel,
      niveau:this.etudiant.niveau,
      busId:1,
      locationId:0,
      latitude:this.etudiant.latitude,
      longtitude:this.etudiant.longtitude,
      ecoleId:this.etudiant.ecoleId
    };
    console.log(data);
    
    /*this.etudiantservice.createEtudiant(data).subscribe(
      response=>{
        console.log(response);
      },
      error=>{
        console.log(error);
      }
    );*/
  }
  updateEtudiant(){
    const data={
      nom:"test",
      prenom:"test",
      email:""
    };
    this.etudiantservice.updateEtudiant(1,data).subscribe(
      response=>{
        console.log(response);
      },
      error=>{
        console.log(error);
      }
    );
  }


}
