import { GenericUpdateObjectRequest } from "../../common/dtos/GenericUpdateObjectRequest";

export interface UpdateWalletRequest extends GenericUpdateObjectRequest {

    fund: number;
}