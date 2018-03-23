package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * A very simple viewer for card layouts in the Warring States game.
 * <p>
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various card placements.
 */
public class Viewer extends Application {

    private static final int VIEWER_WIDTH = 933;
    private static final int VIEWER_HEIGHT = 700;

    private static final String URI_BASE = "assets/";

    private final Group root = new Group();
    private final Group controls = new Group();
    private final Group board = new Group();
    TextField textField;

    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement A valid placement string
     */
    void makePlacement(String placement) {
        // FIXME Task 4: implement the simple placement viewer
        board.getChildren().clear();
        int cardXpos = 510;
        int cardYpos = 10;
        int counter=0;
        Placement p= new Placement();
        String sc= "";
        p.setPlacement();
        sc=p.ShuffleCards();
        placement= sc;


        for (int i = 0; i < placement.length(); i=i+3) {
            switch (placement.charAt(i)){
                case 'a':
                    drawRectangle(cardXpos, cardYpos, Color.LIGHTYELLOW);
                    break;
                case 'b':
                    drawRectangle(cardXpos, cardYpos, Color.LIGHTBLUE);
                    break;
                case 'c':
                    drawRectangle(cardXpos, cardYpos, Color.PINK);
                    break;
                case 'd':
                    drawRectangle(cardXpos, cardYpos, Color.LIGHTGREEN);
                    break;
                case 'e':
                    drawRectangle(cardXpos, cardYpos, Color.LIGHTSALMON);
                    break;
                case 'f':
                    drawRectangle(cardXpos, cardYpos, Color.LAVENDERBLUSH);
                    break;
                case 'g':
                    drawRectangle(cardXpos, cardYpos, Color.LIGHTCORAL);
                    break;
                case 'z':
                    drawRectangle(cardXpos, cardYpos, Color.LIGHTGRAY);
                    break;

            }
            counter++;
            // drawRectangle(cardXpos, cardYpos, Color.RED);
            if ((counter) % 6 == 0) {
                cardXpos = cardXpos - 100;
                cardYpos = 10;
            } else {
                cardYpos = cardYpos + 100;
            }
        }
    }

    //draw rectangle
    private void drawRectangle(int cardXpos,int cardYpos, Color c){
        Rectangle card= new Rectangle(90,90);
        card.setX(cardXpos);
        card.setY(cardYpos);
        card.setFill(c);
        card.setStroke(Color.BLACK);
        board.getChildren().add(card);
    }
    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label label1 = new Label("Placement:");
        textField = new TextField();
        textField.setPrefWidth(300);
        Button button = new Button("Refresh");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                makePlacement(textField.getText());
                textField.clear();
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Warring States Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(controls);
        root.getChildren().add(board);

        makeControls();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
