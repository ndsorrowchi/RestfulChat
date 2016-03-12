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
public class UserRegisterViewModel {
    private String email;
    private String password;
    private String nickname;
    private String yob;
    private String gender;
    
    public UserRegisterViewModel(){}
    public UserRegisterViewModel(String email, String password, String nickname, String yob, String gender)
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
    
    public String getYob(){return this.yob;}
    public void setYob(String yob){this.yob=yob;}
       
    public String getGender(){return this.gender;}
    public void setGender(String gender){this.gender=gender;}
       
}

