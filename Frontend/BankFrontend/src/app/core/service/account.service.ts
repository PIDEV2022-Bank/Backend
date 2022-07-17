
import { User } from "../models/user";
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, of, Subject } from "rxjs";
import { map, tap, catchError } from "rxjs/operators";
import { Account } from "../models/account";
import { Transfer } from "../models/transfer";
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
idAccount : number 
  console: any;
constructor(private http: HttpClient) {}
  emitAccounts(){
  this.accountSubject.next(this.accounts.slice())
}

  emitUsers() {
    this.userSubject.next(this.users.slice());
  }
  
  addAccounts(account: any) {
    this.accounts.unshift(account);
    this.emitAccounts();
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

  addTransfer(transfer:Transfer): Observable<any> {
   return this.http.post<Transfer>(
      apiJavaUrl + "/transactions/accounts/transfer",
      transfer,
      { observe: "response" }
    )
      .pipe(
        tap((_) => this.console.log("fetched Projects"))
      );
  }

  addDepot(transfer:Transfer): Observable<any> {
    return this.http.post<Transfer>(
       apiJavaUrl + "/transactions/accounts/depot",
       transfer,
       { observe: "response" }
     )
       .pipe(
         tap((_) => this.console.log("fetched Projects"))
       );
   }


   addretrait(transfer:Transfer): Observable<any> {
    return this.http.post<Transfer>(
       apiJavaUrl + "/transactions/accounts/retrait",
       transfer,
       { observe: "response" }
     )
       .pipe(
         tap((_) => this.console.log("fetched Projects"))
       );
   }

}
