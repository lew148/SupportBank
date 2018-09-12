package training.supportbank;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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



        for (String line : transactionsList) {

            String[] lineSplit = line.split(",");

            Transaction transaction = new Transaction();

            transaction.date = lineSplit[0];
            transaction.from = lineSplit[1];
            transaction.to = lineSplit[2];
            transaction.narrative = lineSplit[3];
            transaction.amount = lineSplit[4];

            finalList.add(transaction);
        }

        for (Transaction transaction : finalList) {
            
        }

    }
}
