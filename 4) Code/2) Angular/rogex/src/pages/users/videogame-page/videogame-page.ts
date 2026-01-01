import { Component, OnInit } from '@angular/core';
import { Videogame } from '../../../models/videogames/Videogame';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { VideogameService } from '../../../services/enterprise/videogame.service';
import { GetVideogameMultimediaComponent } from "../../../components/enterprise/videogames/multimedia/get-videogame-multimedia-component/get-videogame-multimedia-component";
import { GetVideogameCategoriesComponent } from "../../../components/enterprise/videogames/categories/get-videogame-categories-component/get-videogame-categories-component";

@Component({
  selector: 'app-videogame-page',
  imports: [GetVideogameMultimediaComponent, GetVideogameCategoriesComponent, RouterModule],
  templateUrl: './videogame-page.html',
  styleUrl: './videogame-page.css',
})
export class UserVideogamePage implements OnInit {

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

  public buyVideogame(): void{
    
  }

}
