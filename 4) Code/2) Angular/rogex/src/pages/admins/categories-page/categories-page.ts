import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CategoryService } from '../../../services/admin/categories.service';
import { SuccessfulActionComponent } from "../../../components/response-elements/successful-action-component/successful-action.component";
import { UnsuccessfulActionComponent } from "../../../components/response-elements/unsuccessful-action-component/unsuccessful-action-component";
import { Category } from '../../../models/categories/Category';

@Component({
  selector: 'app-categories-page',
  imports: [FormsModule, ReactiveFormsModule, ReactiveFormsModule, SuccessfulActionComponent, UnsuccessfulActionComponent],
  templateUrl: './categories-page.html',
  styleUrl: './categories-page.css',
})
export class CategoriesPage implements OnInit {

  createCategoryForm!: FormGroup;
  updateCategoryForm!: FormGroup;
  deleteCategoryForm!: FormGroup;

  actionDone = false;
  operationDone = false;
  responseMessage = '';
  categories: Category[] = [];
  loadingCategories = false;


  constructor(
    private formBuilder: FormBuilder,
    private categoryService: CategoryService
  ) { }

  ngOnInit(): void {
    this.createCategoryForm = this.formBuilder.group({
      name: [null, [Validators.required, Validators.maxLength(50)]],
    });

    this.updateCategoryForm = this.formBuilder.group({
      name: [null, [Validators.required, Validators.maxLength(50)]],
      newName: [null, [Validators.required, Validators.maxLength(50)]],
    });

    this.deleteCategoryForm = this.formBuilder.group({
      name: [null, [Validators.required, Validators.maxLength(50)]],
    });

    this.loadCategories();
  }

  createCategory(): void {
    if (this.createCategoryForm.invalid) return;

    const requestPayload = {
      name: this.createCategoryForm.value.name
    };

    this.categoryService.createCategory(requestPayload).subscribe({
      next: () => {
        this.success('Categoría creada correctamente', this.createCategoryForm);
        this.loadCategories();
      },
      error: err => this.fail(err)
    });
  }


  updateCategory(): void {
    if (this.updateCategoryForm.invalid) return;

    const { name, newName } = this.updateCategoryForm.value;
    console.log('Name: ' + name + ' new Name ' + newName);
    this.categoryService.updateCategory(name, { name: newName }).subscribe({
      next: () => {
        this.success('Categoría actualizada', this.updateCategoryForm),
          this.loadCategories();
      },
      error: err => this.fail(err)
    });
  }

  deleteCategory(): void {
    if (this.deleteCategoryForm.invalid) return;

    const { name } = this.deleteCategoryForm.value;

    this.categoryService.deleteCategory(name).subscribe({
      next: () => {
        this.success('Categoría eliminada', this.deleteCategoryForm),
          this.loadCategories();
      },
      error: err => this.fail(err)
    });
  }

  private success(message: string, form: FormGroup): void {
    this.operationDone = true;
    this.actionDone = true;
    this.responseMessage = message;
    form.reset();
  }

  private fail(err: any): void {
    this.operationDone = false;
    this.actionDone = true;
    this.responseMessage = err?.error?.message ?? 'Operación fallida';
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
