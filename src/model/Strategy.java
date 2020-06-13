package model;

/**
 * Implementa o padrão de projeto STRATEGY e FACTORY, para utilizar diversas formas de pagamento
 */
public interface Strategy {
    boolean pay(int value);
    void paymentDetail();
}