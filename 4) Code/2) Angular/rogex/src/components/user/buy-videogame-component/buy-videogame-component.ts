import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { Sale } from '../../../models/sale/sale';
import { GenericCreate } from '../../../common/crud-components/GenericCreate';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { SaleService } from '../../../services/users/sale.service';
import { AuthService } from '../../../core/auth/auth.service';
import { SuccessfulActionComponent } from '../../response-elements/successful-action-component/successful-action.component';
import { UnsuccessfulActionComponent } from '../../response-elements/unsuccessful-action-component/unsuccessful-action-component';
import { Videogame } from '../../../models/videogames/Videogame';

@Component({
  selector: 'app-buy-videogame-component',
  imports: [FormsModule, ReactiveFormsModule, SuccessfulActionComponent,
    UnsuccessfulActionComponent],
  templateUrl: './buy-videogame-component.html',
  styleUrl: './buy-videogame-component.css',
})
export class BuyVideogameComponent extends GenericCreate<Sale> implements OnChanges {

  @Input() videogame?: Videogame;

  constructor(
    formBuilder: FormBuilder,
    saleService: SaleService,
    private authService: AuthService
  ) {
    super(formBuilder, saleService);
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes["videogame"] && this.videogame) { }
  }

  // OVERRIDE METHODS ----------------------------------------------------------
  public override setFormValidations(): void {
    this.createObjectForm = this.formBuilder.group({
      saleDate: [null, [Validators.required]],
      walletName: [null, [Validators.required, Validators.maxLength(150)]],
      walletBanck: [null, [Validators.required]],
    });
  }

  public override getCreateValues(values: FormGroup) {

    const form = values.value;

    if (this.videogame?.title == null || this.videogame?.enterpriseName == null) {
      throw new Error("NULL");
    }

    const sale: Sale = {

      saleDate: form.saleDate,
      userEmail: this.getUserEmail(),
      videogameTitle: this.videogame?.title,
      enterpriseName: this.videogame?.enterpriseName,
      walletName: form.walletName,
      walletBanck: form.walletBanck
    };

    return sale;

  }

  protected getUserEmail(): string {
    const mail = this.authService.user()?.email;
    if (!mail) {
      throw new Error('Enterprise not found in session');
    }
    return mail;
  }


}
