import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-unsuccessful-action-component',
  imports: [],
  templateUrl: './unsuccessful-action-component.html',
})
export class UnsuccessfulActionComponent {
  @Input() message: string = 'Operación realizada con éxito.';
}
