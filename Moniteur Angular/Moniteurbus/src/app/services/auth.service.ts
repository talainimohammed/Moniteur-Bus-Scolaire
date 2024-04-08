import {Inject, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {LoginRequest, LoginResponse} from "../models/login";
import {BehaviorSubject, Observable} from "rxjs";
import {JwtHelperService} from "@auth0/angular-jwt";
import {LoggedUser} from "../models/logged-user";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  jwtHelperService=new  JwtHelperService();
  user=new  BehaviorSubject<LoggedUser|null>(null);
  tokenExpirationTimer:any;
  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
  });
  private requestOptions = { headers: this.headers };
  constructor(private http: HttpClient,private router:Router,@Inject('url_login') private url: string) { }

  public login(user: LoginRequest):Observable<LoginResponse>{
    const formData=new FormData();
    formData.append('email',user.email);
    formData.append('password',user.password);
    console.log(user);
    return this.http.post<LoginResponse>(this.url,user,this.requestOptions);
  }
  saveToken(jwtTokens:LoginResponse){
  const decodedAccessToken=this.jwtHelperService.decodeToken(jwtTokens.token);

  const loggedUser = new LoggedUser(decodedAccessToken.sub,decodedAccessToken.role,jwtTokens.token,this.getExpirationDate(decodedAccessToken.exp));
    this.user.next(loggedUser);
    console.log(loggedUser);
    this.autologout(this.getExpirationDate(decodedAccessToken.exp).valueOf()-new Date().valueOf());
    localStorage.setItem("userData",JSON.stringify(loggedUser));

  this.redirectLoggedInUser(decodedAccessToken,jwtTokens.token);
  console.log(loggedUser);
  }

  getExpirationDate(exp:number){
    const date=new Date(0);
    date.setUTCSeconds(exp);
    return date;
  }
  redirectLoggedInUser(decodedToken:any,accessToken:string){
    if(decodedToken.role.includes("ADMINISTRATEUR"))
      this.router.navigateByUrl("/");
    else if(decodedToken.role.includes("PARENT"))
      this.router.navigateByUrl("/mobile")
    else if(decodedToken.role.includes("CHAUFFEUR"))
      this.router.navigateByUrl("/eleves")
    else if(decodedToken.role.includes("Ecole"))
      this.router.navigateByUrl("/")
  }
  autoLogin(){
    const userData:{
      email:string,
      roles:string,
      _token:string,
      _expiration:Date
    } = JSON.parse(localStorage.getItem('userData') as string);
    if(!userData)return;
    const  loadedUser=new LoggedUser(userData.email,userData.roles,userData._token,new Date(userData._expiration));
    if(loadedUser.token){
      this.user.next(loadedUser);
      this.autologout(loadedUser._expiration.valueOf()- new Date().valueOf());
    }
  }
  logout(){
    localStorage.clear();
    this.user.next(null);
    this.router.navigate(['/login'])
    if(this.tokenExpirationTimer){
      clearTimeout(this.tokenExpirationTimer);
    }
    this.tokenExpirationTimer=null;
  }
  autologout(expirationDuration:number){
    this.tokenExpirationTimer= setTimeout(()=>{
      this.logout();
    },expirationDuration)
  }
}
