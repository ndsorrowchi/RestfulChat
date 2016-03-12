/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import ViewModels.UserViewModel;
import ViewModels.UserListViewModel;
import java.util.Calendar;
import java.util.Random;

/**
 *
 * @author chiming
 */
public final class CommonUtils {
    private CommonUtils(){/* This Class Should NOT be instanciated. */}
    
    // Exception Generated Json
    public static String getErrorJSON(Exception e)
    {
        String errmsg=e.getCause()==null?e.getMessage():e.getCause().getMessage();
        String res="{\"error\":\""+e.getClass().getSimpleName()+"\",\"message\":\""+errmsg+"\"}";
        return res;
    }
    // User Customized Json
    public static String getErrorJSON(String title, String message)
    {
        String res="{\"error\":\""+title+"\",\"message\":\""+message+"\"}";
        return res;
    } 

        // User Customized Json
    public static String getSuccessJSON(String title, String message)
    {
        String res="{\"success\":\""+title+"\",\"message\":\""+message+"\"}";
        return res;
    } 
    
    //debug use only, generates a user
    public static UserViewModel getTestUser()
    {
        UserViewModel x=new UserViewModel("111@balabala.com","male","23","Ming Chi","online");
        return x;
    }
    
    //debug use only, generates a user's frienslist 
    public static UserListViewModel getTestFriendsList()
    {
        UserViewModel[] data= new UserViewModel[20];
        Random rd=new Random();
        rd.setSeed(Calendar.getInstance().get(Calendar.MILLISECOND));
        //some data
        for(int i=0;i<20;i++)
        {
            String id=String.valueOf(i+101)+"@balabala.com";
            String gender=i%2==0?"male":"female";
            String age=String.valueOf(6+rd.nextInt(80));
            String nickname="noobuser"+String.valueOf(i);
            String status=i%3==0?"online":"offline";
            
            data[i]=new UserViewModel(id,gender,age,nickname,status);
        }
        
        UserListViewModel ret=new UserListViewModel(data);
        
        return ret;
    }
}
