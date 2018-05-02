package comp1110.ass2.gui;

import comp1110.ass2.Placement;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Game extends Application {
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private GridPane board=new GridPane();
    private Label[] cardsLabels = new Label[36];
    private Placement placement= new Placement();
    private final Group root = new Group();


    private void setupBoard(){
        int col=0;
        int row=0;
        //board.setPrefSize(600,600);
        board.getColumnConstraints().add(new ColumnConstraints(90));

        for (int i=0; i<36;i++){
            //the stackpane used to group all card information in one place which make it easier to write texts on a specific position
            StackPane s = new StackPane();
            Text kingdom=new Text(placement.getKingdomName(i));
            Text charectar= new Text(placement.getCharacter(i));
            // s.setPrefSize(90,90);
            //  s.relocate(cardXpos,cardYpos);
            //set card color
            BackgroundFill fill= new BackgroundFill(placement.getColor(i), CornerRadii.EMPTY, Insets.EMPTY);
            s.setBackground(new Background(fill));
            //s.setStroke(Color.BLACK);
            //change the font size
            kingdom.setFont(Font.font(25));
            //aligning the position of character to be on the bottom left
            StackPane.setAlignment(charectar, Pos.BOTTOM_CENTER);
            s.getChildren().addAll(kingdom,charectar);
            row=(i%6);
            col=(i/6);
            board.add(s,col,row);

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

