package com.company;

/**
 *  Klasa glowna main odpowiedzialna za uruchomienie aplikacji.
 */
public class Main {

    public static void main(String[] args){
        GameConfiguration g = new GameConfiguration();
        g.getConfiguration();

        Controller controller = new Controller();
        controller.ShowMenu();
    }
}
