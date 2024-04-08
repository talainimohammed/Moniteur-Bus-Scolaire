import { Component, OnInit } from '@angular/core';
import { BusService } from '../../services/bus.service';
import { ActivatedRoute } from '@angular/router';
import { Bus } from '../../models/bus';
import { Userdata } from '../../models/userdata';

@Component({
  selector: 'app-bus',
  templateUrl: './bus.component.html',
  styleUrl: './bus.component.css'
})
export class BusComponent implements OnInit{
  bus?: Bus[];
  searchetu?:'';
  userData:Userdata=new Userdata();
  id_ecole=0;
  constructor(private busService:BusService,private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.userData= JSON.parse(localStorage.getItem('userData') as string);
    this.id_ecole = this.userData.idecole ?? 0;
    this.getBus(this.id_ecole);
  }

  getBus(id:number){
    this.busService.getBus(id).subscribe(
      data=>{
        this.bus = data as Bus[];
        console.log(data);
      },
      error=>{
        console.log(error);
      }
    );
  }
  deleteBus(id:any){
    if(confirm("Voulez vous supprimer ce Bus?")){
      this.busService.deleteBus(id).subscribe(data=>{
        this.getBus(this.id_ecole);
      },
      error=>{
        console.log(error);
      });
    }
  }

}
