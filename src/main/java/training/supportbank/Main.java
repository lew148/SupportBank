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


        //Importing csv file
        List<String> transactionsList = null;
        try {
            transactionsList = Files.readAllLines(Paths.get("C:\\Work\\Training\\SupportBank\\Transactions2014.csv"));
        } catch (IOException e) {
            System.out.println("Error");
        }
        //

        ArrayList<Transaction> finalList = new ArrayList<Transaction>();

        transactionsList.remove(0);


        for (String line : transactionsList) {

            String[] lineSplit = line.split(",");

            Transaction transaction = new Transaction();

            transaction.date = LocalDate.parse(lineSplit[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            transaction.from = lineSplit[1];
            transaction.to = lineSplit[2];
            transaction.narrative = lineSplit[3];
            transaction.amount = Double.parseDouble(lineSplit[4]);

            finalList.add(transaction);
        }

        HashMap<String, Account> accountMap = new HashMap<>();



        for (Transaction transaction : finalList) {
            if (!accountMap.containsKey(transaction.from)) {
                Account account = new Account();
                account.name = transaction.from;
                account.amountDue = 0.0;
                account.amountOwed = 0.0;
                accountMap.put(transaction.from, account);
            }

            Account a = accountMap.get(transaction.from);
            a.accountTransactions.add(transaction);
            a.amountOwed = a.amountOwed + transaction.amount;
        }

        for (Transaction transaction : finalList) {
            if (!accountMap.containsKey(transaction.to)) {
                Account account = new Account();
                account.name = transaction.to;
                account.amountDue = 0.0;
                account.amountOwed = 0.0;
                accountMap.put(transaction.to, account);
            }

            Account a = accountMap.get(transaction.to);
            a.accountTransactions.add(transaction);
            a.amountDue = a.amountDue + transaction.amount;
        }
    }
}
