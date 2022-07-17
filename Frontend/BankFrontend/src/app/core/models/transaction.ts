import { User } from './user';
import { ArrayType } from '@angular/compiler';

export interface Transaction {
    transactionId : number;
    amount: number;
    description: string;
    transactionDate: string;

}