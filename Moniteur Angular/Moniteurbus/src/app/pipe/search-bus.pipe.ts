import { Pipe, PipeTransform } from '@angular/core';
import { Bus } from '../models/bus';

@Pipe({
  name: 'searchBus',
  standalone: true
})
export class SearchBusPipe implements PipeTransform {

  transform(items: Bus[], searchetu: string): any[] {
    if (!items) return [];
    if (!searchetu) return items;

    searchetu = searchetu.toLowerCase();

    return items.filter(it => {
      return (it.matricule ?? '').toLowerCase().includes(searchetu) ||
        (it.nbplaces?.toString() ?? '').includes(searchetu)
    });
  }

}
