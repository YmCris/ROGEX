export interface UserLog {
    email: string;
    name: string;
    role: 'USER' | 'ENTERPRISE' | 'ADMIN';
}