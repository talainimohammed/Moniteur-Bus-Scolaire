import { Component, OnInit } from '@angular/core';
import { Chauffeur } from '../../models/chauffeur';
import { ChauffeurService } from '../../services/chauffeur.service';
import { Userdata } from '../../models/userdata';

@Component({
  selector: 'app-chauffeur',
  templateUrl: './chauffeur.component.html',
  styleUrl: './chauffeur.component.css'
})
export class ChauffeurComponent implements OnInit{

  chauffeurs?: Chauffeur[];
  searchetu?:'';
  userData:Userdata=new Userdata();
  id_ecole:number=0;
  constructor(private chauffeurService: ChauffeurService) { }

  ngOnInit(): void {
    this.userData= JSON.parse(localStorage.getItem('userData') as string);
    this.id_ecole = this.userData.idecole ?? 0;
    this.getChauffeurs(this.id_ecole);
  }
  getChauffeurs(id:number){
    this.chauffeurService.getChauffeurs(this.id_ecole).subscribe(
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
    if(confirm("Voulez vous supprimer ce Chauffeur?")){
      this.chauffeurService.deleteChauffeur(id).subscribe(data=>{
        this.getChauffeurs(this.id_ecole);
      },
      error=>{
        console.log(error);
      });
    }
  }


}
