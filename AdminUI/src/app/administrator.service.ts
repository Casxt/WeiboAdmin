import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Router, ActivatedRouteSnapshot, RouterStateSnapshot, CanActivate } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { Administrator, AdministratorDTO } from './Administrator';
import sha256 from 'fast-sha256';
@Injectable({
    providedIn: 'root'
})
export class AdministratorService implements CanActivate {
    private administrator: Administrator = null;
    private administratorObsv: BehaviorSubject<Administrator> = new BehaviorSubject<Administrator>(null);

    constructor(private http: HttpClient, private router: Router) { }


    /**
     * 登陆操作
     * @param user 用户名
     * @param pass 密码
     */
    SignIn(user: string, pass: string): Observable<Administrator> {
        const upass = new TextEncoder().encode(pass);
        const hashUArray = sha256(upass);
        // Array.from(hashUArray).map(v => this.pad(v.toString(16), 2));

        this.http.get<AdministratorDTO>('/api/administrator/' + user + '/' + this.hexdump(hashUArray))
            .subscribe(resp => {
                if (resp.state === 'Success') {
                    const administrator = new Administrator();
                    administrator.nickname = resp.admin.nickname;
                    administrator.hashPass = resp.admin.hashPass;
                    this.administratorObsv.next(administrator);
                    this.administrator = administrator;
                } else {
                    this.administratorObsv.next(null);
                    this.administrator = null;
                }
            }, error => {
                console.log(error);
                this.administratorObsv.next(null);
                this.administrator = null;
            });
        return this.administratorObsv.asObservable();
    }

    /**
     * 返回Observable<Administrator>供订阅
     */
    GetAdministrator(): Observable<Administrator> {
        return this.administratorObsv.asObservable();
    }

    /**
     * canActivate 供route导航至signin页面使用
     * @param route 用途未知
     * @param state 用途未知
     */
    canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot
    ): Observable<boolean> | Promise<boolean> | boolean {
        if (this.administrator === null) {
            this.router.navigate(['signin']);
            return false;
        }
        return true;
    }

    /**
     * 用于Uint8Array转为HEX字符串
     * @param n 
     * @param width 
     * @param z 
     */
    pad(n: string, width: number) {
        return n.length >= width ? n : new Array(width - n.length + 1).join('0') + n;
    }

    /**
     * 用于Uint8Array转为HEX字符串
     * @param buf 
     */
    hexdump(buf: Uint8Array) {
        const hex = Array.from(buf).map(v => this.pad(v.toString(16), 2));
        return hex.join('');
    }

}
