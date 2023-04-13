import java.util.Scanner;

public class ATM {
    static public void main(String... abc) {

        // init Scanner
        var scan = new Scanner(System.in);

        // get the first name as a user input
        System.out.print("Enter First Name: ");
        String firstName = scan.nextLine();

        // get the last name as a user input
        System.out.print("Enter Last Name: ");
        String lastName = scan.nextLine();

        // allow the user to set their pin
        System.out.print("Create a pin: ");
        String pinNumber = scan.nextLine();

        // init Bank
        Bank bank = new Bank("World Bank");

        // add a user, which also creates a savings account
        User oUser = bank.addUser(firstName, lastName, pinNumber);

        // add a checking account for our user
        Account newAccount = new Account("Checking", oUser, bank);
        oUser.addAccount(newAccount);
        bank.addAccount(newAccount);

        User curUser;
        while (true) {

            // stay in the login prompt until successful login
            curUser = ATM.mainMenuPrompt(bank, scan);

            // stay in main menu until user quits
            ATM.printUserMenu(curUser, scan);
        }
    }

    private static void printUserMenu(User curUser, Scanner scan) {

        // print a summary of the user's accounts
        curUser.printAccountSummary();

        // inits
        int choice;

        // user menu
        do {
            System.out.printf("Welcome %s, what would do you like to do?\n",
                    curUser.getFirstName());
            System.out.println(" 1) Show account transaction history");
            System.out.println(" 2) Withdraw");
            System.out.println(" 3) Deposit");
            System.out.println(" 4) Transfer");
            System.out.println(" 5) Quit");
            System.out.println();
            System.out.println("Enter choice: ");
            choice = scan.nextInt();

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please choice 1-5");
            }
        } while (choice < 1 || choice > 5);

        // process the choice
        switch (choice) {
            case 1:
                ATM.showTransactionHistory(curUser, scan);
                break;

            case 2:
                ATM.WithdrawFunds(curUser, scan);
                break;

            case 3:
                ATM.depositFunds(curUser, scan);
                break;

            case 4:
                ATM.transferFunds(curUser, scan);
                break;

            case 5:
                // gobble up rest of previous input
                scan.nextLine();
                break;
        }

