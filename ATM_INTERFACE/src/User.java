import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {

    // The first name of the user.
    private String firstName;

    // The last name of the user.
    private String lastName;

    // The ID number of the user.
    private String userId;

    // The MDS hash of the user's pin number.
    private byte pinHash[];

    // The list of accounts for this userr.
    private java.util.ArrayList<Account> accounts;

    /**
     * Create a new User
     * 
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @param pin       the user's account pin number
     * @param bank      the Bank object that the user is a customer of
     */
    public User(String firstName, String lastName, String pin, Bank bank) {

        // set the user's name.
        this.firstName = firstName;
        this.lastName = lastName;

        // store the pin's MDS hash , rather than the original value, for
        // security reasons
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        // get a new, unique universal ID for the user
        this.userId = bank.getNewUserId();

        // create empty list of accounts
        this.accounts = new java.util.ArrayList<Account>();

        // print log message
        System.out.printf("New User %s, %s with ID %s created.\n", lastName, firstName, this.userId);
    }

    /**
     * Add an account for the user
     * 
     * @param onAcct the account to add
     */
    public void addAccount(Account onAcct) {
        this.accounts.add(onAcct);
    }

    /**
     * Return the user's UUID
     * 
     * @return the userId.
     */
    public String getUUID() {
        return this.userId;
    }

    /**
     * Check whether a given pin matches the true User pin
     * 
     * @param oPin the pin to check
     * @return whether the pin is valid or not
     */
    public boolean validatePin(String oPin) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return MessageDigest.isEqual(md.digest(oPin.getBytes()),
                    this.pinHash);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }
        return false;
    }

    /**
     * Return the user's first name
     * 
     * @return the first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Return the user's last name'
     * 
     * @return the last name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Print summaries for the accounts of this user.
     */
    public void printAccountSummary() {

        System.out.printf("\n\n%s's account summary\n", this.firstName);
        for (int a = 0; a < this.accounts.size(); a++) {
            System.out.printf("  %d) %s\n", a + 1,
                    this.accounts.get(a).getSummaryLine());
        }
        System.out.println();
    }

    /**
     * Get the number of accounts of the user
     * 
     * @return the number of accounts
     */
    public int numAccounts() {
        return this.accounts.size();
    }

    /**
     * Print transaction history for a particular account.
     * 
     * @param acctIdx the index of the account to use
     */
    public void printAcctTransactonHistory(int acctIdx) {
        this.accounts.get(acctIdx).printTransactionHistory();
    }

    /**
     * Get the balance of a particular account
     * 
     * @param fromAcct the index of the account to use
     * @return the balance of the account
     */
    public double getAcctBalance(int acctIdx) {
        return this.accounts.get(acctIdx).getBalance();
    }

    /**
     * Get the UUID of a particular account
     * 
     * @param acctIdx the index of the account to use
     * @return the UUID of the account
     */
    public String getAcctUUID(int acctIdx) {
        return this.accounts.get(acctIdx).getUUID();
    }

    /**
     * Add a transaction to a particular account
     * 
     * @param acctIdx the index of the account
     * @param amount  the amount of the transaction
     * @param memo    the memo of the transaction
     */
    public void addAcctTransaction(int acctIdx, double amount, String memo) {
        this.accounts.get(acctIdx).addTransaction(amount, memo);
    }
}
