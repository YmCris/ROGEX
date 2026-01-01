import { Component } from '@angular/core';
import { GenericUpdate } from '../../../../common/crud-components/GenericUpdate';
import { Enterprise } from '../../../../models/enterprises/Enterprise';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { SuccessfulActionComponent } from '../../../response-elements/successful-action-component/successful-action.component';
import { UnsuccessfulActionComponent } from '../../../response-elements/unsuccessful-action-component/unsuccessful-action-component';
import { UpdateEnterpriseRequest } from '../../../../models/enterprises/UpdateEnterpriseRequest';
import { EnterpriseService } from '../../../../services/admin/enterprises.service';
import { AuthService } from '../../../../core/auth/auth.service';

@Component({
  selector: 'app-udpate-enterprise-component',
  imports: [FormsModule, ReactiveFormsModule,
    SuccessfulActionComponent, UnsuccessfulActionComponent],
  templateUrl: './udpate-enterprise-component.html',
  styleUrl: './udpate-enterprise-component.css',
})
export class UdpateEnterpriseComponent {

  // FORMS -------------------------------------------------------------------
  protected updateObjectForm!: FormGroup;

  // UI STATE ----------------------------------------------------------------
  protected state: Omit<CrudState<Enterprise>, 'data'> = {
    loading: false,
    success: false,
    error: false,
    message: ''
  };

  public constructor(
    private formBuilder: FormBuilder,
    private enterpriseService: EnterpriseService,
    private authService: AuthService,
  ) {
  }

  // OVERRIDE METHODS --------------------------------------------------------
  /**
   * Method responsible for starts with the specific validations of the page
   * and load all objects (if this is necessary)
   */
  ngOnInit(): void {
    this.setFormValidations();
  }


  // SPECIFIC METHODS --------------------------------------------------------
  /**
   * Method responsible for update an object using the primary keys and do the
   * validations, send the pks and the updater to the generic service
   * 
   * @returns action state
   */
  updateObject(): void {

    if (this.updateObjectForm.invalid) return;

    this.resetState();

    const update: UpdateEnterpriseRequest = this.getUpdateValues(this.updateObjectForm);


    this.enterpriseService.updateEnterprise(this.getEnterpriseName(), update).subscribe({
      next: () => {
        this.success('Objeto actualizado', this.updateObjectForm)
      },
      error: err => this.fail(err)
    });

  }

  // AUXILIAR METHODS --------------------------------------------------------
  /**
   * Method responsible for do the commons actions in some success action
   * 
   * @param message message to show in the response components
   * @param form formGroup to reset this.
   */
  private success(message: string, form: FormGroup): void {
    this.state.success = true;
    this.state.error = false;
    this.state.message = message;
    form.reset();
  }

  /**
   * Method responsible for do the commons actions in some unsuccess action
   * 
   * @param err the error throw
   */
  private fail(err: any): void {
    this.state.error = true;
    this.state.success = false;
    this.state.message = err?.error?.message ?? 'Operación fallida';
  }

  /**
   * Method responsible for reset the UI state
   */
  private resetState(): void {
    this.state.loading = false;
    this.state.success = false;
    this.state.error = false;
    this.state.message = '';
  }

  public setFormValidations(): void {
    this.updateObjectForm = this.formBuilder.group({
      description: [null, [Validators.maxLength(500)]],
      hiddenAllComments: [null],
    });
  }

  public getUpdateValues(values: FormGroup): UpdateEnterpriseRequest {
    const formValues = values.value;

    const enterpriseUpdate: UpdateEnterpriseRequest = {
      description: formValues.description,
      specificCommission: null,
      hiddenAllComments: formValues.hiddenAllComments,
    };

    return enterpriseUpdate;
  }

  protected getEnterpriseName(): string {
    const enterprise = this.authService.user()?.enterpriseName;
    if (!enterprise) {
      throw new Error('Enterprise not found in session');
    }
    return enterprise;
  }



}
