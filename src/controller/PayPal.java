package controller;

import model.Strategy;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * STRATEGY: PAYPAL
 */
public class PayPal implements Strategy {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    private static final Map<String, String> data = new HashMap<>();
    private final Scanner in = new Scanner(System.in);
    private String email;
    private String password;
    private boolean signedIn;

    static {
        data.put("rizzotto", "rizzotto@gmail.com");
        data.put("eu", "eu@gmail.com");
    }

    @Override
    public void paymentDetail() {
        while (!signedIn) {
            System.out.print("Informe seu email: ");
            email = in.nextLine();
            System.out.print("Informe sua senha: ");
            password = in.nextLine();
            if (verify()) {
                System.out.println("Acesso autorizado.");
            } else {
                System.out.println("Email ou senha incorretos!");
            }
        }

    }

    private boolean verify() {
        setSignedIn(email.equals(data.get(password)));
        return signedIn;
    }

    @Override
    public boolean pay(int paymentAmount) {
        if (signedIn) {
            System.out.println(ANSI_RED + "Pagando R$" + paymentAmount + " usando PayPal." + ANSI_RESET);
            return true;
        } else {
            return false;
        }
    }

    private void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }
}