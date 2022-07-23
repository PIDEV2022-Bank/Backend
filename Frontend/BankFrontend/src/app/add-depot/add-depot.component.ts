import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { AccountService } from '../core/service/account.service';

@Component({
  selector: 'app-add-depot',
  templateUrl: './add-depot.component.html',
  styleUrls: ['./add-depot.component.css']
})
export class AddDepotComponent implements OnInit {

  TransactionForm: FormGroup;
  submitted = false;
  isValidDate = true;
  role=localStorage?.getItem('role');
  id = localStorage?.getItem('idUser');
 
  toastConfig = {
    'enableHtml': true,
    'positionClass': 'toast-top-right',
    'closeButton': true,
    'autoDismiss': false,
    'timeOut': 3000,
    'tapToDismiss': false,
    'showEasing': 'swing',
    'hideEasing': 'linear',
    'showMethod': 'fadeIn',
    'hideMethod': 'fadeOut',
    'iconClasses': {
      'error': 'toast-seif',
      'info': 'toast-info',
      'success': 'toast-success',
      'warning': 'toast-warning'
    }}
  constructor(private formBuilder: FormBuilder, public activeModal: NgbActiveModal,  
     private accountService: AccountService, private router: Router,
     private toastr: ToastrService) { }

  ngOnInit(): void {
    this.TransactionForm = this.formBuilder.group({
      amount: ['', Validators.required],
      accountDestination: ['', Validators.required]
    });
  
  }
 



  get f() {
    return this.TransactionForm.controls;
  }


  onFormSubmit() {
    this.submitted = true;

    if (this.TransactionForm.invalid) {
       return;
    
    }
    const transfer = this.TransactionForm.value;
    this.accountService.addDepot(transfer).subscribe((response) => {
      if (response.status == 200){
    
         this.accountService.getMyAccounts();
            
         //  this.toastr.success('Projet ajouté avec succès', '',this.toastConfig);
           this.router.navigateByUrl('/refresh', { skipLocationChange: true }).then(() => {
           this.activeModal.close();
           this.router.navigate(['AccountComponent']);
           });
      
    }
  
}, (err) => {
 this.activeModal.close();
 this.toastr.success('Depot effectué avec succès', '',this.toastConfig);
 if(this.role =="ROLE_USER"){
  this.accountService.getUserAccounts(Number(this.id))
 }else{
  this.accountService.getMyAccounts();
 }
 
 //this.toastr.success('Depot effectué avec succès', '',this.toastConfig);
 // this.toastr.error("Problème survenu lors de l'ajout", '',this.toastConfig);
  console.log(err);
} );


}


}
