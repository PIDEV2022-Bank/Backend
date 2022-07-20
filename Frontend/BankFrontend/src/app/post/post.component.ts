import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import {PostService} from '../core/service/post.service';
import {post} from "../core/models/post";
import {forum} from "../core/models/forum";
import {GridOptions} from "ag-grid-community";
import {ShowDetailsComponent} from "../user/show-details/show-details.component";
import {AccountService} from "../core/service/account.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {Subscription} from "rxjs";


@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  posts:post[]
  /*isEmpty = true;
  noRowsTemplate: string;
  loadingTemplate: string;
  defaultColDef = { resizable: true };
  rowData: post[] | undefined;
  gridOptions: GridOptions;
  domLayout = "autoHeight";
  frameworkComponents = { showDetailsComponent: ShowDetailsComponent }
  columnDefs = [
    { headerName: 'IdPost', field: 'id', sortable: true, filter: true, width: 300 },
    { headerName: 'DateCreated', field: 'date', sortable: true, width: 200 },

    { headerName: 'Post', field: 'contained', sortable: true, width: 200 },

  ];*/
  constructor( private data: PostService){
               /*private modelService :NgbModal)

  {this.noRowsTemplate =
    `<span style="color: #999;">Aucun projet ajouté</span>`;
    this.loadingTemplate =
      `Chargement en cours`;
    this.gridOptions = {
      rowData: this.rowData,
      columnDefs: this.columnDefs, }
  }
  onFilterTextBoxChanged() {
    this.gridOptions.api?.setQuickFilter((<HTMLInputElement>document.getElementById('filter-text-box')).value);
*/
  }

  ngOnInit(): void {

    this.data.getAllPost().subscribe(
      (data:post[]) => {this.posts= data ;
        console.log(data)});
  }


  }
