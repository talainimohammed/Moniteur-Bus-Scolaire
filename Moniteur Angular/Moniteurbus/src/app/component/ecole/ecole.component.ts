import {Component, OnInit} from '@angular/core';
import {EcoleService} from "../../services/ecole.service";
import {Ecole} from "../../models/ecole";

@Component({
  selector: 'app-ecole',
  templateUrl: './ecole.component.html',
  styleUrl: './ecole.component.css'
})
export class EcoleComponent implements OnInit{
  ecole:Ecole ={
    nomEcole:"",
    adresse:"",
    email:"",
    telephone:"",
    locationId:0,
    latitude: 0,
    longtitude: 0
};
  ecoles?:Ecole[];
  submitted = false;
  constructor(private ecoleService: EcoleService){}
    ngOnInit(): void {
      //this.retrieveEcoles();
    }

  retrieveEcoles(): void {
    this.ecoleService.getEcoles()
      .subscribe(
        data => {
          this.ecoles = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }
  saveEcole(): void {
    const data = {
      nomEcole: this.ecole.nomEcole,
      adresse: this.ecole.adresse,
      email: this.ecole.email,
      telephone: this.ecole.telephone,
      locationId: this.ecole.locationId,
      latitude: this.ecole.latitude,
      longtitude: this.ecole.longtitude
    };
    console.log(data);
    
    this.ecoleService.createEcole(data)
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
