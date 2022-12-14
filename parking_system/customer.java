package parking_system;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import parking_system.connection;

public class customer {
    private String platenumber;
    public Ticket t;
    
    
    public customer(String platenumber){
        this.platenumber=platenumber;
    }
    
    
    /*this function to get code of spot that was empty*/
    private String getspot(){
        connection conn = new connection();
        String code = conn.codeparkingspot("true");
        return code;
    }
    
    
    /*this function to print ticket to user*/
    public String printticket(){
        if(!platenumber.isEmpty()&&platenumber.length()<12){
            String spotcode=this.getspot();
            t=new Ticket(platenumber,spotcode);/*ticket class*/
            return t.tostring();
        }
        return "!Process faild....!";
    }
    
    
    /*this function to pay the cost of the time that you spent in parking*/
    public long pay(int ticketid,String platenumber) throws ParseException{
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String leavedate=formater.format(new Date());/*leavedate*/
        
        connection conn = new connection();
        int checkspotid = conn.getspotidfromticket(ticketid,platenumber);/*this will retutn spotid from ticket*/
        
        if(checkspotid>0){
            /*declare eo object from class exitoperator*/
            exitoperator eo = new exitoperator();
            long cost = 5 * eo.parkingHours(ticketid);/*this function to return the differant between enter and exit time and multiplay it with the cost*/
            
            conn.changespot_to_empty(checkspotid);/*this function will return the spot with spotid to be empty*/
            conn.updatetickettime(ticketid,platenumber,leavedate);/*this will update ticket by adding leaving time and date*/
            return cost;

        }
        return -1;
    }
}
