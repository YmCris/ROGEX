import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { StartPageFooterComponent } from '../components/start_page_includes/start-page-footer/start.page.footer.component';
import { StartPageHeaderComponent } from '../components/start_page_includes/start-page-header/start.page.header.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, StartPageFooterComponent, StartPageHeaderComponent],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('rogex');
}
