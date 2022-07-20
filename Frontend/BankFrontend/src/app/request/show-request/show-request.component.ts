import { Component, OnInit } from '@angular/core';
import {Subscription} from "rxjs";
  import {Account} from "../../core/models/account";
  import {Request} from "../../core/models/Request";
import {GridOptions} from "ag-grid-community";
import {ShowDetailsComponent} from "../../user/show-details/show-details.component";
import {RequestService} from "../../core/service/request.service";
import {userError} from "@angular/compiler-cli/src/transformers/util";
import  {User} from "../../core/models/user";

@Component({
  selector: 'app-show-request',
  templateUrl: './show-request.component.html',
  styleUrls: ['./show-request.component.css']
})
export class ShowRequestComponent implements OnInit {

  Request:Request[];

   encours=true
  done=false


  constructor(private ReqService : RequestService  ) {






  }

  ngOnInit(): void {
this.ReqService.getAll().subscribe(
  (data:Request[])=>this.Request=data);


setTimeout(()=>{
  console.log(this.Request)
},3000)

  }
  deleteRequest(id:number){
    this.ReqService.delete(id).subscribe();
    console.log(id);
  }
  UpdateStatus(id:number){
    this.ReqService.changeToDone(id).subscribe();
  }
  AfficherEnCours(){
    this.encours=true;
    this.done=false
    console.log("encours")
  }
  AfficherDone(){
     this.encours=false
    this.done=true;
     console.log("historique")
  }



}
