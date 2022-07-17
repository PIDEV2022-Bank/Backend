import { User } from './user';
import { ArrayType } from '@angular/compiler';

export interface Transfer {
    amount: string;
    accountSource : string;
    accountDestination: string;
}