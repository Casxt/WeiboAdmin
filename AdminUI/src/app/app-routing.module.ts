import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SigninComponent } from './signin/signin.component';
import { AuditComponent } from './audit/audit.component';
import { UnauditComponent } from './unaudit/unaudit.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AdministratorService } from './administrator.service';
import { CreateAdministratorComponent } from './create-administrator/create-administrator.component';
const routes: Routes = [
  { path: '', component: DashboardComponent, canActivate: [AdministratorService] },
  { path: 'signin', component: SigninComponent },
  { path: 'audit', component: AuditComponent, canActivate: [AdministratorService] },
  { path: 'unaudit', component: UnauditComponent, canActivate: [AdministratorService] },
  { path: 'dashboard', component: DashboardComponent, canActivate: [AdministratorService] },
  { path: 'createadministrator', component: CreateAdministratorComponent, canActivate: [AdministratorService] }
];
@NgModule({
  imports: [ RouterModule.forRoot(routes, { enableTracing: true }) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
