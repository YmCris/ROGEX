import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateVideogameCategoryComponent } from './update-videogame-category-component';

describe('UpdateVideogameCategoryComponent', () => {
  let component: UpdateVideogameCategoryComponent;
  let fixture: ComponentFixture<UpdateVideogameCategoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdateVideogameCategoryComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateVideogameCategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
