import { User } from './user';
import { ArrayType } from '@angular/compiler';

export class Request {
  idRequest: number ;
  name:string;
  date:Date;
  state:string;
  message:string;
  type:string;
  user:User;

}
