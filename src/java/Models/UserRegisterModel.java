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
public class UserRegisterModel {
    private String email;
    private String password;
    private String nickname;
    private int yob;
    private String gender;
    
    public UserRegisterModel(){}
    public UserRegisterModel(String email, String password, String nickname, int yob, String gender)
    {
        this.email=email;
        this.password=password;
        this.nickname=nickname;
        this.yob=yob;
        this.gender=gender;
    }
    
    // get {...} set {...}
    public String getEmail(){return this.email;}
    public void setEmail(String email){this.email=email;}
    
    public String getPassword(){return this.password;}
    public void setPassword(String password){this.password=password;}
    
    public String getNickname(){return this.nickname;}
    public void setNickname(String nickname){this.nickname=nickname;}
    
    public int getYob(){return this.yob;}
    public void setYob(int yob){this.yob=yob;}
       
    public String getGender(){return this.gender;}
    public void setGender(String gender){this.gender=gender;}
           
}
