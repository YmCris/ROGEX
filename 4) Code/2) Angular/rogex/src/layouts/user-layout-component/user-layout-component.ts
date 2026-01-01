import { Component } from '@angular/core';
import { UserPageHeader } from "../../components/includes/user-page-includes/user-page-header/user-page-header";
import { UserPageFooter } from "../../components/includes/user-page-includes/user-page-footer/user-page-footer";
import { RouterOutlet } from "@angular/router";

@Component({
  selector: 'app-user-layout-component',
  imports: [UserPageHeader, UserPageFooter, RouterOutlet],
  templateUrl: './user-layout-component.html',
  styleUrl: './user-layout-component.css',
})
export class UserLayoutComponent {

}
