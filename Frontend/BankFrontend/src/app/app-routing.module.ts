import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountComponent } from './account/account.component';
import { AddCommentComponent } from './add-comment/add-comment.component';
import { AddPostComponent } from './add-post/add-post.component';
import { PostComponent } from './post/post.component';
import { ForumComponent } from './forum/forum.component';
import { TransactionComponent } from './user/transaction/transaction.component';
import {ShowRequestComponent} from "./request/show-request/show-request.component";
import {SimulateurComponent} from "./simulateur/simulateur.component";
import {ComplaintComponent} from "./complaint/complaint.component";

const routes: Routes = [
  { path: '\admin', component: AccountComponent},
  {path:'\allRequest',component: ShowRequestComponent},
  {path:'\simulateur',component:SimulateurComponent},
  { path: '\complaint', component: ComplaintComponent},

  {
    path: 'transactions/accounts/:id/operations',
    component: TransactionComponent,
    data: { title: 'Transaction' }
  },

   { path:'forum', component :ForumComponent},
     {path:'addpost', component :AddPostComponent},
     {path:'comment', component :AddCommentComponent},
     {path:'post', component :PostComponent},
 

]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
