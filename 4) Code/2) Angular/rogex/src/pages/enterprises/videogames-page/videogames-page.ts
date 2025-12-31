import { Component } from '@angular/core';
import { GenericCrud } from '../../../common/crud-pages/GenericCrud';
import { Videogame } from '../../../models/videogames/Videogame';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { VideogameService } from '../../../services/enterprise/videogame.service';
import { AuthService } from '../../../core/auth/auth.service';
import { SuccessfulActionComponent } from '../../../components/response-elements/successful-action-component/successful-action.component';
import { UnsuccessfulActionComponent } from '../../../components/response-elements/unsuccessful-action-component/unsuccessful-action-component';
import { UpdateVideogameRequest } from '../../../models/videogames/UpdateVideogameRequest';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-videogames-page',
  imports: [FormsModule, ReactiveFormsModule,
    SuccessfulActionComponent, UnsuccessfulActionComponent, RouterModule],
  templateUrl: './videogames-page.html',
  styleUrl: './videogames-page.css',
})
export class VideogamesPage extends GenericCrud<Videogame> {

  public constructor(
    formBuilder: FormBuilder,
    service: VideogameService,
    private authService: AuthService,
  ) {
    super(formBuilder, service);
  }

  public override setFormsValidations(): void {
    this.createObjectForm = this.formBuilder.group({
      title: [null, [Validators.required, Validators.maxLength(150)]],
      description: [null, [Validators.required, Validators.maxLength(250)]],
      price: [null, [Validators.min(0), Validators.max(1000)]],
      minimumRequirements: [null, [Validators.required, Validators.maxLength(250)]],
      ageRating: [Validators.required],
      enterpriseName: [Validators.required, Validators.maxLength(150)],
      suspensionOfSale: [false],
      hiddenComments: [false],
      hidden: [false],
    });

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

    this.getObjectsForm = this.formBuilder.group({});

  }

  public override getCreateValues(values: FormGroup) {

    const formValues = values.value;

    const videogame: Videogame = {
      title: formValues.title,
      description: formValues.description,
      price: formValues.price,
      minimumRequirements: formValues.minimumRequirements,
      ageRating: formValues.ageRating,
      enterpriseName: this.getEnterpriseName(),
      suspensionOfSale: formValues.suspensionOfSale,
      hiddenComments: formValues.hiddenComments,
      hidden: formValues.hidden
    };

    return videogame;
  }

  protected getEnterpriseName(): string {
    const enterprise = this.authService.user()?.enterpriseName;
    if (!enterprise) {
      throw new Error('Enterprise not found in session');
    }
    return enterprise;
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

  public override afterInit(): void {
    this.loadObjectsWithPKsParameter(this.getEnterpriseName());
  }

}
