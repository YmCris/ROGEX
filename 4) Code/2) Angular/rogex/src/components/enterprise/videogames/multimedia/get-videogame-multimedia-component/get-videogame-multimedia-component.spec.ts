import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetVideogameMultimediaComponent } from './get-videogame-multimedia-component';

describe('GetVideogameMultimediaComponent', () => {
  let component: GetVideogameMultimediaComponent;
  let fixture: ComponentFixture<GetVideogameMultimediaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GetVideogameMultimediaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetVideogameMultimediaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
