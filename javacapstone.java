import java.io.*;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    static final String STAT_FILE = "statistics.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       Random random = new Random(); 
// Creates a new Random object to generate random numbers (for the secret number in the game)
HashMap<String, String> statistics = loadStatistics(); 
// Loads previously saved statistics from the file into a HashMap (key-value pairs as Strings)
int totalGamesPlayed = Integer.parseInt(statistics.getOrDefault("Games Played", "0")); 
// Gets the number of games played from the statistics (or "0" if not found) and converts it from String to int
int gamesWon = Integer.parseInt(statistics.getOrDefault("Games Won", "0")); 
// Gets the number of games won from the statistics (or "0" if not found) and converts it to int
int totalGuesses = Integer.parseInt(statistics.getOrDefault("Total Guesses", "0")); 
// Gets the total number of guesses from the statistics (or "0" if not found) and converts it to int

        boolean continueProgram = true;

        System.out.println("=== NUMBER GUESSING GAME ===");

        while (continueProgram) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Check Statistics");
            System.out.println("2. Play Game");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1-3): ");

          int choice = -1; 
// Initializes the variable 'choice' to -1. This is a placeholder for the user's menu choice.

if (scanner.hasNextInt()) { 
    // Checks if the next input from the user is an integer (e.g., 1, 2, 3, etc.).
    
    choice = scanner.nextInt(); 
    // If the input is an integer, it stores the value in the 'choice' variable.
} else {
    scanner.next(); // consume invalid input 
    // If the input is not an integer (e.g., a letter or special character), this discards the invalid input.
    // This prevents the program from getting stuck and allows the user to input a valid number next time.
}

                      // SWITCH  
            switch (choice) {
                case 1:
                    System.out.println("\n--- GAME STATISTICS ---");
                   if (statistics.isEmpty()) {
                        System.out.println("No game played yet.");
                    } else {
                        for (String key : statistics.keySet()) {
                            System.out.println(key + ": " + statistics.get(key));
                        }
                    }
                    break;

                case 2:
                    // Difficulty Setup
                    int minNumber = 1, maxNumber = 100, maxAttempts = 10;
                    int difficultyLevel = 0;

                    System.out.println("\nSelect difficulty level:");
                    System.out.println("1. Easy (1-50, 15 attempts)");
                    System.out.println("2. Medium (1-100, 10 attempts)");
                    System.out.println("3. Hard (1-200, 8 attempts)");
                    System.out.println("4. Expert (1-500, 5 attempts)");

                    while (difficultyLevel < 1 || difficultyLevel > 4) {
                        System.out.print("Enter your choice (1-4): ");
                        if (scanner.hasNextInt()) {
                            difficultyLevel = scanner.nextInt();
                            switch (difficultyLevel) {
                                case 1: maxNumber = 50; maxAttempts = 15; break;
                                case 2: maxNumber = 100; maxAttempts = 10; break;
                                case 3: maxNumber = 200; maxAttempts = 8; break;
                                case 4: maxNumber = 500; maxAttempts = 5; break;
                                default:
                                    System.out.println("Inva3lid choice! Please select 1-4.");
                                    difficultyLevel = 0;
                            }
                        } else {
                            System.out.println("Invalid input!");
                            scanner.next();
                        }
                    }

                    int secretNumber = random.nextInt(maxNumber - minNumber + 1) + minNumber;
                    System.out.println("\n--- Game Started ---");
                    System.out.println("I'm thinking of a number between " + minNumber + " and " + maxNumber);
                    System.out.println("You have " + maxAttempts + " attempts to guess it.");

                    int attempts = 0;
                    boolean hasWon = false;

                    while (attempts < maxAttempts && !hasWon) {
                        System.out.print("\nAttempt " + (attempts + 1) + "/" + maxAttempts + ": Enter your guess: ");
                        if (scanner.hasNextInt()) {
                            int guess = scanner.nextInt();
                            attempts++;
                            totalGuesses++;

                            if (guess < minNumber || guess > maxNumber) {
                                System.out.println("Out of range!");
                            } else if (guess < secretNumber) {
                                System.out.println("Too low!");
                            } else if (guess > secretNumber) {
                                System.out.println("Too high!");
                            } else {
                                System.out.println("Correct! You guessed it in " + attempts + " attempts.");
                                hasWon = true;
                                gamesWon++;
                            }
                        } else {
                            System.out.println("Invalid input!");
                            scanner.next();
                        }
                    }

                    if (!hasWon) {
                        System.out.println("Game Over! The number was: " + secretNumber);
                    }

                    totalGamesPlayed++;

                    int gamesLost = totalGamesPlayed - gamesWon;
                    double winRatio = (gamesLost > 0) ? (double) gamesWon / gamesLost : gamesWon;
                    double avgGuesses = (double) totalGuesses / totalGamesPlayed;

                    // Update statistics
                    statistics.put("Games Played", String.valueOf(totalGamesPlayed));
                    statistics.put("Games Won", String.valueOf(gamesWon));
                    statistics.put("Games Lost", String.valueOf(gamesLost));
                    statistics.put("Win/Loss Ratio", String.format("%.2f", winRatio));
                    statistics.put("Average Guesses per Game", String.format("%.2f", avgGuesses));
                    statistics.put("Total Guesses", String.valueOf(totalGuesses));

                    saveStatistics(statistics);
                    break;

                case 3:
                    continueProgram = false;
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }

        System.out.println("\nThank you for playing!");
        scanner.close();
    }

    // Save stats to file
    public static void saveStatistics(HashMap<String, String> stats) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(STAT_FILE))) {
            for (String key : stats.keySet()) {
                writer.println(key + "=" + stats.get(key));
            }
        } catch (IOException e) {
            System.out.println("Error saving statistics.");
        }
    }

    // Load stats from file
    public static HashMap<String, String> loadStatistics() {
        HashMap<String, String> stats = new HashMap<>();
        File file = new File(STAT_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("=");
                    if (parts.length == 2) {
                        stats.put(parts[0], parts[1]);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error loading statistics.");
            }
        }
        return stats;
    }
}
