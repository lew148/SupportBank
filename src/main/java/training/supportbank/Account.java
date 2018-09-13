package training.supportbank;

import java.util.ArrayList;
import java.util.List;

public class Account {
    public String name = "";
    public Double amountOwed;
    public Double amountDue;


    //list of transactions for the specific account instance is created upon instance creation
    public List<Transaction> accountTransactions = new ArrayList<>();


    // prints string giving name, amount owed and amount due of person's account
    public void printOweDue() {
        String OweDue = this.name + " owes £" + this.amountOwed + " and is due £" + this.amountDue + "!";
        System.out.println(OweDue);
    }

    // prints account name
    public void printName() {
        System.out.println(this.name);
    }
}