package model;

import controller.PayPal;
import controller.PayUsingCard;

public class Factory {

    private static Strategy strategy;

    public Strategy factoryPayment(String metodoPagamento){
        if (metodoPagamento.equals("1")) {
            strategy = new PayPal();
        } else if(metodoPagamento.equals("2")){
            strategy = new PayUsingCard("Crédito");
        }
        else {
            strategy = new PayUsingCard("Débito");
        }

        return strategy;
    }
}
