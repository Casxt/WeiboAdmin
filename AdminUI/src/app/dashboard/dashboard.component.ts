import { Component, OnInit } from '@angular/core';
import { WeiboService } from '../weibo.service';
import { WeiboStatic, CommentStatic } from '../weibo';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  weiboStatic: WeiboStatic[];
  commentStatic: CommentStatic[];
  // lineChart
  public lineChartData: Array<any> = new Array();
  public lineChartOptions: any = {
    scales: {
      xAxes: [{
        type: 'time',
        time: {
          displayFormats: {
            millisecond: 'YYYY-MMM-DD hh:mm:ss',
            second: 'YYYY-MMM-DD hh:mm:ss',
            minute: 'YYYY-MMM-DD hh:mm:ss',
            hour: 'YYYY-MMM-DD hh:mm:ss',
            day: 'YYYY-MMM-DD hh:mm:ss',
            week: 'YYYY-MMM-DD hh:mm:ss',
            month: 'YYYY-MMM-DD hh:mm:ss',
            quarter: 'YYYY-MMM-DD hh:mm:ss',
            year: 'YYYY-MMM-DD hh:mm:ss'
          }
        }
      }]
    }
  };
  public lineChartLegend = true;
  public lineChartType = 'bar';

  constructor(private weiboService: WeiboService) { }

  ngOnInit() {
    this.weiboService.GetWeiboStaticInfo()
      .subscribe(
        (weiboStatic: WeiboStatic[]) => {
          const tempList = new Array();
          for (const ws of weiboStatic) {
            tempList.push({
              x: ws.time,
              y: ws.count
            });
          }
          this.weiboStatic = tempList;
          this.lineChartData = [
            {
              data: this.weiboStatic,
              label: '微博数量',
            },
            {
              data: this.commentStatic,
              label: '评论数量',
            }
          ];
        }
      );

    this.weiboService.GetCommentStaticInfo()
      .subscribe(
        (commentStatic: CommentStatic[]) => {
          const tempList = new Array();
          for (const cs of commentStatic) {
            tempList.push({
              x: cs.time,
              y: cs.count
            });
          }
          this.commentStatic = tempList;
          this.lineChartData = [
            {
              data: this.weiboStatic,
              label: '微博数量',
            },
            {
              data: this.commentStatic,
              label: '评论数量',
            }
          ];
        }
      );

  }

}
