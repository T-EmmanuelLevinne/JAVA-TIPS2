
         import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the day of the week: ");
        String day = input.nextLine();

        switch (day) {
            case "Monday":
            case "Tuesday":
            case "Wednesday":
            case "Thursday":
            case "Friday":
                System.out.println("It is a weekday");
                break;
            case "Saturday":
            case "Sunday":
                System.out.println("It is a Pahuway");
                break;
            default:
                System.out.println("It is not a day.");
        }

        input.close(); // Close the scanner to prevent resource leak
    }
}



       

