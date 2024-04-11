import { Component, OnInit } from '@angular/core';
import { EtudiantService } from '../../../services/etudiant.service';
import { Etudiant } from '../../../models/etudiant';
import { ActivatedRoute } from '@angular/router';
import { BusService } from '../../../services/bus.service';
import { Bus } from '../../../models/bus';
import { ChauffeurService } from '../../../services/chauffeur.service';
import { Chauffeur } from '../../../models/chauffeur';
import { Utilisateur } from '../../../models/utilisateur';
import { UtilisateurService } from '../../../services/utilisateur.service';
import { Userdata } from '../../../models/userdata';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-etudiant-profile',
  templateUrl: './etudiant-profile.component.html',
  styleUrl: './etudiant-profile.component.css'
})
export class EtudiantProfileComponent implements OnInit{

  etudiant:Etudiant=new Etudiant();
  bus?: Bus[];
  onebus?: Bus=new Bus();
  chauffeur: Chauffeur=new Chauffeur();
  utilisateur: Utilisateur=new Utilisateur();
  id:number=0;
  submitted=false;
  userData:Userdata=new Userdata();
  id_ecole:number=0;
  constructor(private etudiantservice:EtudiantService,private utilisateurService:UtilisateurService,private busService:BusService,private chauffeurService:ChauffeurService,private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.userData= JSON.parse(localStorage.getItem('userData') as string);
    this.id_ecole = this.userData.idecole ?? 0;
    if(this.route.snapshot.params['id']!=null){
      this.id= this.route.snapshot.params['id'];
    }
    if(this.id!=0){
      this.retrieveEtudiant(this.id);
    }
    this.getBus();
  }
  onSubmit(form: NgForm) {
    if (form.valid) {
      if(this.id==0){
        this.saveEtudiant();
      }else{
        this.updateEtudiant(this.id);
      }
    } else {
      form.controls['username'].markAsTouched();
      form.controls['first_name'].markAsTouched();
      form.controls['address'].markAsTouched();
      form.controls['datenaissance'].markAsTouched();
      form.controls['password'].markAsTouched();
      form.controls['email'].markAsTouched();
      form.controls['tel'].markAsTouched();
      form.controls['niveau'].markAsTouched();
      form.controls['bus'].markAsTouched();
      form.controls['latitude'].markAsTouched();
      form.controls['longtitude'].markAsTouched();

    }
  }
  onChange($event: Event) {
    if (this.etudiant.busId!=null) {
      this.getChauffeur(this.etudiant.busId);
    }
  }
  getBus(){
    this.busService.getBus(this.id_ecole).subscribe(
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
    this.busService.getBusById(id).subscribe(
      data=>{
        this.onebus=data as Bus;
        this.chauffeurService.getChauffeur(this.onebus.idchauffeur).subscribe(
          data1 => {
            this.chauffeur = data1;
            console.log(data1);
          },
          error => {
            console.log(error);
          }
        );
      },
      error=>{
        console.log(error);
      }
    );
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
      password:this.etudiant.password,
      tel:this.etudiant.tel,
      niveau:this.etudiant.niveau,
      busId:this.etudiant.busId,
      locationId:0,
      latitude:this.etudiant.latitude,
      longtitude:this.etudiant.longtitude,
      ecoleId:this.id_ecole
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
      ecoleId:this.id_ecole
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
