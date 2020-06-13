import model.Order;

import java.io.IOException;

public class App {

    public static Order pedido;

    public static void main(String[] args) {


      //Implementa padrão de projeto FACHADA, onde um ponto de acesso é definido
      Facade fachada = new Facade();
        try {
            fachada.makeOrder();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
