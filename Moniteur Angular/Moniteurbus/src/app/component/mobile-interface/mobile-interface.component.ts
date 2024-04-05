import { Component } from '@angular/core';
import { EtudiantService } from '../../services/etudiant.service';
import { Etudiant } from '../../models/etudiant';

@Component({
  selector: 'app-mobile-interface',
  templateUrl: './mobile-interface.component.html',
  styleUrl: './mobile-interface.component.css'
})
export class MobileInterfaceComponent {

  idEtudiant: number=5;
  etudiant:Etudiant=new Etudiant();


  constructor(private etudiantService:EtudiantService) { }

  ngOnInit() {
    this.retrieveEtudiant(this.idEtudiant);
  }
  retrieveEtudiant(id:number){
    this.etudiantService.getEtudiant(id).subscribe(
      (data)=>{
        this.etudiant=data as Etudiant;
        console.log(data);
      },
      (error)=>{
        console.log(error);
      }
    );
  }
}
