import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountComponent } from './account/account.component';
import { TransactionComponent } from './user/transaction/transaction.component';
import {ShowRequestComponent} from "./request/show-request/show-request.component";
import {SimulateurComponent} from "./simulateur/simulateur.component";

const routes: Routes = [
  { path: '\admin', component: AccountComponent},
  {path:'\allRequest',component: ShowRequestComponent},
  {path:'\simulateur',component:SimulateurComponent},
  {
    path: 'transactions/accounts/:id/operations',
    component: TransactionComponent,
    data: { title: 'Transaction' }
  },

]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
