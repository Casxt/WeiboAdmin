import { Component, OnInit } from '@angular/core';
import { AdministratorService } from '../administrator.service';

@Component({
  selector: 'app-create-administrator',
  templateUrl: './create-administrator.component.html',
  styleUrls: ['./create-administrator.component.scss']
})
export class CreateAdministratorComponent implements OnInit {
  formUser: string;
  fromPass: string;
  createRes: string;
  constructor(private administratorService: AdministratorService) { }

  ngOnInit() {
  }

  onCreateAdministrator(): void {
    this.administratorService.CreateAdministrator(this.formUser, this.fromPass)
      .subscribe(
        (res: string) => {
          this.createRes = res;
        }
      );
  }

}
