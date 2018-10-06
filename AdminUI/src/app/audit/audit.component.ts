import { Component, OnInit } from '@angular/core';
import { WeiboService } from '../weibo.service';
import { Weibo, AuditWeibo } from '../weibo';

@Component({
  selector: 'app-audit',
  templateUrl: './audit.component.html',
  styleUrls: ['./audit.component.scss']
})
export class AuditComponent implements OnInit {
  private auditWeiboArray: AuditWeibo[] = new Array();

  constructor(private weiboService: WeiboService) { }

  ngOnInit() {
    this.GetAuditedWeibo();
  }

  GetAuditedWeibo(): void {
    this.weiboService.GetAuditWeibo(true, this.auditWeiboArray.length, this.auditWeiboArray.length + 19)
      .subscribe(
        (auditWeiboArray: AuditWeibo[]) => {
          this.auditWeiboArray = this.auditWeiboArray.concat(auditWeiboArray);
        }
      );
  }

  UnbanWeibo(auditWeibo: AuditWeibo): void {
    this.weiboService.UnbanWeibo(auditWeibo)
      .subscribe(
        (banded: boolean) => {
          for (let i = 0; i < this.auditWeiboArray.length; i++) {
            if (this.auditWeiboArray[i].weiboId === auditWeibo.weiboId) {
              this.auditWeiboArray.splice(i, 1);
              return;
            }
          }
        }
      );
  }

  RecoverWeibo(auditWeibo: AuditWeibo): void {
    this.weiboService.RecoverWeibo(auditWeibo)
      .subscribe(
        (banded: boolean) => {
          for (let i = 0; i < this.auditWeiboArray.length; i++) {
            if (this.auditWeiboArray[i].weiboId === auditWeibo.weiboId) {
              this.auditWeiboArray.splice(i, 1);
              return;
            }
          }
        }
      );
  }
}
