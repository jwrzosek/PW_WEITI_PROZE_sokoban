package com.company;

import com.company.elements.*;

import javax.swing.*;
import java.awt.*;





/**
 * Klasa odopowiedzialna za wyswietlenie graficznych elementow aplikacji.
 */

public class View2 extends JPanel {

    private Element[][] movableElements;
    private Element[][] staticElements;
    private Map sizeMap;
    private int direction;
    private int dX;
    private Parameters parameters;
    private Teleport teleport;

    /**
     * Konstruktor klasy View2.
     */
    public View2(){
        this.parameters = new Parameters();
        sizeMap = new Map();
        repaint();
    }

    /**
     * Metoda majaca zadanie pobrac informacje o nicku, punktach oraz poziomie trudnosci z klasy Controller.
     * @param p obiekt klasy Parameters, z ktorego pobieramy informacje
     */
    public void getParameters(Parameters p){
        this.parameters.setNewPlayerNick(p.getNewPlayerNick());
        this.parameters.setNewPlayerScore(p.getNewPlayerScore());
        this.parameters.setDifficultyLevel(p.getDifficultyLevel());
    }

    /**
     * Metoda majaca zadanie pobrac informacje o ilosci dostepnych "mocy" oraz ustalonej liczbie krokow nie objetych ujemnymi punktami.
     * @param p obiekt klasy Parameters, z ktorego pobieramy informacje
     */
    public void getDifficulty(Parameters p){
        this.parameters.setMovesNumber(p.getMovesNumber());

        this.parameters.setPullsNumber(p.getPullsNumber());
        this.parameters.setResetsNumber(p.getResetsNumber());
        this.parameters.setTeleportsNumber(p.getTeleportsNumber());
        this.parameters.setDestroysNumber(p.getDestroysNumber());
    }

    /**
     * Metoda pobierajaca liczbe ruchow z klasy Conroller w celu wyswietlenia jej na ekranie rozgrywki.
     * @param p obiekt klasy Parameters, z ktorego pobieramy informacje
     */
    public void getNumberOfMoves(Parameters p){ this.parameters.setNumberOfMoves(p.getNumberOfMoves()); }

    /**
     * Metoda pobierajaca liczbe resetow z klasy Conroller w celu wyswietlenia jej na ekranie rozgrywki.
     * @param p obiekt klasy Parameters, z ktorego pobieramy informacje
     */
    public void getResetsNumber(Parameters p) {this.parameters.setResetsNumber(p.getResetsNumber());}

    /**
     * Metoda pobierajaca liczbe teleportow z klasy Conroller w celu wyswietlenia jej na ekranie rozgrywki.
     * @param p obiekt klasy Parameters, z ktorego pobieramy informacje
     */
    public void getTeleportsNumber(Parameters p) {this.parameters.setTeleportsNumber(p.getTeleportsNumber());}

    /**
     * Metoda pobierajaca liczbe cofniec z klasy Conroller w celu wyswietlenia jej na ekranie rozgrywki.
     * @param p obiekt klasy Parameters, z ktorego pobieramy informacje
     */
    public void getPullsNumber(Parameters p) {this.parameters.setPullsNumber(p.getPullsNumber());}

    /**
     * Metoda pobierajaca liczbe usuniec skrzyn z klasy Conroller w celu wyswietlenia jej na ekranie rozgrywki.
     * @param p obiekt klasy Parameters, z ktorego pobieramy informacje
     */
    public void getDestroysNumber(Parameters p) {this.parameters.setDestroysNumber(p.getDestroysNumber());}

    /**
     * Metoda liczaca punkty na podstawie wybranego przez gracza poziomu trudnosci.
     * Liczba punktow obliczana jest na podstawie wzoru: ((1000 - ((liczba ruchow gracza - liczba dostepnych krokow nie objetych ujemnymi punktami) * a)
     * gdzie a - mnoznik zalezny od poziomu trudnosci (im wyzszy poziom tym liczbe odejmowanych punktow mnozymy przez mniejszy mnoznik), wartosc mnoznika dla
     * poszczegolnych poziomow: easy(50), medium(40), hard(20).
     * @param difficulty poziom trudnosci wybrany przez gracza
     */
    public void countScore(String difficulty) {
        if (difficulty == "Easy") {
            this.parameters.setNewPlayerScore((1000 - ((parameters.getNumberOfMoves() - parameters.getMovesNumber()) * 50)));
        } else if (difficulty == "Medium"){
            this.parameters.setNewPlayerScore((1000 - ((parameters.getNumberOfMoves() - parameters.getMovesNumber()) * 40)));
        } else if (difficulty == "Hard"){
            this.parameters.setNewPlayerScore((1000 - ((parameters.getNumberOfMoves() - parameters.getMovesNumber()) * 20)));
        }
    }

