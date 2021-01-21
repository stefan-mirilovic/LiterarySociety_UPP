import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { RegisterService } from 'src/app/service/register.service';

@Component({
  selector: 'app-register-redirect',
  templateUrl: './register-redirect.component.html',
  styleUrls: ['./register-redirect.component.css']
})
export class RegisterRedirectComponent implements OnInit {
  id: number;
  uuid: string;

  constructor(
    private route: ActivatedRoute,
    private toastr: ToastrService,
    private registerService: RegisterService,
    private router: Router
  ) { }

  ngOnInit(): void {
    const id: Observable<number> = this.route.params.pipe(map(p => p.id));
    id.subscribe(id => {
      if (id)
        this.id = id;
    });
    const uuid: Observable<string> = this.route.params.pipe(map(p => p.uuid));
    uuid.subscribe(uuid => {
      if (uuid)
        this.uuid = uuid;
    });
    this.registerService.confirmAccount(this.id, this.uuid).subscribe({
      next: () => {
        this.toastr.info("Successfully confirmed your account. To finish setting up your account, please send two drafts for inspection");
        this.router.navigate(["/writer/document"]);
      },
      error: () => {
        this.toastr.error("An error occured while confirming account!");
        this.router.navigate(["/login"]);
      }
    })
  }

}
