package training.supportbank;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        //Scanner scanner = new Scanner(System.in);
        //while (scanner.hasNextLine()) {
        //    String userInput = scanner.nextLine();
        //}

        // ArrayList is created, to hold the raw data, line by line
        List<String> rawDataList = null;

        // (try & catch) = exceptions are accounted for.
        // (try to run this command on this file but run the catch method if an exception occurs)
        try {

            // csv file is imported, read line-by-line and added to list in the same fashion
            rawDataList = Files.readAllLines(Paths.get("C:\\Work\\Training\\SupportBank\\Transactions2014.csv"));

            // "Error" will be displayed if an exception is caught (csv file cannot be found or is corrupted)
        } catch (IOException e) {
            System.out.println("Error");
        }


        // ArrayList is created to 'hold' the soon to be created transaction instances
        ArrayList<Transaction> transactionList = new ArrayList<>();


        // first line of the ArrayList is removed as this is invalid data, used as headings
        rawDataList.remove(0);


        // each line in the data list is read and instances of transactions are created and assigned the correct variables.
        // the instances are then added to the ArrayList
        for (String line : rawDataList) {

            // a string array is created and the string before each comma is added to it
            String[] lineSplit = line.split(",");

            // an new transaction instance is created
            Transaction transaction = new Transaction();

            // each line of the string array ([0], [1], etc.) is assigned to the correct variable of the created instance
            transaction.date = LocalDate.parse(lineSplit[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            transaction.from = lineSplit[1];
            transaction.to = lineSplit[2];
            transaction.narrative = lineSplit[3];
            transaction.amount = Double.parseDouble(lineSplit[4]);


            //the created transaction instance is added to the list of transaction instances
            transactionList.add(transaction);
        }


        // HashMap that links a name to the corresponding instance of account (E.g. "Sarah T" --> *Sarah's account instance*)
        HashMap<String, Account> accountMap = new HashMap<>();


        // this loop deals with the 'from' cells in the ArrayList and, if it doesn't already exist,
        // creates a new key and value ('name' and corresponding account instance) link in the HashMap
        for (Transaction transaction : transactionList) {

            // if there is no existing account, linked to the 'current' 'from' string, one is created
            if (!accountMap.containsKey(transaction.from)) {

                // new account instance creation, as an existing one cannot be found
                Account account = new Account();

                // assignment of variables, within account instance, after it has been created
                account.name = transaction.from;
                account.amountDue = 0.0;
                account.amountOwed = 0.0;

                // adds 'name' --> created account instance link to HashMap (E.g. "Sarah T" --> *Sarah's account instance*)
                accountMap.put(transaction.from, account);
            }

            // if an account does exist, the current transaction is added to the amountOwed variable that already exists
            // (This will assign an account with the sum of all owed money)
            Account a = accountMap.get(transaction.from);
            a.accountTransactions.add(transaction);
            a.amountOwed = a.amountOwed + transaction.amount;
        }


        // this loop deals with the 'to' cells in the ArrayList and, if it doesn't already exist,
        // creates a new key and value ('name' and corresponding account instance) link in the HashMap
        for (Transaction transaction : transactionList) {

            // if there is no existing account, linked to the 'current' 'to' string, one is created
            if (!accountMap.containsKey(transaction.to)) {

                // new account instance creation, as an existing one cannot be found
                Account account = new Account();

                // assignment of variables, within account instance, after it has been created
                account.name = transaction.to;
                account.amountDue = 0.0;
                account.amountOwed = 0.0;

                // adds 'name' --> created account instance link to HashMap (E.g. "Sarah T" --> *Sarah's account instance*)
                accountMap.put(transaction.to, account);
            }

            // if an account does exist, the current transaction is added to the amountOwed variable that already exists
            // (This will assign an account with the sum of all owed money)
            Account a = accountMap.get(transaction.to);
            a.accountTransactions.add(transaction);
            a.amountDue = a.amountDue + transaction.amount;
        }

        System.out.println();
    }
}
