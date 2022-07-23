import {Component, Input, OnInit, Output} from '@angular/core';
import {PostService} from "../core/service/post.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormControl, FormGroup} from "@angular/forms";
import {outputPath} from "@angular-devkit/build-angular/src/test-utils";
import {post} from "../core/models/post";

@Component({
  selector: 'app-pdetail',
  templateUrl: './pdetail.component.html',
  styleUrls: ['./pdetail.component.css']
})
export class PdetailComponent implements OnInit {

  @Input() viewMode = false;
  @Output()
   currentPost = {
  id:this.route.snapshot.params["id"],
     title:'',
     contained:'',
     published: false,
}

  message = '';
  constructor(private postService: PostService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.postService.getPostbyID(this.route.snapshot.params["id"]).subscribe({
        next:(data:any)=>{
          this.currentPost=data;

        },
        error:(e)=>{
          console.error(e)
        }
      })
    }
  }

  setCurrentpost(post:any){
    this.currentPost=post
    console.log(this.currentPost,"current post")
  }

  getPost(id: number): void {
    this.postService.getPostbyID(id)
      .subscribe({
        next: (data) => {
          this.setCurrentpost(data)
                    console.log(data);
        },
        error: (e) => console.error(e)
      });
  }
  updatePublished(status: boolean): void {
    console.log(this.currentPost)
    const data = {
      title: this.currentPost.title,
      description: this.currentPost.contained,
      published: status
    };
    this.message = '';
    this.postService.updatePost(this.route.snapshot.params["id"],data).subscribe({
        next: (res) => {
          console.log(res);
          this.currentPost.published = status;
          this.message = res.message ? res.message : 'The post was updated successfully!';
        },
        error: (e) => console.error(e)
      });
  }
  updatePost(id:number): void {
    console.log(this.currentPost,"update")
    this.message = '';
    this.postService.updatePost(this.route.snapshot.params["id"], this.currentPost)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.message = res.message ? res.message : 'This Post was updated successfully!';
        },
        error: (e) => console.error(e)
      });
  }
  deletePost(): void {
    this.postService.deletePost(this.route.snapshot.params["id"])
      .subscribe({
        next: (res) => {
          console.log(res);
          this.router.navigate(['/post']);
        },
        error: (e) => console.error(e)
      });
  }
}
