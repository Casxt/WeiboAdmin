/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weiboadmin;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Surface
 */
@XmlRootElement
public class BasicDTO {


    private String State;
    private String Msg;
    private String Detail;
    
    public BasicDTO() {
    }
    public BasicDTO(String State, String Msg) {
        this.State = State;
        this.Msg = Msg;
    }
    
    public BasicDTO(String State, String Msg, String Detail) {
        this.State = State;
        this.Msg = Msg;
        this.Detail = Detail;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String Detail) {
        this.Detail = Detail;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

}
