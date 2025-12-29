import { Component } from '@angular/core';
import { GenericCrud } from '../../../common/crud-pages/GenericCrud';
import { EnterpriseUsers } from '../../../models/enterprise-users/EnterpriseUser';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-enterprise-users-page',
  imports: [],
  templateUrl: './enterprise-users-page.html',
  styleUrl: './enterprise-users-page.css',
})
export class EnterpriseUsersPage extends GenericCrud<EnterpriseUsers> {

  // OVERRIDE METHODS ----------------------------------------------------------
  public override setFormsValidations(): void {
    throw new Error('Method not implemented.');
  }

  public override getCreateValues(values: FormGroup) {
    throw new Error('Method not implemented.');
  }

  public override getUpdateValues(values: FormGroup): Partial<EnterpriseUsers> {
    throw new Error('Method not implemented.');
  }

  public override getPrimaryKeys(values: FormGroup): string[] {
    throw new Error('Method not implemented.');
  }

}
