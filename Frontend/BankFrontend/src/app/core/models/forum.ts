import {post} from "./post";


export class forum {
     id:number
    title:string;
    dateCreated: Date
    body :string
    posts:[post]
}
