import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountComponent } from './account/account.component';
import { TransactionComponent } from './user/transaction/transaction.component';
import {ComplaintComponent} from "./complaint/complaint.component";

const routes: Routes = [
  { path: '\admin', component: AccountComponent},
  { path: '\complaint', component: ComplaintComponent},

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
