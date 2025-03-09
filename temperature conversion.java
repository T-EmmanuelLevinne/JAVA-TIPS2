import java.util.*;
 
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
      
       
        
        System.out.print("Enter the temperature: ");
        double temp = input.nextDouble();
        
        
        System.out.print("Convert to Celsius or Fahrenheit? (C or F): ");
        String unit = input.next().toUpperCase();   // we use method chaining
        
         // (condition) ? true : false
        double newtemp = (unit.equals("C")) ? (temp - 32) * 5 / 9 : (temp * 5 / 9) + 32;
        
         System.out.printf("%.1fº%s", newtemp, unit); // "%.1fº%s will print 1 digit
        
        
        
        
        
        
       
    }
}
