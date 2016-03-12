/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Models.*;
import Utils.DBSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author chiming
 */
public class MyUserService {
    
    public static boolean register(UserRegisterModel urm) throws Exception 
    {
        boolean x=false;
        if(urm==null)
        {
            throw new NullPointerException();
        }
        DBSingleton instance=DBSingleton.getInstance();
        Connection con=instance.getConnection();
        String sql="insert into users(uid,gender,yob,nickname,password,status) values (?,?,?,?,?,?)";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1, urm.getEmail());
        ps.setString(2, urm.getGender());
        ps.setInt(3, urm.getYob());
        ps.setString(4, urm.getNickname());
        ps.setString(5, urm.getPassword());
        ps.setInt(6, 0);
        ps.execute();
        sql="insert into friends values(?,'robot@sis.pitt.edu'),('robot@sis.pitt.edu',?)";
        ps=con.prepareStatement(sql);
        ps.setString(1, urm.getEmail());
        ps.setString(2, urm.getEmail());
        ps.execute();
        x=true;
        return x;
    }

    public static boolean registerLogin(UserRegisterModel urm) throws Exception 
    {
        boolean x=false;
        if(urm==null)
        {
            throw new NullPointerException();
        }
        DBSingleton instance=DBSingleton.getInstance();
        Connection con=instance.getConnection();
        String sql="insert into users(uid,gender,yob,nickname,password,status) values (?,?,?,?,?,?)";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1, urm.getEmail());
        ps.setString(2, urm.getGender());
        ps.setInt(3, urm.getYob());
        ps.setString(4, urm.getNickname());
        ps.setString(5, urm.getPassword());
        ps.setInt(6, 1);
        ps.execute();
        sql="insert into friends values(?,'robot@sis.pitt.edu'),('robot@sis.pitt.edu',?)";
        ps=con.prepareStatement(sql);
        ps.setString(1, urm.getEmail());
        ps.setString(2, urm.getEmail());
        ps.execute();
        x=true;
        return x;
    }
    
    public static int follow(AddFriendModel afm) throws Exception 
    {
        int x=0;
        if(afm==null)
        {
            throw new NullPointerException();
        }
        if(afm.getFrom().equals(afm.getTo()))
        {
            return -1;// invalid input: input are the same user
        }
        DBSingleton instance=DBSingleton.getInstance();
        Connection con=instance.getConnection();
        String sql="select count(*) from users where uid=? or uid=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1, afm.getFrom());
        ps.setString(2, afm.getTo());
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            int i=rs.getInt(1);
            if(i!=2)
            {
                x=-2;// invalid input: one or more input user does not exist
            }
            else
            {
                sql="select count(*) from friends where uid=? AND fid=?";
                ps=con.prepareStatement(sql);
                ps.setString(1, afm.getFrom());
                ps.setString(2, afm.getTo());
                ResultSet rs1=ps.executeQuery();
                if(rs1.next())
                {
                    int j=rs1.getInt(1);
                    if (j==1)
                    {
                        x=0;// already followed
                    }
                    else
                    {
                        sql="insert into friends (uid,fid) values (?,?)";
                        ps=con.prepareStatement(sql);
                        ps.setString(1, afm.getFrom());
                        ps.setString(2, afm.getTo());
                        ps.executeUpdate();
                        x=1;
                    }
                }
                                
            }
                       
        }
        return x; 
    }
    
    public static UserModel getUserInfo(String id)  throws Exception 
    {
        if(id==null)
            return null;
        
        DBSingleton instance=DBSingleton.getInstance();
        Connection con=instance.getConnection();
        String sql="select uid,gender,yob,nickname,status from users where uid=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs=ps.executeQuery(); 
        if(rs.next())
        {
            UserModel um=new UserModel(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getInt(5));
            return um;
        }
        else
            return null;
    }
    
    public static UserListModel getUserFriendsList(String id) throws Exception 
    {
        if(id==null)
            return null;
        
        DBSingleton instance=DBSingleton.getInstance();
        Connection con=instance.getConnection();
        String sql="select uid,gender,yob,nickname,status from users where uid in (select fid from friends where uid=?) order by status desc";
        PreparedStatement ps=con.prepareStatement("select status from users where uid=?");
        ps.setString(1, id);
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            ArrayList<UserModel> list=new ArrayList<>();
            ps=con.prepareStatement(sql);
            ps.setString(1, id);
            rs=ps.executeQuery();
            while(rs.next())
            {
                UserModel um=new UserModel(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getInt(5));
                list.add(um);
            }
            UserModel[] data=new UserModel[list.size()];
            for(int i=0;i<list.size();i++)
            {
                data[i]=list.get(i);
            }
            return new UserListModel(data);
        }
        else
            return null;

        
    }

    
    public static UserListModel getSearchResultList(String content) throws Exception 
    {
        if(content==null)
            return null;
        
        DBSingleton instance=DBSingleton.getInstance();
        Connection con=instance.getConnection();
        String sql="select uid,gender,yob,nickname,status from users where uid=? or nickname=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1, content);
        ps.setString(2, content);
        ResultSet rs=ps.executeQuery();
        ArrayList<UserModel> list=new ArrayList<>();

        while(rs.next())
        {
            UserModel um=new UserModel(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getInt(5));
            list.add(um);
        }
        UserModel[] data=new UserModel[list.size()];
        for(int i=0;i<list.size();i++)
        {
            data[i]=list.get(i);
        }
        return new UserListModel(data);


        
    }    
    
}
