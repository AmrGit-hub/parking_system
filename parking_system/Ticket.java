package parking_system;
import java.text.SimpleDateFormat;
import java.util.Date;
import parking_system.connection;

public class Ticket {
    private int id;
    private String platenumber;
    private String entrydata;
    private String soptcode;
    
    public Ticket(String platenumber,String spotcode){
        this.platenumber=platenumber;
        this.soptcode=spotcode;
        SimpleDateFormat formater=new SimpleDateFormat("HH:mm:ss");
        this.entrydata=formater.format(new Date());
        connection conn=new connection();
        this.id = conn.addticketto_db(this.platenumber, this.entrydata, this.soptcode);
    }
    
    public String tostring(){
        return "the ID is "+this.id+"\n"
                +"the platenumber is "+this.platenumber+"\n"
                +"the entrydata is "+this.entrydata+"\n"
                +"the spotcode is "+this.soptcode+"\n";
        
    }
}
