/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModels;
/**
 *
 * @author chiming
 */

public class MessageViewModel {
    private String from;
    private String to;
    private String message;
    private long time;
   
    public MessageViewModel(){}
    public MessageViewModel(String from, String to, String message, long time)
    {
        this.from=from;
        this.to=to;
        this.message=message;
        this.time=time;
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
