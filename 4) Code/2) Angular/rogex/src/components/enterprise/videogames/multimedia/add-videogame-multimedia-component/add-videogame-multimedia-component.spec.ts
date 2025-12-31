import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddVideogameMultimediaComponent } from './add-videogame-multimedia-component';

describe('AddVideogameMultimediaComponent', () => {
  let component: AddVideogameMultimediaComponent;
  let fixture: ComponentFixture<AddVideogameMultimediaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddVideogameMultimediaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddVideogameMultimediaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
