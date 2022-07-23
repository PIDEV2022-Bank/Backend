import { Component, OnInit } from '@angular/core';
import {PostService} from "../core/service/post.service";
import {ActivatedRoute, Router} from "@angular/router";
import {StorageService} from "../_services/storage.service";
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
      idForum:new FormControl(this.route.snapshot.params["id"]),
      user:new FormControl(this.storage.getUser())

  });
submitted=false;
  title = 'addPost';


  closeResult: string = '';
  constructor(private postService :PostService,private route: ActivatedRoute,private router: Router,private storage:StorageService) {

  }

  ngOnInit(): void {
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
          this.router.navigateByUrl("/forum/"+this.route.snapshot.params["id"]+"/post")


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
