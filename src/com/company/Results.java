package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Formatter;
import java.util.Scanner;

/**
 * Klasa odpowiedzialna za zapisywanie i odczytywanie z pliku najlepszych wynikow.
 */
public class Results {

    private Scanner R_file;
    private Formatter F_file;
    private Dialogs dialogs;

    private String firstPlaceNick;
    private String secondPlaceNick;
    private String thirdPlaceNick;

    private int firstPlaceScores;
    private int secondPlaceScores;
    private int thirdPlaceScores;

    /**
     * Konstruktor klasy Results.
     */
    public Results(){
        this.dialogs = new Dialogs();

        this.firstPlaceNick = "";
        this.secondPlaceNick = "";
        this.thirdPlaceNick = "";

        this.firstPlaceScores = 0;
        this.secondPlaceScores = 0;
        this.thirdPlaceScores = 0;
    }

    /**
     * Metoda otwierajaca plik w celu jego formatowania
     */
    public void openFormatFile(){
        try{
            F_file = new Formatter(new File("results.txt"));
        } catch(Exception e) {
            dialogs.showCannotFindFileFrame();
        }
    }

    /**
     * Metoda zamykajaca sformatowany plik
     */
    public void closeFormatFile(){ F_file.close(); }

    /**
     * Metoda pozwalajaca zapisac nowe wyniki do pliku z najlepszymi wynikami
     */
    public void saveToFile(){
        F_file.format("%s %s%n%s %s%n%s %s%n", firstPlaceNick, Integer.toString(firstPlaceScores), secondPlaceNick, Integer.toString(secondPlaceScores),
                thirdPlaceNick, Integer.toString(thirdPlaceScores));
    }

    /* ------------------------------------------------------------------------------------------------- */

    /**
     * Metoda otwierajaca plik w celu odczytania najlepszych wynikow
     */
    public void openReadFile(){
        try{
            R_file = new Scanner(new File("results.txt"));
        } catch(Exception e) {
            dialogs.showCannotFindFileFrame();
        }
    }

    /**
     * Metoda za pomoca ktorej odczytujemy najlepsze wyniki z pliku
     */
    public void readFile(){
        while(R_file.hasNext()) {
            firstPlaceNick = R_file.next();
            firstPlaceScores = Integer.parseInt(R_file.next());
            secondPlaceNick = R_file.next();
            secondPlaceScores = Integer.parseInt(R_file.next());
            thirdPlaceNick = R_file.next();
            thirdPlaceScores = Integer.parseInt(R_file.next());
        }
    }

    /**
     * Metoda zamykajaca plik z ktorego odczytujemy wyniki
     */
    public void closeReadFile(){
        R_file.close();
    }

    /* ------------------------------------------------------------------------------------------------- */

    /**
     * Metoda wczytujaca z pliku wyniki w celu ich pozniejszego porownania
     */
    public void setResults(){
        openReadFile();
        readFile();
        closeReadFile();
    }

    /**
     * Metoda odpowiedzialna za porownanie obecnych wynikow z wynikiem nowego gracza
     */
    public int compareResult(int newPlayerScores){
        if (newPlayerScores > firstPlaceScores){
            System.out.println("pierwszy");
            return 1;
        } else if (newPlayerScores > secondPlaceScores){
            System.out.println("drugi");
            return 2;
        } else if (newPlayerScores > thirdPlaceScores) {
            System.out.println("trzeci");
            return 3;
        } else return 0;
    }

    /**
     * Metoda zapisujaca zaktualizowane wyniki do pliku
     */
    public void replaceResults(int newPlayerScores, String newPlayerNick){
        int scores = newPlayerScores;
        if (compareResult(scores) == 1){
            thirdPlaceScores = secondPlaceScores; thirdPlaceNick = secondPlaceNick;
            secondPlaceScores = firstPlaceScores; secondPlaceNick = firstPlaceNick;
            firstPlaceScores = newPlayerScores; firstPlaceNick = newPlayerNick;
        } else if (compareResult(scores) == 2){
            thirdPlaceScores = secondPlaceScores; thirdPlaceNick = secondPlaceNick;
            secondPlaceScores = newPlayerScores; secondPlaceNick = newPlayerNick;
        } else if (compareResult(scores) == 3){
            thirdPlaceScores = newPlayerScores; thirdPlaceNick = newPlayerNick;
        }
        openFormatFile();
        saveToFile();
        closeFormatFile();
    }

    /**
     * @return nick uzytkownika ktory zajmuje pierwsze miejsce w klasyfikacji najlepszych wynikow
     */
    public String getFirstPlaceNick(){return firstPlaceNick;}

    /**
     * @return nick uzytkownika ktory zajmuje drugie miejsce w klasyfikacji najlepszych wynikow
     */
    public String getSecondPlaceNick(){return secondPlaceNick;}

    /**
     * @return nick uzytkownika ktory zajmuje trzecie miejsce w klasyfikacji najlepszych wynikow
     */
    public String getThirdPlaceNick(){return thirdPlaceNick;}

    /**
     * @return wynik punktowy uzytkownika ktory zajmuje pierwsze miejsce w klasyfikacji
     */
    public int getFirstPlaceScores(){return firstPlaceScores;}

    /**
     * @return wynik punktowy uzytkownika ktory zajmuje drugie miejsce w klasyfikacji
     */
    public int getSecondPlaceScores(){return secondPlaceScores;}

    /**
     * @return wynik punktowy uzytkownika ktory zajmuje trzecie miejsce w klasyfikacji
     */
    public int getThirdPlaceScores(){return thirdPlaceScores;}
}
