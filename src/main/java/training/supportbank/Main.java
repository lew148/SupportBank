package training.supportbank;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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


        System.out.println(transactionsList.get(1));

        //Loop to input value into Transaction instances

        Integer listSize = transactionsList.size();
        int counter = 1;


        while (counter < listSize) {
        System.out.println(transactionsList.get(counter));
            counter = counter + 1;
        }

    }
}