    /**
     * @return zwraca liczbe punktow po ukonczonym poziomie
     */
    public int getCompleteLevelScore(){return this.parameters.getNewPlayerScore();}

    /**
     *P rzekazuje elementy potrzebne do narysowania oraz animowania rozgrywki
     * @param elements1 tablica elementow ruchomych
     * @param elements2 tablica elementow statycznych
     * @param dir kierunek poruszania sie gracza
     * @param dX krok animcji
     */
    public void setElements(Element[][] elements1, Element[][] elements2, int dir, int dX) {
        this.staticElements = elements2;
        this.movableElements = elements1;
        this.direction = dir;
        this.dX = dX;
    }

    public void setTeleport(Teleport teleport) {this.teleport = teleport;}

    public Teleport getTeleport() {return this.teleport;}

    /**
     * Opisuje sposob rysowania elementow rozgrywki.
     */
    public void paint(Graphics g) {
        int elementWidth = this.getWidth()/sizeMap.getSize();
        int elementHeight = this.getHeight()/sizeMap.getSize();

        for (int i = 0; i < staticElements.length; i++) {
            for (int j = 0; j < staticElements.length; j++) {
                Element element = staticElements[i][j];
                if (element instanceof Wall) {
                    g.setColor(Color.blue);
                    g.fillRect(j * elementWidth, i * elementHeight, elementWidth, elementHeight);
                } else if (element instanceof Finish) {
                    g.setColor(Color.yellow);
                    g.fillRect(j * elementWidth, i *elementHeight, elementWidth, elementHeight);
                }
                else {
                    g.setColor(Color.gray);
                    g.fillRect(j * elementWidth, i * elementHeight, elementWidth, elementHeight);
                }
            }
        }

        if (this.direction == 0) {
            for (int i = 0; i < movableElements.length; i++) {
                for (int j = 0; j < movableElements.length; j++) {
                    Element element = movableElements[i][j];
                    if (element instanceof Player) {
                        g.setColor(Color.cyan);
                        g.fillOval(j *elementWidth, i * elementHeight, elementWidth, elementHeight);
                    } else if (element instanceof Crate) {
                    	if(((Crate) element).getFinished()) {
                    		g.setColor(Color.green);
                            g.fillRect(j * elementWidth, i * elementHeight, elementWidth, elementHeight);
                    	}
                    	else {
                        g.setColor(Color.magenta);
                        g.fillRect(j * elementWidth, i * elementHeight, elementWidth, elementHeight);
                    	}
                    }
                    	
                }
            }
            if (teleport.getVisible()) {
                g.setColor(Color.red);
                g.fillOval(((teleport.getStartX()) * elementWidth), teleport.getStartY() * elementHeight, elementWidth, elementHeight);
            }
        }

        if (this.direction == 1) {

            for (int i = 0; i < movableElements.length; i++) {
                for (int j = 0; j < movableElements.length; j++) {
                    Element element = movableElements[i][j];
                    if (element instanceof Player){
                        if (((Player) element).getDirection() == 1) {
                            g.setColor(Color.cyan);
                            g.fillOval((j - 1) * elementWidth + dX, i * elementHeight, elementWidth, elementHeight);
                        }
                        else {
                            g.setColor(Color.cyan);
                            g.fillOval(j * elementWidth, i * elementHeight, elementWidth, elementHeight);
                        }
                    }
                    if (element instanceof Crate){
                        if (((Crate) element).getDirection() == 1) {
                            g.setColor(Color.magenta);
                            g.fillRect((j - 1) * elementWidth + dX, i * elementHeight, elementWidth, elementHeight);
                        }
                        else if(((Crate) element).getFinished()){
                            g.setColor(Color.green);
                            g.fillRect(j * elementWidth, i * elementHeight, elementWidth, elementHeight);
                        }
                        else {
                            g.setColor(Color.magenta);
                            g.fillRect(j * elementWidth, i * elementHeight, elementWidth, elementHeight);
                        }
                    }
                }
            }
            if (teleport.getVisible()) {
                g.setColor(Color.red);
                g.fillOval(((teleport.getStartX() - 1) * elementWidth) + dX, teleport.getStartY() * elementHeight, elementWidth, elementHeight);
            }
        }

        if (this.direction == 2) {
            for (int i = 0; i < movableElements.length; i++) {
                for (int j = 0; j < movableElements.length; j++) {
                    Element element = movableElements[i][j];
                    if (element instanceof Player){
                        if (((Player) element).getDirection() == 2) {
                            g.setColor(Color.cyan);
                            g.fillOval(j * elementWidth, (i-1) * elementHeight+dX, elementWidth, elementHeight);
                        }
                        else {
                            g.setColor(Color.cyan);
                            g.fillOval(j * elementWidth, i * elementHeight, elementWidth, elementHeight);
                        }
                    }
                    if (element instanceof Crate){
                        if (((Crate) element).getDirection() == 2) {
                            g.setColor(Color.magenta);
                            g.fillRect(j * elementWidth, (i-1) * elementHeight +dX, elementWidth, elementHeight);
                        }
                        else if(((Crate) element).getFinished()){
                            g.setColor(Color.green);
                            g.fillRect(j * elementWidth, i * elementHeight, elementWidth, elementHeight);
                        }
                        else {
                            g.setColor(Color.magenta);
                            g.fillRect(j * elementWidth, i * elementHeight, elementWidth, elementHeight);
                        }
                    }
                }
            }
            if (teleport.getVisible()) {
                g.setColor(Color.red);
                g.fillOval(teleport.getStartX() * elementWidth, (teleport.getStartY() - 1) * elementHeight + dX, elementWidth, elementHeight);
            }
        }

        if (this.direction == 3) {

            for (int i = 0; i < movableElements.length; i++) {
                for (int j = 0; j < movableElements.length; j++) {
                    Element element = movableElements[i][j];
                    if (element instanceof Player){
                        if (((Player) element).getDirection() == 3) {
                            g.setColor(Color.cyan);
                            g.fillOval((j+1) * elementWidth-dX, i * elementHeight, elementWidth, elementHeight);
                        }
                        else {
                            g.setColor(Color.cyan);
                            g.fillOval(j * elementWidth, i * elementHeight, elementWidth, elementHeight);
                        }
                    }
                    if (element instanceof Crate){
                        if (((Crate) element).getDirection() == 3) {
                            g.setColor(Color.magenta);
                            g.fillRect((j+1) * elementWidth-dX, i * elementHeight, elementWidth, elementHeight);
                        }
                        else if(((Crate) element).getFinished()){
                            g.setColor(Color.green);
                            g.fillRect(j * elementWidth, i * elementHeight, elementWidth, elementHeight);
                        }
                        else {
                            g.setColor(Color.magenta);
                            g.fillRect(j *elementWidth, i * elementHeight, elementWidth, elementHeight);
                        }
                    }
                }
            }
            if (teleport.getVisible()) {
                g.setColor(Color.red);
                g.fillOval((teleport.getStartX() + 1) * elementWidth - dX, teleport.getStartY() * elementHeight, elementWidth, elementHeight);
            }
        }

        if (this.direction == 4) {
            for (int i = 0; i < movableElements.length; i++) {
                for (int j = 0; j < movableElements.length; j++) {
                    Element element = movableElements[i][j];
                    if (element instanceof Player){
                        if (((Player) element).getDirection() == 4) {
                            g.setColor(Color.cyan);
                            g.fillOval(j * elementWidth, (i+1) * elementHeight-dX, elementWidth, elementHeight);
                        }
                        else {
                            g.setColor(Color.cyan);
                            g.fillOval(j * elementWidth, i * elementHeight, elementWidth, elementHeight);
                        }
                    }
                    if (element instanceof Crate){
                        if (((Crate) element).getDirection() == 4) {
                            g.setColor(Color.magenta);
                            g.fillRect(j * elementWidth, (i+1) * elementHeight-dX, elementWidth, elementHeight);
                        }
                        else if(((Crate) element).getFinished()){
                            g.setColor(Color.green);
                            g.fillRect(j * elementWidth, i * elementHeight, elementWidth, elementHeight);
                        }
                        else {
                            g.setColor(Color.magenta);
                            g.fillRect(j * elementWidth, i * elementHeight, elementWidth, elementHeight);
                        }
                    }
                }
            }
            if (teleport.getVisible()) {
                g.setColor(Color.red);
                g.fillOval(teleport.getStartX() * elementWidth, (teleport.getStartY() + 1) * elementHeight - dX, elementWidth, elementHeight);
            }
        }

        this.countScore(parameters.getDifficultyLevel());
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, elementHeight/3));
        g.drawString("Nick: " + parameters.getNewPlayerNick(), elementHeight/3, (elementHeight/3)+(elementHeight/4));
        g.drawString("Poziom trudnosci: " + parameters.getDifficultyLevel() + "  |  Ruchy: "  + parameters.getNumberOfMoves(), elementHeight/3, (2*elementHeight/3)+(elementHeight/4));
        g.drawString("Resety: " + parameters.getResetsNumber() + "  |  Teleporty: "  + parameters.getTeleportsNumber(), elementHeight/3, (3*elementHeight/3)+(elementHeight/4));
        g.drawString("Cofniecia: " + parameters.getPullsNumber()  + "  |  Usuniecia: " + parameters.getDestroysNumber(),elementHeight/3, (4*elementHeight/3)+(elementHeight/4));
    }
}
















