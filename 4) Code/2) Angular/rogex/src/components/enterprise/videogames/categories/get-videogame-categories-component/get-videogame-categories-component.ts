import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { Videogame } from '../../../../../models/videogames/Videogame';
import { VideogameService } from '../../../../../services/enterprise/videogame.service';
import { Category } from '../../../../../models/categories/Category';

@Component({
  selector: 'app-get-videogame-categories-component',
  imports: [],
  templateUrl: './get-videogame-categories-component.html',
  styleUrl: './get-videogame-categories-component.css',
})
export class GetVideogameCategoriesComponent implements OnChanges {

  @Input() videogame?: Videogame;

  protected state: CrudState<Category> = {
    loading: false,
    success: false,
    error: false,
    message: '',
    data: []
  };

  constructor(
    private service: VideogameService,
  ) { }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['videogame'] && this.videogame) {
      this.loadByPrimaryKeys([this.videogame.title, this.videogame.enterpriseName]);
    }
  }

  protected loadByPrimaryKeys(primaryKeys: string[]): void {
    this.resetState();
    this.state.loading = true;

    this.service.getByPrimaryKeys(primaryKeys).subscribe({
      next: game => {
        if (!game) {
          this.state.error = true;
          this.state.message = 'Videogame no encontrado';
          this.state.loading = false;
          return;
        }

        this.state.data = game.categories ?? [];
        this.state.loading = false;
      },
      error: (err) => {
        console.error('Error HTTP:', err);
        this.state.loading = false;
        this.state.error = true;
        this.state.message = err.error?.message ?? 'Error cargando categorías';
      }
    });
  }

  private resetState(): void {
    this.state.success = false;
    this.state.error = false;
    this.state.message = '';
  }

}
