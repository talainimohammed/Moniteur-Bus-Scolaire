import { Component, OnInit } from '@angular/core';
import { Chauffeur } from '../../../models/chauffeur';
import { ChauffeurService } from '../../../services/chauffeur.service';
import { Role } from '../../../enum/role';
import { ActivatedRoute } from '@angular/router';
import { Bus } from '../../../models/bus';
import { BusService } from '../../../services/bus.service';
import { Userdata } from '../../../models/userdata';

@Component({
  selector: 'app-chauffeur-profile',
  templateUrl: './chauffeur-profile.component.html',
  styleUrl: './chauffeur-profile.component.css'
})
export class ChauffeurProfileComponent implements OnInit{

  chauffeur: Chauffeur = new Chauffeur();
  chauffeur1: Chauffeur = new Chauffeur();
  userData:Userdata=new Userdata();
  id_ecole:number=0;
  bus?: Bus[];
  busupdate: Bus=new Bus();
  submitted = false;
  id: number=0;
  constructor( private chauffeurService:ChauffeurService,private busService:BusService,private route: ActivatedRoute) { }
  ngOnInit(): void {
    this.getBus();
    this.userData= JSON.parse(localStorage.getItem('userData') as string);
    this.id_ecole = this.userData.idecole ?? 0;
    if(this.route.snapshot.params['id']!=null){
      this.id= this.route.snapshot.params['id'];
      this.getChauffeur(this.id);
    }
  }
  onChange($event: Event) {
    console.log(this.busupdate);
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
    this.chauffeurService.getChauffeur(id)
      .subscribe(
        data => {
          this.chauffeur = data;
          console.log(data);
          this.busupdate=this.bus?.find(x=>x.idchauffeur==this.chauffeur.idUtilisateur)!;          
        },
        error => {
          console.log(error);
        });
  }
  saveChauffeur() {
    const data = {
      nom: this.chauffeur.nom,
      prenom: this.chauffeur.prenom,
      adresse: this.chauffeur.adresse,
      dateNaissance: this.chauffeur.dateNaissance,
      tel: this.chauffeur.tel,
      email: this.chauffeur.email,
      password:this.chauffeur.password,
      idEcole:this.id_ecole,
      roleEnum: Role.CHAUFFEUR
    };
    this.chauffeurService.createChauffeur(data)
      .subscribe(
        response => {
          this.chauffeur1=response;
          console.log(this.chauffeur1);
          const data1 = {
                idchauffeur: this.chauffeur1.idUtilisateur,
                idBus: this.busupdate.idBus,
                matricule: this.busupdate.matricule,
                nbplaces: this.busupdate.nbplaces,
                idecole: 1
          };
          this.busService.updateBus(this.busupdate.idBus, data1).subscribe(
            response => {
              console.log(response);
              this.submitted = true;
            },
            error => {
              console.log(error);
            }
          );
          this.submitted = true;

        },
        error => {
          console.log(error);
        });
  }
  updateChauffeur(id: number) {
    const data = {
      nom: this.chauffeur.nom,
      prenom: this.chauffeur.prenom,
      adresse: this.chauffeur.adresse,
      dateNaissance: this.chauffeur.dateNaissance,
      tel: this.chauffeur.tel,
      email: this.chauffeur.email,
      roleEnum: Role.CHAUFFEUR
    };
    const data1 = {
      idchauffeur: this.chauffeur.idUtilisateur,
      idBus: this.busupdate.idBus,
      matricule: this.busupdate.matricule,
      nbplaces: this.busupdate.nbplaces,
      idecole: 1
    };
    console.log(data1);
    this.chauffeurService.updateChauffeur(this.chauffeur.idUtilisateur, data)
      .subscribe(
        response => {
          console.log(response);
          this.submitted = true;
        },
        error => {
          console.log(error);
    });
    this.busService.updateBus(this.busupdate.idBus, data1).subscribe(
          response => {
            console.log(response);
            this.submitted = true;
          },
          error => {
            console.log(error);
          }
        );
  }
}
