import { Component, OnInit } from '@angular/core';
import { AdministratorService } from './administrator.service';
import { Administrator } from './Administrator';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'AdminUI';
  administrator: Administrator = null;
  constructor(private administratorService: AdministratorService) { }
  ngOnInit() {
    this.administratorService.GetAdministrator()
      .subscribe(
        (administrator: Administrator) => {
          this.administrator = administrator;
        }
      );
  }

}
