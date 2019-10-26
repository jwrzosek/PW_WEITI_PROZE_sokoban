package com.company.elements;

import java.util.List;

/**
 * Klasa umozliwiajaca utworzenie obiektow Crate.
 * (obiektow bedacych skrzyniami, ktore gracz musi umiejscowic w odpowienim miejscu).
 */
public class Crate implements Movable {

    private int startX;
    private int startY;
    private int direction;
    private boolean finished;

    /**
     * @return wsporzedna  x objektu klasy Crate
     */
    @Override
    public int getStartX() { return this.startX; }

    /**
     * @param startX wartosc wspolrzednej x przypisywana do obiektu Crate
     */
    public void setStartX(int startX) { this.startX = startX; }

    /**
     * @return wspolrzedna  y obiektu klasy crate
     */
    @Override
    public int getStartY() {return this.startY; }

    /**
     * @param startY wartosc wspolrzednej y przypisywana do obiektu Crate
     */
    public void setStartY(int startY) {this.startY = startY; }

    /**
     * @return kierunek poruszania sie obiektu Crate
     */
    public int getDirection() {return this.direction;}

    /**
     * @param direction ustawia kierunek poruszania sie obiektu Crate
     */
    public void setDirection(int direction) {this.direction = direction;}

    /**
     * Metoda zmieniajaca wspolrzedne obiektu Crate
     * @param x warosc zmiany atrybutu startX
     * @param y warosc zmiany atrybutu startY
     */
    @Override
    public void move(int x, int y) {
        if (x == 1 && y == 0) {
            this.setDirection(1);
        } else if (x == 0 && y == 1){
            this.setDirection(2);
        }else if (x == -1 && y == 0){
            this.setDirection(3);
        }else if (x == 0  && y == -1){
            this.setDirection(4);
        }
        this.setStartX(this.getStartX() + x);
        this.setStartY(this.getStartY() + y);
    }

    /**
     * Srawdza czy w danym kierunku od okreslonego elementu wystepuje kolizja z innym elementem
     * @param direction okresla sprawdzany kierunek
     * @param element okresla element ktorego kolizje sprawdzamy
     * @return true jesli wystepuje kolizja
     */
    @Override
    public boolean collision(int direction, Element element){
        if (direction == 1) {
            if (((this.getStartX() + 1) == element.getStartX()) && (this.getStartY() == element.getStartY())) {
                return true;
            } else {
                return false;
            }
        }
        else if (direction == 2) {
            if ((this.getStartX() == element.getStartX()) && ((this.getStartY() + 1) == element.getStartY())) {
                return true;
            } else {
                return false;
            }
        }
        else if (direction == 3) {
            if (((this.getStartX() - 1) == element.getStartX()) && (this.getStartY() == element.getStartY())) {
                return true;
            } else {
                return false;
            }
        }
        else if (direction == 4) {
            if ((this.getStartX() == element.getStartX()) && ((this.getStartY() - 1) == element.getStartY())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Sprawdza czy w danym kierunku wystepuje kolizja ze sciana
     * @param direction okresla sprawdzany kierunek
     * @param element okresla element ktorego kolizje sprawdzamy
     * @param walls lista scian
     * @return true jesli wystepuje kolizja
     */
    @Override
    public boolean wallCollision(int direction, Element element, List<Wall> walls){
        if (direction == 1){
            for (int i = 0; i < walls.size(); i++) {
                Wall wall = walls.get(i);
                if (this.collision(1, wall)) {
                    return true;
                }
            }
            return false;
        }
        else if (direction == 2){
            for (int i = 0; i < walls.size(); i++) {
                Wall wall = walls.get(i);
                if (this.collision(2, wall)) {
                    return true;
                }
            }
            return false;
        }
        else if (direction == 3){
            for (int i = 0; i < walls.size(); i++) {
                Wall wall = walls.get(i);
                if (this.collision(3, wall)) {
                    return true;
                }
            }
            return false;
        }
        else if (direction == 4){
            for (int i = 0; i < walls.size(); i++) {
                Wall wall = walls.get(i);
                if (this.collision(4, wall)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    /**
     * Sprawdza czy w danym kierunku wystepuje kolizja ze skrzynia
     * @param direction okresla sprawdzany kierunek
     * @param element okresla element ktorego kolizje sprawdzamy
     * @param crates lista skrzyn
     * @return true jesli wystepuje kolizja
     */
    @Override
    public Crate crateCollision(int direction, Element element, List<Crate> crates){
        if(direction == 1){
            for (int i = 0; i < crates.size(); i++){
                Crate crate = crates.get(i);
                if (this.collision(1, crate)){
                    return crate;
                }
            }
            return null;
        }
        else if(direction == 2){
            for (int i = 0; i < crates.size(); i++){
                Crate crate = crates.get(i);
                if (this.collision(2, crate)){
                    return crate;
                }
            }
            return null;
        }
        else if(direction == 3){
            for (int i = 0; i < crates.size(); i++){
                Crate crate = crates.get(i);
                if (this.collision(3, crate)){
                    return crate;
                }
            }
            return null;
        }
        else if(direction == 4){
            for (int i = 0; i < crates.size(); i++){
                Crate crate = crates.get(i);
                if (this.collision(4, crate)){
                    return crate;
                }
            }
            return null;
        }
        return null;
    }

    /**
     * @param bool ustawia true jesli na polu finish znajduje sie skrzynia, w przeciwnym wypadku false
     */
    public void setFinished(boolean bool) {this.finished = bool;}

    /**
     * @return zwraca true jesli na polu finish znajduje sie skrzynia
     */
    public boolean getFinished() {return this.finished;}
}