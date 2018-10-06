import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of, BehaviorSubject } from 'rxjs';
import { Weibo, WeiboDTO, AuditWeibo } from './weibo';
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
}
