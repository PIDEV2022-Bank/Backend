import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import { FormControl, FormGroup } from '@angular/forms';
import {PostService} from "../core/service/post.service";
import {StorageService} from "../_services/storage.service";

import Swal from 'sweetalert2'
import {post} from "../core/models/post";

@Component({
  selector: 'app-update-post',
  templateUrl: './update-post.component.html',
  styleUrls: ['./update-post.component.css']
})
export class UpdatePostComponent implements OnInit {
  postForm = new FormGroup({
    title: new FormControl(''),
    contained: new FormControl(''),
    forum:new FormControl(''),
    user:new FormControl(this.storage.getUser())


  });
  constructor(private postService :PostService,private route: ActivatedRoute,private router: Router,private storage:StorageService) {

  }

  ngOnInit(): void {
    this.postService.getPostbyID(this.route.snapshot.params["id"]).subscribe((data:any)=>{
      this.postForm.patchValue({"title":data.title})
      this.postForm.patchValue({"contained":data.contained})
      this.postForm.patchValue({"forum":data.forum})

    })
  }

  savePost():void {

    const data = this.postForm.value
    //contained: this.post.contained
    this.postService.updatePost(this.route.snapshot.params["id"],data)
      .subscribe({
        next: (res) => {
          console.log(res);
          Swal.fire({
            title: 'sucess!',
            text: 'Post added successfully',
            icon: 'success',
            confirmButtonText: 'ok'

          })
        this.router.navigateByUrl("/forum/"+res.forum.id+"/post")
        },
        error: (e) => console.error(e)

      });
    console.log(data)
  }

}
