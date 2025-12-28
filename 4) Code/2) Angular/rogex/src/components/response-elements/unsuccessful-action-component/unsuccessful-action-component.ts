import { Component, Input } from '@angular/core';

@Component({
  selector: 'unsuccessful-action-component',
  imports: [],
  templateUrl: './unsuccessful-action-component.html',
})
export class UnsuccessfulActionComponent {
  @Input() message: string = 'Operación realizada sin éxito.';
}
