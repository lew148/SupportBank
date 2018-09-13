package training.supportbank;

import java.time.LocalDate;

public class Transaction {
    public LocalDate date;
    public String from = "";
    public String to = "";
    public String narrative = "";
    public Double amount;

    public void printTransaction() {
        String transaction = "On " + this.date + " " + this.from + " borrowed £" + this.amount + " from " + this.to + " because of " + this.narrative + "!";
        System.out.println(transaction);
    }
}
