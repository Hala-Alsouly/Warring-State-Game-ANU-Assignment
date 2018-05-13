package comp1110.ass2.gui;

import comp1110.ass2.Placement;
import comp1110.ass2.WarringStatesGame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;

import static comp1110.ass2.WarringStatesGame.*;


// FIXME Task 9: Implement a basic playable Warring States game in JavaFX

public class Game extends Application {
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private GridPane board = new GridPane();
    private AnchorPane playersCollection = new AnchorPane();
    //private StackPane []playersArray;
    private BorderPane border = new BorderPane();
    private Button[] cardsButtons = new Button[36];
    private Text illegal = new Text();
    private final Group root = new Group();
    private String moveSequence = "";
    private int playerId = 0;
    int i1, i2, i3, i4 = 0;
    public int numPlayers;
    public int numAgents;
    public static String posChars = "456789YZ0123STUVWXMNOPQRGHIJKLABCDEF";
    private static final AudioClip error = new AudioClip(Game.class.getResource("assets/error.wav").toString());
    private Color[] flagColor = {Color.LIGHTYELLOW, Color.LIGHTBLUE, Color.PINK, Color.LIGHTGREEN, Color.LIGHTSALMON, Color.LAVENDERBLUSH, Color.LIGHTCORAL};


    //the menu bar
    private MenuBar menu() {
        MenuBar menuBar = new MenuBar();
        menuBar.setMinWidth(933);
        Menu menuFile = new Menu("File");
        MenuItem newGame = new MenuItem("New game");
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                //new popup window to select the number of players
                getPopup();
            }
        });
        //Exit the game
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                System.exit(0);
            }
        });
        menuFile.getItems().addAll(newGame, exit);
        menuBar.getMenus().addAll(menuFile);
        return menuBar;
    }

    //white button to keep the grid pane as it is
    private Button getWhiteButton() {
        Button wb = new Button();
        wb.setPrefSize(90, 90);
        BackgroundFill fill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
        wb.setBackground(new Background(fill));
        return wb;
    }

    //popup window to choose the players numbers
    private void getPopup() {
        Stage s = new Stage();
        s.setWidth(300);
        s.setHeight(200);
        ComboBox comboBox1 = new ComboBox();
        ComboBox comboBox2 = new ComboBox();
        Button button = new Button("Ok");
        Text textPlayers = new Text("Choose The Players Numbers:");
        Text textAgents = new Text("Choose The Roberts Numbers:");

        Text notification = new Text("");
        VBox popup1 = new VBox(textPlayers, comboBox1, textAgents, comboBox2, button, notification);
        popup1.setAlignment(Pos.CENTER);
        comboBox1.getItems().addAll("1", "2", "3", "4");
        comboBox1.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue == null) return;
                comboBox2.getItems().clear();
                String numPlayer = newValue.toString().trim();
                int nPlayer = Integer.valueOf(numPlayer);
                if (nPlayer != 1) {
                    comboBox2.getItems().add(0);
                }
                for (int i = 1; i <= 4 - nPlayer; i++) {
                    comboBox2.getItems().add(i);
                }
            }
        });

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int numP, numR;
                if (comboBox1.getValue() != null) {
                    if (comboBox2.getValue() == null) {
                        numR = 0;
                    } else {
                        numR = Integer.valueOf(comboBox2.getValue().toString());
                    }
                    numP = Integer.valueOf(comboBox1.getValue().toString());
                    numPlayers = numP + numR;
                    numAgents = numR;
                    s.close();
                    setupBoard();
                } else {
                    notification.setText("You have not selected the number of players!");
                }
//                if (comboBox1.getValue() != null) {
//                    notification.setText("");
//                    switch (comboBox1.getValue().toString()) {
//                        case "1":
//                            numPlayers = 1;
//                            break;
//                        case "2":
//                            numPlayers = 2;
//                            break;
//                        case "3":
//                            numPlayers = 3;
//                            break;
//                        case "4":
//                            numPlayers = 4;
//                            break;
//                    }
//                    if (comboBox2.getValue() != null) {
//                        notification.setText("");
//                        switch (comboBox2.getValue().toString()) {
//                            case "0":
//                                numAgents = 0;
//                                break;
//                            case "1":
//                                numAgents = 1;
//                                break;
//                            case "2":
//                                numAgents = 2;
//                                break;
//                            case "3":
//                                numAgents = 3;
//                                break;
//                        }
//                    }
//                    s.close();
//                    setupBoard();
//                } else {
//                    notification.setText("You have not selected the number of players!");
//                }
            }
        });

        s.setScene(new Scene(popup1));
