import { Component, OnInit } from '@angular/core';
import {RouterLink} from "@angular/router";
import { EtudiantService } from '../../services/etudiant.service';
import { Etudiant } from '../../models/etudiant';
import { Userdata } from '../../models/userdata';
import { SuiviBus } from '../../models/suivi-bus';
import { FirebaseService } from '../../services/firebase.service';
import { ChauffeurService } from '../../services/chauffeur.service';
import { Chauffeur } from '../../models/chauffeur';
import { BusService } from '../../services/bus.service';
import { Bus } from '../../models/bus';
import { SuiviBusService } from '../../services/suivi-bus.service';

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
  currentpos:SuiviBus=new SuiviBus();
  center: google.maps.LatLngLiteral = {lat: 24, lng: 12};
  bus:Bus=new Bus();
  constructor(private suiviBus:SuiviBusService,private busService:BusService,private etudiantService:EtudiantService) { }

  ngOnInit(): void {
    this.userData= JSON.parse(localStorage.getItem('userData') as string);
    this.id_ecole = this.userData.idecole ?? 0;
    if(this.userData.roles?.includes("Ecole")){
    this.retrieveEtudiants();
    }
    if(this.userData.roles?.includes("CHAUFFEUR")){
      this.getBusByChauffeurId(this.userData.id ?? 0);
      this.retrieveEtudiantsByBusId();
      setInterval(() => {
        console.log('send localisation every 5s');
          navigator.geolocation.getCurrentPosition((position) => {
            this.center = {
              lat: position.coords.latitude,
              lng: position.coords.longitude,
            };
            this.currentpos.idbus=this.bus.idBus;    
            this.currentpos.latitude=this.center.lat;
            this.currentpos.longtitude=this.center.lng;
            this.saveRealTimeLoc(this.currentpos);
          });    
        
      }, 10000);
    }
  }
  getBusByChauffeurId(id:number){
    this.busService.getBusByChauffeur(id).subscribe(
      data=>{
        this.bus=data as Bus;
      },
      error=>{
        console.log(error);
      }
    );
  }
  saveRealTimeLoc(currentpos:any){
        const gdata:any = {
          idbus: this.currentpos.idbus,
          latitude: this.currentpos.latitude,
          longtitude: this.currentpos.longtitude
        };
        this.suiviBus.addRealTimeLoc(gdata).subscribe((data:any)=>{
          console.log(data);
        });
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
  retrieveEtudiantsByBusId(){
    this.etudiantService.getEtudiants(this.id_ecole).subscribe(
      data=>{
        console.log(data);
        this.etudiants=data as Etudiant[];
        this.etudiants = this.etudiants.filter(etudiant => etudiant.busId === this.bus.idBus);
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
