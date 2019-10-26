package com.company;

import com.company.configuration.ConfigParser;
import com.company.elements.Crate;
import com.company.elements.Finish;
import com.company.elements.Player;
import com.company.elements.Wall;
import com.company.elements.Teleport;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.Timer;

import java.util.concurrent.TimeUnit;

import java.util.List;

/**
 * Klasa odpowiedzialna za uruchomienie gry.
 */
public class Controller extends JPanel implements ActionListener, KeyListener {

    private Map sizeMap;
    private Dialogs dialogs;
    private Results results;
    private Parameters parameters;
    private GameConfiguration gameConfiguration;

    private JFrame mainWindow;
    private JFrame gamePlayWindow;
    private JFrame bestResultsWindow;

    private JPanel menuPanel;
    private JPanel bestResultsPanel;

    private JButton startGameButton;
    private JButton bestResultsButton;
    private JButton exitButton;

    private JButton bestResultsExitButton;

    private List<Wall> walls;
    private Player player;
    private List<Finish> finishes;
    private List<Crate> crates;
    private View2 view2;
    private Teleport teleport;
    private Map map;
    private ConfigParser configParser;
    private Level config;

    private Timer timer;

    private int dX;
    private int dXtemp;
    private int dir;
    private String path;

    private int levelNumber;
    private String[] pathTable;

    private boolean isMoving;
    private boolean isTeleporting;
    private boolean isPaused;


    /**
     * Konstruktor klasy Controller
     */
    public Controller(){
        this.sizeMap = new Map();
        this.results = new Results(); this.results.setResults();
        this.dialogs = new Dialogs();
        this.parameters = new Parameters();
        this.gameConfiguration = new GameConfiguration(); gameConfiguration.getConfiguration();
        this.view2 = new View2();
        this.configParser = new ConfigParser();
        this.teleport = new Teleport();

        /* mainMenuWindow */
        this.mainWindow = new JFrame();
        mainWindow.setLayout(new BorderLayout());
        mainWindow.setMinimumSize(new Dimension(sizeMap.getBoardSize()-100,sizeMap.getBoardSize()-100));
        mainWindow.setPreferredSize(new Dimension(sizeMap.getBoardSize(), sizeMap.getBoardSize()));
        mainWindow.pack();
        mainWindow.setTitle("SOKOBAN MENU");
        mainWindow.setLocationRelativeTo(null); // ustawia okno w centrum ekranu
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        /* gamePlayWindow */
        this.gamePlayWindow = new JFrame();
        gamePlayWindow.setMinimumSize(new Dimension(250, 250));
        gamePlayWindow.setPreferredSize(new Dimension(sizeMap.getBoardSize()+16, sizeMap.getBoardSize()+39));
        gamePlayWindow.pack();
        gamePlayWindow.setBackground(Color.GRAY);
        gamePlayWindow.setLayout(new BorderLayout());
        gamePlayWindow.setTitle("gamePlayWindow");
        gamePlayWindow.setLocationRelativeTo(null); // ustawia okno w centrum ekranu
        gamePlayWindow.addKeyListener(this);
        gamePlayWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* bestResultsWindow */
        this.bestResultsWindow = new JFrame();
        bestResultsWindow.setLayout(new BorderLayout());
        bestResultsWindow.setMinimumSize(new Dimension(sizeMap.getBoardSize()-100,sizeMap.getBoardSize()-100));
        bestResultsWindow.setPreferredSize(new Dimension(sizeMap.getBoardSize(), sizeMap.getBoardSize()));
        bestResultsWindow.pack();
        bestResultsWindow.setTitle("Sokoban Best Results");
        bestResultsWindow.setLocationRelativeTo(null); // ustawia okno w centrum ekranu
        bestResultsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Panele */
        this.menuPanel = new JPanel();
        this.bestResultsPanel = new JPanel();

        /* Buttony */
        this.startGameButton = new JButton("Nowa gra");
        this.bestResultsButton = new JButton("Najlepsze wyniki");
        this.exitButton = new JButton("Wyjdz z gry");

        this.bestResultsExitButton = new JButton("Powrot do Menu Glownego");

        this.timer = new Timer(10, this);
    }

