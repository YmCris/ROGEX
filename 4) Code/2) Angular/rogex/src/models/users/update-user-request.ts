export interface UserToUpdateRequest {
    photo: File | null;
    birthDate: Date;
    phoneNumber: string;
    country: string;
    publicLibrary: boolean;
}