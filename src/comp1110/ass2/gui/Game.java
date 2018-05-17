package comp1110.ass2.gui;

import comp1110.ass2.CollectedCardsInfo;
import comp1110.ass2.Placement;
import comp1110.ass2.WarringStatesGame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
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
 
/*
 * Author: Qianru Zhu (u6416655), Hala Abdulaziz M Alsouly (u5995105), Xiao Tian (u6277077)
 *
 * This class implements a playable Warring States game in JavaFX and creates robert player
 */

import static comp1110.ass2.WarringStatesGame.*;

// FIXME Task 9: Implement a basic playable Warring States game in JavaFX

public class Game extends Application {  

    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private GridPane board = new GridPane();
    private GridPane resultGrid = new GridPane();
    private StackPane[] playerCollectionStack;
    private BorderPane[] playerBorder;
    private Label[] flags = new Label[7];
    private FlowPane[] flagPane;
    private BorderPane border = new BorderPane();
    private Button[] cardsButtons = new Button[36];
    private Text illegal = new Text();
    private final Group root = new Group();
    private int playerId ;
    public int numPlayers;
    public int numAgents;
    public static String posChars = "456789YZ0123STUVWXMNOPQRGHIJKLABCDEF";
    //the 2 sound files from:
    //http://soundbible.com/tags-error.html
    private static final AudioClip error = new AudioClip(Game.class.getResource("assets/error.wav").toString());
    private static final AudioClip cardSound = new AudioClip(Game.class.getResource("assets/card.wav").toString());
    private Color[] flagColor = {Color.LIGHTYELLOW, Color.LIGHTBLUE, Color.PINK, Color.LIGHTGREEN, Color.LIGHTSALMON, Color.LAVENDERBLUSH, Color.LIGHTCORAL};
    private Placement setup;
    private CollectedCardsInfo []flagsInfo;
    private int []numOfFlags;

    //the menu bar
    private MenuBar menu() {
        MenuBar menuBar = new MenuBar();
        menuBar.setMinWidth(933);
        Menu menuFile = new Menu("File");
        Menu menuHelp = new Menu("Help");
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
        MenuItem instruction =new MenuItem("How to Play");
        instruction.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                //new popup window
                getHelp();
            }
        });
        menuHelp.getItems().add(instruction);
        menuFile.getItems().addAll(newGame, exit);
        menuBar.getMenus().addAll(menuFile, menuHelp);
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
    //Instruction window
    private void getHelp() {
        Stage s = new Stage();
        s.setWidth(400);
        s.setHeight(300);
        Text t=new Text("How to play:\n" +
                "1. The player moves the diplomat Zhang Yi to a card in the same row or column to collect the maximum number of cards.\n" +
                "2. If there is more than one card from the same kingdom, the player should move to the furthest one and collect all card in her way.\n" +
                "3. The player takes the rule and wins a flag if she has the highest number of card from that kingdom, or she is the last one who collects cards if there is a draw.\n" +
                "4. The winner is the player who has the highest number of flags.\n" +
                "5. If 2 or more players draw, the winner is the player who has the most powerful kingdom.\n" +
                "6. The kingdom's power is shown at the bottom of the board.\n");
        Button ok=new Button("OK");
        ok.setAlignment(Pos.CENTER);
        t.setWrappingWidth(390);
        ok.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                s.close();
            }
        });
        VBox popup = new VBox(t,ok);
        popup.setPadding(new Insets(5, 5, 5, 5));
        s.setScene(new Scene(popup));
        s.show();

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
                if (newValue == null) {
                    return;
                }
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
                    if(numPlayers<=1){
                        notification.setText("Please select agents!");
                        return;
                    }
                    numAgents = numR;
                    s.close();
                    setupBoard();
                } else {
                    notification.setText("You have not selected the number of players!");
                }

            }
        });
        s.setScene(new Scene(popup1));
        s.show();
    }

    public static int getPosInArray(char cardPos) {
        int index = posChars.indexOf(cardPos);
        return index;
    }

