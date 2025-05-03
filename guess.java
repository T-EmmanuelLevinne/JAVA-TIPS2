import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        // Game statistics
        int totalGamesPlayed = 0;
        int gamesWon = 0;
        int totalGuesses = 0;
        
        boolean playAgain = true;
        
        System.out.println("=== NUMBER GUESSING GAME ===");
        
        while (playAgain) {
            // Game variables
            int minNumber = 1;
            int maxNumber = 100;
            int maxAttempts = 10;
            int difficultyLevel = 0;
            
            // Difficulty selection
            System.out.println("\nSelect difficulty level:");
            System.out.println("1. Easy (1-50, 15 attempts)");
            System.out.println("2. Medium (1-100, 10 attempts)");
            System.out.println("3. Hard (1-200, 8 attempts)");
            System.out.println("4. Expert (1-500, 5 attempts)");
            
            while (difficultyLevel < 1 || difficultyLevel > 4) {
                System.out.print("Enter your choice (1-4): ");
                
                if (scanner.hasNextInt()) {
                    difficultyLevel = scanner.nextInt();
                    
                    // Set game parameters based on difficulty
                    switch (difficultyLevel) {
                        case 1: // Easy
                            maxNumber = 50;
                            maxAttempts = 15;
                            break;
                        case 2: // Medium
                            maxNumber = 100;
                            maxAttempts = 10;
                            break;
                        case 3: // Hard
                            maxNumber = 200;
                            maxAttempts = 8;
                            break;
                        case 4: // Expert
                            maxNumber = 500;
                            maxAttempts = 5;
                            break;
                        default:
                            System.out.println("Invalid choice! Please select 1-4.");
                            difficultyLevel = 0;
                            break;
                    }
                } else {
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.next(); // Clear the invalid input
                    difficultyLevel = 0;
                }
            }
            
            // Generate the random number to guess
            int secretNumber = random.nextInt(maxNumber - minNumber + 1) + minNumber;
            
            System.out.println("\n--- Game Started ---");
            System.out.println("I'm thinking of a number between " + minNumber + " and " + maxNumber);
            System.out.println("You have " + maxAttempts + " attempts to guess it.");
            
            // Game loop
            int attempts = 0;
            boolean hasWon = false;
            
            while (attempts < maxAttempts && !hasWon) {
                System.out.print("\nAttempt " + (attempts + 1) + "/" + maxAttempts + ": Enter your guess: ");
                
                if (scanner.hasNextInt()) {
                    int userGuess = scanner.nextInt();
                    attempts++;
                    totalGuesses++;
                    
                    // Check the guess
                    if (userGuess < minNumber || userGuess > maxNumber) {
                        System.out.println("Your guess is out of range! Remember, the number is between " + minNumber + " and " + maxNumber);
                    } else if (userGuess < secretNumber) {
                        System.out.println("Too low! Try a higher number.");
                    } else if (userGuess > secretNumber) {
                        System.out.println("Too high! Try a lower number.");
                    } else {
                        System.out.println("Congratulations! You've guessed the number " + secretNumber + " correctly in " + attempts + " attempts!");
                        hasWon = true;
                        gamesWon++;
                    }
                } else {
                    System.out.println("Invalid input! Please enter a valid number.");
                    scanner.next(); // Clear the invalid input
                }
            }
            
            if (!hasWon) {
                System.out.println("\nGame Over! You've used all your attempts.");
                System.out.println("The number was: " + secretNumber);
            }
            
            // Update game statistics
            totalGamesPlayed++;
            
            // Display game statistics
            System.out.println("\n--- GAME STATISTICS ---");
            System.out.println("Games Played: " + totalGamesPlayed);
            System.out.println("Games Won: " + gamesWon);
            
            // Calculate win/loss ratio
            double winRatio = 0.0;
            if (totalGamesPlayed > 0) {
                int gamesLost = totalGamesPlayed - gamesWon;
                if (gamesLost > 0) {
                    winRatio = (double) gamesWon / gamesLost;
                } else if (gamesWon > 0) {
                    winRatio = gamesWon; // All games won
                }
            }
            System.out.println("Win/Loss Ratio: " + String.format("%.2f", winRatio));
            
            // Calculate average guesses
            double avgGuesses = 0.0;
            if (totalGamesPlayed > 0) {
                avgGuesses = (double) totalGuesses / totalGamesPlayed;
            }
            System.out.println("Average Guesses per Game: " + String.format("%.2f", avgGuesses));
            
            // Ask to play again
            System.out.print("\nDo you want to play again? (y/n): ");
            String playAgainResponse = scanner.next().toLowerCase();
            
            if (playAgainResponse.equals("y") || playAgainResponse.equals("yes")) {
                playAgain = true;
            } else {
                playAgain = false;
            }
        }
        
        System.out.println("\nThank you for playing the Number Guessing Game!");
        scanner.close();
    }
}
