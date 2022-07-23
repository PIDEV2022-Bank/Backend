import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import {PostService} from '../core/service/post.service';
import {post} from "../core/models/post";
import {forum} from "../core/models/forum";
import {GridOptions} from "ag-grid-community";
import {ShowDetailsComponent} from "../user/show-details/show-details.component";
import {AccountService} from "../core/service/account.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {Subscription} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {StorageService} from "../_services/storage.service";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  posts: post[]
    p: post
  activeuser:any
  constructor(private data: PostService,private route: ActivatedRoute,private router: Router,private storage:StorageService) {
this.activeuser=storage.getUser()
  }


  ngOnInit(): void {

    this.data.getAllPostByForum(this.route.snapshot.params["id"]).subscribe(
      (data: post[]) => {
        this.setposts(data);
      });
  }
getForumid():any {
    return(this.route.snapshot.params["id"])
}
deletePost(id:any):void{
    this.data.deletePost(id).subscribe(()=>{
      this.data.getAllPostByForum(this.route.snapshot.params["id"]).subscribe(
        (data: post[]) => {
          this.setposts(data);
        });
    })
}
  setposts(data:any):void{
    this.posts = data;
    console.log(this.posts)

  }


  }





