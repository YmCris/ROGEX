import { Component } from '@angular/core';
import { EnterpriseUsers } from '../../../models/enterprise-users/EnterpriseUser';
import { EnterpriseUsersService } from '../../../services/admin/enterprises.users.service';
import { AuthService } from '../../../core/auth/auth.service';
import { GenericGetWithoutForm } from '../../../common/crud-components/GeneriGetWithoutForm';

@Component({
  selector: 'app-get-enterprise-user-component',
  templateUrl: './get-enterprise-user-component.html',
  styleUrl: './get-enterprise-user-component.css',
})
export class GetEnterpriseUserComponent extends GenericGetWithoutForm<EnterpriseUsers> {

  constructor(
    enterpriseUserService: EnterpriseUsersService,
    private authService: AuthService
  ) {

    super(enterpriseUserService);

  }

  private getEnterpriseName(): string | undefined {
    return this.authService.user()?.enterpriseName;
  }

  protected override getPrimaryKeys(): string[] {
    const enterprise = this.getEnterpriseName();
    if (!enterprise) {
      throw new Error('Enterprise not found in session');
    }
    return [enterprise];
  }




}
