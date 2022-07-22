import { Component, OnInit } from '@angular/core';
import {Complaint} from "../Models/Complaint";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-complaint-add',
  templateUrl: './complaint-add.component.html',
  styleUrls: ['./complaint-add.component.css']
})
export class ComplaintAddComponent implements OnInit {

  Complaint:Complaint

  constructor() { }

  ngOnInit(): void {
    this.Complaint= new Complaint ()
  }
  addComplaint(ComplaintForm : NgForm){

    this.Complaint=ComplaintForm.value
    console.log(this.Complaint)
  }
  submit({value,valid}:{value: Complaint,valid: boolean}){
    this.Complaint=value;
    console.log(this.Complaint)
  }
}
