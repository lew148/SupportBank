package training.supportbank;

import java.text.DecimalFormat;
import java.time.LocalDate;

public class Transaction {
    public LocalDate date;
    public String from = "";
    public String to = "";
    public String narrative = "";
    public Double amount;

    // creates decimal format to be used in string
    DecimalFormat df = new DecimalFormat("###########0.00");

    // prints string giving date, person that owes, recipient, narrative and amount due of transaction
    public void printTransaction() {
        String transaction = "On " + this.date + " " + this.from + " borrowed £" + df.format(this.amount) + " from " + this.to + " because of " + this.narrative + "!";
        System.out.println(transaction);
    }
}
