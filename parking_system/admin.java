package parking_system;

public class admin {
    public boolean Addspot(String Spotcode){
        if(!Spotcode.isEmpty()){
            connection conn=new connection();
            int done=conn.addspot(Spotcode);
            if(done>0)
                return true;
        }
        return false;
    }
    
    public void Veiwallspots(){
        connection conn=new connection();
        conn.veiwallspots();
    }
    
    public boolean Adduser(String username,String password,int roleid,int shiftid){
        if(!username.isEmpty() && !password.isEmpty()){
            connection conn=new connection();
            int done=conn.adduser(username, password, roleid, shiftid);
            if(done>0)
                return true;
        }
        return false;
    }
    
    public boolean updateuser(String username,int roleid){
        if(!username.isEmpty()){
            connection conn=new connection();
            int done=conn.updateroleid(username, roleid);
            if(done>0)
                return true;
        }
        return false;
    }
    
    public boolean deleteuser(String username){
        if(!username.isEmpty()){
            connection conn=new connection();
            int done=conn.deleteuser(username);
            if(done>0)
                return true;
        }
        return false;
    }
    
    public void Veiwshiftrebort(int shiftid){
        connection conn=new connection();
        conn.viewshiftsreport(shiftid);
    }
    
    public void Veiwparkedcars(){
        connection conn=new connection();
        conn.viewparkedreport();
    }
}
