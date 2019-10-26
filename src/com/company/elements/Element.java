package com.company.elements;

/**
 * Interfejs, ktory implemntuja wszystkie Klasy, ktorych obiety s elementami rozgrywki.
 */
public interface Element {

    /**
     * Metoda zwracajaca wspolrzedna  X przez obiekty klas implementujacych interfejs Element
     * (Player, Wall, Crate, Finish)
     */
    int getStartX();

    /**
     * Metoda zwracajaca wspolrzedna Y przez obiekty klas implementujacych interfejs Element
     * (Player, Wall, Crate, Finish)
     */
    int getStartY();
}
