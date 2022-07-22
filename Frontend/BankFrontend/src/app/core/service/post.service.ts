import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";


@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) { }
  getAllPost()
  {
    return this.http.get<any>("http://localhost:8082/Post/all")

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
    return  this.http.post(`http://localhost:8082/Post/add/`,{title,msg})
  }
  findcommentByPost( id:number,idPost:number)
  {

    return this.http.get('http://localhost:8082/Post/findComment'+id+idPost)
  }
}
