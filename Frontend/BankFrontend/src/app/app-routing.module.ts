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
import {CommentComponent} from "./comment/comment.component";
import {AddForumComponent} from "./add-forum/add-forum.component";
import {PdetailComponent} from "./pdetail/pdetail.component";
import {FdetailComponent} from "./fdetail/fdetail.component";
import {CdetailComponent} from "./cdetail/cdetail.component";
import {EditForumComponent} from "./edit-forum/edit-forum.component";
import {UpdatePostComponent} from "./update-post/update-post.component";
import {EditCommentComponent} from "./edit-comment/edit-comment.component";
import {LoginComponent} from "./login/login.component";

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
  { path:'forum/edit/:id', component :EditForumComponent},
  { path:'login', component :LoginComponent},

  {path:'addpost/:id', component :AddPostComponent},
  {path:'updatepost/:id', component :UpdatePostComponent},

  {path:'addcomment/:id', component :AddCommentComponent},
     {path:'forum/:id/post', component :PostComponent},
    {path:'post/:id/comment', component :CommentComponent},
    {path:'forum/add', component : AddForumComponent},
  {path:'detailPost/:id', component : PdetailComponent},
  {path:'detailForum/:id', component : FdetailComponent},
  {path:'detailComment/:id', component : CdetailComponent},
  {path:'editcomment/:id', component : EditCommentComponent},

]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
