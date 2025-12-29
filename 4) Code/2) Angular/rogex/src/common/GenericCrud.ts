import { Directive, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

// Generics
import { GenericUpdateObjectRequest } from './GenericUpdateObjectRequest';
import { GenericService } from './GenericService';

/**
 * Generic class of the CRUD pages
 */
@Directive()
export abstract class GenericCrud<T> implements OnInit {

    // FORMS -------------------------------------------------------------------
    protected createObjectForm!: FormGroup;
    protected updateObjectForm!: FormGroup;
    protected deleteObjectForm!: FormGroup;
    protected getObjectsForm!: FormGroup;

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
        this.setFormsValidations();
        this.afterInit();
    }

    /**
     * Override this and leave this empty if the CRUD doesn't need load all objects
     * or use loadObjectsWithPKs() if the CRUD need it
     */
    protected afterInit(): void {
        this.loadObjects();
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
                this.loadObjects();
            },
            error: err => this.fail(err)
        });
    }

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
                this.success('Objeto actualizado', this.updateObjectForm),
                    this.loadObjects();
            },
            error: err => this.fail(err)
        });

    }

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
                this.success('Objeto eliminado', this.deleteObjectForm),
                    this.loadObjects();
            },
            error: err => this.fail(err)
        });
    }

    /**
     * Method responsible for load Objects without restrictions
     */
    loadObjects(): void {

        this.resetState();

        this.state.loading = true;

        this.service.getAllObjects().subscribe({
            next: (objects) => {
                this.state.data = objects;
                this.state.loading = false;
            },
            error: () => {
                this.state.loading = false;
                this.state.error = true;
                this.state.success = false;
                this.state.message = 'Error cargando objetos';
            }
        });
    }

    /**
     * Method responsible for load all objects with some restriction
     * @returns 
     */
    loadObjectsWithPKs(): void {
        if (this.getObjectsForm.invalid) return;

        this.resetState();

        this.state.loading = true;

        const primaryKeys = this.getPrimaryKeys(this.getObjectsForm);

        this.service.getAllByKeys(primaryKeys).subscribe({
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
    public abstract setFormsValidations(): void;

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
