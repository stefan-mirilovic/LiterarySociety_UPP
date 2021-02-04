import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { UserLogin } from '../model/userLogin';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form;
  loading: boolean;
  passwordToggle: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private authService: AuthService,
    private toastr: ToastrService,
    public router: Router
  ) { }

  ngOnInit(): void {
    this.form = new FormGroup({
      'email': new FormControl(null, [Validators.required, Validators.email]),
      'password': new FormControl(null, [Validators.required])
    });
    this.route.queryParams
      .subscribe(params => {
        console.log(params);

        if (params.msg) {
          if (params.msg.startsWith("Subscription failed")) {
            this.toastr.error(params.msg, "", {
              timeOut: 0,
              extendedTimeOut: 0
            });
          } else if (params.msg.startsWith("Subscription activated")){
            this.toastr.success(params.msg, "", {
              timeOut: 0,
              extendedTimeOut: 0
            });
          } else {
            this.toastr.info(params.msg, "", {
              timeOut: 0,
              extendedTimeOut: 0
            });
          }
          
        }
      });
  }

  onSubmit() {
    let user: UserLogin = new UserLogin(this.form.value.email, this.form.value.password);
    this.authService.login(user).subscribe({
      next: (result) => {
        this.loading = false;
        this.form.reset();
        if (result.userType === "READER" || result.userType === "BETA_READER" ) {
          this.router.navigate(["/reader-dashboard"]);
        } else if (result.userType === "WRITER") {
          this.router.navigate(["/writer-dashboard"]);
        } else if (result.userType === "ADMINISTRATOR") {
          this.router.navigate(["/admin-dashboard"]);
        } else if (result.userType === "COMITTEE_MEMBER") {
          this.router.navigate(["/committee-dashboard"]);
        } else if (result.userType === "EDITOR") {
          this.router.navigate(["/editor-dashboard"]);
        } else if (result.userType === "MAIN_EDITOR") {
          this.router.navigate(["/main-editor-dashboard"]);
        } else if (result.userType === "COMITTEE_LEADER") {
          this.router.navigate(["/committee-leader-dashboard"]);
        } else {
          this.toastr.success(`Welcome, ${result.email}`);
          //this.router.navigate(["/reader-dashboard"]);
        }
      },
          error: data => {
            this.loading = false;
            if (data.error && typeof data.error === "string")
              this.toastr.error(data.error);
            else
              this.toastr.error("An error occured while logging in!")
          }
        }
      );
  }
}
