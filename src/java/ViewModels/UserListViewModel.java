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
public class UserListViewModel {
    @XmlElement(name="userslist")
    private UserViewModel[] friendslist;
    
    public UserListViewModel(){}
    public UserListViewModel(UserViewModel[] data) {friendslist=data;}
    
    public UserViewModel[] getFriendslist() {return this.friendslist;}
    public void setFriendslist(UserViewModel[] data) {this.friendslist=data;}
}
