import { Component } from '@angular/core';
import { GenericCrud } from '../../../common/crud-pages/GenericCrud';
import { Wallet } from '../../../models/wallet/Wallet';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { WalletService } from '../../../services/users/wallet.service';
import { SuccessfulActionComponent } from '../../../components/response-elements/successful-action-component/successful-action.component';
import { UnsuccessfulActionComponent } from '../../../components/response-elements/unsuccessful-action-component/unsuccessful-action-component';
import { RouterModule } from '@angular/router';
import { UpdateWalletRequest } from '../../../models/wallet/UpdateWalletRequest';
import { AuthService } from '../../../core/auth/auth.service';

@Component({
  selector: 'app-wallet-page',
  imports: [FormsModule, ReactiveFormsModule,
    SuccessfulActionComponent, UnsuccessfulActionComponent, RouterModule],
  templateUrl: './wallet-page.html',
  styleUrl: './wallet-page.css',
})
export class WalletPage extends GenericCrud<Wallet> {

  public constructor(
    formBuilder: FormBuilder,
    service: WalletService,
    private authService: AuthService
  ) {
    super(formBuilder, service);
  }

  public override setFormsValidations(): void {

    this.createObjectForm = this.formBuilder.group({
      name: [null, [Validators.required, Validators.maxLength(150)]],
      fund: [null, [Validators.min(0), Validators.max(1000)]],
      banck: [Validators.required],
    });

    this.updateObjectForm = this.formBuilder.group({
      name: [null, [Validators.required, Validators.maxLength(150)]],
      fund: [null, [Validators.min(0), Validators.max(1000)]],
      banck: [Validators.required],
    });

    this.getObjectsForm = this.formBuilder.group({});

  }

  public override getCreateValues(values: FormGroup) {

    const formValues = values.value;

    const wallet: Wallet = {
      userEmail: this.getUserEmail(),
      name: formValues.name,
      fund: formValues.fund,
      banck: formValues.banck,
    };

    return wallet;
  }


  public override getUpdateValues(values: FormGroup): Partial<Wallet> {
    const formValues = values.value;

    const walletUpdate: UpdateWalletRequest = {
      fund: formValues.fund
    };

    return walletUpdate as Partial<Wallet>;
  }


  public override getPrimaryKeys(values: FormGroup): string[] {
    const form = values.value;

    const name: string = form.name;
    const banck: string = form.banck;

    const pks: string[] = [name, banck];

    return pks;
  }

  public override afterInit(): void {
    this.loadObjectsWithPKsParameter(this.getUserEmail());
  }

  public getUserEmail(): string {
    const email = this.authService.user()?.email;
    if (!email) {
      throw new Error('email not found in session');
    }
    return email;
  }


}
