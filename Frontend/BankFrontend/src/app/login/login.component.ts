import { Component, OnInit } from '@angular/core';
import {ShowDetailsComponent} from "../user/show-details/show-details.component";
import {ActivatedRoute, Router} from "@angular/router";
import {FormControl, FormGroup} from "@angular/forms";
import {LoginService} from "../login.service";
import { StorageService } from '../_services/storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),

  });
  constructor(private auth : LoginService,private route: ActivatedRoute,private router: Router,private storageService: StorageService) {}
login():void{
    this.auth.loginUser(this.loginForm.value.username,this.loginForm.value.password).subscribe((data:any)=>{
      console.log(data)
      this.storageService.saveUser(data);
      this.router.navigateByUrl('/forum')

    })
  console.log(this.loginForm.value.username.value,this.loginForm.value.username)
}
  ngOnInit(): void {
  }

}
