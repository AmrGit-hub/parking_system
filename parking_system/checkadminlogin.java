package parking_system;

import java.sql.SQLException;

public class checkadminlogin {
    private String username;
    private String password;
    
    public checkadminlogin(String username,String password){
        this.username=username;
        this.password=password;
    }
    
    public boolean check() throws SQLException{
        connection conn=new connection();
        boolean result=conn.checkadmin(this.username,this.password);
        return result;
    }
}
