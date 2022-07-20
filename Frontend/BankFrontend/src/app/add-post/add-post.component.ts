import { Component, OnInit } from '@angular/core';
import {forum} from "../core/models/forum";
import {post} from "../core/models/post";
import {ForumService} from "../core/service/forum.service";

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css']
})
export class AddPostComponent implements OnInit {

  constructor( ) { }

  ngOnInit(): void {

  }

}
