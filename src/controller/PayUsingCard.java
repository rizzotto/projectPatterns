package controller;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.*;

/**
 * STRATEGY: CARD
 */
public class PayUsingCard implements Strategy {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    private final Scanner in = new Scanner(System.in);
    private Card card;
    private String type;
    private String number;
    private String date;
    private String cvv;
    private boolean signedIn;


    public PayUsingCard(String type){
        this.type = type;
    }


    @Override
    public void paymentDetail() {
        while (!signedIn){
            System.out.print("Informe o número do cartão: ");
            number = in.nextLine();
            System.out.print("Informe a data de validade mm/yy: ");
            date = in.nextLine();
            System.out.print("Informe o código de segurança: ");
            cvv = in.nextLine();
            card = new Card(number, date, cvv);
            if (verify()) {
                System.out.println("Acesso autorizado.");
            } else {
                System.out.println("Dados incorretos!");
            }
        }
    }

    private boolean verify() {
        setSignedIn(isValidCVVNumber(cvv) && isValidDate(date) &&isValidCard(number));
        return signedIn;
    }

    public boolean isValidDate(String date) {
        date = date.trim();
        if (date.length() < 6)
            return false;

        String[] parts = date.split("/");
        if (parts.length != 2)
            return false;

        int month = 0;
        try {
            month = Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
        if (month < 1 || month > 12)
            return false;

        int year = 0;
        try {
            year = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
        if (year < 1000)
            return false;

        return true;
    }

    public boolean isValidCVVNumber(String str) {

        String regex = "^[0-9]{3,4}$";

        Pattern p = Pattern.compile(regex);

        if (str == null) {
            return false;
        }

        Matcher m = p.matcher(str);

        return m.matches();
    }

    public boolean isValidCard(String c) {
        return c.matches("[0-9]{16}");
    }

    @Override
    public boolean pay(int paymentAmount) {
        if (cardIsPresent() && verify()) {
            System.out.println(ANSI_RED + "Pagando R$" + paymentAmount + " usando Cartão de " + type + "." + ANSI_RESET);
            card.setTotal(card.getTotal() - paymentAmount);
            return true;
        } else {
            return false;
        }
    }

    private boolean cardIsPresent() {
        return card != null;
    }

    private void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }
}