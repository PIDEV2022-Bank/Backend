import { Component, OnInit } from '@angular/core';
import { Account } from '../core/models/account';
import { Subscription } from 'rxjs';
import { AccountService } from 'src/app/core/service/account.service';
import { GridOptions } from 'ag-grid-community';
import { ShowDetailsComponent } from '../user/show-details/show-details.component';
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
    { headerName: 'NumeroCompte', field: 'accountNumber', sortable: true, filter: true, width: 300 },
    { headerName: 'solde', field: 'balance', sortable: true, width: 300 },
    {
      headerName: 'Date création', field: 'creationDate', sortable: true, filter: true, width: 260,
      cellRenderer: (data: { value: string | number | Date; }) => {
        return data.value ? (new Date(data.value)).toLocaleDateString('fr-FR') : '';
      }
    },
  
    {
      headerName: 'Action', sortable: true, filter: true, maxWidth: 250,
      cellRenderer: 'showDetailsComponent',
      colId: 'params',
    },

  ];
  
  constructor(public accountService: AccountService,) {this.noRowsTemplate =
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
    this.accountService.getMyAccounts();
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


}
