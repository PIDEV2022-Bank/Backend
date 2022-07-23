import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class CommentService {
  getAllCommentByPost(id:Number) {
    return this.http.get<any>("http://localhost:8082/Comment/find/post/"+id)
  }


  constructor(private http: HttpClient) {
  }

  getCommentbyID(id: number) {

    return this.http.get('http://localhost:8082/Comment/find/' + id,)
  }

  deleteComments(id: number) {
    console.log(id)
    return this.http.delete('http://localhost:8082/Comment/delete/' + id, {})
  }

  addComment(id:any,data: any) {
    console.warn(data)
    //const commentId = data.commentId
    const msg = data.msg
    //  const createAt = data.createAt
    return this.http.post('http://localhost:8082/Comment/add/'+id, data)
  }

  updateComment( data: any,id: any): Observable<any> {
    console.log(data, "service comment")
    return this.http.post('http://localhost:8082/Comment/update/'+id, data)
  }
}
