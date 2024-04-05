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
import { SearchetudiantPipe } from "./pipe/searchetudiant.pipe";
import { ChauffeurProfileComponent } from './component/chauffeur/chauffeur-profile/chauffeur-profile.component';
import { SearchChauffeurPipe } from "./pipe/search-chauffeur.pipe";
import { BusProfileComponent } from './component/bus/bus-profile/bus-profile.component';
import { SearchBusPipe } from "./pipe/search-bus.pipe";
import { SuiviBusComponent } from './component/suivi-bus/suivi-bus.component';
import { GoogleMapsModule } from '@angular/google-maps';
import { EtudiantLocalisationComponent } from './component/etudiant-localisation/etudiant-localisation.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { MobileInterfaceComponent } from './component/mobile-interface/mobile-interface.component';

@NgModule({
    declarations: [
        AppComponent,
        SidenavComponent,
        TopnavComponent,
        EcoleComponent,
        ChauffeurComponent,
        ChauffeurProfileComponent,
        BusComponent,
        BusProfileComponent,
        UtilisateurComponent,
        EtudiantComponent,
        EtudiantProfileComponent,
        EtudiantLocalisationComponent,
        SuiviBusComponent,
        MobileInterfaceComponent,
        DashboardComponent
    ],
    providers: [
        DatePipe,
        ReactiveFormsModule,
        FormsModule,
    ],
    bootstrap: [AppComponent],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule,
        SearchetudiantPipe,
        SearchChauffeurPipe,
        SearchBusPipe,
        GoogleMapsModule
    ]
})
export class AppModule {
  constructor() {
  }
}

