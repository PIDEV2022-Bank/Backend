import { Component, OnInit } from '@angular/core';
import {ComplaintService} from "../services/complaint.service";
import {Complaint} from "../Models/Complaint";
import { ColDef } from 'ag-grid-community';

@Component({
  selector: 'app-complaint',
  templateUrl: './complaint.component.html',
  styleUrls: ['./complaint.component.css']
})
export class ComplaintComponent implements OnInit {

  enCours=true
  historique=false


//complaints:Complaint[]
    rowData:Complaint[]
  constructor(private complaintservice : ComplaintService) { }

  ngOnInit(): void {
    this.complaintservice.getAllComplaints().subscribe(
      (data: Complaint[]) => this.rowData= data);


    setTimeout(() => {
      console.log(this.rowData)
    }, 1000);


  }

  changeToDone(id:number) {
    this.complaintservice.changeToDone(id).subscribe()
  }

  AfficherEnCours() {
    this.enCours=true
    this.historique=false
  }

  AfficherHistorique() {
    this.enCours=false
    this.historique=true
  }
}