//Show the power of each kingdom so, players know what is the best one to win
    private FlowPane setKingdomPower(){
        FlowPane flowPane= new FlowPane();
        for (int i=0;i<7;i++){
            Label l = new Label(""+(7-i));
            l.setPrefSize(20, 20);
            l.setTextAlignment(TextAlignment.CENTER);
            l.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
            BackgroundFill fill = new BackgroundFill(flagColor[i], CornerRadii.EMPTY, Insets.EMPTY);
            l.setBackground(new Background(fill));
            flowPane.getChildren().add(l);
        }
        flowPane.setPrefWrapLength(150);
        flowPane.setPadding(new Insets(0, 0, 5, 0));
        return flowPane;
    }

    //set every players collection
    //playerId is the player number, c is the collected card
    private void setPlayersCollection(Button c, int playerId) {
        int n = playerCollectionStack[playerId].getChildren().size();
        int m = n * 10;
        for (Node node : playerCollectionStack[playerId].getChildren()) {
            Button b = (Button) node;
            b.setPrefSize(90, 90 + m);
            m -= 10;
        }
        playerCollectionStack[playerId].getChildren().add(c);
    }

//Update the shown flags every movement
   private void setFlags(ArrayList<String> collectedCard, int playerId ){
       String kingdoms = "abcdefg";
       int i=0;
       for (String s : collectedCard) {
           i=kingdoms.indexOf(s.charAt(0));
           if(flagsInfo[i]==null)
               flagsInfo[i]=new CollectedCardsInfo();
           flagsInfo[i].playerscollection[playerId]++;
       }
       if (flagsInfo[i].playerscollection[playerId]>=flagsInfo[i].count){

           flagsInfo[i].count=flagsInfo[i].playerscollection[playerId];
           try {flagPane[playerId].getChildren().add(flags[i]);
               numOfFlags[playerId]++;
               if (flagsInfo[i].playernum !=-1)
                   numOfFlags[flagsInfo[i].playernum]--;
               flagsInfo[i].playernum=playerId;
           } catch (Exception ex){
           }
       }
   }

    //method to check if the game end
    private boolean isEnd(Placement placement, int Zpos) {
        if (WarringStatesGame.generateMove(placement.toString()) == '\0') {
            return true;
        } else {
            return false;
        }
    }

    //popup window shows the winner
    private void setAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        int winner=-1, max=0;
        for (int i=0;i<numPlayers;i++){
            if (max<numOfFlags[i]||(winner==-1)){
                winner=i;
                max=numOfFlags[i];
                }else if (max==numOfFlags[i]) {
                for (int j = 0; j < 7; j++)
                    if (flagsInfo[j].playernum == winner)
                        break;
                    else if (flagsInfo[j].playernum== i) {
                        winner = i;
                        break;
                    }
            }
        }
        alert.setTitle("End of the game");
        alert.setHeaderText(null);
        alert.setContentText("Player " +(winner+1)+ " wins!");
        alert.show();
    }

    private void setUpGame() {
        border.setCenter(board);
        border.setRight(resultGrid);
        border.setTop(menu());
    }

    Placement placement;

    private void setupBoard() {
        //reset the game
        resultGrid.getChildren().clear();
        board.getChildren().clear();
        GridPane kingdomAndError =new GridPane();
        kingdomAndError.getColumnConstraints().add(new ColumnConstraints(200));
        kingdomAndError.getRowConstraints().add(new RowConstraints(30));
        kingdomAndError.add(new Label("The power of kingdom:"),1,0);
        kingdomAndError.add(setKingdomPower(),1,1);
        for (int i = 0; i < 7; i++) {
            flags[i] = new Label();
            flags[i].setPrefSize(10, 10);
            BackgroundFill fill = new BackgroundFill(flagColor[i], CornerRadii.EMPTY, Insets.EMPTY);
            flags[i].setBackground(new Background(fill));
        }
        flagsInfo= new CollectedCardsInfo[7];
        playerId=0;
        playerBorder = new BorderPane[numPlayers];
        flagPane = new FlowPane[numPlayers];
        playerCollectionStack = new StackPane[numPlayers];
        numOfFlags=new int[numPlayers];
        resultGrid.setHgap(10);
        resultGrid.setVgap(10);
        for (int i = 0; i < numPlayers; i++) {
            flagPane[i] = new FlowPane();
            playerBorder[i] = new BorderPane();
            playerCollectionStack[i] = new StackPane();
            playerCollectionStack[i].setAlignment(Pos.BOTTOM_CENTER);
            playerBorder[i].setCenter(playerCollectionStack[i]);
            Label l = new Label("Player " + (i+1));
            playerBorder[i].setTop(l);
            flagPane[i].setHgap(4);
            flagPane[i].setPrefWrapLength(90);
            playerBorder[i].setBottom(flagPane[i]);
            resultGrid.add(playerBorder[i], i / 2, i % 2);
        }
        setup = new Placement();
        placement = new Placement(setup.toString());
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
            if (setup.cards[i].getKingdomName() == "") {
                cardsButtons[i].setStyle("-fx-border-color: red; -fx-border-width: 1px;");
            }
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
                        cardSound.play();
                        char zyPos = zyCurrentPos(placement.toString()); //zy's current position
                        ArrayList<String> collected = new ArrayList();
                        String newPlacement = WarringStatesGame.removeCards(placement.toString(), placement.cards[trans_i].getCardPos(), collected);

                        int index = getPosInArray(zyPos);
                        //move zy card to the new place, and change it is position
                        placement.cards[index].setCardPos(placement.cards[trans_i].getCardPos());
                        setFlags(collected,playerId);
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
                        playerId = (playerId + 1) % numPlayers;
                        if (playerId >= numPlayers - numAgents) {
                            Timeline timeline = new Timeline(new KeyFrame(
                                    Duration.millis(500),
                                    ae -> {
                                        makeRobotMove();
                                    }));
                            timeline.play();
                        }
                        if (isEnd(placement, trans_i)) {
                            setAlert();
                        }

                    } else {
                        //highlight the zy and card when the move is illegal then write text message on the bottom of the window
                        illegal.setText("\n Illegal move!");
                        illegal.setFont(Font.font("Arial", 20));
                        illegal.setFill(Color.RED);
                        illegal.setTextAlignment(TextAlignment.CENTER);
                        error.play();
                        try {
                            kingdomAndError.add(illegal,0,0);
                        }catch (Exception ex){}
                    }
                }
            });
            board.add(cardsButtons[i], col, row);
        }
        border.setCenter(board);
        border.setRight(resultGrid);
        border.setBottom(kingdomAndError);

    }

    // FIXME Task 11: Allow players of your Warring States game to play against your simple agent
    void makeRobotMove() {

        char newmove = WarringStatesGame.generateMove(placement.toString());
        if (newmove == '\0') {
            return;
        }
        illegal.setText("");
        cardSound.play();
        char zyPos = zyCurrentPos(placement.toString()); //zy's current position
        ArrayList<String> collected = new ArrayList();
        String newPlacement = WarringStatesGame.removeCards(placement.toString(), newmove, collected);

        int index = getPosInArray(zyPos);
        placement.cards[index].setCardPos(newmove);
        setFlags(collected,playerId);
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

        playerId = (playerId + 1) % numPlayers;
        if (playerId >= numPlayers - numAgents) {

            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.millis(500),
                    ae -> {
                        makeRobotMove();
                    }));
            timeline.play();
        }
        if (isEnd(placement, getPosInArray(newmove))) {
            setAlert();
        }
    }
    // FIXME Task 12: Integrate a more advanced opponent into your game

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Warring States");
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);
        root.getChildren().add(border);
        setUpGame();

        primaryStage.setScene(scene);
        primaryStage.show();
        getPopup();
    }
}

