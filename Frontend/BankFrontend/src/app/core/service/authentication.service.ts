import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable, BehaviorSubject, of } from "rxjs";
import { map, throwIfEmpty, catchError, tap } from "rxjs/operators";
import { User } from "../models/user";
import { Router } from "@angular/router";
const apiJavaUrl = "http://localhost:8082"; // you have to customise this adress
@Injectable({
  providedIn: "root",
})
export class AuthenticationService {
  isLoadingResults = false;
  user: User;
  isLoggedIn = false;
  redirectUrl: string;
  currentUserSubject: BehaviorSubject<User>;
  currentUser: Observable<User>;
  constructor(private http: HttpClient, private router: Router) {}
  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }
  public setCurrentUser(user: User) {
    this.currentUserSubject.next(user);
  }

  login(username:string, password:string) {
    return this.http.post<any>( apiJavaUrl + '/api/auth/signin', {username, password});
}

logout() {
    localStorage.clear();
    this.isLoggedIn = false;
    //this.currentUserSubject.next();
    this.router.navigate(["login"]);
  }

 

}