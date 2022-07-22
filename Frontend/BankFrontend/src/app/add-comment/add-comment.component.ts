import { Component, OnInit } from '@angular/core';
import {Account} from "../core/models/account";
import {GridOptions} from "ag-grid-community";
import {ShowDetailsComponent} from "../user/show-details/show-details.component";
import {FormControl, FormGroup} from "@angular/forms";
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {CommentService} from "../core/service/comment.service";
import Swal from "sweetalert2";

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
  constructor(private modalService :NgbModal,private commentService :CommentService) { }



  ngOnInit(): void {
  }
  open(content:any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }
  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }
  saveComment():void {

    const data = this.commentForm.value
    //contained: this.post.contained
    this.commentService.addComment(data)
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
