import { Component } from '@angular/core';
import { GenericUpdate } from '../../../../../common/crud-components/GenericUpdate';
import { Videogame } from '../../../../../models/videogames/Videogame';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { UnsuccessfulActionComponent } from '../../../../response-elements/unsuccessful-action-component/unsuccessful-action-component';
import { SuccessfulActionComponent } from '../../../../response-elements/successful-action-component/successful-action.component';
import { ActivatedRoute } from '@angular/router';
import { VideogameService } from '../../../../../services/enterprise/videogame.service';
import { AuthService } from '../../../../../core/auth/auth.service';
import { UpdateVideogameRequest } from '../../../../../models/videogames/UpdateVideogameRequest';

@Component({
  selector: 'app-update-videogame-category-component',
  imports: [FormsModule, ReactiveFormsModule,
    SuccessfulActionComponent, UnsuccessfulActionComponent],
  templateUrl: './update-videogame-category-component.html',
  styleUrl: './update-videogame-category-component.css',
})
export class UpdateVideogameCategoryComponent extends GenericUpdate<Videogame> {

  title?: string;
  enterprise?: string;

  public constructor(
    private route: ActivatedRoute,
    formBuilder: FormBuilder,
    service: VideogameService,
    private authService: AuthService,
  ) {
    super(formBuilder, service);
  }

  override ngOnInit(): void {
    this.setFormValidations();
    this.setVideogameInformation();
  }

  public override setFormValidations(): void {
    this.updateObjectForm = this.formBuilder.group({
      newCategory: [null, [Validators.required]],
      existingCategory: [null, [Validators.required]]
    });
  }

  public override getUpdateValues(values: FormGroup): Partial<Videogame> {
    const formValues = values.value;

    const videogameUpdate: UpdateVideogameRequest = {
      description: null,
      price: null,
      minimumRequirements: null,
      ageRating: null,
      suspensionOfSale: null,
      hiddenComments: null,
      hidden: null,

      newCategory: formValues.newCategory,
      existingCategory: formValues.existingCategory
    };

    return videogameUpdate as Partial<Videogame>;
  }

  public override getPrimaryKeys(values: FormGroup): string[] {

    if (this.title == null || this.enterprise == null) {
      throw new Error("CAN'T GET THE PKS TO UPDATE AN CATEGORY");
    }

    const pks: string[] = [this.title, this.enterprise];

    return pks;
  }

  protected getEnterpriseName(): string {
    const enterprise = this.authService.user()?.enterpriseName;
    if (!enterprise) {
      throw new Error('Enterprise not found in session');
    }
    return enterprise;
  }


  public setVideogameInformation(): void {
    const title = this.route.snapshot.paramMap.get('title');
    const enterprise = this.route.snapshot.paramMap.get('enterprise');

    if (!title || !enterprise) return;

    this.title = title;
    this.enterprise = enterprise;

  }

}
