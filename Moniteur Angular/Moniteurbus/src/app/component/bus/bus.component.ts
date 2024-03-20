import { Component, OnInit } from '@angular/core';
import { BusService } from '../../services/bus.service';
import { ActivatedRoute } from '@angular/router';
import { Bus } from '../../models/bus';

@Component({
  selector: 'app-bus',
  templateUrl: './bus.component.html',
  styleUrl: './bus.component.css'
})
export class BusComponent implements OnInit{
  bus?: Bus[];
  searchetu?:'';
  constructor(private busService:BusService,private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.getBus();
  }

  getBus(){
    this.busService.getBus().subscribe(
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
        this.getBus();
      },
      error=>{
        console.log(error);
      });
    }
  }

}
