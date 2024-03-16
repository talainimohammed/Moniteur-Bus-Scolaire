import { Component, OnInit } from '@angular/core';
import { Chauffeur } from '../../../models/chauffeur';
import { ChauffeurService } from '../../../services/chauffeur.service';
import { Role } from '../../../enum/role';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-chauffeur-profile',
  templateUrl: './chauffeur-profile.component.html',
  styleUrl: './chauffeur-profile.component.css'
})
export class ChauffeurProfileComponent implements OnInit{
  chauffeur: Chauffeur = new Chauffeur();
  submitted = false;
  id: number=0;
  constructor( private chauffeurService:ChauffeurService,private route: ActivatedRoute) { }
  ngOnInit(): void {
    if(this.route.snapshot.params['id']!=null){
      this.id= this.route.snapshot.params['id'];
      this.getChauffeur(this.id);
    }
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
  saveChauffeur() {
    const data = {
      nom: this.chauffeur.nom,
      prenom: this.chauffeur.prenom,
      adresse: this.chauffeur.adresse,
      dateNaissance: this.chauffeur.dateNaissance,
      tel: this.chauffeur.tel,
      email: this.chauffeur.email,
      roleEnum: Role.CHAUFFEUR
    };
    this.chauffeurService.createChauffeur(data)
      .subscribe(
        response => {
          console.log(response);
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
    this.chauffeurService.updateChauffeur(this.chauffeur.idUtilisateur, data)
      .subscribe(
        response => {
          console.log(response);
          this.submitted = true;
        },
        error => {
          console.log(error);
        });
  }
}
