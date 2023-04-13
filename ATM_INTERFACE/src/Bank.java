public class Bank {

    // name of the bank
    private String name;

    // The list of users for this Bank.
    private java.util.ArrayList<User> users;

    // The list of accounts for this userr.
    private java.util.ArrayList<Account> accounts;

    /**
     * Create a new Bank object with empty lists of users and accounts
     * 
     * @param name the name of the bank
     */
    public Bank(String name) {

        this.name = name;
        this.users = new java.util.ArrayList<User>();
        this.accounts = new java.util.ArrayList<Account>();
    }

    /**
     * Generate a new universally unique ID for a user.
     * 
     * @return the uuId
     */
    public String getNewUserId() {

        // inits
        String uuId;
        java.util.Random rand = new java.util.Random();
        int len = 6;
        boolean nonUnique;

        // continue looping until we get a unique ID
        do {

            // generate the number
            uuId = "";
            for (int c = 0; c < len; c++) {
                uuId += ((Integer) rand.nextInt(10)).toString();
            }

            // check to make sure it's unique
            nonUnique = false;
            for (User u : this.users) {
                if (uuId.compareTo(u.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }

        } while (nonUnique);

        return uuId;
    }

    /**
     * Generate a new universally unique ID for an account
     * 
     * @return the userId.
     */
    public String getNewAccountId() {

        // inits
        String uuId;
        java.util.Random rand = new java.util.Random();
        int len = 10;
        boolean nonUnique;

        // continue looping until we get a unique ID
        do {

            // generate the number
            uuId = "";
            for (int c = 0; c < len; c++) {
                uuId += ((Integer) rand.nextInt(10)).toString();
            }

            // check to make sure it's unique
            nonUnique = false;
            for (Account a : this.accounts) {
                if (uuId.compareTo(a.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }

        } while (nonUnique);

        return uuId;
    }

    /**
     * Add on account
     * 
     * @param onAcct the account to add
     */
    public void addAccount(Account onAcct) {
        this.accounts.add(onAcct);
    }

    /**
     * Create a new user of the bank
     * 
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @param pin       the user's pin
     * @return the new User object
     */
    public User addUser(String firstName, String lastName, String pin) {

        // create a new user object and add it to our list
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);

        // create a savings account for the user and add to User and Bank
        // accounts list
        Account newAccount = new Account("Savings", newUser, this);
        newUser.addAccount(newAccount);
        this.addAccount(newAccount);

        return newUser;
    }

    /**
     * Get the User object associated with a particular userID and pin, if they
     * are valid
     * 
     * @param userID the userID of the user to log in
     * @param pin    the pin of the user
     * @return the user object, if the login is successful, or null, if it is not
     */
    public User userLogin(String userID, String pin) {

        // search through list of users
        for (User u : this.users) {

            // check user ID is correct
            if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
                return u;
            }
        }

        // if we haven't found the user or have on incorrect pin
        return null;
    }

    /**
     * Get the name of the bank
     * 
     * @return the name of the bank
     */
    public String getName() {
        return this.name;
    }
}
