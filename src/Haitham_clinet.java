
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

class Haitham_clinetT extends Thread{
    public void run(){
        try{
            //connecting to the server
            Socket socket = new Socket("localhost",3326);
            Scanner scan = new Scanner(System.in);
            
            
            //declaring and assigning the values
            System.out.print("Enter the sale revenue: ");
            double haitham_sale = scan.nextDouble();
            if(haitham_sale<0){
                System.out.print("the sale value is negetive. Please enter a postive value: ");
                haitham_sale = scan.nextDouble();
            }
            System.out.print("Enter the customer retention score: ");
            double haitham_crs = scan.nextDouble();
             if(haitham_crs<0){
                System.out.print("the customer retention score value is negetive. Please enter a postive value: ");
                haitham_crs = scan.nextDouble();
            }
            System.out.print("Enter the team sales revenue");
            double haitham_tsr = scan.nextDouble();
            if(haitham_tsr<0){
                System.out.print("the team sales revenue value is negetive. Please enter a postive value: ");
                haitham_tsr = scan.nextDouble();
            }
            
            
            //sending data to the server
            DataOutputStream haitham_dos = new DataOutputStream(socket.getOutputStream());
            haitham_dos.writeDouble(haitham_sale);
            haitham_dos.writeDouble(haitham_crs);
            haitham_dos.writeDouble(haitham_tsr);
            
            
            //reciving the data back form the server
            DataInputStream haitham_dis = new DataInputStream(socket.getInputStream());
            double haitham_com = haitham_dis.readDouble();
            double haitham_commission = haitham_dis.readDouble();
            double haitham_total_bonus = haitham_dis.readDouble();
            double haitham_total_pay = haitham_dis.readDouble();
            
            
            //displaying the result
            System.out.println("the commission before adding the preformance scaling factor = " + haitham_com);
            System.out.println("the commission after adding the preformance scaling factor = " + haitham_commission);
            System.out.println("the bonus that will be added to the commission  = " + haitham_total_bonus);
            System.out.println("Total commission  = " + haitham_total_pay);
            
            
            //closing the connection
            scan.close();
            haitham_dos.close();
            haitham_dis.close();
            socket.close();
    }
        catch(Exception e){
            System.out.println(e);
    }
    }
}
public class Haitham_clinet {
    public static void main(String[] args) {
        Haitham_clinetT T1 = new Haitham_clinetT();
        T1.start();
        
    }
}
