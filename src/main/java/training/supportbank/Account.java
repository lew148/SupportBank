package training.supportbank;

import java.util.ArrayList;
import java.util.List;

public class Account {
    public String name = "";
    public Double amountOwed;
    public Double amountDue;


    //list of transactions for the specific account instance is created upon instance creation
    public List<Transaction> accountTransactions = new ArrayList<>();


    public void printOweDue() {
        String OweDue = this.name + " owes £" + Double.toString(this.amountOwed) + " and is due £" + Double.toString(this.amountDue) + "!";
        System.out.println(OweDue);
    }

    public void printName() {
        System.out.println(this.name);
    }
}