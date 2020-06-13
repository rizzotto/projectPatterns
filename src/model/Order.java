package model;

import java.util.Observable;
import java.util.Observer;

public class Order implements Observer {
    private int totalCost = 0;
    private boolean isClosed = false;

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    private Observable p1, p2, p3, p4, p5;

    public Order(Observable p1, Observable p2, Observable p3, Observable p4, Observable p5 ) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p5 = p5;
        p1.addObserver(this);
        p2.addObserver(this);
        p3.addObserver(this);
        p4.addObserver(this);
        p5.addObserver(this);
    }

    public void processOrder(Strategy strategy) {
        strategy.paymentDetail();
    }

    public void setTotalCost(int cost) {
        this.totalCost += cost;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed() {
        isClosed = true;
    }

    @Override
    public void update(Observable o, Object arg) {
        Product p = (Product) o;
        System.out.println(ANSI_RED + ((Product) o).getName() + " adicionado ao carrinho utilizando o padrão Observer." + ANSI_RESET);
    }
}