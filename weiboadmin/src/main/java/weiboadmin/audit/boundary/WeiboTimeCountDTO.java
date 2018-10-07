/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weiboadmin.audit.boundary;

import java.util.List;
import weiboadmin.BasicDTO;

/**
 *
 * @author Surface
 */
public class WeiboTimeCountDTO extends BasicDTO {
    private List<WeiboTimeCount> weiboTimeCount;
    public WeiboTimeCountDTO(String State, String Msg, List<WeiboTimeCount> weiboTimeCount) {
        super(State, Msg);
        this.weiboTimeCount = weiboTimeCount;
    }

    public WeiboTimeCountDTO() {
    }

    public WeiboTimeCountDTO(String State, String Msg) {
        super(State, Msg);
    }

    public List<WeiboTimeCount> getWeiboTimeCount() {
        return weiboTimeCount;
    }

    public void setWeiboTimeCount(List<WeiboTimeCount> weiboTimeCount) {
        this.weiboTimeCount = weiboTimeCount;
    }
    

}
