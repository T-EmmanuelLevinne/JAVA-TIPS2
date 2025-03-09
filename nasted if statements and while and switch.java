import java.util.*;

public class Main {
    public static void main(String[] args) {
        
     HashMap<String, String> temperature3 = new HashMap<>();
        Scanner input = new Scanner(System.in);
        
        
        double temperature = 24.0;
        
        boolean ac1 = false;
        
        while (true) {
            System.out.println("\n1. Turn on AC");
            System.out.print("Choose an option: ");
            int choice = input.nextInt();
            
            if (choice == 1) {
                ac1 = true;
                break;
            } else {
                System.out.println("Invalid option. Please choose again.");
            }
        }
        
        while (ac1) {
            System.out.print("\nAircon");
            System.out.println("\n----------------------");
            System.out.println("1. Current Temperature");
            System.out.println("2. Increase Heat Room Temperature");
            System.out.println("3. Decrease Heat Room Temperature");
            System.out.println("4. Turn off AC");
            System.out.print("Choose an option: ");
            int tempchanger = input.nextInt();
            
            switch (tempchanger) {
                case 1:
                    System.out.println("Your room temperature: " + temperature + "°C");
                    break;
                
                case 2:
                    System.out.println("Increase Temperature to: ");
                    double increasetemp = input.nextDouble();
                    
                    if (increasetemp >= 16 && increasetemp <= 30) {
                        if (temperature <= increasetemp) {
                            temperature = increasetemp;
                            System.out.println("Your current temperature: " + temperature + "°C");
                        } else {
                            System.out.println("You are decreasing the temperature, Invalid.");
                        }
                    } else {
                        System.out.println("Temperature must be between 16°C and 30°C.");
                    }
                    break;
                
                case 3: 
                    System.out.println("Decreasing Temperature to: ");
                    double decreasetemp = input.nextDouble();
                    
                    if (decreasetemp >= 16 && decreasetemp <= 30) {
                        if (temperature >= decreasetemp) {
                            temperature = decreasetemp;
                            System.out.println("Your current temperature: " + temperature + "°C");
                        } else {
                            System.out.println("You are increasing the temperature, Invalid.");
                        }
                    } else {
                        System.out.println("Temperature must be between 16°C and 30°C.");
                    }
                    break;
                
                case 4:
                    System.out.println("Turning off.");
                    ac1 = false;
                    break;
                
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }
}
