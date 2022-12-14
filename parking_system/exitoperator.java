package parking_system;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.concurrent.TimeUnit;
import parking_system.connection;

public class exitoperator {
    public double parkingHours(int ticketid) throws ParseException{
        if(ticketid<0){
            return 0;
        }
        
        
        SimpleDateFormat formatter=new SimpleDateFormat("HH:mm:ss");
        String date11 = formatter.format(new Date());
        
        connection conn = new connection();
        String enterdate = conn.getentrydateticket(ticketid);
        
        if(enterdate == "0")
            return -1;
        Date date1 = formatter.parse(date11);
        Date date2 = formatter.parse(enterdate);
        double diff = Math.abs(date1.getTime() - date2.getTime());
        double differenceInHours = (diff / (60.0 * 60.0 * 1000.0))% 24;
        return differenceInHours;
    }
}
