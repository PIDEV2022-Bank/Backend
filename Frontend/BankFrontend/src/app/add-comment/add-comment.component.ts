import { Component, OnInit } from '@angular/core';
import {Account} from "../core/models/account";
import {GridOptions} from "ag-grid-community";
import {ShowDetailsComponent} from "../user/show-details/show-details.component";

@Component({
  selector: 'app-add-comment',
  templateUrl: './add-comment.component.html',
  styleUrls: ['./add-comment.component.css']
})
export class AddCommentComponent implements OnInit {
  /*isEmpty = true;
  noRowsTemplate: string;
  loadingTemplate: string;
  defaultColDef = { resizable: true };
  rowData: Comment[] | undefined;
  gridOptions: GridOptions;
  domLayout = "autoHeight";
  frameworkComponents = { showDetailsComponent: ShowDetailsComponent }
  columnDefs = [
    { headerName: 'idComment', field: 'id', sortable: true, filter: true, width: 300 },
    { headerName: 'DateCreated', field: 'date', sortable: true, width: 200 },

    { headerName: 'Comments', field: 'contained', sortable: true, width: 200 },

  ];*/
  constructor() { }

  ngOnInit(): void {
  }

}
