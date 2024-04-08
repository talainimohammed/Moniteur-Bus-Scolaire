import { Component } from '@angular/core';
import {RouterLink} from "@angular/router";
import { Userdata } from '../../models/userdata';
import { Subscription } from 'rxjs';
import { AuthService } from '../../services/auth.service';
import { LoggedUser } from '../../models/logged-user';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrl: './sidenav.component.css'
})
export class SidenavComponent {
  userData:Userdata=new Userdata();
  id_ecole:number=0;
  role:string="";
  userSub!:Subscription;
  isAuthenticated=false;
  isAdmin=false;
  isEcole=false;
  isParent=false;
  isChauffeur=false;
  name:string|undefined;
  constructor(private authService:AuthService) { }

  ngOnInit(): void {
    this.userSub=this.authService.user.subscribe(loggedUser=>{
      this.isAuthenticated=!!loggedUser;
      if(!this.isAuthenticated){
        this.initializeState();
      }
      else if(!!loggedUser){
        this.setRole(loggedUser);
        this.name=loggedUser?.username
      }
    });
    /*this.userData= JSON.parse(localStorage.getItem('userData') as string);
    this.id_ecole = this.userData.idecole ?? 0;*/

  }
  initializeState(){
    this.isAdmin=false;
    this.isParent=false;
    this.isEcole=false;
    this.isChauffeur=false;
  }
  setRole(loggedUser:LoggedUser|null){
    if(loggedUser?.roles.includes("ADMINISTRATEUR"))
      this.isAdmin=true;
    else if(!!loggedUser?.roles.includes("Parent")){
      this.isParent=true;
    }
    else if(!!loggedUser?.roles.includes("Ecole")){
      this.isEcole=true;
    }
    else if(!!loggedUser?.roles.includes("Chauffeur")){
      this.isChauffeur=true;
    }
  }
  ngOnChanges(): void {
    this.userData= JSON.parse(localStorage.getItem('userData') as string);
    this.id_ecole = this.userData.idecole ?? 0;
    this.role = this.userData.roles ?? "";
  }
  ngOnDestroy(): void {
    this.userSub.unsubscribe();
}
}
