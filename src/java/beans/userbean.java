/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;

/**
 *
 * @author chiming
 */
public class userbean extends Object implements Serializable{
    private String uid;
    private String uname;
    public userbean()
    {
        uid=uname=null;
    }
    public userbean(String uid, String uname)
    {
        this.uid=uid;
        this.uname=uname;
    }    
    public String getUid(){return uid;}
    public void setUid(String uid){this.uid=uid;}
    
    public String getUname(){return uname;}
    public void setUname(String uname){this.uname=uname;}    
}
