import { Component, OnInit } from '@angular/core';
import {ComplaintService} from "../services/complaint.service";
import {Complaint} from "../Models/Complaint";

@Component({
  selector: 'app-complaint',
  templateUrl: './complaint.component.html',
  styleUrls: ['./complaint.component.css']
})
export class ComplaintComponent implements OnInit {
complaints:Complaint[]
  constructor(private complaintservice : ComplaintService) { }

  ngOnInit(): void {
    this.complaintservice.getAllComplaints().subscribe(
      (data: Complaint[]) => this.complaints= data);


    setTimeout(() => {
      console.log(this.complaints)
    }, 1000);


  }

}
