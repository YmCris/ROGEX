import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../../../services/admin/categories.service';
import { Category } from '../../../models/categories/Category';

@Component({
  selector: 'app-get-categories-component',
  imports: [],
  templateUrl: './get-categories-component.html',
  styleUrl: './get-categories-component.css',
})
export class GetCategoriesComponent implements OnInit {

  actionDone = false;
  operationDone = false;
  responseMessage = '';
  categories: Category[] = [];
  loadingCategories = false;


  constructor(
    private categoryService: CategoryService
  ) { }

  ngOnInit(): void {
    this.loadCategories();
  }

  loadCategories(): void {
    this.loadingCategories = true;

    this.categoryService.getAllCategories().subscribe({
      next: (categories) => {
        this.categories = categories;
        this.loadingCategories = false;
      },
      error: () => {
        this.loadingCategories = false;
        this.actionDone = true;
        this.operationDone = false;
        this.responseMessage = 'Error cargando categorías';
      }
    });
  }

}
