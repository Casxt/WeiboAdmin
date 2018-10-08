import { Component, OnInit } from '@angular/core';
import { WeiboService } from '../weibo.service';
import { WeiboStatic } from '../weibo';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  weiboStatic: WeiboStatic[];
  // lineChart
  public lineChartData: Array<any>;
  public lineChartOptions: any = {
    scales: {
      xAxes: [{
        type: 'time',
        time: {
          displayFormats: {
            quarter: 'YYYY MMM DD H'
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
            }
          ];
        }
      );
  }

}
