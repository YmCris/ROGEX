import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { VideogameMultimedia } from '../../../../../models/videogames-multimedia/VideogameMultimedia';
import { VideogameMultimediaService } from '../../../../../services/enterprise/videogame-multimedia.service';
import { Videogame } from '../../../../../models/videogames/Videogame';
import { GenericGetWithoutFormInput } from '../../../../../common/crud-components/GenericGetWithoutFormInput';

@Component({
  selector: 'app-get-videogame-multimedia-component',
  imports: [],
  templateUrl: './get-videogame-multimedia-component.html',
  styleUrl: './get-videogame-multimedia-component.css',
})
export class GetVideogameMultimediaComponent extends GenericGetWithoutFormInput<VideogameMultimedia> implements OnChanges {

  @Input() videogame?: Videogame;

  constructor(
    service: VideogameMultimediaService
  ) {
    super(service);
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes["videogame"] && this.videogame) {
      this.defineLoad();
    }
  }

  protected override defineLoad(): void {
    if (!this.videogame?.title || !this.videogame?.enterpriseName) return;

    this.loadByPrimaryKeys([
      this.videogame.title,
      this.videogame.enterpriseName
    ]);
  }

}
