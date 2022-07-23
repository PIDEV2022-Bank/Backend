import { User } from './user';
import { ArrayType } from '@angular/compiler';

export interface Account {
    id: number;
    name: string;
    accountNumber : string;
    accountType: string;
    balance : number;
    autorisation:number;
    client: User;
}