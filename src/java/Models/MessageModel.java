/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author chiming
 */
public class MessageModel {
    private String from;
    private String to;
    private long time;
    private String message;
    
    public MessageModel(){}
    public MessageModel(String fromid, String toid, String msg, long timestamp)
    {
        from=fromid;
        to=toid;
        message=msg;
        time=timestamp;
    }
    
    public String getFrom(){return from;}
    public void setFrom(String fromid){from=fromid;}
    
    public String getTo(){return to;}
    public void setTo(String toid){to=toid;}
    
    public String getMessage(){return message;}
    public void setMessage(String msg){message=msg;}
        
    public long getTime(){return time;}
    public void setTime(long timestamp){time=timestamp;}
}
