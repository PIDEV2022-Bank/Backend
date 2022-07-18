import { Component, OnInit } from '@angular/core';
import { ICellRendererAngularComp } from 'ag-grid-angular';


@Component({
  selector: 'app-show-details',
  template: `
  <span  ><i class="fa fa-eye" routerLink="/transactions/accounts/{{params.data.id}}/operations" ></i></span>`,

  styleUrls: ['./show-details.component.css']
})
export class ShowDetailsComponent implements ICellRendererAngularComp {
  public params: any;


  constructor() { }
  agInit(params: any): void {
    this.params = params;

  }

  refresh(): boolean {
    return false;
  }

}
