/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import ViewModels.*;
import Models.*;
import java.util.Calendar;

/**
 *
 * @author chiming
 */
public final class ConvertModels {
    private ConvertModels(){/* This Class Should NOT be instanciated. */}
    
    public static UserModel view2db(UserViewModel uvm)
    {
        if(uvm.getAge()==null)
            return null;
        if(uvm.getGender()==null)
            return null;
        if(uvm.getId()==null)
            return null;
        if(uvm.getNickname()==null)
            return null;
        if(uvm.getStatus()==null)
            return null;
        int age=-1;
        try{
        age=Integer.valueOf(uvm.getAge());
                }
        catch(NumberFormatException e)
        {
            return null;
        }
        UserModel um=new UserModel(uvm.getId(),uvm.getGender(),age,uvm.getNickname(),uvm.getStatus().equals("online")?1:0);
        return um;
    }

    public static AddFriendModel view2db(AddFriendViewModel afvm)
    {
        if(afvm==null)
            return null;
        if(afvm.getFrom()==null)
            return null;
        if(afvm.getTo()==null)
            return null;
        return new AddFriendModel(afvm.getFrom(),afvm.getTo());
    }
        
    public static UserRegisterModel view2db(UserRegisterViewModel urvm)
    {
        if(urvm.getEmail()==null)
            return null;
        if(urvm.getGender()==null)
            return null;
        if(urvm.getYob()==null)
            return null;
        if(urvm.getPassword()==null)
            return null;
        if(urvm.getNickname()==null)
            return null;
        int yob=-1;
        try{
        yob=Integer.valueOf(urvm.getYob());
                }
        catch(NumberFormatException e)
        {
            return null;
        }
        UserRegisterModel urm=new UserRegisterModel(urvm.getEmail(),urvm.getPassword(),urvm.getNickname(),yob,urvm.getGender());
        return urm;
    }

    public static MessageModel view2db(MessageViewModel mvm)
    {
        if(mvm.getFrom()==null)
            return null;
        if(mvm.getTo()==null)
            return null;
        MessageModel mm=new MessageModel(mvm.getFrom(),mvm.getTo(),mvm.getMessage(),mvm.getTime());
        return mm;
    }    
    
    public static UserViewModel db2view(UserModel um)
    {
        if(um==null)
            return null;
        String status=um.getStatus()>0?"online":"offline";
        int age=Calendar.getInstance().get(Calendar.YEAR)-um.getAge();
        return new UserViewModel(um.getId(),um.getGender(),String.valueOf(age),um.getNickname(),status);
    }
    
    public static UserListViewModel db2view(UserListModel ulm)
    {
        if(ulm==null)
            return null;
        if(ulm.getFriendslist()==null)
            return null;
        int len=ulm.getFriendslist().length;
        UserViewModel[] list=new UserViewModel[len];
        for(int i=0;i<len;i++)
        {
            list[i]=db2view(ulm.getFriendslist()[i]);
        }
        return new UserListViewModel(list);
    }

    public static MessageViewModel db2view(MessageModel mm)
    {
        if(mm==null)
            return null;
        return new MessageViewModel(mm.getFrom(),mm.getTo(),mm.getMessage(),mm.getTime());
    }
    
    public static MessageListViewModel db2view(MessageListModel mlm)
    {
        if(mlm==null)
            return null;
        if(mlm.getMessagelist()==null)
            return null;
        int len=mlm.getMessagelist().length;
        MessageViewModel[] list=new MessageViewModel[len];
        for(int i=0;i<len;i++)
        {
            list[i]=db2view(mlm.getMessagelist()[i]);
        }
        return new MessageListViewModel(list);
    }
}
