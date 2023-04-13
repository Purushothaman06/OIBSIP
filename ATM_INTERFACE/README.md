## Overview:

The ATM Interface project is a Java console-based application that emulates the functionality of an Automated Teller Machine (ATM). It comprises five main classes - ATM, Bank, Account, Transaction and User - that collaborate to provide various banking operations such as transaction history, withdrawals, deposits, transfers, and quitting the application.

## Classes and their Functionalities:

* **ATM**: This class serves as the entry point of the application and presents the main menu to the user for interaction. It displays options for different banking operations and manages user input to execute the selected operation.

* **Bank**: This class represents a bank and oversees the accounts of its customers. It maintains a record of all user accounts and offers methods to perform operations such as deposits, withdrawals, and transfers between accounts. It also manages transaction history for each account.

* **Account**: This class represents a bank account and stores information such as the account number, balance, and transaction history. It provides methods to perform operations such as deposits, withdrawals, and transfers.

* **User**: This class represents a user of the ATM and stores information such as the user's name, account number, and PIN. It offers methods to verify the user's PIN and retrieve user details.

* **Transaction**: This class represents a transaction and stores information such as the transaction type (withdrawal, deposit, transfer), transaction amount, and transaction date/time. It is used to maintain transaction history for each account.

## Functionality:
The ATM Interface project provides the following functionalities:

> **Transaction History**: Users can view the transaction history of their account, which displays the details of all transactions performed on the account, including transaction type, amount, and date/time.

> **Withdrawals**: Users can withdraw money from their account by specifying the withdrawal amount. The ATM and Bank classes handle validations, such as checking if the withdrawal amount is within the account balance and if the ATM has sufficient cash to dispense.

> **Deposits**: Users can deposit money into their account by specifying the deposit amount. The ATM and Bank classes handle validations, such as checking if the deposit amount is valid.

> **Transfers**: Users can transfer money from their account to another account within the same bank by specifying the target account number and transfer amount. The ATM and Bank classes handle validations, such as checking if the target account number is valid and if the transfer amount is within the account balance.

> **Quit**: Users can exit the application at any time by selecting the quit option from the main menu.

## Usage:
To use the ATM Interface project, follow these steps:

1. Compile and run the Java program, starting with the ATM class, which serves as the entry point of the application.

2. The ATM will display a main menu with options for different banking operations.

3. Users can select an option by entering the corresponding number from the menu.

4. The ATM will prompt users for any additional inputs required for the selected operation, such as withdrawal amount, deposit amount, target account number for transfer, etc.

5. The ATM and Bank classes will perform the necessary validations and operations based on the user's inputs.

6. Users can view the transaction history, withdraw, deposit, transfer, or quit the application as needed.

## Conclusion:

The ATM Interface project is a Java console-based application that emulates the functionality of an ATM. It provides various banking operations such as transaction history, withdrawals, deposits, transfers, and quitting the application. The project employs four main classes - ATM, Bank, Account, and User - that work together to deliver the desired functionalities. Users can interact with the ATM through the main menu and perform banking operations based on their requirements.