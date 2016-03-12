/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModels;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chiming
 */
@XmlRootElement
public class MessageListViewModel {
    @XmlElement(name="msglist")
    private MessageViewModel[] messagelist;
    
    public MessageListViewModel(){}
    public MessageListViewModel(MessageViewModel[] msglist)
    {
        this.messagelist=msglist;
    }
    
    public MessageViewModel[] getMessagelist(){return this.messagelist;}
    public void setMessagelist(MessageViewModel[] msglist){this.messagelist=msglist;}
}
