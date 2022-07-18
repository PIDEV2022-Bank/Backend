import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountComponent } from './account/account.component';
import { ForumComponent } from './forum/forum.component';
import { TransactionComponent } from './user/transaction/transaction.component';

const routes: Routes = [
  { path: '\admin', component: AccountComponent},
  {
    path: 'transactions/accounts/:id/operations',
    component: TransactionComponent, 
    data: { title: 'Transaction' }
  },

   { path:'forum', component :ForumComponent},
 

]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
