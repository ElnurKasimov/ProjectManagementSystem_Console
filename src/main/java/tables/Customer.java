package tables;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Customer {
    private long customer_id;
    private String name;
    private Reputation reputation;

    public enum Reputation {
        respectable,
        trustworthy,
        insolvent
    }
}
