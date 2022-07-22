import {Component, Input, OnInit, Output} from '@angular/core';
import {PostService} from "../core/service/post.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormControl, FormGroup} from "@angular/forms";
import {outputPath} from "@angular-devkit/build-angular/src/test-utils";

@Component({
  selector: 'app-pdetail',
  templateUrl: './pdetail.component.html',
  styleUrls: ['./pdetail.component.css']
})
export class PdetailComponent implements OnInit {

  @Input() viewMode = false;
  @Output() post=false;
   currentPost = new FormGroup({
    title: new FormControl(''),
    contained: new FormControl(''),

  });
  message = '';
  constructor(private postService: PostService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.getPost(this.route.snapshot.params["id"]);
    }
  }

  setCurrentpost(post:any){
    this.currentPost=post
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

}
