import { Component, OnInit } from '@angular/core';
import {ComplaintService} from "../services/complaint.service";
import {ActivatedRoute} from "@angular/router";
import {Complaint} from "../Models/Complaint";

@Component({
  selector: 'app-comlpaint-details',
  templateUrl: './comlpaint-details.component.html',
  styleUrls: ['./comlpaint-details.component.css']
})
export class ComlpaintDetailsComponent implements OnInit {
id:Number
  complaint:Complaint
  constructor(private complaintservice : ComplaintService, private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.id=this.route.snapshot.params.complaintId;
    this.complaintservice.getComplaintById(this.id).subscribe(
      (data: Complaint) => this.complaint= data);
    setTimeout(()=>{console.log(this.complaint)},1000)


  }
  changeToDone(id:number) {
    this.complaintservice.changeToDone(id).subscribe()
    setTimeout(()=>{window.location.reload();},1700)


  }  changeToRollback(id:number) {
    this.complaintservice.changeToRollback(id).subscribe()
    setTimeout(()=>{window.location.reload();},1700)

  }

}
