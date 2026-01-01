import { Component } from '@angular/core';
import { BuyVideogameComponent } from "../../../components/user/buy-videogame-component/buy-videogame-component";
import { Videogame } from '../../../models/videogames/Videogame';
import { ActivatedRoute } from '@angular/router';
import { VideogameService } from '../../../services/enterprise/videogame.service';

@Component({
  selector: 'app-buy-videogame-page',
  imports: [BuyVideogameComponent],
  templateUrl: './buy-videogame-page.html',
  styleUrl: './buy-videogame-page.css',
})
export class BuyVideogamePage {

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
