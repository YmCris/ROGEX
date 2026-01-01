import { Component } from '@angular/core';
import { GenericDelete } from '../../../../common/crud-components/GenericDelete';
import { EnterpriseUsers } from '../../../../models/enterprise-users/EnterpriseUser';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { SuccessfulActionComponent } from '../../../response-elements/successful-action-component/successful-action.component';
import { UnsuccessfulActionComponent } from '../../../response-elements/unsuccessful-action-component/unsuccessful-action-component';
import { EnterpriseUsersService } from '../../../../services/admin/enterprises.users.service';

@Component({
  selector: 'app-delete-enterprise-user-component',
  imports: [FormsModule, ReactiveFormsModule, ReactiveFormsModule,
    SuccessfulActionComponent, UnsuccessfulActionComponent],
  templateUrl: './delete-enterprise-user-component.html',
  styleUrl: './delete-enterprise-user-component.css',
})
export class DeleteEnterpriseUserComponent extends GenericDelete<EnterpriseUsers> {


  constructor(formBuilder: FormBuilder,
    enterpriseUserService: EnterpriseUsersService) {

    super(formBuilder, enterpriseUserService);

  }

  public override setFormValidations(): void {
    this.deleteObjectForm = this.formBuilder.group({
      email: [null, [Validators.maxLength(100)]]
    });
  }

  public override getPrimaryKeys(values: FormGroup): string[] {

    const form = values.value;
    const email: string = form.email;
    const pks: string[] = [email];

    return pks;
  }

}
