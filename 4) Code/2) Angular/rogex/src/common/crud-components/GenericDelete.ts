import { Directive, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { GenericService } from '../crud-services/GenericService';

/**
 * Generic class of the Delete components
 */
@Directive()
export abstract class GenericDelete<T> implements OnInit {

    // FORMS -------------------------------------------------------------------
    protected deleteObjectForm!: FormGroup;

    // UI STATE ----------------------------------------------------------------
    protected state: Omit<CrudState<T>, 'data'> = {
        loading: false,
        success: false,
        error: false,
        message: ''
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
    }
    
    // SPECIFIC METHODS --------------------------------------------------------
    /**
    * Method responsible for delete and object using the pks and do the validations
    * 
    * @returns Action state
    */
    deleteObject(): void {
        if (this.deleteObjectForm.invalid) return;

        this.resetState();

        const primaryKeys = this.getPrimaryKeys(this.deleteObjectForm);

        this.service.deleteWithPrimaryKeys(primaryKeys).subscribe({
            next: () => {
                this.success('Objeto eliminado', this.deleteObjectForm)
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

    // ABSTRACT METHODS --------------------------------------------------------
    /**
     * Method responsible for set all validations to the 4 forms groups, this is
     * important to all works like have to do
     */
    public abstract setFormValidations(): void;

    /**
     * Method used to extract the primary keys to get some or delete someone
     * @param values form to extract this
     */
    public abstract getPrimaryKeys(values: FormGroup): string[];

}