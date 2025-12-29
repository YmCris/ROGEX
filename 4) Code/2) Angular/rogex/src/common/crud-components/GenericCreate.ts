import { Directive, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { GenericService } from '../crud-services/GenericService';

/**
 * Generic class of the create components
 */
@Directive()
export abstract class GenericCreate<T> implements OnInit {

    // FORMS -------------------------------------------------------------------
    protected createObjectForm!: FormGroup;

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
     * Method responsible for create the object using the create object form, this
     * already makes the validations, and use the generic services, and should
     * show you the message.
     * 
     * @returns action state
     */
    createObject(): void {
        if (this.createObjectForm.invalid) return;

        this.resetState();

        const objectCreated = this.getCreateValues(this.createObjectForm);

        this.service.createObject(objectCreated).subscribe({
            next: () => {
                this.success('Creado correctamente', this.createObjectForm);
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
     * you should use the correct name of the model (have to coincide with the
     * backend model) and equalize with the form value, example:
     * 
     * const values = this.updateObjectForm.value;
     * 
     *    return Enterprise = {
     *      name: values.createName,
     *      description: values.createDescription,
     *      specificCommission: values.createSpecificCommission,
     *      hiddenAllComments: !!values.createHiddenAllComments,
     *    };
     * 
     * @param values form group to extract the data
     */
    public abstract getCreateValues(values: FormGroup): any;

}