    /**
     * Metoda wyswietlająca Menu Głowne.
     */
    public void ShowMenu() {

        menuPanel.setMinimumSize(new Dimension(sizeMap.getBoardSize()/2,sizeMap.getBoardSize()/2));
        menuPanel.setPreferredSize(new Dimension(sizeMap.getBoardSize()/2,sizeMap.getBoardSize()/2));
        menuPanel.setBackground(Color.BLACK);
        menuPanel.setLayout(new GridLayout(4,1,0,20));

        startGameButton.setPreferredSize(new Dimension(200,50));
        startGameButton.addActionListener(this);
        menuPanel.add(startGameButton);

        bestResultsButton.setPreferredSize(new Dimension(200,50));
        bestResultsButton.addActionListener(this);
        menuPanel.add(bestResultsButton);

        exitButton.setPreferredSize(new Dimension(200,50));
        exitButton.addActionListener(this);
        menuPanel.add(exitButton);

        // Panele potrzebne do wypelnienia pol w BorderLayoutcie
        JPanel northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(sizeMap.getBoardSize()/1,sizeMap.getBoardSize()/4));
        northPanel.setLayout(new FlowLayout());
        northPanel.setBackground(Color.BLACK);
        JLabel northMenuLabel = new JLabel("<html>--MENU--<br>SOKOBAN</html>", JLabel.CENTER);
        northMenuLabel.setFont(new Font("Verdana",2,30));
        northPanel.add(northMenuLabel, BorderLayout.NORTH);

