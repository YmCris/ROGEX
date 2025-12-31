import { Component } from '@angular/core';
import { CreateEnterpriseUserComponent } from '../../../components/enterprise/enterprise-users/create-enterprise-user-component/create-enterprise-user-component';
import { UpdateEnterpriseUserComponent } from '../../../components/enterprise/enterprise-users/update-enterprise-user-component/update-enterprise-user-component';
import { DeleteEnterpriseUserComponent } from '../../../components/enterprise/enterprise-users/delete-enterprise-user-component/delete-enterprise-user-component';
import { GetEnterpriseUserComponent } from '../../../components/enterprise/enterprise-users/get-enterprise-user-component/get-enterprise-user-component';

@Component({
  selector: 'app-employees-page',
  imports: [CreateEnterpriseUserComponent, UpdateEnterpriseUserComponent,
     DeleteEnterpriseUserComponent, GetEnterpriseUserComponent],
  templateUrl: './employees-page.html',
  styleUrl: './employees-page.css',
})
export class EmployeesPage {

}
