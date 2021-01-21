import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
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
    private authService: AuthService,
    private toastr: ToastrService,
    public router: Router
  ) { }

  ngOnInit(): void {
    this.form = new FormGroup({
      'email': new FormControl(null, [Validators.required, Validators.email]),
      'password': new FormControl(null, [Validators.required])
    });
  }

  onSubmit() {
    let user: UserLogin = new UserLogin(this.form.value.email, this.form.value.password);
    this.authService.login(user).subscribe({
      next: (result) => {
        this.loading = false;
        this.form.reset();
        this.toastr.success(`Welcome, ${result.userId}`);
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
