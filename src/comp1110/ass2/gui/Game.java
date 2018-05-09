package comp1110.ass2.gui;

import comp1110.ass2.Placement;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Game extends Application {
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private GridPane board = new GridPane();
    private Button[] cardsButtons = new Button[36];
    private Placement setup=new Placement();
    private Placement placement = setup;
    private final Group root = new Group();

    private void setupBoard() {
        int col = 0;
        int row = 0;
        board.setHgap(10);
        board.setVgap(10);
        for (int i = 0; i < 36; i++) {
            //the stackpane used to group all card information in one place which make it easier to write texts on a specific position
            cardsButtons[i] = new Button();
            cardsButtons[i].setPrefSize(90, 90);
            cardsButtons[i].setText(placement.getKingdomName(i)+"\n"+placement.getCharacter(i));
            //set card color
            BackgroundFill fill = new BackgroundFill(placement.getColor(i), CornerRadii.EMPTY, Insets.EMPTY);
            cardsButtons[i].setBackground(new Background(fill));
            //s.setStroke(Color.BLACK);
            //change the font size
            // kingdom.setFont(Font.font(25));
            //aligning the position of character to be on the bottom left
            cardsButtons[i].setTextAlignment(TextAlignment.CENTER);
            row = (i % 6);
            col = (i / 6);
            final int trans_i=i;
            cardsButtons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    //cardsButtons[trans_i].setRotate(180);
                    //generateMove(placement.toString());
                    //isMoveLegal(placement, char locationChar)
                }
            });
            board.add(cardsButtons[i], col, row);

        }

    }

    // FIXME Task 9: Implement a basic playable Warring States game in JavaFX


    // FIXME Task 11: Allow players of your Warring States game to play against your simple agent



    // FIXME Task 12: Integrate a more advanced opponent into your game

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Warring States Viewer");
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);
        setupBoard();
        root.getChildren().add(board);


        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

