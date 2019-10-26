package com.company;

import com.company.elements.Crate;
import com.company.elements.Finish;
import com.company.elements.Player;
import com.company.elements.Wall;

import java.util.List;

/**
 * Klasa odopowiedzialna za przypisanie danych (z pliku konfiuguracyjnego) do obiektow.
 */
public class Level {

    private Player player;
    private List<Wall> walls;
    private List<Crate> crates;
    private List<Finish> finishes;

    private Difficulty difficulty;

    /**
     * @return zwraca obiekt klasy Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param player obiekt klasy Player przypisywany do obiektu klasy Level
     */
    public void setPlayer(Player player) { this.player = player; }

    /**
     * @return zwraca liste obiektow klasy Wall
     */
    public List<Wall> getWalls() { return this.walls; }

    /**
     * @param walls lista obiektow klasy Wall przypisywana do obiektu klasy Level
     */
    public void setWalls(List<Wall> walls) { this.walls = walls; }

    /**
     * @return zwraca liste obiektow klasy Crate
     */
    public List<Crate> getCrates() { return this.crates; }

    /**
     * @param crates lista obiektow klasy Crate przypisywana do obiektu klasy Level
     */
    public void setCrates(List<Crate> crates) { this.crates = crates; }

    /**
     * @return zwraca liste obiektow klasy Finish
     */
    public List<Finish> getFinishes() { return this.finishes; }

    /**
     * @param finishes lista obiektow klasy Finish przypisywana do obiektu klasy Level
     */
    public void setFinishes(List<Finish> finishes) { this.finishes = finishes; }

    /**
     * @return zwraca obiekt klasy Difficulty
     */
    public Difficulty getDifficulty() { return this.difficulty; }

    /**
     * @param difficulty obiekt klasy Difficulty przypisywany do obiektu klasy Level
     */
    public void setDifficulty(Difficulty difficulty) { this.difficulty = difficulty; }
}
