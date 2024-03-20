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

export const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: DashboardComponent },
  { path: 'ecole', component: EcoleComponent },
  { path: 'eleves', component: EtudiantComponent },
  { path: 'bus', component: BusComponent },
  { path: 'busprofile', component: BusProfileComponent },
  { path: 'busprofile/:id', component: BusProfileComponent },
  { path: 'chauffeurs', component: ChauffeurComponent },
  { path: 'chauffeur', component: ChauffeurProfileComponent },
  { path: 'chauffeur/:id', component: ChauffeurProfileComponent },
  { path: 'eleve', component: EtudiantProfileComponent },
  { path: 'eleve/:id', component: EtudiantProfileComponent },
  { path: '**', redirectTo: '/not-found' }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
