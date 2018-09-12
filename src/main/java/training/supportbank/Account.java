package training.supportbank;

import java.util.ArrayList;
import java.util.List;

public class Account {
    public String name = "";
    public Double amountOwed;
    public Double amountDue;

    public List<Transaction> accountTransactions = new ArrayList<>();
}
