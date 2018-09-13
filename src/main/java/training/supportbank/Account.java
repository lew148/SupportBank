package training.supportbank;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Account {
    public String name = "";
    public Double amountOwed;
    public Double amountDue;


    //list of transactions for the specific account instance is created upon instance creation
    public List<Transaction> accountTransactions = new ArrayList<>();

    DecimalFormat df = new DecimalFormat("###########0.00");

    // prints string giving name, amount owed and amount due of person's account
    public void printOweDue() {
        String OweDue = this.name + " owes £" + df.format(this.amountOwed) + " and is due £" + df.format(this.amountDue) + "!";
        System.out.println(OweDue);
    }

    // prints account name
    public void printName() {
        System.out.println(this.name);
    }
}