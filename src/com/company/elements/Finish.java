package com.company.elements;


/**
 *  Klasa umozliwiajaca utworzenie obiektow Finish
 * (obiektow bedacych miejscami docelowymi dla skrzyn).
 */
public class Finish implements Element {

    private int startX;
    private int startY;

    /**
     * @return wsporzedna  x objektu klasy Finish
     */
    @Override
    public int getStartX() {return this.startX; }

    /**
     * @param startX wartosc wspolrzednej x przypisywana do obiektu Finish
     */
    public void setStartX(int startX) { this.startX = startX; }

    /**
     * @return wspolrzedna  y obiektu klasy Finish
     */
    @Override
    public int getStartY() {return this.startY; }

    /**
     * @param startY wartosc wspolrzednej y przypisywana do obiektu Finish
     */
    public void setStartY(int startY) {this.startY = startY; }

}