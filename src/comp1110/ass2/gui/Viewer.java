package comp1110.ass2.gui;
//Author ://created by Hala Abdulaziz M Alsouly (u5995105)
import comp1110.ass2.WarringStatesGame;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
        int cardXpos;
        int cardYpos;

        //check if the written placement is right
        if (WarringStatesGame.isPlacementWellFormed(placement)) {
            //take 3 char from the placement in sequence to set every card by it is position, kingdom and character
            for (int i = 0; i < placement.length(); i = i + 3) {
                //set the card position
                switch (placement.charAt(i + 2)) {
                    case 'A':
                        cardXpos = 510;
                        cardYpos = 10;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case 'B':
                        cardXpos = 510;
                        cardYpos = 110;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case 'C':
                        cardXpos = 510;
                        cardYpos = 210;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case 'D':
                        cardXpos = 510;
                        cardYpos = 310;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case 'E':
                        cardXpos = 510;
                        cardYpos = 410;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case 'F':
                        cardXpos = 510;
                        cardYpos = 510;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case 'G':
                        cardXpos = 410;
                        cardYpos = 10;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case 'H':
                        cardXpos = 410;
                        cardYpos = 110;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case 'I':
                        cardXpos = 410;
                        cardYpos = 210;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case 'J':
                        cardXpos = 410;
                        cardYpos = 310;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case 'K':
                        cardXpos = 410;
                        cardYpos = 410;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case 'L':
                        cardXpos = 410;
                        cardYpos = 510;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case 'M':
                        cardXpos = 310;
                        cardYpos = 10;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case 'N':
                        cardXpos = 310;
                        cardYpos = 110;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case 'O':
                        cardXpos = 310;
                        cardYpos = 210;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case 'P':
                        cardXpos = 310;
                        cardYpos = 310;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case 'Q':
                        cardXpos = 310;
                        cardYpos = 410;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case 'R':
                        cardXpos = 310;
                        cardYpos = 510;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case 'S':
                        cardXpos = 210;
                        cardYpos = 10;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case 'T':
                        cardXpos = 210;
                        cardYpos = 110;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case 'U':
                        cardXpos = 210;
                        cardYpos = 210;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case 'V':
                        cardXpos = 210;
                        cardYpos = 310;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case 'W':
                        cardXpos = 210;
                        cardYpos = 410;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case 'X':
                        cardXpos = 210;
                        cardYpos = 510;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case 'Y':
                        cardXpos = 110;
                        cardYpos = 10;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case 'Z':
                        cardXpos = 110;
                        cardYpos = 110;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case '0':
                        cardXpos = 110;
                        cardYpos = 210;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case '1':
                        cardXpos = 110;
                        cardYpos = 310;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case '2':
                        cardXpos = 110;
                        cardYpos = 410;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case '3':
                        cardXpos = 110;
                        cardYpos = 510;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case '4':
                        cardXpos = 10;
                        cardYpos = 10;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case '5':
                        cardXpos = 10;
                        cardYpos = 110;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case '6':
                        cardXpos = 10;
                        cardYpos = 210;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case '7':
                        cardXpos = 10;
                        cardYpos = 310;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case '8':
                        cardXpos = 10;
                        cardYpos = 410;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                    case '9':
                        cardXpos = 10;
                        cardYpos = 510;
                        drowCard(placement.charAt(i), placement.charAt(i + 1), cardXpos, cardYpos);
                        break;
                }
            }
        }
    }

    //draw rectangle and set kingdom name and character
    private void drawRectangle(int cardXpos,int cardYpos, Color c,String k, String character1){
        Rectangle card= new Rectangle(90,90);
        //the stackpane used to group all card information in one place which make it easier to write texts on a specific position
        StackPane s = new StackPane();
        Text kingdom=new Text(k);
        Text charectar= new Text(character1);
        s.setPrefSize(90,90);
        s.relocate(cardXpos,cardYpos);
        //set card color
        card.setFill(c);
        card.setStroke(Color.BLACK);
        //change the font size
        kingdom.setFont(Font.font(25));
        //aligning the position of character to be on the bottom left
        StackPane.setAlignment(charectar, Pos.BOTTOM_CENTER);
        s.getChildren().addAll(card,kingdom,charectar);
        board.getChildren().addAll(s);
    }

    //return the charectar name to set it on the card
    private String getCharectarName(char kingdom,char c) {
        if (kingdom == 'a') {
            switch (c) {
                case '0':
                    return "Duke Xiao";
                case '1':
                    return "Shang Yang";
                case '2':
                    return "King Hui";
                case '3':
                    return "King Zhaoxiang";
                case '4':
                    return "Lady Mi";
                case '5':
                    return "Bai Qi";
                case '6':
                    return "Lady Zhao";
                case '7':
                    return "King Zheng";
            }
        } else if (kingdom == 'b') {
            switch (c) {
                case '0':
                    return "King Xuan";
                case '1':
                    return "Zhong Wuyan";
                case '2':
                    return "Lord Menchang";
                case '3':
                    return "King Xiang";
                case '4':
                    return "Queen Junwang";
                case '5':
                    return "King Jian";
                case '6':
                    return "Sun Bin";
            }
        } else if (kingdom == 'c') {
            switch (c) {
                case '0':
                    return "Wu Qi";
                case '1':
                    return "King Kaolie";
                case '2':
                    return "King You";
                case '3':
                    return "Qu Yuan";
                case '4':
                    return "Fuchu";
                case '5':
                    return "Lord Chunshen";
            }
        } else if (kingdom == 'd') {
            switch (c) {
                case '0':
                    return "King Wuling";
                case '1':
                    return "Lord Pingyuan";
                case '2':
                    return "King Xiaocheng";
                case '3':
                    return "Li Mu";
                case '4':
                    return "Lian Po";
            }
        } else if (kingdom == 'e') {
            switch (c) {
                case '0':
                    return "Marquess Ai";
                case '1':
                    return "King Huanhui";
                case '2':
                    return "King An";
                case '3':
                    return "Han Fei";
            }
        } else if (kingdom == 'f') {
            switch (c) {
                case '0':
                    return "Marquess Wen";
                case '1':
                    return "Lord Xinling";
                case '2':
                    return "King Anxi";
            }
        } else if (kingdom == 'g') {
            switch (c) {
                case '0':
                    return "King Xi";
                case '1':
                    return "Prince Dan";
            }
        }
        return "Zhang Yi";
    }

    //drow the cards on it is right position on the board
    private void drowCard(char king, char c,int cardXpos, int cardYpos){
        String k;
        switch (king){
            case 'a':
                k="秦";
                drawRectangle(cardXpos, cardYpos, Color.LIGHTYELLOW,k,getCharectarName(king,c));
                break;
            case 'b':
                k="齊";
                drawRectangle(cardXpos, cardYpos, Color.LIGHTBLUE,k,getCharectarName(king,c));
                break;
            case 'c':
                k="楚";
                drawRectangle(cardXpos, cardYpos, Color.PINK,k,getCharectarName(king,c));
                break;
            case 'd':
                k="趙";
                drawRectangle(cardXpos, cardYpos, Color.LIGHTGREEN,k,getCharectarName(king,c));
                break;
            case 'e':
                k="韓";
                drawRectangle(cardXpos, cardYpos, Color.LIGHTSALMON,k,getCharectarName(king,c));
                break;
            case 'f':
                k="魏";
                drawRectangle(cardXpos, cardYpos, Color.LAVENDERBLUSH,k,getCharectarName(king,c));
                break;
            case 'g':
                k="燕";
                drawRectangle(cardXpos, cardYpos, Color.LIGHTCORAL,k,getCharectarName(king,c));
                break;
            case 'z':
                k="";
                drawRectangle(cardXpos, cardYpos, Color.LIGHTGRAY,k,getCharectarName(king,c));
                break;
        }
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
