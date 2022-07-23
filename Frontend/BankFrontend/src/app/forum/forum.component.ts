import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import {ForumService} from '../core/service/forum.service';
import {forum} from '../core/models/forum';
import {post} from "../core/models/post";
import {StorageService} from "../_services/storage.service";

@Component({
  selector: 'app-forum',
  templateUrl: './forum.component.html',
  styleUrls: ['./forum.component.css']
})

export class ForumComponent implements OnInit {

     forums :forum[]
     user :any


    constructor(private data: ForumService,private storage:StorageService) {
       this.user=this.storage.getUser()
    }
  deleteForums(id:any):void{
    this.data.deleteForum(id).subscribe(()=>{
      this.data.getAllForum().subscribe(
        (data: forum[]) => {this.setformuslist( data) ;
        }


      );
    })
  }


    ngOnInit(): void {
      this.data.getAllForum().subscribe(
     (data: forum[]) => {this.setformuslist( data) ;
     }


     );


    }


setformuslist(data:any):void{
       console.log(data,"da3ta")
       this.forums=data;
  console.log(this.forums,"da3ta")

}


  }




