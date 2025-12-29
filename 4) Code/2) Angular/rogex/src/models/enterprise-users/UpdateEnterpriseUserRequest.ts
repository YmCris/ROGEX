import { GenericUpdateObjectRequest } from "../../common/dtos/GenericUpdateObjectRequest";
import { EnterpriseUsers } from "./EnterpriseUser";

export interface UpdateEnterpriseUserRequest extends GenericUpdateObjectRequest<EnterpriseUsers> {
    name: string;
    password: string;
    birthDate: Date;
}