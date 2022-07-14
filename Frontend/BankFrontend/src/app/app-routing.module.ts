import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountComponent } from './account/account.component';
import { TransactionComponent } from './user/transaction/transaction.component';

const routes: Routes = [
  { path: '', component: AccountComponent},
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
