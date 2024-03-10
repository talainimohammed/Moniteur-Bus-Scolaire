import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app.routes';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { AppComponent } from './app.component';
import { SidenavComponent } from './component/sidenav/sidenav.component';
import { TopnavComponent } from './component/topnav/topnav.component';
import { EcoleComponent } from './component/ecole/ecole.component';
import { ChauffeurComponent } from './component/chauffeur/chauffeur.component';
import { BusComponent } from './component/bus/bus.component';
import { UtilisateurComponent } from './component/utilisateur/utilisateur.component';
import { EtudiantComponent } from './component/etudiant/etudiant.component';
import { EtudiantProfileComponent } from './component/etudiant/etudiant-profile/etudiant-profile.component';


@NgModule({
  declarations: [
     AppComponent,
     SidenavComponent, 
     TopnavComponent,
     EcoleComponent,
     ChauffeurComponent,
     BusComponent,
     UtilisateurComponent,
     EtudiantComponent,
     EtudiantProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [
    DatePipe,
    ReactiveFormsModule,
    FormsModule,
    
  ],

  bootstrap: [AppComponent]
})
export class AppModule {
  constructor() {
  }
}

