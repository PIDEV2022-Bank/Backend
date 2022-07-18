

import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, of, Subject } from "rxjs";
import { map, tap, catchError } from "rxjs/operators";
import {Request} from "../models/Request";
import { Transfer } from "../models/transfer";
import { Transaction } from "../models/transaction";
const apiJavaUrl = "http://localhost:8082";
@Injectable({
  providedIn: 'root'
})
export class RequestService {

  constructor(private http: HttpClient) { }
  getAll(){
  return  this.http.get<Request[]>("http://localhost:8082/Request/all");
  }
}
