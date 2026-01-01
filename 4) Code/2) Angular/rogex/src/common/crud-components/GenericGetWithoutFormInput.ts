import { Directive, OnInit } from "@angular/core";
import { GenericService } from "../crud-services/GenericService";

@Directive()
export abstract class GenericGetWithoutFormInput<T> {

    // UI STATE ----------------------------------------------------------------
    protected state: CrudState<T> = {
        loading: false,
        success: false,
        error: false,
        message: '',
        data: []
    };

    // CONSTRUCTOR METHOD ------------------------------------------------------
    constructor(
        protected service: GenericService<T>
    ) { }

    protected abstract defineLoad(): void;

    protected loadByPrimaryKey(primaryKey: string): void {
        this.resetState();
        this.state.loading = true;

        this.service.getAllByKey(primaryKey).subscribe({
            next: data => {
                this.state.data = data;
                this.state.loading = false;
            },
            error: () => {
                this.state.loading = false;
                this.state.error = true;
                this.state.message = 'Error cargando objetos';
            }
        });
    }

    protected loadByPrimaryKeys(primaryKeys: string[]): void {
        this.resetState();
        this.state.loading = true;

        this.service.getAllByKeys(primaryKeys).subscribe({
            next: data => {
                this.state.data = data;
                this.state.loading = false;
            },
            error: () => {
                this.state.loading = false;
                this.state.error = true;
                this.state.message = 'Error cargando objetos';
            }
        });
    }

    private resetState(): void {
        this.state.success = false;
        this.state.error = false;
        this.state.message = '';
    }

}
