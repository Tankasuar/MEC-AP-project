import java.util.*;
public class haitham_task4 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        LinkedHashSet haitham_lhs = new LinkedHashSet();
         double haitham_sale=0,haitham_crc=0,haitham_tsr=0;
         char con ;
         
        do{
            System.out.println("..........................Sales Details..........................");
            System.out.println("Please chose any of the following options: ");
            System.out.println("..............................................................................");
            System.out.println("1) add details to the app");
            System.out.println("2) Delete an Element in the app ");
            System.out.println("3) Search for an Element in the app ");
            System.out.println("4) Exit ");
            int haitham_num = scan.nextInt();
            scan.nextLine();
            switch(haitham_num){
                case 1:
                    System.out.println("Enter Sale Number: ");
                     haitham_sale = scan.nextDouble();
                    System.out.println("Enter Customer Retention Score");
                    haitham_crc=scan.nextDouble();
                    System.out.println("Enter team sales revenue: ");
                   haitham_tsr=scan.nextDouble();
                    haitham_lhs.add(haitham_sale);
                    haitham_lhs.add(haitham_crc);
                    haitham_lhs.add(haitham_tsr);
                    System.out.println("The Elements are : "+haitham_lhs);
                   
                    break;
                case 2:
                    System.out.println("The elements are : "+haitham_lhs);
                    System.out.println("Element  "+haitham_crc+" will be deleted.");
                     
                    haitham_lhs.remove(haitham_crc);
                     System.out.println("The elements after deleting are : "+haitham_lhs);
                    break;
                case 3:
                     System.out.println("The elements are : "+haitham_lhs);
                     System.out.println("Does the app contains "+haitham_tsr+" ? " + haitham_lhs.contains(haitham_tsr));
                     
                    break;
                case 4:
                    System.exit(0);
                    break;

        }
            System.out.println("Do you want to continue (y/Y?");
            con=scan.next().charAt(0);
        }
        while(con == 'y' || con == 'Y');
    }
}
