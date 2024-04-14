import {RouterModule, Routes} from '@angular/router';
import {DashboardComponent} from "./component/dashboard/dashboard.component";
import {EcoleComponent} from "./component/ecole/ecole.component";
import {EtudiantComponent} from "./component/etudiant/etudiant.component";
import {BusComponent} from "./component/bus/bus.component";
import {ChauffeurComponent} from "./component/chauffeur/chauffeur.component";
import {NgModule} from "@angular/core";
import {EtudiantProfileComponent} from "./component/etudiant/etudiant-profile/etudiant-profile.component";
import { ChauffeurProfileComponent } from './component/chauffeur/chauffeur-profile/chauffeur-profile.component';
import { BusProfileComponent } from './component/bus/bus-profile/bus-profile.component';
import { SuiviBusComponent } from './component/suivi-bus/suivi-bus.component';
import { EtudiantLocalisationComponent } from './component/etudiant-localisation/etudiant-localisation.component';
import { MobileInterfaceComponent } from './component/mobile-interface/mobile-interface.component';
import { LoginComponent } from './component/login/login.component';
import { UtilisateurComponent } from './component/utilisateur/utilisateur.component';
import {AuthGuardService} from "./services/auth-guard.service";
import { RegistrationComponent } from './component/registration/registration.component';

export const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: DashboardComponent,canActivate:[AuthGuardService],data:{role:['ADMINISTRATEUR', 'Ecole']} },
  { path: 'ecole', component: EcoleComponent,canActivate:[AuthGuardService],data:{role:['ADMINISTRATEUR', 'Ecole']} },
  { path: 'ecole/:id', component: EcoleComponent,canActivate:[AuthGuardService],data:{role:['ADMINISTRATEUR', 'Ecole', 'CHAUFFEUR']} },
  { path: 'eleves', component: EtudiantComponent,canActivate:[AuthGuardService],data:{role:['ADMINISTRATEUR', 'Ecole', 'CHAUFFEUR']} },
  { path: 'bus', component: BusComponent,canActivate:[AuthGuardService],data:{role:['ADMINISTRATEUR', 'Ecole']} },
  { path: 'busprofile', component: BusProfileComponent,canActivate:[AuthGuardService],data:{role:['ADMINISTRATEUR', 'Ecole']} },
  { path: 'busprofile/:id', component: BusProfileComponent,canActivate:[AuthGuardService],data:{role:['ADMINISTRATEUR', 'Ecole']} },
  { path: 'chauffeurs', component: ChauffeurComponent ,canActivate:[AuthGuardService],data:{role:['ADMINISTRATEUR', 'Ecole']}},
  { path: 'chauffeur', component: ChauffeurProfileComponent,canActivate:[AuthGuardService],data:{role:['ADMINISTRATEUR', 'Ecole']} },
  { path: 'chauffeur/:id', component: ChauffeurProfileComponent,canActivate:[AuthGuardService],data:{role:['ADMINISTRATEUR', 'Ecole','PARENT']} },
  { path: 'eleve', component: EtudiantProfileComponent,canActivate:[AuthGuardService],data:{role:['ADMINISTRATEUR', 'Ecole', 'CHAUFFEUR']} },
  { path: 'eleve/:id', component: EtudiantProfileComponent,canActivate:[AuthGuardService],data:{role:['ADMINISTRATEUR', 'Ecole','PARENT', 'CHAUFFEUR']} },
  { path: 'suivibus/:id', component: SuiviBusComponent ,canActivate:[AuthGuardService],data:{role:['ADMINISTRATEUR', 'Ecole','PARENT']}},
  { path: 'etudloc', component: EtudiantLocalisationComponent,canActivate:[AuthGuardService],data:{role:['ADMINISTRATEUR', 'CHAUFFEUR']} },
  { path: 'etudloc/:id', component: EtudiantLocalisationComponent,canActivate:[AuthGuardService],data:{role:['ADMINISTRATEUR', 'CHAUFFEUR']} },
  { path: 'mobile', component: MobileInterfaceComponent,canActivate:[AuthGuardService],data:{role:['ADMINISTRATEUR', 'PARENT']} },
  { path: 'login', component: LoginComponent },
  { path: 'utilisateurs', component: UtilisateurComponent,canActivate:[AuthGuardService],data:{role:['ADMINISTRATEUR', 'Ecole']} },
  { path: 'necole', component: RegistrationComponent,canActivate:[AuthGuardService],data:{role:['ADMINISTRATEUR']} },
  { path: '**', redirectTo: '/not-found' }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
