package training.supportbank;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {
    public LocalDate date;
    public String from = "";
    public String to = "";
    public String narrative = "";
    public Double amount;

}
