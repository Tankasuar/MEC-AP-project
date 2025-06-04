
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class haitham_serverT extends Thread{
    public void run(){
        try{
            //declaring the variables
            double  charge_rate=0;
            double sale_bonus=0;
            String chargee="";
            int bonuss=0;
            double haitham_commission=0;
            double haitham_total_bonus=0;
            double Performance=0;
            //connect to the database created in task 1
            String haitham_url="jdbc:derby://localhost:1527/ooredooo";
            String haitham_user="haitham";
            String haitham_pass="1234";
            Connection haitham_con = DriverManager.getConnection(haitham_url,haitham_user,haitham_pass);
            System.out.println("connection ready");
            //Server socket and socket
            ServerSocket ser = new ServerSocket(3326);
            while(true){
            Socket ss = ser.accept();
                System.out.println("Server is Running");
             //recive data from clinet
            DataInputStream haitham_dis = new DataInputStream(ss.getInputStream());
            double haitham_sale = haitham_dis.readDouble();
            double haitham_crs = haitham_dis.readDouble();
            double haitham_tsr = haitham_dis.readDouble();
            //calculating the charge rate for the sale
            if(haitham_sale>25000){
                chargee = "charge_rate1";
            }else if(haitham_sale>=15000 && haitham_sale <=25000){
                chargee="charge_rate2";
            }else if(haitham_sale<15000){
                chargee="charge_rate1";
            }
            //calculating customer retention score
            if( haitham_crs>80){
                bonuss=bonuss+200;
            }else if( haitham_crs>=60 &&  haitham_crs<=80){
                bonuss=bonuss+100;
            }else if(haitham_crs<60){
                System.out.println("no bonus added");
            }
            //find the customer in the database
            Statement smt = haitham_con.createStatement();
            String sql = "select * from CHARGE where ID= '" + chargee+ "'";
            smt.executeQuery(sql);
            ResultSet rs = smt.getResultSet();
            //getting the correct charege rate form the database
            if(rs.next()){
                 charge_rate = rs.getDouble("RATE");
                 sale_bonus=rs.getDouble("bonus");
            }else{
                System.out.println("id not found");
            }
            //calculating the preformance based in the team sale score
            if(haitham_tsr>200000){
                Performance=1.1;
            }else if(haitham_tsr>=100000 && haitham_tsr<=200000){
                Performance=1.05;
            }else if(haitham_tsr<100000){
                Performance=1;
            }
            //calculating the commission before and after the preformance and adding the bonus
            double haitham_com=haitham_sale*charge_rate;
            haitham_commission = haitham_sale*charge_rate*Performance;
            haitham_total_bonus=sale_bonus+bonuss;
            double total_pay= haitham_commission+haitham_total_bonus;
             
            //sending the data back to the clinet
            DataOutputStream haitham_dos = new DataOutputStream(ss.getOutputStream());
            haitham_dos.writeDouble(haitham_com);
            haitham_dos.writeDouble(haitham_commission);
            haitham_dos.writeDouble(haitham_total_bonus);
            haitham_dos.writeDouble(total_pay);
            //closing the conmnection
            haitham_dos.close();
            haitham_dis.close();
            smt.close();
            ss.close();
        }
           
            
            
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
public class haitham_server {
    public static void main(String[] args) {
        haitham_serverT T2 = new haitham_serverT();
        T2.start();
    }
}
