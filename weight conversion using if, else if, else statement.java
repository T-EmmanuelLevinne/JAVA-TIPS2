import java.util.*;

// Create a weight conversion program
// This program is not a while and switch, and it does not contains '' default '' and it does not loop.
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double weight;  // hiii this will actually Stores user input weight

        System.out.println("Weight Conversion Program");
        System.out.println("1. Convert lbs to kg");
        System.out.println("2. Convert kgs to lbs");
       System.out.println("Choose an option: ");
       int choice = input.nextInt(); 
       
       if (choice == 1) { //option 1 kanang 1. diha
           System.out.println("Enter weight to lbs:");
           weight = input.nextDouble();
           double newweight = weight * 0.453592;
           System.out.printf("Weight in lbs: " + newweight);
           
           
       }   else if (choice == 2) { //option 2 diha
           System.out.println("Enter to lbs:");
           weight = input.nextDouble();
           double newweight = weight * 2.20462;
           System.out.printf("Kgs to lbs: " + newweight);
           
       } else {
           System.out.println("Invalid Option");
       }
       
    
       
    }
}
