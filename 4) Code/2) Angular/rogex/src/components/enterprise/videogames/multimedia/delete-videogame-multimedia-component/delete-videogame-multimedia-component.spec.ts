import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteVideogameMultimediaComponent } from './delete-videogame-multimedia-component';

describe('DeleteVideogameMultimediaComponent', () => {
  let component: DeleteVideogameMultimediaComponent;
  let fixture: ComponentFixture<DeleteVideogameMultimediaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DeleteVideogameMultimediaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeleteVideogameMultimediaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
