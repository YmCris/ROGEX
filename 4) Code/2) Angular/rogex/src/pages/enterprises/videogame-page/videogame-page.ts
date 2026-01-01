import { Component } from '@angular/core';
import { CreateVideogameCategoryComponent } from '../../../components/enterprise/videogames/categories/create-videogame-category-component/create-videogame-category-component';
import { UpdateVideogameCategoryComponent } from '../../../components/enterprise/videogames/categories/update-videogame-category-component/update-videogame-category-component';
import { DeleteVideogameCategoryComponent } from '../../../components/enterprise/videogames/categories/delete-videogame-category-component/delete-videogame-category-component';
import { GetVideogameCategoriesComponent } from '../../../components/enterprise/videogames/categories/get-videogame-categories-component/get-videogame-categories-component';

import { AddVideogameMultimediaComponent } from '../../../components/enterprise/videogames/multimedia/add-videogame-multimedia-component/add-videogame-multimedia-component';
import { DeleteVideogameMultimediaComponent } from '../../../components/enterprise/videogames/multimedia/delete-videogame-multimedia-component/delete-videogame-multimedia-component';
import { GetVideogameMultimediaComponent } from '../../../components/enterprise/videogames/multimedia/get-videogame-multimedia-component/get-videogame-multimedia-component';

import { ActivatedRoute } from '@angular/router';
import { UpdateVideogameComponent } from '../../../components/enterprise/videogames/videogame/update-videogame-component/update-videogame-component';
import { VideogameService } from '../../../services/enterprise/videogame.service';

import { Videogame } from '../../../models/videogames/Videogame';
import { GetCategoriesComponent } from "../../../components/admins/get-categories-component/get-categories-component";

@Component({
  selector: 'app-videogame-page',
  imports: [CreateVideogameCategoryComponent, UpdateVideogameCategoryComponent,
    DeleteVideogameCategoryComponent, GetVideogameCategoriesComponent,
    AddVideogameMultimediaComponent,/* DeleteVideogameMultimediaComponent,*/
    GetVideogameMultimediaComponent, UpdateVideogameComponent, GetCategoriesComponent],
  templateUrl: './videogame-page.html',
  styleUrl: './videogame-page.css',
})
export class VideogamePage {

  title?: string;
  enterprise?: string;
  videogame?: Videogame;

  constructor(
    private route: ActivatedRoute,
    private service: VideogameService
  ) { }

  ngOnInit(): void {
    this.setVideogameInformation();
  }

  public setVideogameInformation(): void {
    const title = this.route.snapshot.paramMap.get('title');
    const enterprise = this.route.snapshot.paramMap.get('enterprise');

    if (!title || !enterprise) return;

    this.service.getByPrimaryKeys([title, enterprise]).subscribe({
      next: (obtainedVideogame) => {
        this.videogame = obtainedVideogame
      },
      error: () => {
        throw new Error('Failed to load videogame');
      }
    });
  }


}
