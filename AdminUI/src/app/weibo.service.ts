import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of, BehaviorSubject } from 'rxjs';
import { Weibo, WeiboDTO, AuditWeibo, WeiboStaticDTO, WeiboStatic } from './weibo';
@Injectable({
  providedIn: 'root'
})
export class WeiboService {

  constructor(private http: HttpClient) { }

  /**
   * 获取需要审核的微博
   * @param audited true when need audited weibo, false when need unaudited weibo
   * @param from start num of weibo, start from 0
   * @param to end num of weibo
   */
  GetAuditWeibo(audited: boolean, from: Number, to: Number): Observable<AuditWeibo[]> {
    const weiboArrayObsv: BehaviorSubject<AuditWeibo[]> = new BehaviorSubject<AuditWeibo[]>([]);

    this.http.get<WeiboDTO>(`/api/audit/${audited ? 'audited' : 'unaudit'}/${from}/${to}`)
      .subscribe(resp => {
        if (resp.state === 'Success') {
          weiboArrayObsv.next(resp.auditWeibo);
        } else {
          weiboArrayObsv.next(null);
        }
      }, error => {
        console.log(error);
        weiboArrayObsv.next(null);
      });
    return weiboArrayObsv.asObservable();
  }

  /**
   * 封禁微博
   * @param auditWeibo 要封禁的微博
   */
  BanWeibo(auditWeibo: AuditWeibo): Observable<boolean> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    auditWeibo.isAudited = true;
    auditWeibo.weibo.isBaned = true;
    const res: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(null);
    this.http.put<WeiboDTO>(`/api/audit/${auditWeibo.weibo.id}`, JSON.stringify(auditWeibo), httpOptions)
      .subscribe(
        resp => { console.log(resp); res.next(resp.state === 'Success'); },
        error => {
          console.log(error);
          res.next(false);
        }
      );
    return res.asObservable();
  }

  /**
   * 删除微博
   * @param auditWeibo 要删除的微博
   */
  DeleteWeibo(auditWeibo: AuditWeibo): Observable<boolean> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    auditWeibo.isAudited = true;
    auditWeibo.weibo.isDeleted = true;
    const res: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(null);
    this.http.put<WeiboDTO>(`/api/audit/${auditWeibo.weibo.id}`, JSON.stringify(auditWeibo), httpOptions)
      .subscribe(
        resp => { console.log(resp); res.next(resp.state === 'Success'); },
        error => {
          console.log(error);
          res.next(false);
        }
      );
    return res.asObservable();
  }

  /**
   * 解封微博
   * @param auditWeibo 要解封的微博
   */
  UnbanWeibo(auditWeibo: AuditWeibo): Observable<boolean> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    auditWeibo.isAudited = false;
    auditWeibo.weibo.isBaned = false;
    const res: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(null);
    this.http.put<WeiboDTO>(`/api/audit/${auditWeibo.weibo.id}`, JSON.stringify(auditWeibo), httpOptions)
      .subscribe(
        resp => { console.log(resp); res.next(resp.state === 'Success'); },
        error => {
          console.log(error);
          res.next(false);
        }
      );
    return res.asObservable();
  }

  /**
   * 取消删除
   * @param auditWeibo 要恢复的对象
   */
  RecoverWeibo(auditWeibo: AuditWeibo): Observable<boolean> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    auditWeibo.isAudited = false;
    auditWeibo.weibo.isDeleted = false;
    const res: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(null);
    this.http.put<WeiboDTO>(`/api/audit/${auditWeibo.weibo.id}`, JSON.stringify(auditWeibo), httpOptions)
      .subscribe(
        resp => { console.log(resp); res.next(resp.state === 'Success'); },
        error => {
          console.log(error);
          res.next(false);
        }
      );
    return res.asObservable();
  }

  /**
   * 获取微博统计信息
   */
  GetWeiboStaticInfo(): Observable<WeiboStatic[]> {
    const res: BehaviorSubject<WeiboStatic[]> = new BehaviorSubject<WeiboStatic[]>([]);
    this.http.get<WeiboStaticDTO>(`/api/weibo/count`)
      .subscribe(
        resp => {
          if (resp.state !== 'Success') {
            res.next(new Array());
          }
          res.next(resp.weiboTimeCount);
        },
        error => {
          console.log(error);
          res.next(new Array());
        }
      );
      return res;
  }
}
