import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Complaint} from "../Models/Complaint";

@Injectable({
  providedIn: 'root'
})

export class ComplaintService {


  constructor(private http: HttpClient) {  }
  getAllComplaints(){
    return this.http.get<Complaint[]>('http://127.0.0.1:8082/complaint/all');
  }
  changeToDone(id:Number){
    return this.http.put('http://localhost:8082/complaint/updateStatusToDone/'+id,id)

  }
  deleteComplaint(id:Number){
    return this.http.delete('http://localhost:8082/complaint/delete/'+id)
  }
}
