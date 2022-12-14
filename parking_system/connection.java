package parking_system;
import java.sql.*;
import static java.time.Clock.system;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class connection {
    public static Connection conn;
    
    
    public connection() {
        try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/parkingsystem", "root", "root");
        }
        catch(SQLException ex){
            Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public int addticketto_db(String plateNum, String date, String spotCode){
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select id from spots where BINARY name = '"+spotCode+"'");
            rs.next();
            
            PreparedStatement stmt2 = conn.prepareStatement("insert into ticket(platenumber, entrydate, spotid) values('"+plateNum+"','"+date+"',"+rs.getInt(1)+")", Statement.RETURN_GENERATED_KEYS);
            stmt2.executeUpdate();
            ResultSet rs2 =stmt2.getGeneratedKeys();
            rs2.next();
            return rs2.getInt(1);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }
    
    
    public String codeparkingspot(String status){
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select name from spots where status = '"+status+"' LIMIT 1");
            if(rs.next()){
                String code = rs.getString(1);
                stmt.execute("update spots set status = 'false' where BINARY name = '"+code+"'");
                return code;
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return "false";
    }
    
    
    public int getspotidfromticket(int ticketid,String platenumber){
        try{
            Statement stmt = conn.createStatement();
            ResultSet id =stmt.executeQuery("select spotid from ticket where id ='"+ticketid+"'and platenumber ='"+platenumber+"' and exitdate is null");
            id.next();
            return id.getInt(1);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }
    
    
    public boolean changespot_to_empty(int spotid){
        try{
            Statement stmt = conn.createStatement();
            return stmt.execute("update spots set status ='true' where id="+spotid+"");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    
    public boolean updatetickettime(int ticketid,String platenumber,String leavedate){
        try{
            Statement stmt = conn.createStatement();
            return stmt.execute("update ticket set exitdate = '"+leavedate+"' where id = "+ticketid+" and BINARY platenumber = '"+platenumber+"' and exitdate IS NULL");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    
    public String getentrydateticket(int ticketid){
        try{
            Statement stmt = conn.createStatement();
            ResultSet enterdate=stmt.executeQuery("select entrydate from ticket where id="+ticketid+" and exitdate is null");
            
            if(enterdate.next()){
                return enterdate.getString(1);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return "0";
    }
    
    
    public void veiwallfreespots(){
        try{
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("select id,status,name from spots where BINARY status='true'");
            System.out.println("id\t status\t name\t ");
            while(rs.next()){
                int id=rs.getInt("id");
                String status=rs.getString("status");
                String name=rs.getString("name");
                System.out.format("%s\t %s\t %s\t \n",id,status,name);

            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public int addspot(String spotcode){
        try{
            PreparedStatement stmt = conn.prepareStatement("insert into spots(status,name) values('true','"+spotcode+"')");
            int vaild = stmt.executeUpdate();
            return vaild;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }
    
    public int adduser(String username,String password,int roleid,int shiftid){
        try{
            PreparedStatement stmt=conn.prepareStatement("insert into user(username,password,roleid,shiftid) values('"+username+"','"+password+"','"+roleid+"','"+shiftid+"')");
            int vaild =stmt.executeUpdate();
            return vaild;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }
    
    public int updateroleid(String username,int roleid){
        try{
            PreparedStatement stmt=conn.prepareStatement("update user set roleid='"+roleid+"' where BINARY username='"+username+"'");
            int vaild =stmt.executeUpdate();
            return vaild;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }
    
    public int deleteuser(String username){
        try{
            PreparedStatement stmt=conn.prepareStatement("delete from user where BINARY username='"+username+"'");
            int vaild =stmt.executeUpdate();
            return vaild;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }
    
    public void viewshiftsreport(int shiftid){
        try{
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("select start,end from shifts where id='"+shiftid+"'");
            
            if(rs.next()){
                rs=stmt.executeQuery("select id,platenumber,spotid from ticket where entrydate between '"+rs.getTime(1)+"' AND '"+rs.getTime(2)+"' or exitdate between '"+rs.getTime(1)+"' AND ' "+rs.getTime(2)+"' ");
                System.out.println("id\t platenumber\t spotid");
                while(rs.next()){
                    int id=rs.getInt("id");
                    String plate=rs.getString("platenumber");
                    int spotid=rs.getInt("spotid");
                    
                    System.out.format("%s\t %s\t\t %s\t \n",id,plate,spotid);
                   
                }
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        
    }
    
    public void viewparkedreport(){
        try{
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("select id,platenumber,entrydate,exitdate,spotid from ticket where entrydate is not null and exitdate is not null");
            System.out.println("id\t platenumber\t entrydate\t exitdate\t spotid ");
            while(rs.next()){
                int id=rs.getInt("id");
                String plate=rs.getString("platenumber");
                String entrydate=rs.getString("entrydate");
                String exitdate=rs.getString("exitdate");
                int spotid=rs.getInt("spotid");
                System.out.format("%s\t %s\t\t %s\t %s\t %s\t \n",id,plate,entrydate,exitdate,spotid);

            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    }
    
    
    public void veiwallspots(){
        try{
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("select id,status,name from spots ");
            System.out.println("id\t status\t name\t ");
            while(rs.next()){
                int id=rs.getInt("id");
                String status=rs.getString("status");
                String name=rs.getString("name");
                System.out.format("%s\t %s\t %s\t \n",id,status,name);

            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    }
}
