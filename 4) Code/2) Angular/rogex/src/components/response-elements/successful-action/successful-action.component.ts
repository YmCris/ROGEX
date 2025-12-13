import { Component, Input } from '@angular/core';

@Component({
    selector: 'successful-action',
    templateUrl: './successful-action.component.html',
})
export class SuccessfulActionComponent {
    @Input() message: string = 'Operación realizada con éxito.';
}
