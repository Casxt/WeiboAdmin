/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weiboadmin.audit.boundary;

import java.util.Date;
import java.util.List;
import weiboadmin.BasicDTO;

/**
 *
 * @author Surface
 */
public class WeiboTimeCount {
    
    private int count;
    private Date time;
    
    public WeiboTimeCount() {
    }

    public WeiboTimeCount(int count, Date time) {
        this.count = count;
        this.time = time;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
