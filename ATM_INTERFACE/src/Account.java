public class Account {

    // The name of the account.
    private String name;

    // The account ID number.
    private String userId;

    // The User object that own this account.
    private User holder;

    /**
     * Get the User for the account
     * 
     * @return holder of the account
     */
    public User getHolder() {
        return this.holder;
    }

    // The list of transactions for this account.
    private java.util.ArrayList<Transaction> transactions;

    /**
     * Create a new Account
     * 
     * @param name   the name of the account
     * @param holder the User object that holds this account
     * @param bank   the bank that issues the account
     */
    public Account(String name, User holder, Bank bank) {

        // set the account name and holder
        this.name = name;
        this.holder = holder;

        // get new account userId
        this.userId = bank.getNewAccountId();

        // init transactions
        this.transactions = new java.util.ArrayList<Transaction>();
    }

    /**
     * Get the account ID
     * 
     * @return the userId
     */
    public String getUUID() {
        return this.userId;
    }

    /**
     * Get summary line for the account
     * 
     * @return the string summary
     */
    public String getSummaryLine() {

        // get the account's balance
        double balance = this.getBalance();

        // format the summary line, depending on the whether the balance is negative
        if (balance >= 0) {
            return String.format("%s : $%.02f : %s", this.userId, balance,
                    this.name);
        } else {
            return String.format("%s : $(%.02f) : %s", this.userId, balance,
                    this.name);
        }
    }

    /**
     * Get the balance of this account by adding the amount of the transactions
     * 
     * @return the balance value
     */
    public double getBalance() {

        double balance = 0;
        for (Transaction tx : transactions) {
            balance += tx.getAmount();
        }
        return balance;
    }

    /**
     * Print the transaction history of the account
     */
    public void printTransactionHistory() {

        System.out.printf("\nTransaction history for account %s\n", this.userId);
        for (int t = this.transactions.size() - 1; t >= 0; t--) {
            System.out.println(this.transactions.get(t).getSummaryLine());
        }
        System.out.println();
    }

    /**
     * Add a new transaction in this account
     * 
     * @param amount the amount transacted
     * @param memo   the transaction memo
     */
    public void addTransaction(double amount, String memo) {

        // create new transaction object and add it to our list
        Transaction newTransaction = new Transaction(amount, memo, this);
        this.transactions.add(newTransaction);
    }
}
