import { Component, OnInit } from '@angular/core';
import {Account} from "../core/models/account";
import {GridOptions} from "ag-grid-community";
import {ShowDetailsComponent} from "../user/show-details/show-details.component";
import {FormControl, FormGroup} from "@angular/forms";
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {CommentService} from "../core/service/comment.service";
import Swal from "sweetalert2";
import {ActivatedRoute, Router} from "@angular/router";
import {StorageService} from "../_services/storage.service";

@Component({
  selector: 'app-add-comment',
  templateUrl: './add-comment.component.html',
  styleUrls: ['./add-comment.component.css']
})
export class AddCommentComponent implements OnInit {

  commentForm = new FormGroup({
    contained: new FormControl(''),

  });
  submitted=false;
  title = 'addComment';
  closeResult: string = '';
  constructor(private data: CommentService,private route: ActivatedRoute,private router: Router) {}



  ngOnInit(): void {
  }


  saveComment():void {

    const data = this.commentForm.value
    console.log(data)
    //contained: this.post.contained
    this.data.addComment(this.route.snapshot.params["id"],data)

       .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
           Swal.fire({
            title: 'sucess!',
            text: 'Comment added successfully',
            icon: 'success',
            confirmButtonText: 'ok'

          })
          this.router.navigateByUrl("/post/"+this.route.snapshot.params["id"]+"/comment")
        },
        error: (e) => console.error(e)
      });
    console.log(data)
  }
  newAddPost(): void {
    this.submitted = false;
    this.commentForm.reset();
  }

}
