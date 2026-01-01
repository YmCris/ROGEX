import { Component } from '@angular/core';
import { GenericCreate } from '../../../common/crud-components/GenericCreate';
import { Invitation } from '../../../models/invitations/Invitation';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { InvitationService } from '../../../services/users/invitations.service';
import { AuthService } from '../../../core/auth/auth.service';
import { SuccessfulActionComponent } from '../../../components/response-elements/successful-action-component/successful-action.component';
import { UnsuccessfulActionComponent } from '../../../components/response-elements/unsuccessful-action-component/unsuccessful-action-component';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-invitate-group-member-page',
  imports: [FormsModule, ReactiveFormsModule, SuccessfulActionComponent, UnsuccessfulActionComponent],
  templateUrl: './invitate-group-member-page.html',
  styleUrl: './invitate-group-member-page.css',
})
export class InvitateGroupMemberPage extends GenericCreate<Invitation> {

  groupName!: string;

  constructor(
    formBuilder: FormBuilder,
    service: InvitationService,
    private authService: AuthService,
    private route: ActivatedRoute,
  ) {
    super(formBuilder, service);
  }

  override ngOnInit(): void {
    super.ngOnInit();
    this.setGroupInformation();
  }


  public setGroupInformation(): void {
    const groupName = this.route.snapshot.paramMap.get('groupName');
    if (!groupName) return;

    this.groupName = groupName;
  }


  public override setFormValidations(): void {
    this.createObjectForm = this.formBuilder.group({
      receiverEmail: [null, [Validators.required, Validators.maxLength(100)]],
      invitationText: [null, [Validators.required, Validators.maxLength(150)]],
    });
  }

  public override getCreateValues(values: FormGroup): Invitation {
    if (!this.groupName) {
      throw new Error('groupName not found');
    }

    const form = values.value;

    return {
      invitationText: form.invitationText,
      groupName: this.groupName,
      senderEmail: this.getEmail(),
      receiverEmail: form.receiverEmail,
    };
  }



  protected getEmail(): string {
    const email = this.authService.user()?.email;
    if (!email) {
      throw new Error('Email not found in session');
    }
    return email;
  }

}
