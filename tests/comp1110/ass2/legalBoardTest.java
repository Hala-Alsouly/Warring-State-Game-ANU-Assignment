package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class legalBoardTest {

    @Rule
    public Timeout globalTimeout = Timeout.millis(2000);

    static final char[][] BOARD = {
            {'A', 'G', 'M', 'S', 'Y', '4'},
            {'B', 'H', 'N', 'T', 'Z', '5'},
            {'C', 'I', 'O', 'U', '0', '6'},
            {'D', 'J', 'P', 'V', '1', '7'},
            {'E', 'K', 'Q', 'W', '2', '8'},
            {'F', 'L', 'R', 'X', '3', '9'}

    };

    @Test
    public void quickIndex_Legal_Board_Test() {
        boolean illegalBoard = true;

        for (int i = 0; i < 6; i++) {
            WarringStatesGame.quickIndex('0');
            if (!Arrays.equals(BOARD[i], WarringStatesGame.sixSix[i])) {
                illegalBoard = false;
                break;
            }
        }
        System.out.println(BOARD[0]);
        System.out.println();
        System.out.println(WarringStatesGame.sixSix[2]);
        assertTrue("The 6*6 board is illegal !", illegalBoard);
    }
}
