import { Pipe, PipeTransform } from '@angular/core';
import { Etudiant } from '../models/etudiant';

@Pipe({
  name: 'searchetudiant',
  standalone: true
})
export class SearchetudiantPipe implements PipeTransform {

  transform(items: Etudiant[], searchetu: string): any[] {
    if (!items) return [];
    if (!searchetu) return items;

    searchetu = searchetu.toLowerCase();

    return items.filter(it => {
      return (it.nom ?? '').toLowerCase().includes(searchetu) ||
        (it.prenom ?? '').toLowerCase().includes(searchetu) ||
        (it.niveau ?? '').toLowerCase().includes(searchetu) ||
        (it.tel ?? '').includes(searchetu);
    });
  }

}
