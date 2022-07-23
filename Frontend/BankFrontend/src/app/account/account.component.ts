import { Component, OnInit } from '@angular/core';
import { Account } from '../core/models/account';
import { Subscription } from 'rxjs';
import { AccountService } from 'src/app/core/service/account.service';
import { GridOptions } from 'ag-grid-community';
import { NgbModal, NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';
import { ShowDetailsComponent } from '../user/show-details/show-details.component';
import { AddTransactionComponent } from '../add-transaction/add-transaction.component';
import { AddDepotComponent } from '../add-depot/add-depot.component';
import { AddWithdrawalComponent } from '../add-withdrawal/add-withdrawal.component';
import { Router } from '@angular/router';
@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
 AccountSubscription: Subscription | undefined;
  isEmpty = true;
  noRowsTemplate: string;
  loadingTemplate: string;
  defaultColDef = { resizable: true };
  rowData: Account[] | undefined;
   gridOptions: GridOptions;
  domLayout = "autoHeight";
  frameworkComponents = { showDetailsComponent: ShowDetailsComponent }
  columnDefs = [
    { headerName: 'Numero Compte', field: 'accountNumber', sortable: true, filter: true, width: 200 },
    { headerName: 'Type', field: 'accountType', sortable: true, width: 120 },
    { headerName: 'solde', field: 'balance', sortable: true, width: 120 },

    {
      headerName: 'Date création', field: 'creationDate', sortable: true, filter: true, width:120,
      cellRenderer: (data: { value: string | number | Date; }) => {
        return data.value ? (new Date(data.value)). toLocaleDateString('fr-FR') : '';
     
      }
    },
    { headerName: 'Client', field: 'client.username', sortable: true, width: 150 },
    { headerName: 'Email', field: 'client.email', sortable: true, width: 200 },
    { headerName: 'Deccouvert', field: 'autorisation', sortable: true, width: 100 },
    {
      headerName: 'Historique Transaction', sortable: true, filter: true, maxWidth: 300,
      cellRenderer: 'showDetailsComponent',
      colId: 'params',
    },

  ];
  
  constructor(public accountService: AccountService, private modalService: NgbModal,private router:Router) {this.noRowsTemplate =
    `<span style="color: #999;">Aucun projet ajouté</span>`;
  this.loadingTemplate =
    `Chargement en cours`;
  this.gridOptions = {
    rowData: this.rowData,
    columnDefs: this.columnDefs, }
  }
  onFilterTextBoxChanged() {
    this.gridOptions.api?.setQuickFilter((<HTMLInputElement>document.getElementById('filter-text-box')).value);
   
  }
  ngOnInit() {
    let id = localStorage?.getItem('idUser');
    console.log(id);
    let role = localStorage?.getItem('role');
     switch (role) {
         
       case "ROLE_USER": {
         
         this.accountService.getUserAccounts(Number(id));
         this.router.navigateByUrl("/user/account");
         break;
       }
       case "ROLE_ADMIN": {
         this.accountService.getMyAccounts();
         this.router.navigateByUrl("/admin/account");
         break;
       }
     }
  
    // this.accountService.getMyAccounts(localStorage.getItem('idUser'));
     this.listening();
     this.accountService.emitAccounts();
   }
  listening() {
    this.AccountSubscription = this.accountService.accountSubject.subscribe((accounts: Account[]) => {
      if (accounts) {
        this.rowData = accounts;
      }
    });
  }


 
  openTransactionModal() {
    const ngbModalOptions: NgbModalOptions = {
      backdrop: 'static',
      keyboard: false
    };
    this.modalService.open(AddTransactionComponent, ngbModalOptions);
  }


  openDepotModal() {
    const ngbModalOptions: NgbModalOptions = {
      backdrop: 'static',
      keyboard: false
    };
    this.modalService.open(AddDepotComponent, ngbModalOptions);
  }

  openWithDrawalModal() {
    const ngbModalOptions: NgbModalOptions = {
      backdrop: 'static',
      keyboard: false
    };
    this.modalService.open(AddWithdrawalComponent, ngbModalOptions);
  }

  
}
