import { Component, OnInit } from '@angular/core';
import { Weibo, AuditWeibo } from '../weibo';
import { WeiboService } from '../weibo.service';

@Component({
  selector: 'app-unaudit',
  templateUrl: './unaudit.component.html',
  styleUrls: ['./unaudit.component.scss']
})
export class UnauditComponent implements OnInit {
  private auditWeiboArray: AuditWeibo[] = new Array();

  constructor(private weiboService: WeiboService) { }

  ngOnInit() {
    this.GetUnauditWeibo();
  }

  /**
   * 自动获取微博
   */
  GetUnauditWeibo(): void {
    this.weiboService.GetAuditWeibo(false, this.auditWeiboArray.length, this.auditWeiboArray.length + 19)
      .subscribe(
        (weiboArray: AuditWeibo[]) => {
          this.auditWeiboArray = this.auditWeiboArray.concat(weiboArray);
        }
      );
  }

  BanWeibo(auditWeibo: AuditWeibo): void {
    this.weiboService.BanWeibo(auditWeibo)
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

  DeleteWeibo(auditWeibo: AuditWeibo): void {
    this.weiboService.DeleteWeibo(auditWeibo)
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
