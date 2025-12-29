import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { SuccessfulActionComponent } from "../../../components/response-elements/successful-action-component/successful-action.component";
import { UnsuccessfulActionComponent } from "../../../components/response-elements/unsuccessful-action-component/unsuccessful-action-component";
import { GlobalConfigService } from '../../../services/admin/global.config.service';
import { GlobalConfig } from '../../../models/global-config/GlobalConfig';
import { UpdateSystemRequest } from '../../../models/global-config/UpdateSystemRequest';

@Component({
  selector: 'app-global-configurations-page',
  imports: [FormsModule, ReactiveFormsModule, ReactiveFormsModule, SuccessfulActionComponent, UnsuccessfulActionComponent],
  templateUrl: './global-configurations-page.html',
  styleUrl: './global-configurations-page.css',
})
export class GlobalConfigurationsPage implements OnInit {

  updateConfigForm!: FormGroup;
  globalConfig !: GlobalConfig;
  actionDone: boolean = false;
  operationDone: boolean = false;
  responseMessage: string = '';

  constructor(
    private formBuilder: FormBuilder,
    private globalConfigService: GlobalConfigService
  ) { }

  ngOnInit(): void {

    this.updateConfigForm = this.formBuilder.group({
      commission: [null, [Validators.min(0)]],
      description: [null, [Validators.maxLength(255)]],
    });


    this.loadActualConfig();

  }

  submit(): void {
    console.log('se hizo submit');
    if (this.updateConfigForm.valid) {
      this.updateConfig();
    }
  }

  reset(): void {
    this.updateConfigForm.reset();
    this.actionDone = false;
    this.operationDone = false;
    this.responseMessage = '';
  }

  updateConfig(): void {
    if (this.updateConfigForm.invalid) return;

    const payload: UpdateSystemRequest = {
      description: this.updateConfigForm.value.description,
      globalCommissionPercentage: this.updateConfigForm.value.commission
    };

    this.globalConfigService.updateConfig(payload).subscribe({
      next: (config) => {
        this.operationDone = true;
        this.actionDone = true;
        this.responseMessage = 'Configuration updated successfully.';

        this.updateConfigForm.patchValue({
          commission: config.globalCommissionPercentage,
          description: config.description
        });

        this.updateConfigForm.markAsPristine();
        this.updateConfigForm.markAsUntouched();
      },
      error: (err) => {
        this.operationDone = true;
        this.actionDone = false;
        this.responseMessage =
          err?.error?.message ?? 'Error updating configuration';
      }
    });
  }


  loadActualConfig(): void {
    this.globalConfigService.getConfig().subscribe({
      next: (config) => {
        this.globalConfig = config;

        this.updateConfigForm.patchValue({
          commission: config.globalCommissionPercentage,
          description: config.description
        });
      },
      error: () => {
        this.actionDone = true;
        this.operationDone = false;
        this.responseMessage = 'Error loading current configuration';
      }
    });
  }


}
