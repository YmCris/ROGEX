export interface User {
    photo: File | null;
    nickname: string;
    password: string;
    birthDate: Date;
    email: string;
    phoneNumber: string;
    country: string;
    publicLibrary: boolean;
}