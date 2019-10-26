package com.company.elements;

/**
 * * Klasa umozliwiajaca utworzenie obiektow Wall
 * (obiektow bedacych przeszkodami).
 */
public class Wall implements Element {

    private int startX;
    private int startY;

    /**
     * @return wsporzedna  x objektu klasy Crate
     */
    @Override
    public int getStartX() {
        return this.startX;
    }

    /**
     * @param startX wartosc wspolrzednej x przypisywana do obiektu Crate
     */
    public void setStartX(int startX) {
        this.startX = startX;
    }

    /**
     * @return wspolrzedna  y obiektu klasy crate
     */
    @Override
    public int getStartY() {
        return this.startY;
    }

    /**
     * @param startY wartosc wspolrzednej y przypisywana do obiektu Crate
     */
    public void setStartY(int startY) {
        this.startY = startY;
    }
}