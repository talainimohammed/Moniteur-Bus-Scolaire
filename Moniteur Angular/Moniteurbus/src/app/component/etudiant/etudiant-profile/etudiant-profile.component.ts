import { Component, OnInit } from '@angular/core';
import { EtudiantService } from '../../../services/etudiant.service';
import { Etudiant } from '../../../models/etudiant';
import { ActivatedRoute } from '@angular/router';
import { BusService } from '../../../services/bus.service';
import { Bus } from '../../../models/bus';
import { ChauffeurService } from '../../../services/chauffeur.service';
import { Chauffeur } from '../../../models/chauffeur';

@Component({
  selector: 'app-etudiant-profile',
  templateUrl: './etudiant-profile.component.html',
  styleUrl: './etudiant-profile.component.css'
})
export class EtudiantProfileComponent implements OnInit{

  etudiant:Etudiant=new Etudiant();
  bus?: Bus[];
  chauffeur: Chauffeur=new Chauffeur();
  id:number=0;
  submitted=false;
  constructor(private etudiantservice:EtudiantService,private busService:BusService,private chauffeurService:ChauffeurService,private route: ActivatedRoute) { }

  ngOnInit(): void {
    if(this.route.snapshot.params['id']!=null){
      this.id= this.route.snapshot.params['id'];
    }
    if(this.id!=0){
      this.retrieveEtudiant(this.id);
    }
    this.getBus();
  }
  onChange($event: Event) {
    if (this.etudiant.busId!=null) {
      this.getChauffeur(this.etudiant.busId);
    }
  }
  getBus(){
    this.busService.getBus().subscribe(
      data=>{
        this.bus = data as Bus[];
        console.log(data);
      },
      error=>{
        console.log(error);
      }
    );
  }
  getChauffeur(id: number): void {
    this.chauffeurService.getChauffeur(id)
      .subscribe(
        data => {
          this.chauffeur = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }
  retrieveEtudiant(id:any){
    this.etudiantservice.getEtudiant(id).subscribe(
      data=>{
        this.etudiant=data;
        if (this.etudiant.busId!=null) {
          this.getChauffeur(this.etudiant.busId);
        }
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
      email:this.etudiant.email,
      tel:this.etudiant.tel,
      niveau:this.etudiant.niveau,
      busId:this.etudiant.busId,
      locationId:0,
      latitude:this.etudiant.latitude,
      longtitude:this.etudiant.longtitude,
      ecoleId:1
    };
    console.log(data);
    
    this.etudiantservice.createEtudiant(data).subscribe(
      response=>{
        console.log(response);
      },
      error=>{
        console.log(error);
      }
    );
  }
  updateEtudiant(id:number){
    const data={
      nom:this.etudiant.nom,
      prenom:this.etudiant.prenom,
      adresse:this.etudiant.adresse,
      dateNaissance:this.etudiant.dateNaissance,
      email:this.etudiant.email,
      tel:this.etudiant.tel,
      niveau:this.etudiant.niveau,
      busId:this.etudiant.busId,
      locationId:this.etudiant.locationId,
      latitude:this.etudiant.latitude,
      longtitude:this.etudiant.longtitude,
      ecoleId:this.etudiant.ecoleId
    };
    this.etudiantservice.updateEtudiant(id,data).subscribe(
      response=>{
        console.log(response);
      },
      error=>{
        console.log(error);
      }
    );
  }


}
