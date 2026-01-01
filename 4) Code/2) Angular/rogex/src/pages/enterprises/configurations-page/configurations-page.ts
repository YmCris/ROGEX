import { Component } from '@angular/core';
import { UdpateEnterpriseComponent } from "../../../components/enterprise/enterprise/udpate-enterprise-component/udpate-enterprise-component";
import { ViewEnterpriseInformationComponent } from "../../../components/enterprise/enterprise/view-enterprise-information-component/view-enterprise-information-component";

@Component({
  selector: 'app-configurations-page',
  imports: [UdpateEnterpriseComponent, ViewEnterpriseInformationComponent],
  templateUrl: './configurations-page.html',
  styleUrl: './configurations-page.css',
})
export class ConfigurationsPage {

}
