import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {PostService} from "../core/service/post.service";
import {ForumService} from "../core/service/forum.service";
import Swal from "sweetalert2";
import {ActivatedRoute, Router} from "@angular/router";
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
  constructor(private forumService :ForumService,private route: ActivatedRoute,private router: Router
  ) {

  }

  ngOnInit(): void {
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
          this.router.navigateByUrl("forum")
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
