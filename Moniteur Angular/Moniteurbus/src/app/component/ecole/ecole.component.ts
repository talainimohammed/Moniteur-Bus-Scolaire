import {Component, OnInit} from '@angular/core';
import {EcoleService} from "../../services/ecole.service";
import {Ecole} from "../../models/ecole";
import { ActivatedRoute } from '@angular/router';
import { Userdata } from '../../models/userdata';
import { NgForm, NgModel } from '@angular/forms';

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
  ecoleId:number=0;
  ecoles?:Ecole[];
  submitted = false;
  userData:Userdata=new Userdata();
  constructor(private ecoleService: EcoleService,private route:ActivatedRoute){}
    ngOnInit(): void {
      
      if(this.route.snapshot.params['id']){
        this.ecoleId=this.route.snapshot.params['id'];
        this.retrieveEcole(this.ecoleId);
      }
    }
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

      }
    }
    valid: boolean = false;
    validate0(val: NgModel) {
      if (val.value == 0) {
        this.valid = true;
      }
    }
    retrieveEcole(id:number): void {
      this.ecoleService.getEcole(id)
        .subscribe(
          data => {
            this.ecole = data;
            console.log(data);
          },
          error => {
            console.log(error);
          });
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
