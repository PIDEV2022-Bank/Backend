import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {CommentService} from "../core/service/comment.service";
import Swal from "sweetalert2";
import {ActivatedRoute, Router} from "@angular/router";
import {data} from "autoprefixer";

@Component({
  selector: 'app-edit-comment',
  templateUrl: './edit-comment.component.html',
  styleUrls: ['./edit-comment.component.css']
})
export class EditCommentComponent implements OnInit {
  commentForm = new FormGroup({
    contained: new FormControl(''),
  });
  constructor(private data: CommentService,private route: ActivatedRoute,private router: Router) { }

  ngOnInit(): void {
    this.data.getCommentbyID(this.route.snapshot.params["id"]).subscribe((data:any)=>{
      this.commentForm.patchValue({"contained":data.contained})
    })
  }

  updateComment():void{
    console.log(this.commentForm.value)
    this.data.updateComment(this.commentForm.value,this.route.snapshot.params["id"]).subscribe((res)=>{
      Swal.fire({
        title: 'sucess!',
        text: 'Comment updated successfully',
        icon: 'success',
        confirmButtonText: 'ok'

      })
      this.router.navigateByUrl("/post/"+res.post.id+"/comment")
    })
    console.log("update")
  }

}