        JPanel southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(sizeMap.getBoardSize(),sizeMap.getBoardSize()/4));
        southPanel.setBackground(Color.BLACK);

        JPanel westPanel = new JPanel();
        westPanel.setPreferredSize(new Dimension(sizeMap.getBoardSize()/4,sizeMap.getBoardSize()/2));
        westPanel.setBackground(Color.BLACK);

        JPanel eastPanel = new JPanel();
        eastPanel.setPreferredSize(new Dimension(sizeMap.getBoardSize()/4,sizeMap.getBoardSize()/2));
        eastPanel.setBackground(Color.BLACK);
        // --------------------------------------------------------

        mainWindow.add(northPanel, BorderLayout.NORTH);
        mainWindow.add(menuPanel, BorderLayout.CENTER);
        mainWindow.add(southPanel, BorderLayout.SOUTH);
        mainWindow.add(westPanel, BorderLayout.WEST);
        mainWindow.add(eastPanel, BorderLayout.EAST);

        mainWindow.setVisible(true);
    }

    /**
     * Inicjalizuje okno rozgrywki.
     */
    public void ShowGamePlay() throws IOException {

        levelNumber = 0;
        this.path = gameConfiguration.getPaths()[levelNumber];

        resetLevel(this.path);
        view2.setTeleport(new Teleport());

        map = new Map(walls, player, crates, finishes);
        view2.setElements(map.getMap(1), map.getMap(2), dir, dX);

        view2.getParameters(parameters); // nick i pierdoly
        this.initDiffParameters(parameters.getDifficultyLevel());

        gamePlayWindow.setVisible(true);
        gamePlayWindow.requestFocus();
        mainWindow.setVisible(false);
        gamePlayWindow.add(view2);

    }

    /**
     * Otwiera okno dialogowe majace na celu pobranie od uzytkownika nicku oraz poziomu trudnosci.
     */
    public void openNewGameDialogWindow(){
        String nick = dialogs.showNickInputFrame();
        if(nick != null) {
            if (nick.length() > 0) {
                parameters.setNewPlayerNick(nick);
                parameters.setDifficultyLevel(dialogs.showChooseDifficultyLevelWindow());
                if (parameters.getDifficultyLevel() != "error") {
                    try {
                        ShowGamePlay();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                dialogs.showErrorNickFrame();
            }
        }
    }

    /**
     *Pobiera dane konfiguracyjne poziomu oraz restuje go.
     * @param path sciezka pliku konfiguracyjnego
     * @throws IOException
     */
    public void resetLevel(String path) throws IOException{

        config = configParser.getConfig(path);
        walls = config.getWalls();
        player = config.getPlayer();
        finishes = config.getFinishes();
        crates = config.getCrates();
        parameters.resetNumberOfMoves();
        view2.getNumberOfMoves(parameters);
        dir=0;
        isTeleporting = false;
        isPaused = false;
        isMoving = false;

    }

    /**
     * Wyswietla okno z najlepszymi wynikami.
     */
    public void ShowBestResults(){
        bestResultsPanel.setMinimumSize(new Dimension(sizeMap.getBoardSize(),sizeMap.getBoardSize()*(3/4)));
        bestResultsPanel.setPreferredSize(new Dimension(sizeMap.getBoardSize(),sizeMap.getBoardSize()*(3/4)));
        bestResultsPanel.setLayout(new GridLayout(4,2,0,0));
        bestResultsPanel.setBackground(Color.lightGray);
        bestResultsPanel.removeAll();
        bestResultsWindow.add(bestResultsPanel, BorderLayout.CENTER);

        changeResultsLabels(bestResultsPanel);

        JPanel northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(sizeMap.getBoardSize(),sizeMap.getBoardSize()/8));
        northPanel.setLayout(new FlowLayout());
        northPanel.setBackground(Color.GRAY);
        JLabel jlabel = new JLabel("NAJLEPSZE WYNIKI", JLabel.CENTER);
        jlabel.setFont(new Font("Verdana",2,30));
        northPanel.add(jlabel, BorderLayout.NORTH);
        bestResultsWindow.add(northPanel, BorderLayout.NORTH);


        JPanel southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(sizeMap.getBoardSize(),sizeMap.getBoardSize()/8));
        southPanel.setBackground(Color.GRAY);
        bestResultsExitButton.addActionListener(this);
        bestResultsExitButton.setPreferredSize(new Dimension(sizeMap.getBoardSize(),sizeMap.getBoardSize()/8));
        southPanel.add(bestResultsExitButton);
        bestResultsWindow.add(southPanel, BorderLayout.SOUTH);

        bestResultsWindow.add(bestResultsPanel);
        bestResultsWindow.setVisible(true);
        mainWindow.setVisible(false);
    }

    /**
     * Ustawia/zmienia w oknie 3 najlepsze rezultaty zapisane w pliku results.txt.
     * @param panel panel, do ktorego wstawiane sa labele z nickami oraz punktami 3 najlepszych graczy.
     */
    public void changeResultsLabels(JPanel panel){
        JLabel nickLabel = new JLabel("Nick:", JLabel.CENTER);
        nickLabel.setForeground(Color.magenta);
        nickLabel.setFont(new Font("Lucida Fax",1,15));
        panel.add(nickLabel);

        JLabel scoresLabel = new JLabel("Wynik:", JLabel.CENTER);
        scoresLabel.setForeground(Color.magenta);
        scoresLabel.setFont(new Font("Lucida Fax",1,15));
        panel.add(scoresLabel);


        JLabel firstPlaceNickLabel = new JLabel("1. " + results.getFirstPlaceNick(), JLabel.CENTER);
        firstPlaceNickLabel.setForeground(Color.magenta);
        firstPlaceNickLabel.setFont(new Font("Lucida Fax",1,15));
        panel.add(firstPlaceNickLabel);

        JLabel firstPlaceScoresLabel = new JLabel(Integer.toString(results.getFirstPlaceScores()), JLabel.CENTER);
        firstPlaceScoresLabel.setForeground(Color.magenta);
        firstPlaceScoresLabel.setFont(new Font("Lucida Fax",1,15));
        panel.add(firstPlaceScoresLabel);

        JLabel secondPlaceNickLabel = new JLabel("2. " + results.getSecondPlaceNick(), JLabel.CENTER);
        secondPlaceNickLabel.setForeground(Color.magenta);
        secondPlaceNickLabel.setFont(new Font("Lucida Fax",1,15));
        panel.add(secondPlaceNickLabel);

        JLabel secondPlaceScoresLabel = new JLabel(Integer.toString(results.getSecondPlaceScores()), JLabel.CENTER);
        secondPlaceScoresLabel.setForeground(Color.magenta);
        secondPlaceScoresLabel.setFont(new Font("Lucida Fax",1,15));
        panel.add(secondPlaceScoresLabel);

        JLabel thirdPlaceNickLabel = new JLabel("3. " + results.getThirdPlaceNick(), JLabel.CENTER);
        thirdPlaceNickLabel.setForeground(Color.magenta);
        thirdPlaceNickLabel.setFont(new Font("Lucida Fax",1,15));
        panel.add(thirdPlaceNickLabel);

        JLabel thirdPlaceScoresLabel = new JLabel(Integer.toString(results.getThirdPlaceScores()), JLabel.CENTER);
        thirdPlaceScoresLabel.setForeground(Color.magenta);
        thirdPlaceScoresLabel.setFont(new Font("Lucida Fax",1,15));
        panel.add(thirdPlaceScoresLabel);
    }

    public void actionPerformed(ActionEvent event){
        if (event.getSource() == startGameButton) {
            openNewGameDialogWindow();
        }
        else if(event.getSource() == bestResultsButton) {
            ShowBestResults();
        }
        else if(event.getSource() == exitButton) {
            System.exit(0);
        }
        else if(event.getSource() == bestResultsExitButton) {
            bestResultsWindow.setVisible(false);
            mainWindow.setVisible(true);
        }
        else if (event.getSource() == timer){
            updateGameplay(dir);
        }
    }

    public void keyTyped(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (!isMoving && !isPaused) {
            dX = 0;

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                dir = 1;
                if (!isTeleporting) {
                    if (player.wallCollision(dir, player, walls)) {
                        return;
                    } else if (player.crateCollision(dir, player, crates) != null) {
                        Crate crate = player.crateCollision(dir, player, crates);
                        if (e.isShiftDown() && parameters.getDestroysNumber() > 0) {

                            parameters.decreaseDestroysNumber();
                            view2.getDestroysNumber(parameters);

                            crates.remove(crate);
                            dir = 0;
                            this.updateGameplay(dir);
                            this.levelComplete();
                            isMoving = false;

                        }

                        if (crate.wallCollision(dir, crate, walls)) {
                            return;
                        } else if (crate.crateCollision(dir, crate, crates) != null) {
                            return;
                        } else if (crate.crateCollision(dir, crate, crates) == null) {
                            if (!e.isShiftDown()) {
                                crate.move(1, 0);
                                player.move(1, 0);
                                parameters.increaseNumberOfMoves();
                                view2.getNumberOfMoves(parameters);
                                timer.start();
                                this.updateGameplay(dir);
                                this.levelComplete();
                            }
                        }
                    } else if (player.crateCollision(dir, player, crates) == null) {
                        Crate crate = player.crateCollision(3, player, crates);
                        if (e.isControlDown() && player.crateCollision(3, player, crates) != null && parameters.getPullsNumber() > 0) {
                            crate.move(1, 0);
                            parameters.decreasePullsNumber();
                            view2.getPullsNumber(parameters);
                        }
                        player.move(1, 0);
                        parameters.increaseNumberOfMoves();
                        view2.getNumberOfMoves(parameters);
                        timer.start();
                        this.updateGameplay(dir);
                        this.levelComplete();
                    }
                } else if (isTeleporting) {
                    if (teleport.wallCollision(dir, teleport, walls)) {
                        return;
                    } else {
                        teleport = view2.getTeleport();
                        teleport.move(1, 0);
                        view2.setTeleport(teleport);
                        timer.start();
                        this.updateGameplay(dir);
                        this.levelComplete();
                    }
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                dir = 2;
                if (!isTeleporting ) {
                    if (player.wallCollision(dir, player, walls)) {
                        return;
                    } else if (player.crateCollision(dir, player, crates) != null) {
                        Crate crate = player.crateCollision(dir, player, crates);
                        if (e.isShiftDown() && parameters.getDestroysNumber() > 0) {

                            parameters.decreaseDestroysNumber();
                            view2.getDestroysNumber(parameters);

                            crates.remove(crate);
                            dir = 0;
                            this.updateGameplay(dir);
                            this.levelComplete();
                            isMoving = false;
                        }
                        if (crate.wallCollision(dir, crate, walls)) {
                            return;
                        } else if (crate.crateCollision(dir, crate, crates) != null) {
                            return;
                        } else if (crate.crateCollision(dir, crate, crates) == null) {
                            if (!e.isShiftDown()) {
                                crate.move(0, 1);
                                player.move(0, 1);
                                parameters.increaseNumberOfMoves();
                                view2.getNumberOfMoves(parameters);
                                timer.start();
                                this.updateGameplay(dir);
                                this.levelComplete();
                            }
                        }
                    } else if (player.crateCollision(dir, player, crates) == null) {
                        Crate crate = player.crateCollision(4, player, crates);
                        if (e.isControlDown() && player.crateCollision(4, player, crates) != null && parameters.getPullsNumber() > 0) {
                            crate.move(0, 1);
                            parameters.decreasePullsNumber();
                            view2.getPullsNumber(parameters);
                        }
                        player.move(0, 1);
                        parameters.increaseNumberOfMoves();
                        view2.getNumberOfMoves(parameters);
                        timer.start();
                        this.updateGameplay(dir);
                        this.levelComplete();
                    }
                } else if (isTeleporting) {
                    if (teleport.wallCollision(dir, teleport, walls)) {
                        return;
                    } else {
                        teleport = view2.getTeleport();
                        teleport.move(0, 1);
                        view2.setTeleport(teleport);
                        timer.start();
                        this.updateGameplay(dir);
                        this.levelComplete();
                    }
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                dir = 3;
                if (!isTeleporting) {
                    if (player.wallCollision(dir, player, walls)) {
                        return;
                    } else if (player.crateCollision(dir, player, crates) != null) {
                        Crate crate = player.crateCollision(dir, player, crates);
                        if (e.isShiftDown() && parameters.getDestroysNumber() > 0) {

                            parameters.decreaseDestroysNumber();
                            view2.getDestroysNumber(parameters);

                            crates.remove(crate);
                            dir = 0;
                            this.updateGameplay(dir);
                            this.levelComplete();
                            isMoving = false;
                        }
                        if (crate.wallCollision(dir, crate, walls)) {
                            return;
                        } else if (crate.crateCollision(dir, crate, crates) != null) {
                            return;
                        } else if (crate.crateCollision(dir, crate, crates) == null) {
                            if (!e.isShiftDown()) {
                                crate.move(-1, 0);
                                player.move(-1, 0);
                                parameters.increaseNumberOfMoves();
                                view2.getNumberOfMoves(parameters);
                                timer.start();
                                this.updateGameplay(dir);
                                this.levelComplete();
                            }
                        }
                    } else if (player.crateCollision(dir, player, crates) == null) {
                        Crate crate = player.crateCollision(1, player, crates);
                        if (e.isControlDown() && player.crateCollision(1, player, crates) != null && parameters.getPullsNumber() > 0) {
                            crate.move(-1, 0);
                            parameters.decreasePullsNumber();
                            view2.getPullsNumber(parameters);
                        }
                        player.move(-1, 0);
                        parameters.increaseNumberOfMoves();
                        view2.getNumberOfMoves(parameters);
                        timer.start();
                        this.updateGameplay(dir);
                        this.levelComplete();
                    }
                } else if (isTeleporting) {
                    if (teleport.wallCollision(dir, teleport, walls)) {
                        return;
                    } else {
                        teleport = view2.getTeleport();
                        teleport.move(-1, 0);
                        view2.setTeleport(teleport);
                        timer.start();
                        this.updateGameplay(dir);
                        this.levelComplete();
                    }
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                dir = 4;
                if (!isTeleporting) {
                    if (player.wallCollision(dir, player, walls)) {
                        return;
                    } else if (player.crateCollision(dir, player, crates) != null) {
                        Crate crate = player.crateCollision(dir, player, crates);
                        if (e.isShiftDown() && parameters.getDestroysNumber() > 0) {

                            parameters.decreaseDestroysNumber();
                            view2.getDestroysNumber(parameters);

                            crates.remove(crate);
                            dir = 0;
                            this.updateGameplay(dir);
                            this.levelComplete();
                            isMoving = false;
                        }
                        if (crate.wallCollision(dir, crate, walls)) {
                            return;
                        } else if (crate.crateCollision(dir, crate, crates) != null) {
                            return;
                        } else if (crate.crateCollision(dir, crate, crates) == null) {
                            if (!e.isShiftDown()) {
                                crate.move(0, -1);
                                player.move(0, -1);
                                parameters.increaseNumberOfMoves();
                                view2.getNumberOfMoves(parameters);
                                timer.start();
                                this.updateGameplay(dir);
                                this.levelComplete();
                            }
                        }
                    } else if (player.crateCollision(dir, player, crates) == null) {
                        Crate crate = player.crateCollision(2, player, crates);
                        if (e.isControlDown() && player.crateCollision(2, player, crates) != null && parameters.getPullsNumber() > 0) {
                            crate.move(0, -1);
                            parameters.decreasePullsNumber();
                            view2.getPullsNumber(parameters);
                        }
                        player.move(0, -1);
                        parameters.increaseNumberOfMoves();
                        view2.getNumberOfMoves(parameters);
                        timer.start();
                        this.updateGameplay(dir);
                        this.levelComplete();
                    }
                } else if (isTeleporting) {
                    if (teleport.wallCollision(dir, teleport, walls)) {
                        return;
                    } else {
                        teleport = view2.getTeleport();
                        teleport.move(0, -1);
                        view2.setTeleport(teleport);
                        timer.start();
                        this.updateGameplay(dir);
                        this.levelComplete();
                    }
                }
            }
        }


        if (e.getKeyCode() == KeyEvent.VK_R) {
            if (parameters.getResetsNumber() > 0) {
                try {
                    this.resetLevel(path);
                    parameters.decreaseResetsNumber();
                    view2.getResetsNumber(parameters);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                this.updateGameplay(dir);
                isMoving = false;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_P) {
            isPaused = !isPaused;
            if(!isPaused){
                dX = dXtemp;
                timer.start();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_T) {
            if (parameters.getTeleportsNumber() > 0 && !isMoving && !isPaused) {
                teleport = view2.getTeleport();
                dir = 0;
                int k = 0;
                for (int i = 0; i < crates.size(); i++) {
                    Crate c = crates.get(i);
                    if (c.getStartX() == teleport.getStartX() && c.getStartY() == teleport.getStartY()) {
                        k = k + 1;
                    }
                }
                if (k == 0) {
                    isTeleporting = !isTeleporting;
                    if (isTeleporting) {
                        teleport.setVisible(true);
                        teleport.setStartX(player.getStartX());
                        teleport.setStartY(player.getStartY());
                    } else if (!isTeleporting) {
                        player.setStartX(teleport.getStartX());
                        player.setStartY(teleport.getStartY());
                        teleport.setVisible(false);

                        parameters.decreaseTeleportsNumber();
                        view2.getTeleportsNumber(parameters);
                    }
                    this.updateGameplay(dir);
                    isMoving = false;
                }
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (dialogs.showGamePlayExitFrame() == JOptionPane.YES_OPTION) {
                gamePlayWindow.setVisible(false);
                mainWindow.setVisible(true);
            }
        }
    }

    /**
     * Odpowiada za animacje w zaleznosci od kierunku poruszania sie gracza
     * @param dir kierunek poruszania
     */
    public void updateGameplay(int dir) {

        int elementWidth = view2.getWidth()/sizeMap.getSize();
        int elementHeight = view2.getHeight()/sizeMap.getSize();

        if(dir == 1 || dir == 3) {
            dX = dX + (elementWidth / sizeMap.getSize());
        }
        else{
            dX = dX + (elementHeight / sizeMap.getSize());
        }

        dXtemp = dX;
        map.updateMap(walls, player, crates, finishes);
        view2.setElements(map.getMap(1), map.getMap(2), dir, dX );
        view2.repaint();
        isMoving = true;
        if(isPaused){
            timer.stop();
            isMoving = false;
            return;
        }
        if(dir == 1 || dir == 3) {
            if (dXtemp >= elementWidth) {
                timer.stop();
                isMoving = false;
                this.resetDirections();
                dXtemp = 0;
            }
        }
        else {
            if (dXtemp >= elementHeight) {
                timer.stop();
                isMoving = false;
                this.resetDirections();
                dXtemp = 0;
            }
        }
    }

    /**
     * Sprawdza warunek zakonczenia poziomu
     * @return true jesli wszystkie skrzynie znajduja sie na miejscach docelowych
     */
    public boolean checkCompletion() {
        int k = 0;
        this.resetFinishes();
        for (int i = 0; i < crates.size(); i++) {
            Crate c = crates.get(i);
            for (int j = 0; j < finishes.size(); j++) {
                Finish f = finishes.get(j);
                if (c.getStartX() == f.getStartX() && c.getStartY() == f.getStartY()) {
                    k = k+1;
                    c.setFinished(true);
                }
            }
        }
        if (k == crates.size()) return true;
        else return false;
    }

    /**
     * Resetuje kierunek w jakim poruszaly sie obiekty, potrzebne do resetu poziomu
     */
    public void resetDirections(){
        for (int i = 0; i< crates.size(); i++) {
            Crate c = crates.get(i);
            c.setDirection(0);
        }
        player.setDirection(0);
    }

    /**
     * Ustawia miejsca docelowe jako niepokryte przez skrzynie
     */
    public void resetFinishes(){
        for (int i = 0; i< crates.size(); i++) {
            Crate c = crates.get(i);
            c.setFinished(false);
        }
    }

    /**
     * Laduje kolejny poziom po skonczeniu poprzedniego.
     * Po skonczeniu ostataniego poziomu sprawdza czy uzyksnay wynik kwalifikuje sie na liste najlepszych wynikow.
     */
    public void levelComplete(){
        if(checkCompletion()) {
            parameters.setNewPlayerFinalScore(view2.getCompleteLevelScore());// dodawanie punktów po ukończonym levelu do puli punktów gracza
            levelNumber = levelNumber + 1;
            if (levelNumber < gameConfiguration.getNumberOfLevels()) {
                dialogs.showNextLevelFrame();
                path = gameConfiguration.getPaths()[levelNumber];
                try {
                    this.resetLevel(path);

                    this.initDiffParameters(parameters.getDifficultyLevel());

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                this.updateGameplay(dir);
            }
            else if (levelNumber == gameConfiguration.getNumberOfLevels()){
                results.setResults();
                results.replaceResults(parameters.getNewPlayerFinalScore(),parameters.getNewPlayerNick());
                dialogs.showWinningLevelFrame(parameters.getNewPlayerNick(), parameters.getNewPlayerFinalScore());
                parameters.resetNewPlayerFinalScore();
                gamePlayWindow.setVisible(false);
                mainWindow.setVisible(true);
            }
        }
    }

    /**
     * Ustawia mozliwa do wykorzystania ilosc dodatkowych umiejetnosci
     * (resety, cofanie skrzyn, usuwanie skrzyn, teleporty) w zaleznosci od poziomu trudnosci.
     * @param difficulty wybrany poziom trudnosci
     */
    public void initDiffParameters(String difficulty) {
        parameters.getDifficulty(config.getDifficulty());

        if (difficulty == "Easy") {
            parameters.modifyPowers(3);
        } else if (difficulty == "Medium"){
            parameters.modifyPowers(1);
        } else if (difficulty == "Hard") {
            parameters.modifyPowers(0);
        }
        view2.getDifficulty(parameters);
    }
}





























