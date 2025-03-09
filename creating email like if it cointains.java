import java.util.*;

public class Main {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        
        String email;
        String name;
        String domain;
        
        System.out.println("Enter your Email:");
        email = input.nextLine();
        
        // Check if email contains valid domains
        if (email.contains("@gmail.com") || email.contains("@yahoo.com") || email.contains("@ymail.com")) {
            name = email.substring(0, email.indexOf("@")); // Extract username before '@'
            domain = email.substring(email.indexOf("@"));  // Extract domain after '@'
            
            System.out.println("Username: " + name);
            System.out.println("Email: " + domain);
        } else {
            System.out.println("Invalid user email.");
        }
        
        input.close(); // Close Scanner to avoid memory leaks
    }
}
