import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GridOptions } from 'ag-grid-community';
import { TabsetComponent } from 'ngx-bootstrap/tabs';
import { Subscription } from 'rxjs';
import { Transaction } from 'src/app/core/models/transaction';
import { AccountService } from 'src/app/core/service/account.service';
@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit  {
  gridOptions!: GridOptions;
  noRowsTemplate!: string;
  loadingTemplate!: string;
  disableSwitching: boolean | undefined;
  @ViewChild('tabset', { static: false }) tabset: TabsetComponent | undefined;
  
  subscription: Subscription | undefined;

  isEmpty = true;

  defaultColDef = { resizable: true };
  rowData: Transaction[] | undefined;
  transaction: Transaction | undefined;
  id: number | undefined;

  domLayout = "autoHeight";
  columnDefs = [
    { headerName: 'Réference Transaction', field: 'transactionId', sortable: true, filter: true, width: 300 },
    { headerName: 'Montant', field: 'amount', sortable: true, width: 300 },
    {
      headerName: 'Date Opération', field: 'transactionDate', sortable: true, filter: true, width: 260,
      cellRenderer: (data: { value: string | number | Date; }) => {
        return data.value ? (new Date(data.value)).toLocaleDateString('fr-FR') : '';
      }
    },
    { headerName: 'Description', field: 'description', sortable: true, width: 300 },

  ];
  

  constructor( private route: ActivatedRoute, private router: Router ,public accountService: AccountService) { }

  ngOnInit(): void {
    this.subscription = this.route.params.subscribe(params => {
      this.id = +params['id']
      this.getTransaction(this.id);
    });
    
    
  }


  getTransaction(idAccount: number) {
    this.accountService.getMyTransactionsAccount(idAccount).subscribe(
      (transaction) => {
        if (transaction !== null) {
            this.rowData = transaction;
        }
      },
      (err) => {
        console.log(err);
      }
    );
  }
}
