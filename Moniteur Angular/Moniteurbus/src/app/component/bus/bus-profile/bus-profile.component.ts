import { Component,OnInit } from '@angular/core';
import { BusService } from '../../../services/bus.service';
import { Bus } from '../../../models/bus';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-bus-profile',
  templateUrl: './bus-profile.component.html',
  styleUrl: './bus-profile.component.css'
})
export class BusProfileComponent implements OnInit{

  bus:Bus=new Bus();
  submitted=false;
  id:number=0;

  constructor(private busService:BusService,private route:ActivatedRoute) { }

  ngOnInit(): void {
    if(this.route.snapshot.params['id']){
      this.id = this.route.snapshot.params['id'];
      this.getBus(this.id);
    }

  }

  saveBus(){
    const data = {
      matricule: this.bus.matricule,
      nbplaces: this.bus.nbplaces,
      idecole: 1
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
      idecole: 1
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
