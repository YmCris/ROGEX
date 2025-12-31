import { Component } from '@angular/core';
import { GenericGetWithoutForm } from '../../../../../common/crud-components/GenericGetWithoutForm';
import { Videogame } from '../../../../../models/videogames/Videogame';

@Component({
  selector: 'app-get-videogame-categories-component',
  imports: [],
  templateUrl: './get-videogame-categories-component.html',
  styleUrl: './get-videogame-categories-component.css',
})
export class GetVideogameCategoriesComponent extends GenericGetWithoutForm<Videogame>{
  
  protected override defineLoad(): void {
    throw new Error('Method not implemented.');
  }
  protected override getPrimaryKeys(): string[] {
    throw new Error('Method not implemented.');
  }

}
