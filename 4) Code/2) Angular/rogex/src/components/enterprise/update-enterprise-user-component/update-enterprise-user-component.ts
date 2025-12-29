import { Component } from '@angular/core';
import { GenericUpdate } from '../../../common/crud-components/GenericUpdate';
import { EnterpriseUsers } from '../../../models/enterprise-users/EnterpriseUser';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { EnterpriseUsersService } from '../../../services/admin/enterprises.users.service';
import { SuccessfulActionComponent } from '../../response-elements/successful-action-component/successful-action.component';
import { UnsuccessfulActionComponent } from '../../response-elements/unsuccessful-action-component/unsuccessful-action-component';
import { UpdateEnterpriseUserRequest } from '../../../models/enterprise-users/UpdateEnterpriseUserRequest';

@Component({
  selector: 'app-update-enterprise-user-component',
  imports: [FormsModule, ReactiveFormsModule, ReactiveFormsModule,
    SuccessfulActionComponent, UnsuccessfulActionComponent],
  templateUrl: './update-enterprise-user-component.html',
  styleUrl: './update-enterprise-user-component.css',
})
export class UpdateEnterpriseUserComponent extends GenericUpdate<EnterpriseUsers> {


  constructor(formBuilder: FormBuilder,
    enterpriseUserService: EnterpriseUsersService) {

    super(formBuilder, enterpriseUserService);

  }

  public override setFormValidations(): void {
    this.updateObjectForm = this.formBuilder.group({
      email: [null, [Validators.maxLength(100)]],
      name: [null, [Validators.maxLength(150)]],
      password: [null, [Validators.maxLength(25)]],
      birthDate: [null, []],
    });
  }

  public override getUpdateValues(values: FormGroup): Partial<EnterpriseUsers> {

    const form = values.value;

    const updateAttributes: UpdateEnterpriseUserRequest = {
      name: form.name,
      password: form.password,
      birthDate: form.birthDate,
    };

    return updateAttributes;
  }

  public override getPrimaryKeys(values: FormGroup): string[] {

    const form = values.value;
    const email: string = form.email;
    const pks: string[] = [email];

    return pks;
  }

}
