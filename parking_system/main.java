package parking_system;
import java.util.Scanner;
import java.sql.SQLException;
import java.text.ParseException;
import static java.time.Clock.system;

public class main {
    public static void main(String[] args) throws ParseException, SQLException{
        Scanner input= new Scanner(System.in);
        entryoperator ep=new entryoperator();
        customer cu=new customer();
        admin ad=new admin();
        int con= 1;
        int con2=1;    
        
        System.out.println("1 for Customer");
        System.out.println("2 for Admin");
        System.out.println("who are you :");   
        int x=input.nextInt();

        if(x==1){
            while(con==1){
                System.out.println("1 for Veiw free spots");
                System.out.println("2 for Print Ticket");
                System.out.println("3 for pay");
                System.out.println("What do you want to do:");
                int z=input.nextInt();
                if(z==1){
                    ep.veiwFreespot();
                    System.out.println("do you want to continue in customer part?");
                    System.out.println("1 for yes");
                    System.out.println("2 for no");
                    con=input.nextInt();

                }

                else if(z==2){
                    System.out.println("Enter your platenumber :");
                    String plate=input.next();


                    String ticket=cu.printticket(plate);
                    System.out.println(ticket);

                    System.out.println("do you want to continue in customer part?");
                    System.out.println("1 for yes");
                    System.out.println("2 for no");
                    con=input.nextInt();
                }

                else if(z==3){
                    System.out.println("Enter yor Ticketid :");
                    int tic_id=input.nextInt();

                    System.out.println("Enter your platenumber :");
                    String plate=input.next();

                    double cost =cu.pay(tic_id, plate);
                    System.out.println(cost);

                    System.out.println("do you want to continue in customer part?");
                    System.out.println("1 for yes");
                    System.out.println("2 for no");
                    con=input.nextInt();
                }

                else{
                    System.out.println("Sorry we haven't this option....!");
                }
            }    
        }

        else if(x==2){
            System.out.println("Enter admin username :");
            String admin_username =input.next();

            System.out.println("Enter admin password");
            String admin_password=input.next();

            checkadminlogin ch=new checkadminlogin(admin_username,admin_password);
            if(ch.check()){
                while(con2==1){
                    System.out.println("1 for Add spots");
                    System.out.println("2 for Add user");
                    System.out.println("3 for Update user");
                    System.out.println("4 for Delete user");
                    System.out.println("5 for Veiw All Spots");
                    System.out.println("6 for Veiw shift rebort");
                    System.out.println("7 for Veiw parked cars");
                    System.out.println("Enter  what do you want :");
                    int a=input.nextInt();

                    if(a==1){
                        System.out.println("Enter Spotcode :");
                        String spotcode=input.next();
                        System.out.println("Adding is Done..!");
                        
                        ad.Addspot(spotcode);
                        System.out.println("\ndo you want to continue in admin part?");
                        System.out.println("1 for yes");
                        System.out.println("2 for no");
                        con2=input.nextInt();
                    }

                    else if(a==2){
                        System.out.println("Enter username :");
                        String username=input.next();
                        System.out.println("Enter password :");
                        String password=input.next();
                        System.out.println("Enter roleid from 1 to 3 :");
                        int roleid=input.nextInt();
                        System.out.println("Enter shifid from number 1 to 4");
                        int shiftid=input.nextInt();

                        ad.Adduser(username, password, roleid, shiftid);
                        System.out.println("Adding is Done..!");
                        
                        System.out.println("\ndo you want to continue in admin part?");
                        System.out.println("1 for yes");
                        System.out.println("2 for no");
                        con2=input.nextInt();
                    }

                    else if(a==3){
                        System.out.println("enter username :");
                        String username=input.next();

                        System.out.println("enter new roleid :");
                        int roleid=input.nextInt();

                        ad.updateuser(username, roleid);
                        System.out.println("Updating is Done..!");
                        
                        System.out.println("\ndo you want to continue in admin part?");
                        System.out.println("1 for yes");
                        System.out.println("2 for no");
                        con2=input.nextInt();
                    }

                    else if(a==4){
                        System.out.println("enter username :");
                        String username=input.next();

                        ad.deleteuser(username);
                        System.out.println("Deleting is Done..!");
                        
                        System.out.println("\ndo you want to continue in admin part?");
                        System.out.println("1 for yes");
                        System.out.println("2 for no");
                        con2=input.nextInt();
                    }

                    else if(a==5){
                        ad.Veiwallspots();
                        System.out.println("\ndo you want to continue in admin part?");
                        System.out.println("1 for yes");
                        System.out.println("2 for no");
                        con2=input.nextInt();
                    }

                    else if(a==6){
                        System.out.println("Enter shifid from number 1 to 4");
                        int shiftid=input.nextInt();

                        ad.Veiwshiftrebort(shiftid);
                        System.out.println("\ndo you want to continue in admin part?");
                        System.out.println("1 for yes");
                        System.out.println("2 for no");
                        con2=input.nextInt();
                    }

                    else if(a==7){
                        ad.Veiwparkedcars();
                        System.out.println("\ndo you want to continue in admin part?");
                        System.out.println("1 for yes");
                        System.out.println("2 for no");
                        con2=input.nextInt();
                    }

                    else{
                        System.out.println("Sorry we haven't this option....!");
                    }
                }
            }

            else{
                System.out.println("Sorry you cann't enter admin...!");
            }
        }

        else {
            System.out.println("we hadn't know who you are....!");
        }
    }
}
