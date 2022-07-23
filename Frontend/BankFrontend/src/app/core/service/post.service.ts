import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) { }
  getAllPostByForum(id:Number)
  {
    return this.http.get<any>("http://localhost:8082/Post/findPostForum/"+id)

  }
  getPostbyID( id:number)
  {

    return this.http.get('http://localhost:8082/Post/find/'+id)
  }
  deletePost(id:number)
  {
    console.log(id)
    return this.http.delete('http://localhost:8082/Post/delete/'+id)
  }
  addpost(data:any)
  {
    console.warn(data)
    const title=data.title
    const msg=data.msg
    const id =data.id
    console.log(data,"service post")
    return  this.http.post(`http://localhost:8082/Post/add/`,data)
  }
  findcommentByPost( id:number,idPost:number)
  {

    return this.http.get('http://localhost:8082/Post/findComment'+id+idPost)
  }

   updatePost(id:any ,data: any): Observable<any> {
console.log(data,"service post")
     return this.http.post('http://localhost:8082/Post/update/'+id,data)
   }
}
