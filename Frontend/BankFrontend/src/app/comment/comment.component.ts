import { Component, OnInit } from '@angular/core';
import {comment} from "../core/models/comment";
import {CommentService} from "../core/service/comment.service";
import {ActivatedRoute, Router} from "@angular/router";
import {StorageService} from "../_services/storage.service";

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {
  comments: comment[]
  activeuser :any

  constructor(private data: CommentService,private route: ActivatedRoute,private router: Router,private storage:StorageService) {
    this.activeuser=this.storage.getUser()

  }

  ngOnInit(): void {
    this.data.getAllCommentByPost(this.route.snapshot.params["id"]).subscribe(
      (data: comment[]) => {
        this.comments = data;
        console.log(data[0].contained)
      });
  }

deleteComment(id:any):void{
    this.data.deleteComments(id).subscribe(()=>{
      this.data.getAllCommentByPost(this.route.snapshot.params["id"]).subscribe(
        (data: comment[]) => {
          this.comments = data;
          console.log(data[0].contained)
        });
    })
  }

  getpostid():any{
    return this.route.snapshot.params["id"]
  }

}
