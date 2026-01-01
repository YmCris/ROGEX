import { Component } from '@angular/core';
import { GenericDelete } from '../../../../../common/crud-components/GenericDelete';
import { Videogame } from '../../../../../models/videogames/Videogame';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { SuccessfulActionComponent } from '../../../../response-elements/successful-action-component/successful-action.component';
import { UnsuccessfulActionComponent } from '../../../../response-elements/unsuccessful-action-component/unsuccessful-action-component';
import { VideogameService } from '../../../../../services/enterprise/videogame.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-delete-videogame-category-component',
  imports: [FormsModule, ReactiveFormsModule, SuccessfulActionComponent,
    UnsuccessfulActionComponent],
  templateUrl: './delete-videogame-category-component.html',
  styleUrl: './delete-videogame-category-component.css',
})
export class DeleteVideogameCategoryComponent extends GenericDelete<Videogame> {

  title?: string;
  enterprise?: string;
  category?: string;

  constructor(
    private route: ActivatedRoute,
    formBuilder: FormBuilder,
    service: VideogameService
  ) {

    super(formBuilder, service);

  }

  override ngOnInit(): void {
    this.setFormValidations();
    this.setVideogameInformation();
  }

  public override setFormValidations(): void {
    this.deleteObjectForm = this.formBuilder.group({
      category: [null, [Validators.maxLength(100)]]
    });
  }

  public override getPrimaryKeys(values: FormGroup): string[] {

    const form = values.value;

    const category: string = form.category;
    
    if (this.title == null || this.enterprise == null) {
      throw new Error("CAN'T GET THE PKS TO UPDATE AN CATEGORY");
    }

    const pks: string[] = [this.title, this.enterprise, category];

    return pks;
  }

  public setVideogameInformation(): void {
    const title = this.route.snapshot.paramMap.get('title');
    const enterprise = this.route.snapshot.paramMap.get('enterprise');

    if (!title || !enterprise) return;

    this.title = title;
    this.enterprise = enterprise;

  }

}
