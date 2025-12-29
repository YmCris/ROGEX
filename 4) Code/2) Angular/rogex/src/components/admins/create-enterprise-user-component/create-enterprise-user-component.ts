import { Component } from '@angular/core';
import { GenericCreate } from '../../../common/crud-components/GenericCreate';
import { EnterpriseUsers } from '../../../models/enterprise-users/EnterpriseUser';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { SuccessfulActionComponent } from '../../response-elements/successful-action-component/successful-action.component';
import { UnsuccessfulActionComponent } from '../../response-elements/unsuccessful-action-component/unsuccessful-action-component';
import { EnterpriseUsersService } from '../../../services/admin/enterprises.users.service';

@Component({
  selector: 'app-create-enterprise-user-component',
  imports: [FormsModule, ReactiveFormsModule, ReactiveFormsModule, SuccessfulActionComponent, UnsuccessfulActionComponent],
  templateUrl: './create-enterprise-user-component.html',
  styleUrl: './create-enterprise-user-component.css',
})
export class CreateEnterpriseUserComponent extends GenericCreate<EnterpriseUsers> {

  constructor(
    fb: FormBuilder,
    enterpriseUserService: EnterpriseUsersService
  ) {
    super(fb, enterpriseUserService);
  }

  // OVERRIDE METHODS ----------------------------------------------------------
  public override setFormValidations(): void {
    this.createObjectForm = this.formBuilder.group({
      email: [null, [Validators.required, Validators.maxLength(100)]],
      name: [null, [Validators.required, Validators.maxLength(150)]],
      password: [null, [Validators.required, Validators.max(25)]],
      birthDate: [null, [Validators.required]],
      enterpriseName: [null, [Validators.required, Validators.maxLength(150)]]
    });
  }

  public override getCreateValues(values: FormGroup) {

    const form = values.value;

    const enterpriseUser: EnterpriseUsers = {
      email: form.email,
      name: form.name,
      password: form.password,
      birthDate: form.birthDate,
      enterpriseName: form.enterpriseName
    };

    return enterpriseUser;

  }

}
