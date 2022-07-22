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


@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  posts:post[]

  constructor( private data: PostService){

  }

  ngOnInit(): void {

    this.data.getAllPost().subscribe(
      (data:post[]) => {this.posts= data ;
        console.log(data)});
  }


  }
