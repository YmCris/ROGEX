import { Directive, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { GenericService } from '../crud-services/GenericService';

/**
 * Generic class of the get components
 */
@Directive()
export abstract class GenericGetWithForm<T> implements OnInit {

    // FORMS -------------------------------------------------------------------
    protected getObjectsForm!: FormGroup;

    // UI STATE ----------------------------------------------------------------
    protected state: CrudState<T> = {
        loading: false,
        success: false,
        error: false,
        message: '',
        data: []
    };

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public constructor(
        protected formBuilder: FormBuilder,
        protected service: GenericService<T>,
    ) { }

    // OVERRIDE METHODS --------------------------------------------------------
    /**
     * Method responsible for starts with the specific validations of the page
     * and load all objects (if this is necessary)
     */
    ngOnInit(): void {
        this.setFormValidations();
        this.loadObjectsWithForm();
    }

    // SPECIFIC METHODS --------------------------------------------------------
    /**
     * Method responsible for load all objects with some restriction
     * @returns 
     */
    loadObjectsWithForm(): void {
        if (this.getObjectsForm.invalid) return;

        this.resetState();

        this.state.loading = true;

        const parameters = this.getFormParameters(this.getObjectsForm);

        this.service.getAllByKeys(parameters).subscribe({
            next: (objects) => {
                this.state.data = objects;
                this.state.loading = false;
            },
            error: () => {
                this.state.loading = false;
                this.state.error = true;
                this.state.message = 'Error cargando objetos';
            }
        });
    }

    // AUXILIAR METHODS --------------------------------------------------------
    /**
     * Method responsible for reset the UI state
     */
    private resetState(): void {
        this.state.success = false;
        this.state.error = false;
        this.state.message = '';
    }

    // ABSTRACT METHODS --------------------------------------------------------
    /**
     * Method responsible for set all validations to the group if the ps need this
     * , this is important to all works like have to do
     */
    public abstract setFormValidations(): void;

    /**
     * Method used to extract the primary keys to get some or delete someone
     * 
     * @param values form to extract this
     */
    public abstract getFormParameters(values: FormGroup): string[];

}