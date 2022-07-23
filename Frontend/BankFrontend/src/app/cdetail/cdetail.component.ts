import {Component, Input, OnInit, Output} from '@angular/core';
import {PostService} from "../core/service/post.service";
import {ActivatedRoute, Router} from "@angular/router";
import {comment} from "../core/models/comment";
import {CommentService} from "../core/service/comment.service";

@Component({
  selector: 'app-cdetail',
  templateUrl: './cdetail.component.html',
  styleUrls: ['./cdetail.component.css']
})
export class CdetailComponent implements OnInit {
  @Input() viewMode = false;
  @Output()
  currentComment = {
    id:this.route.snapshot.params["id"],
    title:'',
    contained:'',
    published: false,
  }

  message = '';
  constructor(private commentService: CommentService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.commentService.getCommentbyID(this.route.snapshot.params["id"]).subscribe({
        next:(data:any)=>{
          this.currentComment=data;

        },
        error:(e)=>{
          console.error(e)
        }
      })
    }
  }
  setCurrentComment(comment:any){
    this.currentComment=comment
    console.log(this.currentComment,"current post")
  }
  getComment(id: number): void {
    this.commentService.getCommentbyID(id)
      .subscribe({
        next: (data) => {
          this.setCurrentComment(data)
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }
  updatePublished(status: boolean): void {
    console.log(this.currentComment)
    const data = {
      title: this.currentComment.title,
      description: this.currentComment.contained,
      published: status
    };
    this.message = '';
    this.commentService.updateComment(this.route.snapshot.params["id"],data).subscribe({
      next: (res) => {
        console.log(res);
        this.currentComment.published = status;
        this.message = res.message ? res.message : 'The Comment was updated successfully!';
      },
      error: (e) => console.error(e)
    });
  }
  updateComment(id:number): void {
    console.log(this.currentComment,"update")
    this.message = '';
    this.commentService.updateComment(this.route.snapshot.params["id"], this.currentComment)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.message = res.message ? res.message : 'This Post was updated successfully!';
        },
        error: (e) => console.error(e)
      });
  }
  deletePost(): void {
    this.commentService.deleteComments(this.route.snapshot.params["id"])
      .subscribe({
        next: (res) => {
          console.log(res);
          this.router.navigate(['/comment']);
        },
        error: (e) => console.error(e)
      });
  }

}
