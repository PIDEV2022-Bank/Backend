import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AccountComponent } from './account/account.component';
import { AgGridAngular, AgGridModule } from 'ag-grid-angular';
import { CellClickedEvent, ColDef, GridReadyEvent } from 'ag-grid-community';
import { HttpClientModule } from '@angular/common/http'; 
import { TransactionComponent } from './user/transaction/transaction.component';
import { ShowDetailsComponent } from './user/show-details/show-details.component';
import { NavbarComponent } from './navbar/navbar.component';
import { AddTransactionComponent } from './add-transaction/add-transaction.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ComplaintComponent } from './complaint/complaint.component';
@NgModule({
  declarations: [
    AppComponent,
    AccountComponent,
    TransactionComponent,
    ShowDetailsComponent,
    NavbarComponent,
    AddTransactionComponent,
    ComplaintComponent
     
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
