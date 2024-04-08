import { Component, Injector, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent{

  idEcole:number=1;

  loginFormGroup:FormGroup=this.fb.group({});
  submitted:boolean=false;
  errorMessage?:string;
  AuthService = this.injector.get<AuthService>(AuthService);
  constructor(private fb: FormBuilder, private injector: Injector) { }

  ngOnInit(): void {
    this.loginFormGroup=this.fb.group({
      email:["",[Validators.required]],
      password: ["", Validators.required],
    })
  }
onLogin(){
//if(this.loginFormGroup.invalid)return;
this.AuthService.login(this.loginFormGroup.value).subscribe({
  next:loginResponse=>{
    this.AuthService.saveToken(loginResponse);
    console.log(loginResponse);
    },
  error:err=>{
    console.log(err);
    this.errorMessage="An error : "+err.message;
  }
})
}

  
}
