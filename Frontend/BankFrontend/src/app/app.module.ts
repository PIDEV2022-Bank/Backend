import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AccountComponent } from './account/account.component';
import { AgGridModule } from 'ag-grid-angular';
import { HttpClientModule } from '@angular/common/http';
import { TransactionComponent } from './user/transaction/transaction.component';
import { ShowDetailsComponent } from './user/show-details/show-details.component';
import { NavbarComponent } from './navbar/navbar.component';
import { AddTransactionComponent } from './add-transaction/add-transaction.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ShowRequestComponent } from './request/show-request/show-request.component';
import { SimulateurComponent } from './simulateur/simulateur.component';
import { ComplaintComponent } from './complaint/complaint.component';
import { AddDepotComponent } from './add-depot/add-depot.component';
import { AddWithdrawalComponent } from './add-withdrawal/add-withdrawal.component';
import { ForumComponent } from './forum/forum.component';
import { AddPostComponent } from './add-post/add-post.component';
import { AddCommentComponent } from './add-comment/add-comment.component';
import { PostComponent } from './post/post.component';
import { ComlpaintDetailsComponent } from './comlpaint-details/comlpaint-details.component';
import { ComplaintAddComponent } from './complaint-add/complaint-add.component';
import { HomeComponent } from './home/home.component';
import { AdminComponent } from './admin/admin.component';
import { UserComponent } from './user/user.component';
import { SpinnerComponent } from './spinner/spinner.component';
import { UserInterfaceComponent } from './user-interface/user-interface.component';
import { MyComlaintComponent } from './my-comlaint/my-comlaint.component';
import { LoginComponent } from './authentication/login/login.component';
@NgModule({
  declarations: [
    AppComponent,
    AccountComponent,
    TransactionComponent,
    ShowDetailsComponent,
    NavbarComponent,
    AddTransactionComponent,
    ShowRequestComponent,
    SimulateurComponent,
    AddTransactionComponent,
    ComplaintComponent,
    AddTransactionComponent,
    AddDepotComponent,
    AddWithdrawalComponent,
    ForumComponent,
    AddPostComponent,
    AddCommentComponent,
    PostComponent,


    AddWithdrawalComponent,
        ComlpaintDetailsComponent,
        ComplaintAddComponent,
        HomeComponent,
        AdminComponent,
        UserComponent,
        SpinnerComponent,
        UserInterfaceComponent,
        MyComlaintComponent,
        LoginComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AgGridModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
