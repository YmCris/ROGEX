import { Component, Input, input, OnInit } from '@angular/core';
import { User } from '../../models/users/user';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { KeyValuePipe, NgFor } from '@angular/common';
import { UsersService } from '../../services/users/users.service';
import { SuccessfulActionComponent } from "../../components/response-elements/successful-action/successful-action.component";
import { UnsuccessfulActionComponent } from "../../components/response-elements/unsuccessful-action-component/unsuccessful-action-component";


@Component({
  selector: 'app-sign-up-page',
  imports: [FormsModule, ReactiveFormsModule, SuccessfulActionComponent, UnsuccessfulActionComponent],
  templateUrl: './sign-up-page.html',
  styleUrl: './sign-up-page.css'
})
export class SignUpPage implements OnInit {
  [x: string]: any;
  @Input()
  isEditMode: boolean = false;
  @Input()
  userToUpdate!: User;

  newUserForm!: FormGroup;
  newUser!: User;
  actionDone: boolean = false;
  operationDone: boolean = false;

  constructor(private formBuilder: FormBuilder,
    private usersService: UsersService
  ) {

  }

  onFileSelected(event: Event) {
    const file = (event.target as HTMLInputElement).files?.[0];
  }

  ngOnInit(): void {
    this.newUserForm = this.formBuilder.group({
      photo: [null, [Validators.maxLength(255)]],
      nickname: [null, [Validators.required, Validators.maxLength(100)]],
      password: [null, [Validators.required, Validators.maxLength(100)]],
      birthDate: [null, Validators.required],
      email: [null, [Validators.required, Validators.email]],
      phoneNumber: [null, Validators.required],
      country: [null, Validators.required],
      publicLibrary: [false]
    });
    this.reset();
  }

  submit(): void {
    console.log('se hizo submit');
    if (this.newUserForm.valid) {
      if (this.isEditMode) {
        this.updateUser();
      } else {
        this.saveNewUser();
      }
    }
  }

  reset(): void {
    if (this.isEditMode) {
      this.resetOnEdit();
      console.log('reset edit');
    } else {
      this.resetOnCreate();
    }
    this.operationDone = false;
    this.actionDone = false;
  }

  private resetOnCreate(): void {
    this.newUserForm.reset({
      startDate: new Date().toISOString().substring(0, 10)
    });
  }

  private resetOnEdit(): void {
    this.newUserForm.reset(this.userToUpdate);
  }

  private saveNewUser(): void {
    this.newUser = this.newUserForm.value as User;

    this.usersService.createNewUser(this.newUser).subscribe({
      next: () => {
        this.operationDone = true;
        this.actionDone = true;
        this.reset();
      },
      error: (error: any) => {
        this.operationDone = false;
        this.actionDone = true;
        console.log(error);
      }
    });
    console.log(this.newUser);
  }

  private updateUser(): void {
    this.userToUpdate = this.newUserForm.value as User;
    const { email, ...userToUpdateRequest } = this.userToUpdate;

    this.usersService.updateUser(this.userToUpdate.email, userToUpdateRequest).subscribe({
      next: () => {
        this.operationDone = true;
        this.actionDone = true;
        this.reset();
      },
      error: (error: any) => {
        console.log(error);
        this.operationDone = false;
        this.actionDone = true;
      }
    });
  }
}