//        s.setScene(new Scene(popup2));

        s.show();
    }

    public static int getPosInArray(char cardPos) {
        int index = posChars.indexOf(cardPos);
        return index;
    }

    //set every players collection
    //playerId is the player number, c is the collected card
    private void setPlayersCollection(Button c, int playerId) {
        //playersCollection.add(c, playerId/2, playerId%2);
        switch (playerId) {
            case 0:
                i1 += 5;
                c.setLayoutX(30 + (playerId % 2) * 100);
                c.setLayoutY(500 - (300 * (playerId / 2)) - i1);
                break;
            case 1:
                i2 += 5;
                c.setLayoutX(30 + (playerId % 2) * 100);
                c.setLayoutY(500 - (300 * (playerId / 2)) - i2);
                break;
            case 2:
                i3 += 5;
                c.setLayoutX(30 + (playerId % 2) * 100);
                c.setLayoutY(500 - (300 * (playerId / 2)) - i3);
                break;
            case 3:
                i4 += 5;
                c.setLayoutX(30 + (playerId % 2) * 100);
                c.setLayoutY(500 - (300 * (playerId / 2)) - i4);
                break;
        }


        // playersCollection.setStyle("-fx-background-color:lightgray");
        playersCollection.getChildren().addAll(c);
    }

    //set flags
    private void setFlags(String setup, String moveSequence, int numPlayers, int playerId) {
        int[] flags = getFlags(setup, moveSequence, numPlayers);
        // for (int i=0;i<7;i++)
        // if (flags[i]==playerId)
        //creat new small sequare for flags

    }

    //method to check if the game end
    private boolean isEnd(Placement placement, int Zpos) {
        if(WarringStatesGame.generateMove(placement.toString())=='\0'){
            return true;
        }
        else {
            return false;
        }
//        for (int i = 0; i < 36; i++) {
//            if ((i / 6) == (Zpos / 6)) {
//                if (placement.cards[i] != null)
//                    return false;
//            }
//            if ((i % 6) == (Zpos % 6)) {
//                if (placement.cards[i] != null)
//                    return false;
//            }
//        }
    }

    private void setUpGame() {
        border.setCenter(board);
        border.setRight(playersCollection);
        border.setTop(menu());
    }

    Placement placement;

    private void setupBoard() {
        //reset the game
        playersCollection.getChildren().clear();
        board.getChildren().clear();
        Placement setup = new Placement();
        this.placement = setup;
        int col = 0;
        int row = 0;
        board.setHgap(10);
        board.setVgap(10);
        board.setPadding(new Insets(5, 5, 5, 5));
        border.setPrefSize(933, 700);
        board.getColumnConstraints().add(new ColumnConstraints(90, 90, 90));
        board.getRowConstraints().add(new RowConstraints(90, 90, 90));
        for (int i = 0; i < 36; i++) {
            cardsButtons[i] = new Button();
            cardsButtons[i].setPrefSize(90, 90);
            cardsButtons[i].setStyle("-fx-border-color: black; -fx-border-width: 1px;");
            cardsButtons[i].setText(setup.getKingdomName(i) + "\n" + setup.getCharacter(i));
            //set card color
            BackgroundFill fill = new BackgroundFill(setup.getColor(i), CornerRadii.EMPTY, Insets.EMPTY);
            cardsButtons[i].setBackground(new Background(fill));
            cardsButtons[i].setTextAlignment(TextAlignment.CENTER);
            row = (i % 6);
            col = (i / 6);
            final int trans_i = i;
            cardsButtons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    if (isMoveLegal(placement.toString(), setup.getCardPos(trans_i))) {
                        illegal.setText("");
                        char zyPos = zyCurrentPos(placement.toString()); //zy's current position
                        ArrayList<String> collected = new ArrayList();
                        String newPlacement = WarringStatesGame.removeCards(placement.toString(), placement.cards[trans_i].getCardPos(), collected);


                        int index = getPosInArray(zyPos);
                        //move zy card to the new place, and change it is position
                        placement.cards[index].setCardPos(placement.cards[trans_i].getCardPos());
                        //moveSequence+=placement.cards[trans_i].getCardPos();
                        //moveSequence+=placement.cards[trans_i].getCardPos();//save players movement
                        // placement.cards[index]=null;
                        for (String s : collected) {
                            int indexOfCard = placement.toString().indexOf(s);
                            char cardPos = placement.toString().charAt(indexOfCard + 2);
                            indexOfCard = getPosInArray(cardPos);
                            placement.cards[indexOfCard] = null;
                            setPlayersCollection(cardsButtons[indexOfCard], playerId);
                            board.getChildren().remove(cardsButtons[indexOfCard]);
                            board.add(getWhiteButton(), (indexOfCard / 6), (indexOfCard % 6));
                        }
                        placement.cards[trans_i] = placement.cards[index];
                        board.getChildren().remove(cardsButtons[index]);
                        cardsButtons[trans_i] = cardsButtons[index];
                        board.add(cardsButtons[index], (trans_i / 6), (trans_i % 6));
                        System.out.println(playerId);

                        playerId = (playerId + 1) % numPlayers;
                        if (playerId >= numPlayers - numAgents) {
                            Timeline timeline = new Timeline(new KeyFrame(
                                    Duration.millis(500),
                                    ae -> {
                                        makeRobotMove();
                                    }));
                            timeline.play();
                        }
                        if (isEnd(placement, trans_i))
                            System.out.println("End");

                    } else {
                        //highlight the zy and card when the move is illegal then write text message on the bottom of the window
                        illegal.setText("\n Illegal move!");
                        illegal.setFont(Font.font("Arial", 20));
                        illegal.setFill(Color.RED);
                        error.play();
                        border.setBottom(illegal);
                    }
                }
            });
            board.add(cardsButtons[i], col, row);
        }
        border.setCenter(board);
        border.setRight(playersCollection);
    }


    // FIXME Task 11: Allow players of your Warring States game to play against your simple agent
    void makeRobotMove() {

        char newmove = WarringStatesGame.generateMove(placement.toString());
        illegal.setText("");
        char zyPos = zyCurrentPos(placement.toString()); //zy's current position
        ArrayList<String> collected = new ArrayList();
        String newPlacement = WarringStatesGame.removeCards(placement.toString(), newmove, collected);


        int index = getPosInArray(zyPos);
        placement.cards[index].setCardPos(newmove);
        for (String s : collected) {
            int indexOfCard = placement.toString().indexOf(s);
            char cardPos = placement.toString().charAt(indexOfCard + 2);
            indexOfCard = getPosInArray(cardPos);
            placement.cards[indexOfCard] = null;
            setPlayersCollection(cardsButtons[indexOfCard], playerId);
            board.getChildren().remove(cardsButtons[indexOfCard]);
            board.add(getWhiteButton(), (indexOfCard / 6), (indexOfCard % 6));
        }
        placement.cards[getPosInArray(newmove)] = placement.cards[index];
        board.getChildren().remove(cardsButtons[index]);
        cardsButtons[getPosInArray(newmove)] = cardsButtons[index];
        board.add(cardsButtons[index], (getPosInArray(newmove) / 6), (getPosInArray(newmove) % 6));
        System.out.println(playerId + " robot");

        playerId = (playerId + 1) % numPlayers;
        if (playerId >= numPlayers - numAgents) {
            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.millis(500),
                    ae -> {
                        makeRobotMove();
                        System.out.println("324");
                    }));
            timeline.play();
        }
        if (isEnd(placement, getPosInArray(newmove)))
            System.out.println("End");
    }
    // FIXME Task 12: Integrate a more advanced opponent into your game

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Warring States Viewer");
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);
        root.getChildren().add(border);
        setUpGame();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

