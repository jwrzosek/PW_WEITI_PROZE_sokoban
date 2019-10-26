package com.company;

public class Difficulty {

    private int resetsNumber;
    private int teleportsNumber;
    private int pullsNumber;
    private int destroysNumber;

    private int movesNumber;

    /**
     * Konstruktor klasy Difficulty.
     */
    public Difficulty(){
        this.resetsNumber = 0;
        this.teleportsNumber = 0;
        this.pullsNumber = 0;
        this.destroysNumber = 0;

        this.movesNumber = 0;
    }

    /**
     * @return liczbe dostepnych resetow
     */
    public int getResetsNumber() {return this.resetsNumber; }

    /**
     * @param resetsNumber liczba resetow przypisywana obiektowi klasy Difficulty
     */
    public void setResetsNumber(int resetsNumber) { this.resetsNumber = resetsNumber; }

    /**
     * @return ustalona liczbe krokow powyzej ktorej gracz zdobywa ujemne punkty
     */
    public int getMovesNumber() {return this.movesNumber; }

    /**
     * @param movesNumber liczba krokow nie punktowanych ujemnie przypisywana obiektowi klasy Difficulty
     */
    public void setMovesNumber(int movesNumber) {this.movesNumber = movesNumber; }

    /**
     * @return liczbe dostepnych teleportow
     */
    public int getTeleportsNumber() {return this.teleportsNumber; }

    /**
     * @param teleportsNumber liczba teleportow przypisywana obiektowi klasy Difficulty
     */
    public void setTeleportsNumber(int teleportsNumber) {this.teleportsNumber = teleportsNumber; }

    /**
     * @return liczbe dostepnych cofniec
     */
    public int getPullsNumber() {return this.pullsNumber; }

    /**
     * @param pullsNumber liczba cofniec przypisywana obiektowi klasy Difficulty
     */
    public void setPullsNumber(int pullsNumber) {this.pullsNumber = pullsNumber; }

    /**
     * @return liczbe dostepnych usuniec skrzyn
     */
    public int getDestroysNumber() {return this.destroysNumber; }

    /**
     * @param destroysNumber liczba usuniec przypisywana obiektowi klasy Difficulty
     */
    public void setDestroysNumber(int destroysNumber) {this.destroysNumber = destroysNumber; }

}
