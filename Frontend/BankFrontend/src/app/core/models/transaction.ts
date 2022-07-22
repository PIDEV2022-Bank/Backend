import { User } from './user';
import { ArrayType } from '@angular/compiler';
import { Timestamp } from 'rxjs/internal/operators/timestamp';

export interface Transaction {
    transactionId : number;
    amount: number;
    description: string;
    transactionDate: Date;

}