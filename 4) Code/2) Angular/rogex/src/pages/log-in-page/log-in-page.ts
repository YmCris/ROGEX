import { Component } from '@angular/core';
import { LogInForm } from '../../components/system/log-in-form/log-in-form';
import { User } from '../../models/users/user';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { KeyValuePipe, NgFor } from '@angular/common';
import { SuccessfulActionComponent } from "../../components/response-elements/successful-action/successful-action.component";
import { UnsuccessfulActionComponent } from "../../components/response-elements/unsuccessful-action-component/unsuccessful-action-component";
@Component({
  selector: 'app-log-in-page',
  imports: [LogInForm, FormsModule, ReactiveFormsModule,  ReactiveFormsModule, SuccessfulActionComponent, UnsuccessfulActionComponent],
  templateUrl: './log-in-page.html',
  styleUrl: './log-in-page.css',
})
export class LogInPage {

}
