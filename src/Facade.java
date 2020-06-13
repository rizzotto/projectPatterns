import controller.PayUsingCard;
import controller.PayPal;
import model.Order;
import model.Product;
import model.Strategy;

import java.io.IOException;
import java.util.*;

//Padrão de projeto: FACHADA
public class Facade {
    private final Scanner in = new Scanner(System.in);
    private static Order pedido;
    private static Strategy strategy;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static Product p1, p2, p3, p4, p5;

    public Facade(){

        p1 = new Product(35, "Cabo USB");
        p2 = new Product(7000, "Notebook Gamer");
        p3 = new Product(220, "Google Nest Mini");
        p4 = new Product(2500, "Nintendo Switch");
        p5 = new Product(36, "Livro do Harry Potter");
        singletonVerification();

    }

    public void makeOrder() throws IOException {

        while (!pedido.isClosed()) {
            int custo = 0;

            String escolha;
            System.out.println(ANSI_RED + "E-commerce do Rizzotto!!" + ANSI_RESET);
            do {

                System.out.print(ANSI_RED + "Selecione um produto:"  + ANSI_RESET + "\n" +
                        "1) " + p1.getName() + "\n" +
                        "2) " + p2.getName() + "\n" +
                        "3) " + p3.getName() + "\n" +
                        "4) " + p4.getName() + "\n"  +
                        "5) " + p5.getName() + "\n"
                );
                int choice = Integer.parseInt(in.nextLine());
                //Observer escuta o getValue()
                switch (choice) {
                    case 1: custo = p1.getValue();
                            break;
                    case 2: custo = p2.getValue();
                        break;
                    case 3: custo = p3.getValue();
                        break;
                    case 4: custo = p4.getValue();
                        break;
                    case 5: custo = p5.getValue();
                        break;
                }
                System.out.print("Quantos você deseja? ");
                int count = Integer.parseInt(in.nextLine());
                pedido.setTotalCost(custo * count);
                System.out.print("Gostaria de selecionar algum outro produto? S/N: ");
                escolha = in.nextLine();
            } while (escolha.equalsIgnoreCase("S"));

            if (strategy == null) {
                System.out.println(ANSI_RED + "Por favor, selecione a forma de pagamento:"  + ANSI_RESET + "\n" +
                        "1) PalPay" + "\n" +
                        "2) Cartão de Crédito" + "\n" +
                        "3) Cartão de Débito"
                );
                String metodoPagamento = in.nextLine();

                if (metodoPagamento.equals("1")) {
                    strategy = new PayPal();
                } else if(metodoPagamento.equals("2")){
                    strategy = new PayUsingCard("Crédito");
                }
                else {
                    strategy = new PayUsingCard("Débito");
                }

                // Utilizando o padrao STRATEGY
                pedido.processOrder(strategy);

                System.out.print("Pagar R$" + pedido.getTotalCost() + " reais ou continuar comprando? P/C: ");
                String continuar = in.nextLine();
                if (continuar.equalsIgnoreCase("P")) {

                    if (strategy.pay(pedido.getTotalCost())) {
                        System.out.println(ANSI_GREEN + "Pedido realizado." + ANSI_RESET);
                    } else {
                        System.out.println("Erro! por favor, verifique os dados inseridos");
                    }
                    pedido.setClosed();
                }
            }
        }
    }

    //Aplica o padrão de projeto SINGLETON definindo apenas uma instância de por pedido
    private static void singletonVerification() {
        if(pedido == null) {
            pedido = new Order(p1, p2, p3, p4, p5);
        }
    }

}
