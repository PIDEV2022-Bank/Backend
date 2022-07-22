import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";


@Injectable({
  providedIn: 'root'
})
export class CommentService {
  getAllComment() {
    return this.http.get<any>("http://localhost:8082/Comment/all")
  }


  constructor(private http: HttpClient) {
  }

  getCommentbyID(id: number) {

    return this.http.get('http://localhost:8082/Comment/find' + id,)
  }

  deleteComments(id: number) {
    console.log(id)
    return this.http.post('http://localhost:8082/Comment/delete/' + id,{})
  }

  addComment(data: any) {
    console.warn(data)
    //const commentId = data.commentId
    const msg = data.msg
  //  const createAt = data.createAt
    return this.http.post( 'http://localhost:8082/Comment/add/',{msg})
  }
}
