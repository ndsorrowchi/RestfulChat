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
public class MessageListModel {
    private MessageModel[] messagelist;
    
    public MessageListModel(){}
    public MessageListModel(MessageModel[] msglist)
    {
        this.messagelist=msglist;
    }
    
    public MessageModel[] getMessagelist(){return this.messagelist;}
    public void setMessagelist(MessageModel[] msglist){this.messagelist=msglist;}    
}
