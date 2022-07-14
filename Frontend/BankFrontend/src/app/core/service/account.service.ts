
import { User } from "../models/user";
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, of, Subject } from "rxjs";
import { map, tap, catchError } from "rxjs/operators";
import { Account } from "../models/account";
import { Transaction } from "../models/transaction";

const apiJavaUrl = "http://localhost:8082";

@Injectable({
  providedIn: "root",
})
export class AccountService {
users: any = [];
userSubject = new Subject<User[]>();
accounts:any=[];
accountSubject = new Subject<Account[]>();
idAccount : number | undefined
  console: any;
constructor(private http: HttpClient) {}
  emitAccounts(){
  this.accountSubject.next(this.accounts.slice())
}

  emitUsers() {
    this.userSubject.next(this.users.slice());
  }



getMyAccounts() {
    this.http.get<any>(apiJavaUrl + "/account/all").subscribe(response => {
      response.sort((item1: Account, item2: Account) => {
        return item1.id - item2.id;
      });
      this.accounts = response;
      
      this. emitAccounts();
    });
  }


  getMyTransactionsAccount(id:number): Observable<any> {
    return this.http.get<Transaction>(apiJavaUrl + "/transactions/accounts/" + id + "/operations");
  }

}
