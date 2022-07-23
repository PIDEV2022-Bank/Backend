import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { forum } from '../models/forum';
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ForumService {


  constructor(private http: HttpClient) {  }

  getAllForum() {
    return  this.http.get<any>("http://localhost:8082/Forum/all")
  }


  getForumbyID(id:number)
  {

    return this.http.get('http://localhost:8082/Forum/find/'+id,)
  }

  deleteForum(id:number)
  {
    console.log(id)
    return this.http.delete('http://localhost:8082/Forum/delete/'+id)
  }

  findCommentByPost(id : number)
{
  return
}

  addforum(data:any)
  {
    console.warn(data)
    const title=data.title
    const msg=data.msg

    return this.http.post( 'http://localhost:8082/Forum/add/',data)
  }

  findPostByForum( id:number,idPost:number)
  {

    return this.http.get('http://localhost:8082/Forum/findPost'+id+idPost)
  }
  updateForum(id:any ,data: any): Observable<any> {
    console.log(data,"service forum")
    return this.http.post('http://localhost:8082/Forum/update/'+id,data)
  }

}
