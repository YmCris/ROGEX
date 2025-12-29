import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { SuccessfulActionComponent } from "../../../components/response-elements/successful-action-component/successful-action.component";
import { UnsuccessfulActionComponent } from "../../../components/response-elements/unsuccessful-action-component/unsuccessful-action-component";
import { Router } from '@angular/router';

import { AuthService } from '../../../core/auth/auth.service';
import { UserLog } from '../../../models/log-in/user-log';

@Component({
  selector: 'app-log-in-page',
  imports: [FormsModule, ReactiveFormsModule, ReactiveFormsModule, SuccessfulActionComponent, UnsuccessfulActionComponent],
  templateUrl: './log-in-page.html',
  styleUrl: './log-in-page.css',
})

export class LogInPage implements OnInit {

  logInForm!: FormGroup;
  actionDone: boolean = false;
  operationDone: boolean = false;
  responseMessage: string = '';

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) { }


  ngOnInit(): void {

    this.authService.logout().catch(() => { });// Close Session where the user return to log in page
    console.log('Session closed');

    this.logInForm = this.formBuilder.group({
      email: [null, [Validators.required, Validators.maxLength(50)]],
      password: [null, [Validators.required, Validators.maxLength(50)]],
    });
    this.reset();
  }

  submit(): void {
    console.log('se hizo submit');
    if (this.logInForm.valid) {
      this.logIn();
    }
  }

  reset(): void {
    this.logInForm.reset();
    this.actionDone = false;
    this.operationDone = false;
    this.responseMessage = '';
  }

  logIn(): void {
    if (this.logInForm.invalid) return;

    this.authService.login(
      this.logInForm.value.email,
      this.logInForm.value.password
    ).then((user: UserLog) => {

      this.operationDone = true;
      this.responseMessage = 'Log in successful.';
      this.actionDone = true;
      console.log('Logged as:', user.role);
      switch (user.role) {
        case 'USER':
          this.router.navigateByUrl('/gamer');
          break;

        case 'ENTERPRISE':
          this.router.navigateByUrl('/employee');
          break;

        case 'ADMIN':
          this.router.navigateByUrl('/admin');
          break;

        default:
          this.router.navigateByUrl('/');
      }

    }).catch(err => {
      this.operationDone = false;
      this.responseMessage = err?.error?.message ?? 'Login failed';
      this.actionDone = true;
    });
  }


}

