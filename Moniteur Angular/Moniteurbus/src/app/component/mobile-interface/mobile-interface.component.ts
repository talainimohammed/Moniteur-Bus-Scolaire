import { Component } from '@angular/core';
import { EtudiantService } from '../../services/etudiant.service';
import { Etudiant } from '../../models/etudiant';
import { Userdata } from '../../models/userdata';
import { delay } from 'rxjs';

@Component({
  selector: 'app-mobile-interface',
  templateUrl: './mobile-interface.component.html',
  styleUrl: './mobile-interface.component.css'
})
export class MobileInterfaceComponent {

  idEtudiant: number=0;
  emailEtudiant: string='';
  etudiant:Etudiant=new Etudiant();
  userData:Userdata=new Userdata();


  constructor(private etudiantService:EtudiantService) { }

  ngOnInit() {
    this.userData= JSON.parse(localStorage.getItem('userData') as string);
    this.emailEtudiant = this.userData.username ?? '';
    this.retrieveEtudiant(this.emailEtudiant);
  }
  retrieveEtudiant(email:string){
    this.etudiantService.getEtudiantByemail(email).subscribe(
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
