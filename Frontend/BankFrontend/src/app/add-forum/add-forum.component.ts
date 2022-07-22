import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {PostService} from "../core/service/post.service";
import {ForumService} from "../core/service/forum.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-add-forum',
  templateUrl: './add-forum.component.html',
  styleUrls: ['./add-forum.component.css']
})
export class AddForumComponent implements OnInit {

  forumForm = new FormGroup({
    title: new FormControl(''),
    body: new FormControl(''),
    dateCreated:new FormControl(''),
  });
  submitted=false;
  title = 'addForum';

  closeResult: string = '';
  constructor(private modalService :NgbModal,private forumService :ForumService) {

  }

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
  saveForum():void {

    const data = this.forumForm.value
    //contained: this.post.contained
    this.forumService.addforum(data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
          Swal.fire({
            title: 'sucess!',
            text: 'Forum added successfully',
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
    this.forumForm.reset();
  }

}
