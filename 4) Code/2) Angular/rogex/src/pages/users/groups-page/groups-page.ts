import { Component } from '@angular/core';
import { UnsuccessfulActionComponent } from '../../../components/response-elements/unsuccessful-action-component/unsuccessful-action-component';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { SuccessfulActionComponent } from '../../../components/response-elements/successful-action-component/successful-action.component';
import { Group } from '../../../models/groups/Group';
import { GenericCrud } from '../../../common/crud-pages/GenericCrud';
import { GroupService } from '../../../services/users/group.service';
import { AuthService } from '../../../core/auth/auth.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-groups-page',
  imports: [FormsModule, ReactiveFormsModule,
    SuccessfulActionComponent, UnsuccessfulActionComponent, RouterModule],
  templateUrl: './groups-page.html',
  styleUrl: './groups-page.css',
})
export class GroupsPage extends GenericCrud<Group> {

  public constructor(
    formBuilder: FormBuilder,
    service: GroupService,
    private authService: AuthService
  ) {
    super(formBuilder, service);
  }

  public override setFormsValidations(): void {

    this.createObjectForm = this.formBuilder.group({
      name: [null, [Validators.required, Validators.maxLength(100)]],
    });

    this.updateObjectForm = this.formBuilder.group({});

    this.deleteObjectForm = this.formBuilder.group({
      name: [null, [Validators.required]]
    });


  }

  public override getCreateValues(values: FormGroup) {

    const formValues = values.value;

    const group: Group = {
      name: formValues.name,
      creatorEmail: this.getUserEmail(),
    };

    return group;
  }


  public override getUpdateValues(values: FormGroup): Partial<Group> {
    throw new Error("Not avaible");
  }

  public override getPrimaryKeys(values: FormGroup): string[] {
    const form = values.value;
    const name: string = form.name;

    const pks: string[] = [name];

    return pks;
  }

  public override afterInit(): void {
    this.loadObjects();
  }

  public getUserEmail(): string {
    const email = this.authService.user()?.email;
    if (!email) {
      throw new Error('email not found in session');
    }
    return email;
  }


}
