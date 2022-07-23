import { Component, OnInit } from '@angular/core';
import {ForumService} from "../core/service/forum.service";
import Swal from "sweetalert2";
import {FormControl, FormGroup} from "@angular/forms";
import {forum} from "../core/models/forum";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-edit-forum',
  templateUrl: './edit-forum.component.html',
  styleUrls: ['./edit-forum.component.css']
})
export class EditForumComponent implements OnInit {

  forumForm = new FormGroup({
    title: new FormControl(''),
    body: new FormControl(''),
    dateCreated:new FormControl(''),
  });
  Forumtoupdate:forum;
  submitted=false;
  title = 'addForum';

  closeResult: string = '';
  constructor(private forumService :ForumService,private route: ActivatedRoute,private router: Router) {

  }

  ngOnInit(): void {
    this.forumService.getForumbyID(this.route.snapshot.params["id"]).subscribe(
      (data: any) => {this.setActiveForum(data)
      });

  }


setActiveForum(data:any):void{
    this.Forumtoupdate=data
  this.forumForm.patchValue({title:data.title});
  this.forumForm.patchValue({body:data.body});
}

  updateForum():void {
  console.log("called")
    const data = this.forumForm.value
    //contained: this.post.contained
    this.forumService.updateForum(this.route.snapshot.params["id"],data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
          Swal.fire({
            title: 'sucess!',
            text: 'Forum updated successfully',
            icon: 'success',
            confirmButtonText: 'ok'

          })
          this.router.navigateByUrl("forum")

        },
        error: (e) => console.error(e)
      });
    console.log(data)
  }



}
