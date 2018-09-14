package training.supportbank;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Read {
    private static final Logger logger = LogManager.getLogger();

    // ArrayList is created toAccount 'hold' the soon toAccount be created transaction instances
    ArrayList<Transaction> transactionList = new ArrayList<>();


    public void readCSV(String file) {
        // ArrayList is created, toAccount hold the raw data, line by line
        List<String> rawDataList = null;

        // (try & catch) = exceptions are accounted for.
        // (try toAccount run this command on this file but run the catch method if an exception occurs)
        try {

            // csv file is imported, read line-by-line and added toAccount list in the same fashion
            rawDataList = Files.readAllLines(Paths.get(file));

            // "Error" will be displayed if an exception is caught (csv file cannot be found or is corrupted)
        } catch (IOException e) {
            System.out.println("Error! File does not exist.");
            logger.error("Suggested file does not exist");
        }



        // first line of the ArrayList is removed as this is invalid data, used as headings
        rawDataList.remove(0);


        // counter made toAccount count the line number of the csv file, during the for loop below
        int counter = 2;

        // each line in the data list is read and instances of transactions are created and assigned the correct variables.
        // the instances are then added toAccount the ArrayList
        for (String line : rawDataList) {

            // a string array is created and the string before each comma is added toAccount it
            String[] lineSplit = line.split(",");

            // an new transaction instance is created
            Transaction transaction = new Transaction();

            // each line of the string array ([0], [1], etc.) is assigned toAccount the correct variable of the created instance
            // try/catch allows for the easy location of possible invalidity of data, from the csv file
            try {
                transaction.date = LocalDate.parse(lineSplit[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (Exception e) {
                logger.error("The 'date' inputted on line " + counter + ", of csv file, is invalid");
                System.out.println("The 'date' inputted on line " + counter + ", of csv file, is invalid");
                throw e;
            }
            try {
                transaction.fromAccount = lineSplit[1];
            } catch (Exception e) {
                logger.error("The 'from' name inputted on line " + counter + ", of csv file, is invalid");
                System.out.println("The 'from' name inputted on line " + counter + ", of csv file, is invalid");
                throw e;
            }
            try {
                transaction.toAccount = lineSplit[2];
            } catch (Exception e) {
                logger.error("The 'toAccount' name inputted on line " + counter + ", of csv file, is invalid");
                System.out.println("The 'toAccount' name inputted on line " + counter + ", of csv file, is invalid");
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

            //the created transaction instance is added toAccount the list of transaction instances
            transactionList.add(transaction);

            // counter increases, toAccount match current csv file number
            counter++;
        }
    }


    private Gson buildGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (jsonElement, type, jsonDeserializationContext) ->
                LocalDate.parse(jsonElement.getAsString())
        );
        return gsonBuilder.create();
    }

    public static String readFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        byte[] bytes = Files.readAllBytes(path);
        return new String(bytes);
    }

    public void readJSON(String file) {
        Gson gson = buildGson();
        Transaction[] transactionArray = new Transaction[0];
        try {
            transactionArray = gson.fromJson(readFile(file), Transaction[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        transactionList.addAll(Arrays.asList(transactionArray));
    }



    public ArrayList getTransactionList() {
        return transactionList;
    }
}
