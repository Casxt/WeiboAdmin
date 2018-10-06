import { Component, OnInit } from '@angular/core';
import { AdministratorService } from '../administrator.service';
import { Administrator } from '../Administrator';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export class SigninComponent implements OnInit {
  administrator: Administrator = null;
  formUser: string;
  fromPass: string;
  constructor(private administratorService: AdministratorService) { }

  ngOnInit() {
    this.administratorService.GetAdministrator()
    .subscribe(
      (administrator: Administrator) => {
        this.administrator = administrator;
      }
    );
  }

  onSignIn(): void {
    this.administratorService.SignIn(this.formUser, this.fromPass);
  }

}
