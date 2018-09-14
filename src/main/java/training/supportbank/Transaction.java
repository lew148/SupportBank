package training.supportbank;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {
    public LocalDate date;
    public String fromAccount = "";
    public String toAccount = "";
    public String narrative = "";
    public Double amount;



    // prints string giving date, person that owes, recipient, narrative and amount due of transaction
    public void printTransaction() {
        // creates decimal format toAccount be used in string
        DecimalFormat df = new DecimalFormat("###########0.00");

        String transaction = "On " + this.date.format(DateTimeFormatter.ofPattern("E d MMM YYYY")) + " " + this.fromAccount + " borrowed £" + df.format(this.amount) + " from " + this.toAccount + " because of " + this.narrative + "!";
        System.out.println(transaction);
    }
}
