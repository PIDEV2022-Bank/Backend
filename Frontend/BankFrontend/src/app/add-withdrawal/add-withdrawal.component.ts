import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AccountService } from '../core/service/account.service';

@Component({
  selector: 'app-add-withdrawal',
  templateUrl: './add-withdrawal.component.html',
  styleUrls: ['./add-withdrawal.component.css']
})
export class AddWithdrawalComponent implements OnInit {

  TransactionForm: FormGroup;
  submitted = false;
  isValidDate = true;
  constructor(private formBuilder: FormBuilder, public activeModal: NgbActiveModal,  
     private accountService: AccountService, private router: Router) { }

  ngOnInit(): void {
    this.TransactionForm = this.formBuilder.group({
      amount: ['', Validators.required],
      accountSource: ['', Validators.required]
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
    this.accountService.addretrait(transfer).subscribe((res) => {
      if (res.status == 200){
        if (res.body != null){


         this.accountService.getMyAccounts();
            
         //  this.toastr.success('Projet ajouté avec succès', '',this.toastConfig);
           this.router.navigateByUrl('/refresh', { skipLocationChange: true }).then(() => {
           this.activeModal.close();
           this.router.navigate(['AccountComponent']);
           });
      }
    }
  
}, (err) => {
  this.activeModal.close();
 // this.toastr.error("Problème survenu lors de l'ajout", '',this.toastConfig);
  console.log(err);
} );


}


}
