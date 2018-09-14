package training.supportbank;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String args[]) {

        // creates new instance of Read class, for use of functions within
        Read read = new Read();


        // inputs CSV file path into the method: readCSV
        read.readCSV("C:\\Work\\Training\\SupportBank\\Transactions2014.csv");
        //read.readJSON("C:\\Work\\Training\\SupportBank\\Transactions2013.json");
        //read.readXML("C:\\Work\\Training\\SupportBank\\Transactions2012.xml");


        // creates an array list in Main that is set as equal toAccount that which was created in the readCSV method
        ArrayList<Transaction> transactionList;
        transactionList = read.getTransactionList();


        // HashMap that links a name toAccount the corresponding instance of account (E.g. "Sarah T" --> *Sarah's account instance*)
        HashMap<String, Account> accountMap = new HashMap<>();

        // created new array list for accounts
        ArrayList<Account> accountList = new ArrayList<>();


        // this loop deals with the 'from' cells in the ArrayList and, if it doesn't already exist,
        // creates a new key and value ('name' and corresponding account instance) link in the HashMap
        for (Transaction transaction : transactionList) {

            // if there is no existing account, linked toAccount the 'current' 'from' string, one is created
            if (!accountMap.containsKey(transaction.fromAccount)) {

                // new account instance creation, as an existing one cannot be found
                Account account = new Account();

                // assignment of variables, within account instance, after it has been created
                account.name = transaction.fromAccount;
                account.amountDue = 0.0;
                account.amountOwed = 0.0;

                // add new account instance toAccount the list of accounts
                accountList.add(account);

                // adds 'name' --> created account instance link toAccount HashMap (E.g. "Sarah T" --> *Sarah's account instance*)
                accountMap.put(transaction.fromAccount, account);
            }

            // if an account does exist, the current transaction is added toAccount the amountOwed variable that already exists
            // (This will assign an account with the sum of all owed money)
            Account a = accountMap.get(transaction.fromAccount);
            a.accountTransactions.add(transaction);
            a.amountOwed = a.amountOwed + transaction.amount;
        }


        // this loop deals with the 'toAccount' cells in the ArrayList and, if it doesn't already exist,
        // creates a new key and value ('name' and corresponding account instance) link in the HashMap
        for (Transaction transaction : transactionList) {

            // if there is no existing account, linked toAccount the 'current' 'toAccount' string, one is created
            if (!accountMap.containsKey(transaction.toAccount)) {

                // new account instance creation, as an existing one cannot be found
                Account account = new Account();

                // assignment of variables, within account instance, after it has been created
                account.name = transaction.toAccount;
                account.amountDue = 0.0;
                account.amountOwed = 0.0;

                // add new account instance toAccount the list of accounts
                accountList.add(account);

                // adds 'name' --> created account instance link toAccount HashMap (E.g. "Sarah T" --> *Sarah's account instance*)
                accountMap.put(transaction.toAccount, account);
            }

            // if an account does exist, the current transaction is added toAccount the amountOwed variable that already exists
            // (This will assign an account with the sum of all owed money)
            Account a = accountMap.get(transaction.toAccount);
            a.accountTransactions.add(transaction);
            a.amountDue = a.amountDue + transaction.amount;
        }


        // prints all account 'names' toAccount screen for user review
        System.out.println("Here is a list of all Accounts: ");
        for (Account account : accountList) {
            account.printName();
        }


        // takes user input and stores it in a string variable, userInput
        Scanner scanner = new Scanner(System.in);
        System.out.println("List All or [Account Name] (Account Name is Case Sensitive): ");
        String userInput = scanner.nextLine();


        // if the user inputs "all" or "All", program will print a list of each person, how much they owe and how much they're due
        // if anything else, x, is inputted, x is found within the account map and all transactions, and it's details are printed
        if (userInput.toLowerCase().equals("all")) {
            for (Account account : accountList) {
                account.printOweDue();
            }
        } else {

            // try/catch toAccount highlight possible invalid data input, from user
            try {
                for (Transaction transaction : accountMap.get(userInput).accountTransactions) {
                    transaction.printTransaction();
                }
            } catch(Exception e) {
                logger.error("Inputted string: \'" + userInput + "\' is invalid or incorrect! Please try again.");
                System.out.println("Inputted string: \'" + userInput + "\' is invalid or incorrect!");
                throw e;
            }
        }
    }
}
