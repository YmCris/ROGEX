import { Component, OnInit } from '@angular/core';
import { GenericGetWithoutForm } from '../../../common/crud-components/GenericGetWithoutForm';
import { Videogame } from '../../../models/videogames/Videogame';
import { VideogameService } from '../../../services/enterprise/videogame.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { SearchComponents } from "../../../components/user/search-components/search-components";

@Component({
  selector: 'app-shop',
  standalone: true,
  imports: [CommonModule, RouterModule, SearchComponents],
  templateUrl: './shop.html',
  styleUrl: './shop.css',
})
export class Shop extends GenericGetWithoutForm<Videogame> {

  constructor(
    service: VideogameService
  ) {
    super(service);
  }

  protected override defineLoad(): void {
    this.load();
  }

}
