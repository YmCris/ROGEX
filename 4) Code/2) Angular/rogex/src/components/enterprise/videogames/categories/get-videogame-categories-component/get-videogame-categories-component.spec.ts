import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetVideogameCategoriesComponent } from './get-videogame-categories-component';

describe('GetVideogameCategoriesComponent', () => {
  let component: GetVideogameCategoriesComponent;
  let fixture: ComponentFixture<GetVideogameCategoriesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GetVideogameCategoriesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetVideogameCategoriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
