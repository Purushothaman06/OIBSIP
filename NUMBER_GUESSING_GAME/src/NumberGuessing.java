import java.util.Scanner;
import java.util.Random;

public class NumberGuessing {
    static public void main(String... abc) throws Exception {

        // create a scanner object
        var scan = new Scanner(System.in);

        // get maximum number for program to guess
        System.out.println("Enter Maximum Value: ");
        int max = scan.nextInt();

        // start game loop
        int numRounds = 0;
        int totalScore = 0;
        boolean playAgain = true;
        while (playAgain) {

            // generate target number
            Random rand = new Random();
            int target = 1 + rand.nextInt(max);
            int curGuess = 0;
            int roundScore = 0;

            // start guessing loop
            System.out.println("Round " + (numRounds + 1));
            System.out.println("Guess a number between 1 and " + max);
            for (int numGuesses = 1; numGuesses <= 10; numGuesses++) {

                if (curGuess != target) {
                    if (numGuesses != 10) {

                        // get the user's next guess
                        System.out.println("Enter your guess: ");
                        curGuess = scan.nextInt();

                        // print output based on the guess
                        if (curGuess < target)
                            System.out.println("Your guess is too low. Try again.");
                        else if (curGuess > target)
                            System.out.println("Your guess is too high. Try again.");
                        else {
                            System.out.println("Congratulations, you guessed the correct number!");
                            roundScore = 10 - numGuesses + 1;
                            totalScore += roundScore;
                            break;
                        }
                    } else {
                        System.out.println("Sorry, you ran out of attempts.");
                        System.out.println("The correct number was " + target);
                        break;
                    }
                }
            }
            System.out.println("Round score: " + roundScore);
            System.out.println("Total score: " + totalScore);

            // ask if the user wants to play again
            System.out.println("Do you want to play again? (yes or no)");
            String playAgainStr = scan.next();
            
            if (!playAgainStr.equalsIgnoreCase("yes")) {
                playAgain = false;
            }
            numRounds++;
        }
        scan.close();
    }
}
