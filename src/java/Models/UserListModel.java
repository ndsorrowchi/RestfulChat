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
public class UserListModel {
    private UserModel[] friendslist;
    
    public UserListModel(){}
    public UserListModel(UserModel[] data) {friendslist=data;}
    
    public UserModel[] getFriendslist() {return this.friendslist;}
    public void setFriendslist(UserModel[] data) {this.friendslist=data;}    
}
