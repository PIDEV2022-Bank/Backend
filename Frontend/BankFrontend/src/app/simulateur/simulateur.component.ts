import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-simulateur',
  templateUrl: './simulateur.component.html',
  styleUrls: ['./simulateur.component.css']
})

export class SimulateurComponent implements OnInit {
  amountInput:number;
  monthInput:number;
  intrestInput:number;

  capital = 0;
   monthlyIntrestRate = 0;
monthlyIntrest = 0;
monthlyCapital = 0;
   monthlyPayement = 0;
  constructor() { }

  ngOnInit(): void {
  }
  getUserInfo(){
this.monthInput=this.monthInput*12;
this.capital=this.amountInput;
    this.monthlyIntrestRate = this.intrestInput / 1200;
    this.monthlyPayement = ((this.amountInput * this.monthlyIntrestRate) / (1 - (Math.pow((1 + this.monthlyIntrestRate), this.monthInput* -1))));
   this.showOverview();
    this.getDetails();
  }
showOverview() {
    // @ts-ignore
  document.getElementById('overview').innerHTML = `
    <p class="m-0">paiement mensuel : ${this.monthlyPayement.toLocaleString('fr',{minimumFractionDigits: 2,maximumFractionDigits: 2})} €</p>
    <p class="m-0">Paiement annuel : ${(this.monthlyPayement * 12).toLocaleString('fr',{minimumFractionDigits: 2,maximumFractionDigits: 2})} €</p>
    <p class="m-0">Total a payer : ${(this.monthlyPayement * this.monthInput).toLocaleString('fr',{minimumFractionDigits: 2,maximumFractionDigits: 2})} €</p>
    <p class="m-0">Total des intérêts a payer : ${((this.monthlyPayement * this.monthInput) - this.amountInput).toLocaleString('fr',{minimumFractionDigits: 2,maximumFractionDigits: 2})} €</p>
    `;
  }

getDetails() {
    let information = "";
    let counter = 1


    while (counter <= this.monthInput) {
      let payementdate = new Date;
      payementdate.setMonth(payementdate.getMonth() + (counter));
      let month = payementdate.getMonth()+1;
      let year = payementdate.getFullYear();
      let displayDate = '';
      if(month <10){
        displayDate = `01/0${month}/${year}`;
      }else{
        displayDate = `01/${month}/${year}`;
      }
      this.monthlyIntrest =(this.capital * this.monthlyIntrestRate);
      this.capital -= (this.monthlyPayement - this.monthlyIntrest);

      information += `
        <tr>
            <td>${counter++}</td>
            <td>${displayDate}</td>
            <td>${(this.monthlyPayement - this.monthlyIntrest).toLocaleString('fr',{minimumFractionDigits: 2,maximumFractionDigits: 2})} €</td>
            <td>${this.monthlyIntrest.toLocaleString('fr',{minimumFractionDigits: 2,maximumFractionDigits: 2})} €</td>
            <td>${this.capital.toLocaleString('be',{minimumFractionDigits: 2,maximumFractionDigits: 2})} €</td>
        </tr>
    `;
    }

    // @ts-ignore
  document.getElementById('details').innerHTML = information;
  }
}
