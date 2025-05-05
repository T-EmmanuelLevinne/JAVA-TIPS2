import java.io.*;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    static final String STAT_FILE = "statistics.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        HashMap<String, String> statistics = loadGameStats(); // Load game stats

        int totalGamesPlayed = Integer.parseInt(statistics.getOrDefault("Games Played", "0"));
        int gamesWon = Integer.parseInt(statistics.getOrDefault("Games Won", "0"));
        int totalGuesses = Integer.parseInt(statistics.getOrDefault("Total Guesses", "0"));

        boolean continueProgram = true;

        System.out.println("=== NUMBER GUESSING GAME ===");

        while (continueProgram) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Check Statistics");
            System.out.println("2. Play Game");
            System.out.println("3. Clear History");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");

            int choice = -1;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                scanner.next(); // consume invalid input
            }

            switch (choice) {
                case 1:
                    // Check Statistics
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
                    // Play Game
                    playGame(scanner, random, statistics);
                    break;
     // NAADIRI!!!!
                case 3:
                    // Clear History
                    clearHistory();
                    statistics.clear(); // Clear current statistics from memory
                    System.out.println("Game history cleared!");
                    break;

                case 4:
                    continueProgram = false;
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }

        System.out.println("\nThank you for playing!");
        scanner.close();
    }

    // Play the game, same as before
    public static void playGame(Scanner scanner, Random random, HashMap<String, String> statistics) {
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
                        System.out.println("Invalid choice! Please select 1-4.");
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
                int totalGuesses = Integer.parseInt(statistics.getOrDefault("Total Guesses", "0")) + 1;
                statistics.put("Total Guesses", String.valueOf(totalGuesses));

                if (guess < minNumber || guess > maxNumber) {
                    System.out.println("Out of range!");
                } else if (guess < secretNumber) {
                    System.out.println("Too low!");
                } else if (guess > secretNumber) {
                    System.out.println("Too high!");
                } else {
                    System.out.println("Correct! You guessed it in " + attempts + " attempts.");
                    hasWon = true;
                    int gamesWon = Integer.parseInt(statistics.getOrDefault("Games Won", "0")) + 1;
                    statistics.put("Games Won", String.valueOf(gamesWon));
                }
            } else {
                System.out.println("Invalid input!");
                scanner.next();
            }
        }

        if (!hasWon) {
            System.out.println("Game Over! The number was: " + secretNumber);
        }

        int totalGamesPlayed = Integer.parseInt(statistics.getOrDefault("Games Played", "0")) + 1;
        statistics.put("Games Played", String.valueOf(totalGamesPlayed));

        int gamesLost = totalGamesPlayed - Integer.parseInt(statistics.getOrDefault("Games Won", "0"));
        double winRatio = (gamesLost > 0) ? (double) Integer.parseInt(statistics.getOrDefault("Games Won", "0")) / gamesLost : Integer.parseInt(statistics.getOrDefault("Games Won", "0"));
        double avgGuesses = (double) totalGuesses / totalGamesPlayed;

        statistics.put("Games Lost", String.valueOf(gamesLost));
        statistics.put("Win/Loss Ratio", String.format("%.2f", winRatio));
        statistics.put("Average Guesses per Game", String.format("%.2f", avgGuesses));

        saveGameStats(statistics); // Save updated statistics
    }

    // Clear the statistics file
  // IF THEY ASK FOR CLEAR HISTORY!!!!!!!!!!!!!!!!!!!!!!
    public static void clearHistory() {
        File file = new File(STAT_FILE);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("History cleared successfully.");
            } else {
                System.out.println("Error clearing history.");
            }
        } else {
            System.out.println("No history file found.");
        }
    }


    // Save game stats to a file
    public static void saveGameStats(HashMap<String, String> stats) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(STAT_FILE))) {
            for (String key : stats.keySet()) {
                writer.println(key + "=" + stats.get(key));
            }
        } catch (IOException e) {
            System.out.println("Error saving statistics.");
        }
    }

    // Load game stats from file
    public static HashMap<String, String> loadGameStats() {
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
