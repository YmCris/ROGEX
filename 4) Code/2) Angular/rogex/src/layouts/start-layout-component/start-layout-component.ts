import { Component } from '@angular/core';
import { RouterOutlet } from "@angular/router";
import {StartPageHeaderComponent} from "../../components/includes/start-page-includes/start-page-header/start.page.header.component";
import { StartPageFooterComponent } from '../../components/includes/start-page-includes/start-page-footer/start.page.footer.component';

@Component({
  selector: 'app-start-layout-component',
  imports: [RouterOutlet, StartPageHeaderComponent, StartPageFooterComponent],
  templateUrl: './start-layout-component.html',
})
export class StartLayoutComponent {

}
