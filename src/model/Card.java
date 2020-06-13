package model;

/**
 * Credit card class.
 */
public class Card {
    private int total;
    private String number;
    private String date;
    private String cvv;

    public Card(String number, String date, String cvv) {
        this.total = 100;
        this.number = number;
        this.date = date;
        this.cvv = cvv;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }
}