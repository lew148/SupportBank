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

    // creates decimal format toAccount be used in string
    DecimalFormat df = new DecimalFormat("###########0.00");

    // prints string giving name, amount owed and amount due of person's account
    public void printOweDue() {
        // doubles are formatted in monetary fashion (e.g £4.00005 --> £4.00)
        String OweDue = this.name + " owes £" + df.format(this.amountOwed) + " and is due £" + df.format(this.amountDue) + "!";
        System.out.println(OweDue);
    }

    // prints account name
    public void printName() {
        System.out.println(this.name);
    }
}