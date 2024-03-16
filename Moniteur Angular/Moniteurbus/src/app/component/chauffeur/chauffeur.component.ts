import { Component, OnInit } from '@angular/core';
import { Chauffeur } from '../../models/chauffeur';
import { ChauffeurService } from '../../services/chauffeur.service';

@Component({
  selector: 'app-chauffeur',
  templateUrl: './chauffeur.component.html',
  styleUrl: './chauffeur.component.css'
})
export class ChauffeurComponent implements OnInit{

  chauffeurs?: Chauffeur[];
  constructor(private chauffeurService: ChauffeurService) { }

  ngOnInit(): void {
    this.getChauffeurs();
  }
  getChauffeurs(){
    this.chauffeurService.getChauffeurs().subscribe(
      data=>{
        console.log(data);
        this.chauffeurs = data as Chauffeur[];
      },
      error=>{
        console.log(error);
      }
    );
  
  }
  deleteChauffeur(id:any){
    this.chauffeurService.deleteChauffeur(id).subscribe(data=>{
      this.getChauffeurs();
    });
  }


}
