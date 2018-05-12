package comp1110.ass2.gui;

import comp1110.ass2.Placement;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import static comp1110.ass2.WarringStatesGame.*;


// FIXME Task 9: Implement a basic playable Warring States game in JavaFX

public class Game extends Application {
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private GridPane board = new GridPane();
    private GridPane playersCollection = new GridPane();
    //private FlowPane flowPane=new FlowPane();
    private BorderPane border = new BorderPane();
    private Button[] cardsButtons = new Button[36];
    private Text illegal = new Text();
    private Placement setup=new Placement();
    private Placement placement = setup;
    private final Group root = new Group();
    private String moveSequence="";
    private int playerId;
    //public final int numPlayers;
    public static String posChars="456789YZ0123STUVWXMNOPQRGHIJKLABCDEF";
    private Color[]flagColor={Color.LIGHTYELLOW,Color.LIGHTBLUE, Color.PINK, Color.LIGHTGREEN, Color.LIGHTSALMON, Color.LAVENDERBLUSH, Color.LIGHTCORAL};

    //the menu bar
    private MenuBar menu() {
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        MenuItem newGame = new MenuItem("New game");
        newGame.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle (ActionEvent e){
                //new pupup window to select the number of players

            }
        });
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>()

        {
            public void handle (ActionEvent e){
                System.exit(0);
            }
        });
        menuFile.getItems().addAll(newGame, exit);
        menuBar.getMenus().addAll(menuFile);
        return menuBar;
    }

    public static int getPosInArray(char cardPos){
        int index=posChars.indexOf(cardPos);
        return index;
    }

    //set every players collection
    //playerId is the player number, c is the collected card
    private void setPlayersCollection(Button c, int playerId){
        playersCollection.add(c, playerId/2, playerId%2);
    }

    //set flags
    private void setFlags (String setup, String moveSequence, int numPlayers){
        getFlags(setup, moveSequence, numPlayers);
        // new



    }


    private void setupBoard() {
        int col = 0;
        int row = 0;
        board.setHgap(10);
        board.setVgap(10);
        board.getColumnConstraints().add(new ColumnConstraints(90,90,90));
        board.getRowConstraints().add(new RowConstraints(90,90,90));
        for (int i = 0; i < 36; i++) {
            //the stackpane used to group all card information in one place which make it easier to write texts on a specific position
            cardsButtons[i] = new Button();
            cardsButtons[i].setPrefSize(90, 90);
            cardsButtons[i].setText(setup.getKingdomName(i)+"\n"+setup.getCharacter(i));
            //set card color
            BackgroundFill fill = new BackgroundFill(setup.getColor(i), CornerRadii.EMPTY, Insets.EMPTY);
            cardsButtons[i].setBackground(new Background(fill));
            cardsButtons[i].setTextAlignment(TextAlignment.CENTER);
            row = (i % 6);
            col = (i / 6);
            final int trans_i=i;
            cardsButtons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    if (isMoveLegal(placement.toString(),setup.getCardPos(trans_i))){
                        illegal.setText("");
                        char zyPos = zyCurrentPos(placement.toString()); //zy's current position
                        int index =getPosInArray(zyPos);
                        //move zy card to the new place, and change it is position
                        placement.cards[index].setCardPos(placement.cards[trans_i].getCardPos());
                        moveSequence+=placement.cards[trans_i].getCardPos();
                        placement.cards[trans_i]=placement.cards[index];
                        moveSequence+=placement.cards[trans_i].getCardPos();//save players movement
                        placement.cards[index]=null;
                        setPlayersCollection(cardsButtons[trans_i],playerId);
                        board.getChildren().remove(cardsButtons[trans_i]);
                        board.getChildren().remove(cardsButtons[index]);
                        cardsButtons[trans_i]=cardsButtons[index];
                        board.add(cardsButtons[index],(trans_i / 6),(trans_i % 6));


                    }else {
                        //highlight the zy and card when the move is illegal then write text message on the bottom of the window
                        illegal.setText("\n Illegal move!");
                        illegal.setFont(Font.font ("Arial", 20));
                        illegal.setFill(Color.RED);
                        border.setBottom(illegal);
                    }
                }
            });
            board.add(cardsButtons[i], col, row);

        }
        border.setCenter(board);
        border.setRight(playersCollection);
        border.setTop(menu());

    }


    // FIXME Task 11: Allow players of your Warring States game to play against your simple agent



    // FIXME Task 12: Integrate a more advanced opponent into your game

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Warring States Viewer");
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);
        setupBoard();
        root.getChildren().add(border);


        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

