import { Component, OnInit } from '@angular/core';
import {Subscription} from "rxjs";
  import {Account} from "../../core/models/account";
  import {Request} from "../../core/models/Request";
import {GridOptions} from "ag-grid-community";
import {ShowDetailsComponent} from "../../user/show-details/show-details.component";
import {RequestService} from "../../core/service/request.service";


@Component({
  selector: 'app-show-request',
  templateUrl: './show-request.component.html',
  styleUrls: ['./show-request.component.css']
})
export class ShowRequestComponent implements OnInit {
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
    { headerName: 'Nom Client', field: 'idUser', sortable: true, filter: true, width: 300 },
    { headerName: 'Demande', field: 'name', sortable: true, width: 200 },
    {
      headerName: 'Date ', field: 'date', sortable: true, filter: true, width: 200,
      cellRenderer: (data: { value: string | number | Date; }) => {
        return data.value ? (new Date(data.value)).toLocaleDateString('fr-FR') : '';
      }
    },
    { headerName: 'Message', field: 'message', sortable: true, width: 200 },
    { headerName: 'Etat', field: 'state', sortable: true, width: 200 },


  ];
  Request:Request[];

  onFilterTextBoxChanged() {
    this.gridOptions.api?.setQuickFilter((<HTMLInputElement>document.getElementById('filter-text-box')).value);

  }
  constructor(private ReqService : RequestService  ) {

    this.noRowsTemplate =
      `<span style="color: #999;">Aucun projet ajouté</span>`;
    this.loadingTemplate =
      `Chargement en cours`;


  }

  ngOnInit(): void {
this.ReqService.getAll().subscribe(
  (data:Request[])=>this.Request=data);


setTimeout(()=>{
  console.log(this.Request)
},3000)

  }



}
