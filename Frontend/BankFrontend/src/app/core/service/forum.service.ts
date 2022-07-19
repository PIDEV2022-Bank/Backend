import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { forum } from '../models/forum';

@Injectable({
  providedIn: 'root'
})
export class ForumService {
  

  constructor(private http: HttpClient) {  }

  getAllForum() {
    return  this.http.get<any>("http://localhost:8082/Forum/all")
  }

  getAllComment()
  {
    return this.http.get("http://localhost:8082/Comment/all")
  }
  getAllPost()
  {
    return this.http.get("hhttp://localhost:8082/Post/all")
  }
  getForumbyID(id:number)
  {
    
    return this.http.get('http://localhost:8082/Forum/find/'+id,)
  }
  getCommentbyID( id:number)
  {
    
    return this.http.get('http://localhost:8082/Forum/find/'+id)
  }
  getPostbyID( id:number)
  {
    
    return this.http.get('http://localhost:8082/Post/find/'+id)
  }
  deleteForum(id:number)
  {
    console.log(id)
    return this.http.delete('http://localhost:8082/Forum/delete/'+id)
  }
  deleteComments(id:number)
  {
    console.log(id)
    return this.http.post('http://localhost:8082/Comment/delete/'+id,{})
  }
  deletePost(id:number)
  {
    console.log(id)
    return this.http.delete('http://localhost:8082/Post/delete/'+id)
  }

  addComment(data:any)
  {
    console.warn(data)
    const commentId=data.commentId
    const msg=data.msg
    const createAt=data.createAt
    return  this.http.post(`http://localhost:8082//new?msg=${msg}&idcomment=${commentId}`,{})
  }

  addpost(data:any)
  {
    console.warn(data)
    const title=data.title
    const msg=data.msg
    return  this.http.post(`http://localhost:8082/post/add/new?idClient=1&msg=${msg}&title=${title}`,{})
  }
  addforum(data:any)
  {
    console.warn(data)
    const title=data.title
    const msg=data.msg
    return  this.http.post(`http://localhost:8082/forum/add/new?idClient=1&msg=${msg}&title=${title}`,{})
  }

}
