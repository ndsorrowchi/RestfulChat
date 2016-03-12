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
public class MyChatService {
    public static String post(MessageModel mm) throws Exception
    {
        if(mm==null)
            return null;
        if(mm.getMessage()==null || mm.getMessage().replaceAll("\\s+", "").equals(""))// should not be a blank
            return null;
        DBSingleton instance=DBSingleton.getInstance();
        Connection con=instance.getConnection();
        PreparedStatement ps=con.prepareStatement("select count(*) from users where (uid=? or uid=?)");
        ps.setString(1, mm.getFrom());
        ps.setString(2, mm.getTo());
        ResultSet rs=ps.executeQuery();
        if(rs.next()&&rs.getInt(1)==2)//make sure two users exist in db.
        {
            String sql="insert into post (fromid, toid, msg, ptime) values(?,?,?,?)";
            if(mm.getTo().equals("robot@sis.pitt.edu"))//robot auto reply function
            {
                sql="insert into post (fromid, toid, msg, ptime) values(?,?,?,?),('robot@sis.pitt.edu',?,'Robot auto reply',?)";
                ps=con.prepareStatement(sql);
                ps.setString(1, mm.getFrom());
                ps.setString(2, mm.getTo());
                ps.setString(3, mm.getMessage());
                ps.setLong(4, mm.getTime());
                ps.setString(5, mm.getFrom());
                ps.setLong(6, System.currentTimeMillis());                 
            }
            else
            {
                ps=con.prepareStatement(sql);
                ps.setString(1, mm.getFrom());
                ps.setString(2, mm.getTo());
                ps.setString(3, mm.getMessage());
                ps.setLong(4, mm.getTime());
            }
            ps.executeUpdate();

            return "{\"success\":\"post successfully submitted\"}";
        }
        else
            return null;
    }
    
    public static MessageListModel update(MessageModel mm) throws Exception
    {
        if(mm==null)
            return null;
        DBSingleton instance=DBSingleton.getInstance();
        Connection con=instance.getConnection();
        String sql="select fromid, toid, msg, ptime from post where ((fromid=? and toid=?) or (fromid=? and toid=?)) and (ptime>?) order by ptime asc";
        PreparedStatement ps=con.prepareStatement("select count(*) from users where (uid=? or uid=?)");
        ps.setString(1, mm.getFrom());
        ps.setString(2, mm.getTo());
        ResultSet rs=ps.executeQuery();
        if(rs.next()&&rs.getInt(1)==2)//make sure two users exist in db.
        {
            ps=con.prepareStatement(sql);
            ps.setString(1, mm.getFrom());
            ps.setString(2, mm.getTo());
            ps.setString(3, mm.getTo());
            ps.setString(4, mm.getFrom());
            ps.setLong(5, mm.getTime());
            rs=ps.executeQuery();
            
            ArrayList<MessageModel> list=new ArrayList<>();

            while(rs.next())
            {
                MessageModel temp=new MessageModel(rs.getString(1),rs.getString(2),rs.getString(3),rs.getLong(4));
                list.add(temp);
            }
            MessageModel[] data=new MessageModel[list.size()];
            for(int i=0;i<list.size();i++)
            {
                data[i]=list.get(i);
            }
            return new MessageListModel(data);
        }
        else
            return null;
    }
    
}
