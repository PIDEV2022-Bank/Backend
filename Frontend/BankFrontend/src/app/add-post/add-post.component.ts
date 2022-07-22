import { Component, OnInit } from '@angular/core';
import {PostService} from "../core/service/post.service";

import { FormControl, FormGroup } from '@angular/forms';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css']
})
export class AddPostComponent implements OnInit {

  /* Post:post= {
    title:'',
    contained:'',
    published:false
  };*/
    postForm = new FormGroup({
    title: new FormControl(''),
    contained: new FormControl(''),
  });
submitted=false;
  title = 'addPost';

  closeResult: string = '';
  constructor(private modalService :NgbModal,private postService :PostService) {

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
  savePost():void {

    const data = this.postForm.value
      //contained: this.post.contained
    this.postService.addpost(data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
          Swal.fire({
            title: 'sucess!',
            text: 'Post added successfully',
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
      this.postForm.reset();
    }


}
