import { Component } from '@angular/core';
import { CreateEnterpriseUserComponent } from '../../../components/enterprise/create-enterprise-user-component/create-enterprise-user-component';
import { UpdateEnterpriseUserComponent } from '../../../components/enterprise/update-enterprise-user-component/update-enterprise-user-component';
import { DeleteEnterpriseUserComponent } from '../../../components/enterprise/delete-enterprise-user-component/delete-enterprise-user-component';
import { GetEnterpriseUserComponent } from '../../../components/enterprise/get-enterprise-user-component/get-enterprise-user-component';

@Component({
  selector: 'app-employees-page',
  imports: [CreateEnterpriseUserComponent, UpdateEnterpriseUserComponent
    , DeleteEnterpriseUserComponent, GetEnterpriseUserComponent
  ],
  templateUrl: './employees-page.html',
  styleUrl: './employees-page.css',
})
export class EmployeesPage {

}
