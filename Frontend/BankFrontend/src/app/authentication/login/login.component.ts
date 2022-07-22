import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { AuthenticationService } from "src/app/core/service/authentication.service";
@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"],
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  submitted = false;
  isLoadingResults = true;
  isLoggedIn = false;
​
  constructor(
    private formBuilder: FormBuilder,
    private authenticationService: AuthenticationService,
    private router: Router
  ) {}
​
  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ["", [Validators.required, Validators.pattern]],
      password: ["", [Validators.required, Validators.minLength(6)]],
    });
​

  }
​
​
  get username() {
    return this.loginForm.get("username");
  }
​
  get password() {
    return this.loginForm.get("password");
  }
​



  login() {
    
    const username = this.loginForm.value.username;
    const password = this.loginForm.value.password;
    this.authenticationService.login(username, password).subscribe((user) => {
      if (user) {

       let iduser = user.id;
       let role = user.role[0];
       localStorage.setItem("idUser", iduser.toString());
       localStorage.setItem("role", role);
       //ROUTING
       switch (role) {
        
         case "ROLE_USER": {
           this.router.navigateByUrl("/user");
           break;
         }
         case "ROLE_ADMIN": {
           this.router.navigateByUrl("/admin");
           break;
         }
       }
      }
    });
  }

}
