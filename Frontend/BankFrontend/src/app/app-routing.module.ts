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
import {ComlpaintDetailsComponent} from "./comlpaint-details/comlpaint-details.component";

import {HomeComponent} from "./home/home.component";
import {AdminComponent} from "./admin/admin.component";
import {UserComponent} from "./user/user.component";
import {UserInterfaceComponent} from "./user-interface/user-interface.component";
import {ComplaintAddComponent} from "./complaint-add/complaint-add.component";
import {MyComlaintComponent} from "./my-comlaint/my-comlaint.component";
const routes: Routes = [
  {path: '', component:HomeComponent},
  {path:'user', component:UserInterfaceComponent,
    children: [
      { path: 'addComplaint', component: ComplaintAddComponent },
      {path:'myComplaint',component: MyComlaintComponent},]},
  { path: 'admin', component: AdminComponent,
    children: [
      { path: 'complaint', component: ComplaintComponent },
      {path:'allRequest',component: ShowRequestComponent},
      { path:'forum', component :ForumComponent},
      {path:'complaint/:complaintId', component :ComlpaintDetailsComponent},
      { path: 'account', component: AccountComponent},
    ],},

  {path:'simulateur',component:SimulateurComponent},
  //{ path: 'admin/complaint', component: ComplaintComponent},
  {
    path: 'transactions/accounts/:id/operations',
    component: TransactionComponent,
    data: { title: 'Transaction' }
  },


     {path:'addpost', component :AddPostComponent},
     {path:'comment', component :AddCommentComponent},
     {path:'post', component :PostComponent},




]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
