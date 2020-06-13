package model;


import java.util.Observable;

public class Product extends Observable {

    private int value;
    private String name;

    public Product(int value, String name) {
        this.value = value;
        this.name = name;

    }


    public int getValue() {
        setChanged();
        notifyObservers();

        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
