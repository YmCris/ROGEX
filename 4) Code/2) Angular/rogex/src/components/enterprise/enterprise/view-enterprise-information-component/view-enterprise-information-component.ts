import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../../../../core/auth/auth.service';
import { EnterpriseService } from '../../../../services/admin/enterprises.service';
import { Enterprise } from '../../../../models/enterprises/Enterprise';

@Component({
  selector: 'app-view-enterprise-information-component',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './view-enterprise-information-component.html',
  styleUrl: './view-enterprise-information-component.css',
})
export class ViewEnterpriseInformationComponent implements OnInit {

  enterpriseForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private enterpriseService: EnterpriseService,
    private authService: AuthService,
  ) {}

  ngOnInit(): void {
    this.enterpriseForm = this.formBuilder.group({
      name: [{ value: '', disabled: true }],
      description: [{ value: '', disabled: true }],
      specificCommission: [{ value: 0, disabled: true }],
      hiddenAllComments: [{ value: false, disabled: true }],
    });

    this.loadEnterprise();
  }

  private loadEnterprise(): void {
    const enterpriseName = this.authService.user()?.enterpriseName;
    if (!enterpriseName) return;

    this.enterpriseService.getEnterprise(enterpriseName).subscribe({
      next: (enterprise: Enterprise) => {
        this.enterpriseForm.patchValue(enterprise);
      },
      error: () => {
        console.error('Error loading enterprise');
      }
    });
  }
}
