import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { GenericCreate } from '../../../../../common/crud-components/GenericCreate';
import { VideogameMultimedia } from '../../../../../models/videogames-multimedia/VideogameMultimedia';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { VideogameMultimediaService } from '../../../../../services/enterprise/videogame-multimedia.service';
import { UnsuccessfulActionComponent } from '../../../../response-elements/unsuccessful-action-component/unsuccessful-action-component';
import { SuccessfulActionComponent } from '../../../../response-elements/successful-action-component/successful-action.component';
import { Videogame } from '../../../../../models/videogames/Videogame';

@Component({
  selector: 'app-add-videogame-multimedia-component',
  imports: [FormsModule, ReactiveFormsModule,
    SuccessfulActionComponent, UnsuccessfulActionComponent],
  templateUrl: './add-videogame-multimedia-component.html',
  styleUrl: './add-videogame-multimedia-component.css',
})
export class AddVideogameMultimediaComponent extends GenericCreate<VideogameMultimedia> implements OnChanges {

  @Input() videogame?: Videogame;
  selectedFile!: File;

  public constructor(
    formBuilder: FormBuilder,
    protected videogameService: VideogameMultimediaService,
  ) {
    super(formBuilder, videogameService);
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes["videogame"] && this.videogame) { }
  }

  public override setFormValidations(): void {
    this.createObjectForm = this.formBuilder.group({
      photo: [null, [Validators.required]]
    });
  }

  public override getCreateValues(values: FormGroup) { }
  override createObject(): void {
    if (this.createObjectForm.invalid || !this.selectedFile) return;

    const formData = new FormData();

    const data = {
      videogameTitle: this.videogame!.title,
      enterpriseName: this.videogame!.enterpriseName
    };

    formData.append(
      'data',
      new Blob([JSON.stringify(data)], { type: 'application/json' })
    );

    formData.append('fileObject', this.selectedFile);

    this.videogameService.createMultipart(formData).subscribe({
      next: () => this['success']('Creado correctamente', this.createObjectForm),
      error: err => this['fail'](err)
    });
  }


  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;

    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
      this.createObjectForm.patchValue({ photo: this.selectedFile });
    }
  }

}
