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
public class UserModel {

    private String id;
    private String gender;
    private int age;
    private String nickname;
    private int status;
    
    public UserModel(){}
    public UserModel(String id, String gender, int age, String nickname, int status)
    {
        this.id=id;
        this.gender=gender;
        this.age=age;
        this.nickname=nickname;
        this.status=status;
    }
/* get{...},  set{...} */    
    public String getId() {return id;}
    public void setId(String id) {this.id=id;}
    
    public String getGender() {return gender;}
    public void setGender(String gender) {this.gender=gender;}
    
    public int getAge(){return age;}
    public void setAge(int age){this.age=age;}
    
    public String getNickname(){return nickname;}
    public void setNickname(String nickname){this.nickname=nickname;}
    
    public int getStatus(){return status;}
    public void setStatus(int status){this.status=status;}    
}
