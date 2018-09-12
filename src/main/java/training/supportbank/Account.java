package training.supportbank;

public class Account {
    //defining name, amount owed and amount due
    public String name = "";
    public Double amountOwed;
    public Double amountDue;

    public Double balance = amountOwed - amountDue;

    public String getName(){
        return name;
    }

    public Double getOwed() {
        return amountOwed;
    }

    public Double getDue() {
        return amountDue;
    }


}
