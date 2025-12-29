import { Component } from '@angular/core';
import { EnterprisePageHeader } from "../../components/includes/enterprise-page-includes/enterprise-page-header/enterprise-page-header";
import { EnterprisePageFooter } from "../../components/includes/enterprise-page-includes/enterprise-page-footer/enterprise-page-footer";
import { RouterOutlet } from "@angular/router";

@Component({
  selector: 'app-enterprise-layout-component',
  imports: [EnterprisePageHeader, EnterprisePageFooter, RouterOutlet],
  templateUrl: './enterprise-layout-component.html',
  styleUrl: './enterprise-layout-component.css',
})
export class EnterpriseLayoutComponent {

}
