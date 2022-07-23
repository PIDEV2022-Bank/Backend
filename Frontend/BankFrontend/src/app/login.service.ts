import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http:HttpClient) {

  }

  loginUser(username:any,password:any){
    return this.http.post('http://localhost:8082/api/auth/signin',{username,password})
  }
}
