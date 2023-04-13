## Overview:
 
This is a Java program that implements a number guessing game. The program generates a random number between 1 and a maximum value chosen by the user, and the user has up to 10 attempts to guess the number. The program awards points based on the number of attempts it takes to guess the number, and allows the user to play multiple rounds.

## Program Structure:

The program is structured as follows:

1. **Import necessary packages**: The program uses the Scanner and Random classes, so these are imported at the beginning of the program.

2. **Define the main method**: The main method is the entry point for the program.

3. **Prompt user for maximum value**: The program prompts the user to enter the maximum value for the program to generate a random number from.

4. **Start game loop**: The program uses a while loop to allow the user to play multiple rounds. The loop continues until the user chooses to stop playing.

5. **Generate target number**: Inside the game loop, the program generates a random number between 1 and the user-specified maximum value.

6. **Start guessing loop**: Inside the game loop, the program uses a for loop to handle each guess. The loop continues until the user guesses the correct number or runs out of attempts.

7. **Get user's guess**: Inside the guessing loop, the program prompts the user to enter their guess.

8. **Check user's guess**: Inside the guessing loop, the program checks the user's guess against the generated target number. If the guess is incorrect, the program provides feedback to the user and allows them to guess again. If the guess is correct, the program calculates the user's score and displays it to the user.

9. **Ask if user wants to play again**: After each round, the program prompts the user to play again or quit. If the user chooses to play again, the program generates a new target number and starts a new round. If the user chooses to quit, the program exits.

10. **Display score**: After each round, the program displays the user's round score and total score so far.


## Features:

The program has the following features:

> **User-defined maximum value**: The user can enter a maximum value for the program to generate a random number from.

> **Multiple rounds**: The program allows the user to play multiple rounds.

> **Score tracking**: The program tracks the user's score and displays it after each round.

> **Point system**: The program awards points based on the number of attempts it takes the user to guess the number.

> **User-friendly interface**: The program provides clear instructions and feedback to the user throughout the game.

## Conclusion:

The number guessing program is a fun and interactive game that allows users to test their guessing skills. With its intuitive user interface and clear feedback on score and progress, it provides an engaging experience for users of all ages.
