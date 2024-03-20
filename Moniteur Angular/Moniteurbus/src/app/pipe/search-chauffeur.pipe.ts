import { Pipe, PipeTransform } from '@angular/core';
import { Chauffeur } from '../models/chauffeur';

@Pipe({
  name: 'searchChauffeur',
  standalone: true
})
export class SearchChauffeurPipe implements PipeTransform {

  transform(items: Chauffeur[], searchetu: string): any[] {
    if (!items) return [];
    if (!searchetu) return items;

    searchetu = searchetu.toLowerCase();

    return items.filter(it => {
      return (it.nom ?? '').toLowerCase().includes(searchetu) ||
        (it.prenom ?? '').toLowerCase().includes(searchetu) ||
        (it.adresse ?? '').toLowerCase().includes(searchetu) ||
        (it.tel ?? '').includes(searchetu);
    });
  }

}
