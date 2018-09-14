package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Read {
    private static final Logger logger = LogManager.getLogger();

    // ArrayList is created to 'hold' the soon to be created transaction instances
    ArrayList<Transaction> transactionList = new ArrayList<>();


    public void readCSV(String file) {
        // ArrayList is created, to hold the raw data, line by line
        List<String> rawDataList = null;

        // (try & catch) = exceptions are accounted for.
        // (try to run this command on this file but run the catch method if an exception occurs)
        try {

            // csv file is imported, read line-by-line and added to list in the same fashion
            rawDataList = Files.readAllLines(Paths.get(file));

            // "Error" will be displayed if an exception is caught (csv file cannot be found or is corrupted)
        } catch (IOException e) {
            System.out.println("Error! File does not exist.");
            logger.error("Suggested file does not exist");
        }



        // first line of the ArrayList is removed as this is invalid data, used as headings
        rawDataList.remove(0);


        // counter made to count the line number of the csv file, during the for loop below
        int counter = 2;

        // each line in the data list is read and instances of transactions are created and assigned the correct variables.
        // the instances are then added to the ArrayList
        for (String line : rawDataList) {

            // a string array is created and the string before each comma is added to it
            String[] lineSplit = line.split(",");

            // an new transaction instance is created
            Transaction transaction = new Transaction();

            // each line of the string array ([0], [1], etc.) is assigned to the correct variable of the created instance
            // try/catch allows for the easy location of possible invalidity of data, from the csv file
            try {
                transaction.date = LocalDate.parse(lineSplit[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (Exception e) {
                logger.error("The 'date' inputted on line " + counter + ", of csv file, is invalid");
                System.out.println("The 'date' inputted on line " + counter + ", of csv file, is invalid");
                throw e;
            }
            try {
                transaction.from = lineSplit[1];
            } catch (Exception e) {
                logger.error("The 'from' name inputted on line " + counter + ", of csv file, is invalid");
                System.out.println("The 'from' name inputted on line " + counter + ", of csv file, is invalid");
                throw e;
            }
            try {
                transaction.to = lineSplit[2];
            } catch (Exception e) {
                logger.error("The 'to' name inputted on line " + counter + ", of csv file, is invalid");
                System.out.println("The 'to' name inputted on line " + counter + ", of csv file, is invalid");
                throw e;
            }
            try {
                transaction.narrative = lineSplit[3];
            } catch (Exception e) {
                logger.error("The 'narrative' inputted on line " + counter + ", of csv file, is invalid");
                System.out.println("The 'narrative' inputted on line " + counter + ", of csv file, is invalid");
                throw e;
            }
            try {
                transaction.amount = Double.parseDouble(lineSplit[4]);
            } catch (Exception e) {
                logger.error("The 'amount' inputted on line " + counter + ", of csv file, is invalid");
                System.out.println("The 'amount' inputted on line " + counter + ", of csv file, is invalid");
                throw e;
            }

            //the created transaction instance is added to the list of transaction instances
            transactionList.add(transaction);

            // counter increases, to match current csv file number
            counter++;
        }
    }


    public void readJSON(String file) {

    }


    public ArrayList getTransactionList() {
        return transactionList;
    }
}
