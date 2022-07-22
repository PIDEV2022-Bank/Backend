import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import {ForumService} from '../core/service/forum.service';
import {forum} from '../core/models/forum';
import {post} from "../core/models/post";

@Component({
  selector: 'app-forum',
  templateUrl: './forum.component.html',
  styleUrls: ['./forum.component.css']
})

export class ForumComponent implements OnInit {

     forums :forum[]

    constructor(private data: ForumService) {}



    ngOnInit(): void {
      this.data.getAllForum().subscribe(
     (data: forum[]) => {this.forums= data ;
    console.log(data)}

     );


    }



  }




