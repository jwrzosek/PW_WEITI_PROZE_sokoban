package com.company;

import com.company.elements.Element;
import com.company.elements.Player;
import com.company.elements.Wall;
import com.company.elements.Crate;
import com.company.elements.Finish;

import java.util.ArrayList;
import java.util.List;


/**
 * Klasa odpowiedzialna za przechowywanie wszystkich elementow rozgrywki takich jak:
 * sciany, skrzynie, miejsca docelowe dla skrzyn oraz gracz.
 */
public class Map {

    private List<Element> elements;
    private Element[][] map;
    private int size;
    private int boardSize;


    /**
     * Konstruktor Mapy inicjalizujacy elementy potrzebne do ustawienia preferowanych rozmiarow okna.
     */
    public Map(){
        this.size = 14;
        this.boardSize = 560;//336
    }

    /**
     * Konstruktor Mapy inicjalizujacy elementy gry.
     */
    public Map(List<Wall> walls, Player player, List<Crate> crates, List<Finish> finishes){
        this.elements = new ArrayList<>();
        this.elements.addAll(walls);
        this.elements.add(player);
        this.elements.addAll(crates);
        this.elements.addAll(finishes);
        this.size = 14;
    }

    /**
     * Jesli tablica jest pusta, wywolywana jest metoda resetMap();
     * @return tablice elementow
     */
    public Element[][] getMap(int i) {
        this.resetMovableMap(i);
        return this.map;
    }

    /**
     * @return zwrace rozmiar tablicy elementow.
     */
    public int getSize(){ return this.size; }

    /**
     * @return zwrace rozmiar tablicy elementow.
     */
    public int getBoardSize(){ return this.boardSize; }

    /**
     * Metoda wpisujaca wartosci(wspolrzedne) elementow ruchomych do tablicy.
     */
    public void resetMovableMap(int i) {
        Element[][] newMap = new Element[size][size];
        if(i == 1) {
            for (Element element : elements) {
                if (element instanceof Player || element instanceof Crate) {
                    newMap[element.getStartY()][element.getStartX()] = element;
                }
            }
        }
        if(i == 2) {
            for (Element element : elements) {
                if (element instanceof Wall || element instanceof Finish) {
                    newMap[element.getStartY()][element.getStartX()] = element;
                }
            }
        }
        map = newMap;
    }

    /**
     * Uaktualnia liste elementow.
     * @param walls lista scian
     * @param player gracz
     * @param crates lista skrzyn
     * @param finishes lista miejsc docelowych skrzyn
     */
    public void updateMap(List<Wall> walls, Player player, List<Crate> crates, List<Finish> finishes){
        this.elements.clear();
        this.elements.addAll(walls);
        this.elements.add(player);
        this.elements.addAll(crates);
        this.elements.addAll(finishes);
    }

}
