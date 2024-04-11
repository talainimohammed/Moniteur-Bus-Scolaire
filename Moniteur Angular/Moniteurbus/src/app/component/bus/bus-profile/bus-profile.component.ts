import { Component,OnInit } from '@angular/core';
import { BusService } from '../../../services/bus.service';
import { Bus } from '../../../models/bus';
import { ActivatedRoute } from '@angular/router';
import { Userdata } from '../../../models/userdata';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-bus-profile',
  templateUrl: './bus-profile.component.html',
  styleUrl: './bus-profile.component.css'
})
export class BusProfileComponent implements OnInit{

  bus:Bus=new Bus();
  submitted=false;
  id:number=0;
  userData:Userdata=new Userdata();
  id_ecole=0;
  constructor(private busService:BusService,private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.userData= JSON.parse(localStorage.getItem('userData') as string);
    this.id_ecole = this.userData.idecole ?? 0;
    if(this.route.snapshot.params['id']){
      this.id = this.route.snapshot.params['id'];
      this.getBus(this.id);
    }
  }
  onSubmit(form: NgForm) {
    if (form.valid) {
      if(this.id==0){
        this.saveBus();
      }else{
        this.updateBus(this.id);
      }
    } else {
      form.controls['matricule'].markAsTouched();
      form.controls['nbplaces'].markAsTouched();
    }
  }
  saveBus(){
    const data = {
      matricule: this.bus.matricule,
      nbplaces: this.bus.nbplaces,
      idecole: this.id_ecole
    };
    this.busService.addBus(data).subscribe(
      response=>{
        console.log(response);
        this.submitted=true;
      },
      error=>{
        console.log(error);
      }
    );
  }
  updateBus(id:number){
    const data = {
      matricule: this.bus.matricule,
      nbplaces: this.bus.nbplaces,
      idchauffeur: this.bus.idchauffeur,
      idecole: this.id_ecole
    };
    this.busService.updateBus(id,data).subscribe(
      response=>{
        console.log(response);
        this.submitted=true;
      },
      error=>{
        console.log(error);
      }
    );
  }
  getBus(id:number){
    this.busService.getBusById(id).subscribe(
      data=>{
        this.bus = data as Bus;
        console.log(data);
      },
      error=>{
        console.log(error);
      }
    );
  }

}
