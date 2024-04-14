import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-topnav',
  templateUrl: './topnav.component.html',
  styleUrl: './topnav.component.css'
})
export class TopnavComponent {
  isAuthenticated=false;
  userSub!:Subscription;
  name="";
  iduser:number=0;
  constructor(private authService:AuthService) { }
  ngOnInit(): void {
    this.userSub=this.authService.user.subscribe(loggedUser=>{
      this.isAuthenticated=!!loggedUser;
      if(!this.isAuthenticated){
        this.initializeState();
      }
      else if(!!loggedUser){
        //this.setRole(loggedUser);
        this.name=loggedUser?.username
        this.iduser=loggedUser?.id;
      }
    });
  }
  initializeState(){
    this.isAuthenticated=false;
  }
  logout() {
    this.authService.logout();
  }
}
