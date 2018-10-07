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
  disableButton: boolean;
  buttonMsg: string;
  constructor(private administratorService: AdministratorService) { }

  ngOnInit() {
    this.disableButton = false;
    this.buttonMsg = '创建';
  }

  onCreateAdministrator(): void {
    this.administratorService.CreateAdministrator(this.formUser, this.fromPass)
      .subscribe(
        (res: string) => {
          this.buttonMsg = res;
          this.disableButton = true;
          setTimeout(() => { this.ngOnInit(); }, 1000);
        }
      );

  }

}
