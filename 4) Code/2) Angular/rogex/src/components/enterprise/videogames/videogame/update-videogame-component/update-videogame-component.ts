import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { GenericUpdate } from '../../../../../common/crud-components/GenericUpdate';
import { Videogame } from '../../../../../models/videogames/Videogame';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { VideogameService } from '../../../../../services/enterprise/videogame.service';
import { AuthService } from '../../../../../core/auth/auth.service';
import { UpdateVideogameRequest } from '../../../../../models/videogames/UpdateVideogameRequest';
import { SuccessfulActionComponent } from '../../../../response-elements/successful-action-component/successful-action.component';
import { UnsuccessfulActionComponent } from '../../../../response-elements/unsuccessful-action-component/unsuccessful-action-component';

@Component({
  selector: 'app-update-videogame-component',
  imports: [FormsModule, ReactiveFormsModule,
    SuccessfulActionComponent, UnsuccessfulActionComponent],
  templateUrl: './update-videogame-component.html',
  styleUrl: './update-videogame-component.css',
})
export class UpdateVideogameComponent extends GenericUpdate<Videogame> implements OnChanges {

  @Input() videogame?: Videogame;

  public constructor(
    formBuilder: FormBuilder,
    service: VideogameService,
    private authService: AuthService,
  ) {
    super(formBuilder, service);
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['videogame'] && this.videogame) {
      this.updateObjectForm.patchValue({
        title: this.videogame.title,
        description: this.videogame.description,
        price: this.videogame.price,
        minimumRequirements: this.videogame.minimumRequirements,
        ageRating: this.videogame.ageRating,
        suspensionOfSale: this.videogame.suspensionOfSale,
        hiddenComments: this.videogame.hiddenComments,
        hidden: this.videogame.hidden,
      });
    }
  }

  public override setFormValidations(): void {
    this.updateObjectForm = this.formBuilder.group({
      title: [null, [Validators.required, Validators.maxLength(150)]],
      description: [null, [Validators.maxLength(250)]],
      price: [null, [Validators.max(1000)]],
      minimumRequirements: [null, [Validators.maxLength(250)]],
      ageRating: [null],
      enterpriseName: [Validators.maxLength(150)],
      suspensionOfSale: [null],
      hiddenComments: [null],
      hidden: [null],
    });
  }

  public override getUpdateValues(values: FormGroup): Partial<Videogame> {
    const formValues = values.value;

    const videogameUpdate: UpdateVideogameRequest = {
      description: formValues.description,
      price: formValues.price,
      minimumRequirements: formValues.minimumRequirements,
      ageRating: formValues.ageRating,

      suspensionOfSale: formValues.suspensionOfSale,
      hiddenComments: formValues.hiddenComments,
      hidden: formValues.hidden,

      newCategory: null,
      existingCategory: null
    };

    return videogameUpdate as Partial<Videogame>;
  }

  public override getPrimaryKeys(values: FormGroup): string[] {

    const form = values.value;
    const title: string = form.title;
    const enterpriseName: string = this.getEnterpriseName();
    const pks: string[] = [title, enterpriseName];

    return pks;
  }

  protected getEnterpriseName(): string {
    const enterprise = this.authService.user()?.enterpriseName;
    if (!enterprise) {
      throw new Error('Enterprise not found in session');
    }
    return enterprise;
  }

}
