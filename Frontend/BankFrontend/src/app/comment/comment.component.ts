import { Component, OnInit } from '@angular/core';
import {comment} from "../core/models/comment";
import {CommentService} from "../core/service/comment.service";

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {
  comments: comment[]

  constructor(private data: CommentService) {
  }

  ngOnInit(): void {
    this.data.getAllComment().subscribe(
      (data: comment[]) => {
        this.comments = data;
        console.log(data[0].contained)
      });
  }



}
