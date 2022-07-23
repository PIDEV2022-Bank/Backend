import {comment} from "./comment";

export class post {
  id: number
  title: string
  dateCreated: Date
  contained: string;
  comments: [comment];
  idUser: number;
  idForum:number;
  user:any
}
