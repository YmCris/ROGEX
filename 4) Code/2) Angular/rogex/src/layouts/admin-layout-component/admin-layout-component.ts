import { Component } from '@angular/core';
import { AdminPageHeader } from "../../components/includes/admin-page-includes/admin-page-header/admin-page-header";
import { AdminPageFooter } from "../../components/includes/admin-page-includes/admin-page-footer/admin-page-footer";
import { RouterOutlet } from "@angular/router";

@Component({
  selector: 'app-admin-layout-component',
  imports: [AdminPageHeader, AdminPageFooter, RouterOutlet],
  templateUrl: './admin-layout-component.html',
})
export class AdminLayoutComponent {

}
