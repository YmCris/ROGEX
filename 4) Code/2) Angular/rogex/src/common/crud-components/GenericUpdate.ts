import { Directive, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { GenericUpdateObjectRequest } from '../dtos/GenericUpdateObjectRequest';
import { GenericService } from '../crud-services/GenericService';

/**
 * Generic class of the update components
 */
@Directive()
export abstract class GenericUpdate<T> implements OnInit {

    // FORMS -------------------------------------------------------------------
    protected updateObjectForm!: FormGroup;

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
     * Method responsible for update an object using the primary keys and do the
     * validations, send the pks and the updater to the generic service
     * 
     * @returns action state
     */
    updateObject(): void {

        if (this.updateObjectForm.invalid) return;

        this.resetState();

        const update: GenericUpdateObjectRequest = this.getUpdateValues(this.updateObjectForm);

        const primaryKeys: string[] = this.getPrimaryKeys(this.updateObjectForm);

        this.service.updateObject(primaryKeys, update).subscribe({
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

    // ABSTRACT METHODS --------------------------------------------------------
    /**
     * Method responsible for set all validations to the 4 forms groups, this is
     * important to all works like have to do
     */
    public abstract setFormValidations(): void;

    /**
     * Method responsible for create the entity / object with the form filled,
     * you should use the correct name of the updater (have to coincide with the
     * backend updater) and equalize with the form value, example:
     * 
     * const values = this.updateObjectForm.value;
     * 
     *    return Enterprise = {
     *      description: values.createDescription,
     *      specificCommission: values.createSpecificCommission,
     *      hiddenAllComments: !!values.createHiddenAllComments,
     *    };
     * 
     * @param values form group to extract the data
     */
    public abstract getUpdateValues(values: FormGroup): GenericUpdateObjectRequest<T>;

    /**
     * Method used to extract the primary keys to get some or delete someone
     * @param values form to extract this
     */
    public abstract getPrimaryKeys(values: FormGroup): string[];


}
