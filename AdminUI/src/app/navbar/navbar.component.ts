import { Component, OnInit } from '@angular/core';
import { AdministratorService } from '../administrator.service';
import { Administrator } from '../Administrator';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
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

