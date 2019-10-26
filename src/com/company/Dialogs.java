package com.company;

import javax.swing.*;
import java.awt.*;

/**
 *  Klasa odpowiedzialna za wyswietlanie okienek dialogowych.
 */
public class Dialogs {
    private String[] choices = { "Easy", "Medium", "Hard"};

    private Component winningLevelFrame;
    private Component errorNickFrame;
    private Component nextLevelFrame;
    private Component gamePlayExitFrame;
    private Component cannotFindFileFrame;

    /**
     * Konstruktor klasy Dialogs.
     */
    public Dialogs(){
        this.winningLevelFrame = new JFrame("YOU WIN!!!");
        this.errorNickFrame = new JFrame("Error");
        this.nextLevelFrame = new JFrame("Next level");
        this.gamePlayExitFrame = new JFrame();
        this.cannotFindFileFrame = new JFrame("Error");
    }

    /**
     * Wyswietla okno dialogowe po zakonczeniu gry. W oknie wyswietlany jest nick oraz punkty zdobyte przez gracza.
     * @param nick okresla nick identyfikujacy uzytkownika
     * @param scores okresla ilosc punktow zdobytych przez uzytkownika
     */
    public void showWinningLevelFrame(String nick, int scores) {
        JOptionPane.showMessageDialog(winningLevelFrame, "Gratulacje " + nick +"!!!\nWygrałes :)\n" +
                        "Zdobyłes: " + scores + " punktow.","Winning message", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Wyswietla okno dialogowe informujace uzytkownika, iz podany przez niego nick jest niedozwolony.
     */
    public void showErrorNickFrame() {
        JOptionPane.showMessageDialog(errorNickFrame, "Podales niedozwolony nick. Sprobuj ponownie!!!");
    }

    /**
     * Wyswietla okno dialogowe w momencie, w ktorym uzytkownik wciska przycisk Escape. Zwraca wartosc
     * na podstawie ktorej nastepuje obsluga wybranej przez uzytkownika opcji.
     * @return warosc decyzyjna, potrzebna do podjecia krokow obslugi decyzji uzytkownika
     */
    public int showGamePlayExitFrame(){
        Object[] options = {"Tak","Nie"};
        int n = JOptionPane.showOptionDialog(gamePlayExitFrame, "Czy na pewno chcesz wrocic do menu glownego?",
                "Exit",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);
        return n;
    }

    /**
     * Wyswietla okienko dialogowe informujace gracza o zakonczonym poziomie i
     * pozwalajace przejsc do kolejnej planszy.
     */
    public void showNextLevelFrame() {
        JOptionPane.showMessageDialog(nextLevelFrame, "Kliknij ok, aby przejsc do nastepnej planszy!!!",
                "Next Level message", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Wyswietla okienko dialogowe, w ktorym uzytkownik proszony jest o podanie nicku.
     * @return wybrany przez gracza nick (dowolna zmienna typu String)
     */
    public String showNickInputFrame(){
        String nick = JOptionPane.showInputDialog("Please enter your nick: ");
        return nick;
    }

    /**
     * Wyswietla okienko dialogowe, w ktorym gracz proszony jest o wybranie poziomu trudnosci.
     * @return wybrany przez gracza poziom trudnosci (Easy, Medium, Hard)
     */
    public String showChooseDifficultyLevelWindow(){
        String difficultyLevel = (String) JOptionPane.showInputDialog(null, "Choose now...",
                "Wybierz poziom trudnosci", JOptionPane.QUESTION_MESSAGE, null,  // Use
                // default
                // icon
                choices, // Array of choices
                choices[1]); // Initial choice
        if (difficultyLevel == null){
            return "error";
        } else{
            return difficultyLevel;
        }
    }

    public void showCannotFindFileFrame() {
        JOptionPane.showMessageDialog(cannotFindFileFrame, "Error. Could not find file.");
    }

}
