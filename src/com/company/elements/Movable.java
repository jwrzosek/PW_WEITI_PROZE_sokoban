package com.company.elements;

import java.util.List;

/**
 * Interfejs, ktory implemntuja wszystkie Klasy, ktorych obiety sa dynamicznymi elementami rozgrywki.
 */
public interface Movable extends Element {

    boolean collision(int direction, Element element);

    boolean wallCollision(int direction, Element element, List<Wall> walls);

    Crate crateCollision(int direction, Element element, List<Crate> crates);

    void move (int x, int y);

    int getDirection();

    void setDirection(int direction);
}
