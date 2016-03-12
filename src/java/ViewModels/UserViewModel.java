/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModels;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author chiming
 */
@XmlRootElement
public class UserViewModel {
    @XmlElement
    private String id;
    @XmlElement
    private String gender;
    @XmlElement
    private String age;
    @XmlElement
    private String nickname;
    @XmlElement
    private String status;
    
    public UserViewModel(){}
    public UserViewModel(String id, String gender, String age, String nickname, String status)
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
    
    public String getAge(){return age;}
    public void setAge(String age){this.age=age;}
    
    public String getNickname(){return nickname;}
    public void setNickname(String nickname){this.nickname=nickname;}
    
    public String getStatus(){return status;}
    public void setStatus(String status){this.status=status;}
}
