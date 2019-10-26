package com.company;

public class Parameters {
    // gracz
    private String newPlayerNick;
    private int newPlayerScores;

    private int newPlayerFinalScores;

    // poziom trudnosci
    private String difficultyLevel;
    private int numberOfMoves;

    // difficulty
    private int movesNumber;

    private int resetsNumber;
    private int teleportsNumber;
    private int pullsNumber;
    private int destroysNumber;


    /**
     * Konstruktor klasy Parameters.
     */
    public void Parameters(){
        this.newPlayerNick = "";
        this.newPlayerScores = 0;

        this.newPlayerFinalScores = 0;

        this.difficultyLevel = "";
        this.numberOfMoves = 0;

        this.movesNumber = 0;

        this.resetsNumber = 0;
        this.teleportsNumber = 0;
        this.pullsNumber = 0;
        this.destroysNumber = 0;
    }

    public void getDifficulty(Difficulty d){
        this.setMovesNumber(d.getMovesNumber());

        this.setPullsNumber(d.getPullsNumber());
        this.setResetsNumber(d.getResetsNumber());
        this.setTeleportsNumber(d.getTeleportsNumber());
        this.setDestroysNumber(d.getDestroysNumber());
    }

    /**
     * Metoda zwiekszajaca liczbe krokow gracza.
     */
    public void increaseNumberOfMoves(){ numberOfMoves = numberOfMoves + 1; }

    /**
     * Metoda zmiejszajaca liczbe dostepnych resetow
     */
    public void decreaseResetsNumber(){resetsNumber = resetsNumber - 1; }

    /**
     * Metoda zmniejszajaca liczbe dostepnych teleportow.
     */
    public void decreaseTeleportsNumber(){teleportsNumber = teleportsNumber - 1; }

    /**
     * Metoda zmniejszajaca liczbe dostepnych usuniec skrzyn.
     */
    public void decreaseDestroysNumber(){destroysNumber = destroysNumber - 1; }

    /**
     * Metoda zmniejszajaca liczba dostepnych cofniec.
     */
    public void decreasePullsNumber(){pullsNumber = pullsNumber - 1; }

    /**
     * Metoda zerujaca ilosc krokow gracza (np. przy przechodzeniu do kolejnego poziomu).
     */
    public void resetNumberOfMoves(){ numberOfMoves = 0; }

    /**
     * Metoda zerujaca calkowita pule punkow gracza (po przejsciu gry i ewentualnym zapisaniu wyniku do pliku z najlepszymi wynikami).
     */
    public void resetNewPlayerFinalScore(){ newPlayerFinalScores = 0;}


    /**
     * @return nick gracza.
     */
    public String getNewPlayerNick(){return newPlayerNick;}

    /**
     * @return punkty gracza (liczone w kazdej planszy)
     */
    public int getNewPlayerScore(){return newPlayerScores;}

    /**
     * @return sume punktow gracza (sume punkow z wszystkich plansz/calkowita ilosc punktow)
     */
    public int getNewPlayerFinalScore(){return newPlayerFinalScores;}

    /**
     * @return wybrany przez gracza poziom trudnosci
     */
    public String getDifficultyLevel(){return difficultyLevel;}

    /**
     * @return liczbe wykonanych przez gracza ruchow/krokow
     */
    public int getNumberOfMoves(){return numberOfMoves;}

    /**
     * @param nick zmienna typu String zawierajaca nick gracza, ktora ma zostac przypisana do obiektu klasy Parameters
     */
    public void setNewPlayerNick(String nick){this.newPlayerNick = nick;}

    /**
     * @param score liczba punktow, ktora ma zostac przypisana do obiektu klasy Parameters przechowujacego informacje rozgrywce
     */
    public void setNewPlayerScore(int score){this.newPlayerScores = score;}

    /**
     * @param score liczba punktow, o ktora ma zostac powiekszona calkowita liczba punktow gracza
     */
    public void setNewPlayerFinalScore(int score){this.newPlayerFinalScores = this.newPlayerFinalScores + score;}

    /**
     * @param diff zmienna typu String zawierajaca poziom trudnosci wybrany przez gracza, ktora ma zostac przypisana
     *             do obiektu klasy Parameters przechowujacego informacje o rozgrywce
     */
    public void setDifficultyLevel(String diff){this.difficultyLevel = diff;}

    /**
     * @param moves liczba krokow ktora ma zostac przypisana do obiektu klasy Parameters, ktory przechowuje
     *              informacje o rozgrywce
     */
    public void setNumberOfMoves(int moves){ this.numberOfMoves = moves;}

    /**
     * @param movesNumber liczba ustalonych krokow nie objetych ujemna punktacja, ktora ma zostac przypisana
     *                    do obiektu klasy Parameters przechowujacego informacje o rozgrywce
     */
    public void setMovesNumber(int movesNumber) {this.movesNumber = movesNumber;}

    /**
     * @param resetsNumber liczba resetow, ktora ma zostac przypisana do obiektu klasy Parameters przechowujacego informacje o rozgrywce
     */
    public void setResetsNumber(int resetsNumber) {this.resetsNumber = resetsNumber;}

    /**
     * @param teleportsNumber liczba teleportow, ktora ma zostac przypisana do obiektu klasy Parameters przechowujacego informacje o rozgrywce
     */
    public void setTeleportsNumber(int teleportsNumber) {this.teleportsNumber = teleportsNumber;}

    /**
     * @param pullsNumber liczba cofniec skrzyn, ktora ma zostac przypisana do obiektu klasy Parameters przechowujacego informacje o rozgrywce
     */
    public void setPullsNumber(int pullsNumber) {this.pullsNumber = pullsNumber;}

    /**
     * @param destroysNumber liczba usuniec skrzyn, ktora ma zostac przypisana do obiektu klasy Parameters przechowujacego informacje o rozgrywce
     */
    public void setDestroysNumber(int destroysNumber) {this.destroysNumber = destroysNumber;}

    /**
     * @return liczbe dozwolonych (nie objetych ujemnymi punktami) krokow
     */
    public int getMovesNumber() {return  this.movesNumber;}

    /**
     * @return dostepna na danym poziomie liczbe resetow
     */
    public int getResetsNumber() {return this.resetsNumber;}

    /**
     * @return dostepna na danym poziomie liczbe teleportow
     */
    public int getTeleportsNumber() {return this.teleportsNumber;}

    /**
     * @return dostepna na danym poziomie liczbe cofniec
     */
    public int getPullsNumber() {return this.pullsNumber;}

    /**
     * @return dostepna na danym poziomie liczbe usuniec skrzyn
     */
    public int getDestroysNumber() {return this.destroysNumber;}

    /**
     * Metoda modyfikujaca przewidziana w pliku konfiguracyjnym liczbe "mocy" w zaleznosci od wybranego poziomu trudnosci.
     * @param a mnoznik (zmienny w zaleznosci od poziomu: easy(3), medium(1), hard(0)
     */
    public void modifyPowers(int a){
        setDestroysNumber(destroysNumber*a);
        setTeleportsNumber(teleportsNumber*a);
        setPullsNumber(pullsNumber*a);
        setResetsNumber(resetsNumber*a);
    }
}
