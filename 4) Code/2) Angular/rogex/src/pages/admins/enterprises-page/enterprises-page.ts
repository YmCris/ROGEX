import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Enterprise } from '../../../models/enterprises/Enterprise';
import { EnterpriseService } from '../../../services/admin/enterprises.service';
import { SuccessfulActionComponent } from "../../../components/response-elements/successful-action-component/successful-action.component";
import { UnsuccessfulActionComponent } from "../../../components/response-elements/unsuccessful-action-component/unsuccessful-action-component";
import { UpdateEnterpriseRequest } from '../../../models/enterprises/UpdateEnterpriseRequest';
import { CreateEnterpriseUserComponent } from '../../../components/admins/create-enterprise-user-component/create-enterprise-user-component';

@Component({
  selector: 'app-enterprises-page',
  imports: [FormsModule, ReactiveFormsModule, ReactiveFormsModule, SuccessfulActionComponent, UnsuccessfulActionComponent, CreateEnterpriseUserComponent],
  templateUrl: './enterprises-page.html',
  styleUrl: './enterprises-page.css',
})
export class EnterprisesPage implements OnInit {

  createEnterpriseForm!: FormGroup;
  updateEnterpriseForm!: FormGroup;
  deleteEnterpriseForm!: FormGroup;

  actionDone = false;
  operationDone = false;
  responseMessage = '';
  enterprises: Enterprise[] = [];
  loadingEnterprises = false;

  constructor(
    private formBuilder: FormBuilder,
    private enterpriseService: EnterpriseService
  ) { }

  ngOnInit(): void {
    this.createEnterpriseForm = this.formBuilder.group({
      createName: [null, [Validators.required, Validators.maxLength(50)]],
      createDescription: [null, [Validators.required, Validators.maxLength(500)]],
      createSpecificCommission: [null, [Validators.min(0), Validators.max(50)]],
      createHiddenAllComments: [false],
    });

    this.updateEnterpriseForm = this.formBuilder.group({
      updateName: [null, [Validators.required, Validators.maxLength(50)]],
      updateDescription: [null, [Validators.maxLength(500)]],
      updateSpecificCommission: [null, [Validators.min(0), Validators.max(50)]],
      updateHiddenAllComments: [false],
    });

    this.deleteEnterpriseForm = this.formBuilder.group({
      deleteName: [null, [Validators.required, Validators.maxLength(50)]],
    });

    this.loadEnterprises();
  }

  createEnterprise(): void {
    if (this.createEnterpriseForm.invalid) return;

    const values = this.createEnterpriseForm.value;

    const enterprise: Enterprise = {
      name: values.createName,
      description: values.createDescription,
      specificCommission: values.createSpecificCommission,
      hiddenAllComments: !!values.createHiddenAllComments,
    };

    this.enterpriseService.createEnterprise(enterprise).subscribe({
      next: () => {
        this.success('Empresa creada correctamente', this.createEnterpriseForm);
        this.loadEnterprises();
      },
      error: err => this.fail(err)
    });
  }


  updateEnterprise(): void {
    if (this.updateEnterpriseForm.invalid) return;

    const values = this.updateEnterpriseForm.value;

    const name = values.updateName;

    const update: UpdateEnterpriseRequest = {
      description: values.updateDescription,
      specificCommission: values.updateSpecificCommission,
      hiddenAllComments: !!values.updateHiddenAllComments,
    };

    this.enterpriseService.updateEnterprise(name, update).subscribe({
      next: () => {
        this.success('Empresa actualizada', this.updateEnterpriseForm),
          this.loadEnterprises();
      },
      error: err => this.fail(err)
    });

  }

  deleteEnterprise(): void {
    if (this.deleteEnterpriseForm.invalid) return;

    const name = this.deleteEnterpriseForm.value.deleteName;

    this.enterpriseService.deleteEnterprise(name).subscribe({
      next: () => {
        this.success('Empresa eliminada', this.deleteEnterpriseForm),
          this.loadEnterprises();
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

  loadEnterprises(): void {
    this.loadingEnterprises = true;

    this.enterpriseService.getAllEnterprises().subscribe({
      next: (enterprises) => {
        this.enterprises = enterprises;
        this.loadingEnterprises = false;
      },
      error: () => {
        this.loadingEnterprises = false;
        this.actionDone = true;
        this.operationDone = false;
        this.responseMessage = 'Error cargando Empresas';
      }
    });
  }





}
