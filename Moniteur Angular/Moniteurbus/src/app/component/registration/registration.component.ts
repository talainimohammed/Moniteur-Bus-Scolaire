import { Component } from '@angular/core';
import { Ecole } from '../../models/ecole';
import { EcoleService } from '../../services/ecole.service';
import { Utilisateur } from '../../models/utilisateur';
import { UtilisateurService } from '../../services/utilisateur.service';
import { NgForm, NgModel } from '@angular/forms';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.css'
})
export class RegistrationComponent {
  ecole:Ecole =new Ecole();
  utilisateur:Utilisateur=new Utilisateur();
  ecoleId:number=0;
  ecoles?:Ecole[];
  submitted = false;

  constructor(private ecoleService: EcoleService,private utilisateurService:UtilisateurService) {}
  onSubmit(form: NgForm) {
    if (form.valid) {
      this.saveEcole();
    } else {
      form.controls['nomEcole'].markAsTouched();
      form.controls['email'].markAsTouched();
      form.controls['tel'].markAsTouched();
      form.controls['adresse'].markAsTouched();
      form.controls['latitude'].markAsTouched();
      form.controls['longtitude'].markAsTouched();
      form.controls['nom'].markAsTouched();
      form.controls['prenom'].markAsTouched();
      form.controls['loginemail'].markAsTouched();
      form.controls['pass'].markAsTouched();

    }
  }
  valid: boolean = false;
    validate0(val: NgModel) {
      if (val.value == 0) {
        this.valid = true;
      }
    }
  saveEcole(): void {
    const dataecole = {
      nomEcole: this.ecole.nomEcole,
      adresse: this.ecole.adresse,
      email: this.ecole.email,
      telephone: this.ecole.telephone,
      locationId: this.ecole.locationId,
      latitude: this.ecole.latitude,
      longtitude: this.ecole.longtitude
    };
    console.log(dataecole);
    this.ecoleService.createEcole(dataecole)
      .subscribe(
        response => {
          console.log(response);
          const resp=response as Ecole;
          const datauser = {
            nom: this.utilisateur.nom,
            prenom: this.utilisateur.prenom,
            email: this.utilisateur.email,
            password: this.utilisateur.password,
            tel: "0000000000",
            roleEnum: 3,    
            adresse: "adresse",
            dateNaissance: new Date(),
            idEcole: resp.idEcole
          };
          this.utilisateurService.createutilisateur(datauser).subscribe(
            response => {
              console.log(response);
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
}