        // redisplay this menu unless the user wants to quit
        if (choice != 5) {
            ATM.printUserMenu(curUser, scan);
        }
    }

    /**
     * Process transferring funds from one account to another
     * 
     * @param curUser the logged in User object
     * @param scan    the Scanner object used for user input
     */
    public static void transferFunds(User curUser, Scanner scan) {

        // inits
        int fromAcct;
        int toAcct;
        double amount;
        double accBal;

        // get the account to transfer from
        do {
            System.out.printf("Enter the number (1-%d) of the account\n" +
                    "to transfer from: ", curUser.numAccounts());
            fromAcct = scan.nextInt() - 1;
            if (fromAcct < 0 || fromAcct >= curUser.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (fromAcct < 0 || fromAcct >= curUser.numAccounts());
        accBal = curUser.getAcctBalance(fromAcct);

        // get the account to transfer to
        do {
            System.out.printf("Enter the number (1-%d) of the account\n" +
                    "to transfer to: ", curUser.numAccounts());
            toAcct = scan.nextInt() - 1;
            if (toAcct < 0 || toAcct >= curUser.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (toAcct < 0 || toAcct >= curUser.numAccounts());

        // get the amount to transfer
        do {
            System.out.printf("Enter the amount to transfer (max $%.02f): $",
                    accBal);
            amount = scan.nextDouble();
            if (amount < 0) {
                System.out.println("Account must be greater than zero");
            } else if (amount > accBal) {
                System.out.printf("Amount must not be greater than\n" +
                        "balance of $%.02f.\n", accBal);
            }
        } while (amount < 0 || amount > accBal);

        // finally, do the transfer
        curUser.addAcctTransaction(fromAcct, -1 * amount, String.format(
                "Transfer to account %s", curUser.getAcctUUID(toAcct)));
        curUser.addAcctTransaction(toAcct, amount, String.format(
                "Transfer to account %s", curUser.getAcctUUID(fromAcct)));
    }

    /**
     * Process a fund deposit to an account
     * 
     * @param curUser the logged-in User object
     * @param scan    the Scanner object used for user input
     */
    public static void depositFunds(User curUser, Scanner scan) {

        // inits
        int toAcct;
        double amount;
        double accBal;
        String memo;

        // get the account to transfer from
        do {
            System.out.printf("Enter the number (1-%d) of the account\n" +
                    "to deposit in: ", curUser.numAccounts());
            toAcct = scan.nextInt() - 1;
            if (toAcct < 0 || toAcct >= curUser.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (toAcct < 0 || toAcct >= curUser.numAccounts());
        accBal = curUser.getAcctBalance(toAcct);

        // get the amount to transfer
        do {
            System.out.printf("Enter the amount to transfer (max $%.02f): $",
                    accBal);
            amount = scan.nextDouble();
            if (amount < 0) {
                System.out.println("Account must be greater than zero");
            }
        } while (amount < 0);

        // gobble up rest of previous input
        scan.nextLine();

        // get a memo
        System.out.print("Enter a memo: ");
        memo = scan.nextLine();

        // do the withdraw
        curUser.addAcctTransaction(toAcct, amount, memo);
    }

    /**
     * Process a fund withdraw from an account
     * 
     * @param curUser the logged in User object
     * @param scan    the Scanner object user for user input
     */
    public static void WithdrawFunds(User curUser, Scanner scan) {

        // inits
        int fromAcct;
        double amount;
        double accBal;
        String memo;

        // get the account to withdraw from
        do {
            System.out.printf("Enter the number (1-%d) of the account\n" +
                    "to withdraw from: ", curUser.numAccounts());
            fromAcct = scan.nextInt() - 1;
            if (fromAcct < 0 || fromAcct >= curUser.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (fromAcct < 0 || fromAcct >= curUser.numAccounts());
        accBal = curUser.getAcctBalance(fromAcct);

        // get the amount to transfer
        do {
            System.out.printf("Enter the amount to withdraw (max $%.02f): $",
                    accBal);
            amount = scan.nextDouble();
            if (amount < 0) {
                System.out.println("Account must be greater than zero");
            } else if (amount > accBal) {
                System.out.printf("Amount must not be greater than\n" +
                        "balance of $%.02f.\n", accBal);
            }
        } while (amount < 0 || amount > accBal);

        // gobble up rest of previous input
        scan.nextLine();

        // get a memo
        System.out.print("Enter a memo: ");
        memo = scan.nextLine();

        // do the withdraw
        curUser.addAcctTransaction(fromAcct, -1 * amount, memo);
    }

    /**
     * Show the transaction history for an account
     * 
     * @param curUser the logged-in User object
     * @param scan    the Scanner object used for user input
     */
    public static void showTransactionHistory(User curUser, Scanner scan) {

        int theAcct;

        // get account whose transaction history to look at
        do {
            System.out.printf("Enter the number (1-%d) of the account\n" +
                    " whose transactions you want to see: ",
                    curUser.numAccounts());
            theAcct = scan.nextInt() - 1;
            if (theAcct < 0 || theAcct >= curUser.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (theAcct < 0 || theAcct >= curUser.numAccounts());

        // print the transaction history
        curUser.printAcctTransactonHistory(theAcct);
    }

    /**
     * Print the ATM's login menu
     * 
     * @param bank the Bank object whose accounts to use
     * @param scan the Scanner object to use for user input
     * @return the authenticated User object
     */
    private static User mainMenuPrompt(Bank bank, Scanner scan) {

        // inits
        String userID;
        String pin;
        User authUser;

        // prompt the user for user ID/pin combo until a correct one is reached
        do {

            System.out.printf("\n\nWelcom to %s\n\n", bank.getName());
            System.out.print("Enter user ID: ");
            userID = scan.nextLine();
            System.out.print("Enter pin: ");
            pin = scan.nextLine();

            // try to get the user object corresponding to the ID and pin combo
            authUser = bank.userLogin(userID, pin);
            if (authUser == null) {
                System.out.println("Incorrect user ID/pin combination." +
                        "Please try again.");
            }

        } while (authUser == null); // continue looping until successful login

        return authUser;
    }
}
