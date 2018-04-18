package comp1110.ass2;

import gittest.C;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * This class provides the text interface for the Warring States game
 */
public class WarringStatesGame {

    /**
     * Determine whether a card placement is well-formed according to the following:
     * - it consists of exactly three characters
     * - the first character is in the range a..g (kingdom) or is z (Zhang Yi)
     * - the second character is numeric, and is a valid character number for that kingdom (9 for Zhang Yi)
     * - the third character is in the range A .. Z or 0..9 (location)
     *
     * @param cardPlacement A string describing a card placement
     * @return true if the card placement is well-formed
     */
    static boolean isCardPlacementWellFormed(String cardPlacement) {
        // FIXME Task 2: determine whether a card placement is well-formed
        if (cardPlacement.length() != 3) {
            return false;
        }
        char[] source = cardPlacement.toCharArray();
        char char0 = source[0];
        int code0 = (int) char0;
        char char1 = source[1];
        int code1 = (int) char1;
        char char2 = source[2];
        int code2 = (int) char2;
        if ((code0 >= 97 && code0 <= 103) || code0 == 122) {
            if ((code1 >= 48 && code1 <= 55) || code1 == 57) {
                if ((code2 >= 65 && code2 <= 90) || (code2 >= 48 && code2 <= 57)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Determine whether a placement string is well-formed:
     * - it consists of exactly N three-character card placements (where N = 1 .. 36);
     * - each card placement is well-formed
     * - no card appears more than once in the placement
     * - no location contains more than one card
     *
     * @param placement A string describing a placement of one or more cards
     * @return true if the placement is well-formed
     */
    public static boolean isPlacementWellFormed(String placement) {
        // FIXME Task 3: determine whether a placement is well-formed
        ArrayList<String> arrayListID = new ArrayList<>();
        ArrayList<Character> arrayListPos = new ArrayList<>();
        if (placement == null || placement.isEmpty()) {
            return false;
        } else if (placement.length() % 3 != 0) {
            return false;
        } else if (placement.length() % 3 == 0) {
            for (int i = 0; i < placement.length(); i += 3) {
                String card = placement.substring(i, i + 3);
                if (!isCardPlacementWellFormed(card)) {
                    return false;
                }

                String id = card.substring(0, 2);
                if (arrayListID.contains(id)) {
                    return false;
                }
                arrayListID.add(id);

                char pos = card.charAt(2);
                if (arrayListPos.contains(pos)) {
                    return false;
                }
                arrayListPos.add(pos);
            }

        }
        return true;
    }

    //if find Zhang Yi in the placement (cards), return his current position(char), else return'F'
    public static char zyCurrentPos(String placement) {
        for (int i = 0; i < placement.length(); i += 3) {
            String card = placement.substring(i, i + 3);
            char firstChar = card.charAt(0);
            char posChar = card.charAt(2);
            if (firstChar == 'z') {
                return posChar;
            }
        }
        return '!'; // method must have return statement
    }

    // find the index of row or column (Zhang Yi's position  or location or.......)
    public static int quickIndex(char position) {
        char[][] sixSix = new char[6][6];
        char location = 'A';
        //put board's locations into a 6*6 coordinate
        // A-X
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 6; i++) {
                sixSix[i][j] = location;
                location++;
            }
        }
        //Y,Z, 0-9
        location = '0';
        for (int j = 4; j < 6; j++) {
            for (int i = 0; i < 6; i++) {
                if (i == 0 && j == 4) {
                    sixSix[i][j] = 'Y';
                } else if (i == 1 && j == 4) {
                    sixSix[i][j] = 'Z';
                } else {
                    sixSix[i][j] = location;
                    location++;
                }
            }
        }
        //if Zhang Yi's current position or location in coordinate, return its row + column
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (sixSix[i][j] == position) {
                    return i * 10 + j;
                }
            }
        }
        //if the Zhang Yi's current position or location not in coordinate, return -1
        return -1;
    }


    //  card & another card in same kingdom, and the card is further
    public static boolean furtherCard(String placement, char cardKingdom, char cardSecond, char cardPos, char zyPos) {
        int cardRC = quickIndex(cardPos);
        int cardRow = cardRC / 10;
        int cardColumn = cardRC % 10;

        int zyRC = quickIndex(zyPos);
        int zyRow = zyRC / 10;
        int zyColumn = zyRC % 10;
        //zy and card not in same line, return false
        if (cardRow != zyRow && cardColumn != zyColumn) {
            return false;
        }
        //zy still stay at the origin, return false
        if (cardRow == zyRow && cardColumn == zyColumn)
            return false;

        // exist another card , has same kingdom with card in location, collect them
        //char furPos = ' ';
        char[] cards = new char[8];
        for (int i = 0, k = 0; i < placement.length(); i += 3) {
            String card = placement.substring(i, i + 3);
            char furKingdom = card.charAt(0);
            char furSecond = card.charAt(1);
            /*char furPos = card.charAt(2);
            int furCardRC = quickIndex(furPos);
            if (furCardRC == 0) {
                return false;
            }*/
            //int furCardRow = furCardRC / 10;
            //int furCardColumn = furCardRC % 10;

            // if card & another card in same kingdom
            if ((furKingdom == cardKingdom))
            //&& ((furCardRow == cardRow && cardRow == zyRow) || (furCardColumn == cardColumn) && (cardColumn == zyColumn)))
            {
                //if  card & another card are not same card
                if (furSecond != cardSecond) {
                    cards[k] = card.charAt(2); // collect another card's position
                    k++;
                    //furPos = card.charAt(2);
                    //break;
                }
            }
        }
       /* if (cardRow == zyRow) {
            for (int i = 0; i < cards.length; i++) {
                int iCard = quickIndex(cards[i]);
                int furCard = quickIndex(furPos);
                int row1 = iCard / 10;
                int row2 = furCard / 10;
                if ((row1 - zyRow) * (row2 - zyRow) > 0) {
                    if (Math.abs((row1 - zyRow)) > Math.abs((row2 - zyRow))) {
                        furPos = cards[i];
                    }
                }

            }
        }
        if (cardColumn == zyColumn) {
            for (int i = 0; i < cards.length; i++) {
                int iCard = quickIndex(cards[i]);
                int furCard = quickIndex(furPos);
                int column1 = iCard / 10;
                int column2 = furCard / 10;
                if ((column1 - zyRow) * (column2 - zyRow) > 0) {
                    if (Math.abs((column1 - zyRow)) > Math.abs((column2 - zyRow))) {
                        furPos = cards[i];
                    }
                }
            }
        }*/

        //another card in same kingdom whether is further
        for (int k = 0; k < cards.length; k++) {
            if (cards[k] != 0) {
                int furCardRC = quickIndex(cards[k]);
                /*if (furCardRC == 0) {
                    return false;
                }*/
                int furCardRow = furCardRC / 10;
                int furCardColumn = furCardRC % 10;

                //if three cards in the same row
                if ((cardRow == zyRow) && (furCardRow == cardRow)) {
                    if (((furCardColumn - zyColumn) * (cardColumn - zyColumn) > 0)) {
                        if (Math.abs(furCardColumn - zyColumn) > Math.abs(cardColumn - zyColumn)) {
                            return false;
                        }// find further card in same row, return false
                    }
                }
                //if three cards in same column
                if ((cardColumn == zyColumn) && (furCardColumn == cardColumn)) {
                    if (((furCardRow - zyRow) * (cardRow - zyRow) > 0)) {
                        if (Math.abs(furCardRow - zyRow) > Math.abs(cardRow - zyRow)) {
                            return false; // find further card in same column, return false
                        }
                    }
                    // if (cardRow != zyRow && cardColumn != zyColumn) {
                    //return false;
                    //}
                }

            }
        }

        return true;
    }


    /**
     * Determine whether a given move is legal given a provided valid placement:
     * - the location char is in the range A .. Z or 0..9
     * - there is a card at the chosen location;
     * - the location is in the same row or column of the grid as Zhang Yi's current position; and
     * - drawing a line from Zhang Yi's current location through the card at the chosen location,
     * there are no other cards along the line from the same kingdom as the chosen card
     * that are further away from Zhang Yi.
     *
     * @param placement    the current placement string
     * @param locationChar a location for Zhang Yi to move to
     * @return true if Zhang Yi may move to that location
     */
    public static boolean isMoveLegal(String placement, char locationChar) {
        // FIXME Task 5: determine whether a given move is legal
        int code = (int) locationChar; //chosen location's char
        //if locationChar is not well-formed, not belongs to A-Z or 0-9
        if (!((code >= 65 && code <= 90) || (code >= 48 && code <= 57))) {
            return false;
        }

        char cardKingdom = ' ';
        char cardSecond = ' ';
        int i;
        for (i = 0; i < placement.length(); i += 3) {
            String card = placement.substring(i, i + 3);
            char cardPos = card.charAt(2);
            // task2
            if (cardPos == locationChar) {
                if (!isCardPlacementWellFormed(card)) {
                    return false;
                }
//                if (!(card.charAt(0) == 'a' || card.charAt(0) == 'b' || card.charAt(0) == 'c'
//                        || card.charAt(0) == 'd' || card.charAt(0) == 'e' || card.charAt(0) == 'f' || card.charAt(0) == 'g')) {
//                    return false;
//                }
                else {
                    cardKingdom = card.charAt(0);
                    cardSecond = card.charAt(1);
                    break;
                }
            }
        }
        if (i == placement.length()) {
            return false;
        }
        char zyPos = zyCurrentPos(placement); //zy's current position
        int zyPosRC = quickIndex(zyPos);     //zy's row & column
        int chosenLocRC = quickIndex(locationChar);  //location's row & column
        //Zhang Yi's position not in placement, false
        if (zyPos == '!') {
            return false;
        }
        // Zhang Yi not in coordinate, false
        if (zyPosRC == -1) {
            return false;
        }
        // chosen location not in coordinate, false
        if (chosenLocRC == -1) {
            return false;
        }
        boolean furCard = furtherCard(placement, cardKingdom, cardSecond, locationChar, zyPos);
        if (!furCard) {
            return false;
        }
        return true;
    }


    /**
     * Determine whether a move sequence is valid.
     * To be valid, the move sequence must be comprised of 1..N location characters
     * showing the location to move for Zhang Yi, and each move must be valid
     * given the placement that would have resulted from the preceding sequence
     * of moves.
     *
     * @param setup        A placement string representing the board setup
     * @param moveSequence a string of location characters representing moves
     * @return True if the placement sequence is valid
     */
    static boolean isMoveSequenceValid(String setup, String moveSequence) {
        System.out.println(setup);
        System.out.println(moveSequence);
        // FIXME Task 6: determine whether a placement sequence is valid
        for (int i = 0; i < moveSequence.length(); i++) {
            char move = moveSequence.charAt(i);
            if (isMoveLegal(setup, move)) {
                setup = removeCards(setup, move, new ArrayList<>());   //*********
                System.out.println(setup);
                if (setup == null) {
                    System.out.println("false 1");  //debug
                    return false;
                }
                if (setup.isEmpty()) {
                    System.out.println("false 2");  //debug
                    return false;
                }
            } else {
                System.out.println("false 3");  //debug
                return false;
            }
        }

        return true;
    }

    static String removeCards(String placement, char move, ArrayList<String> collection) {   //*********
        char zyPos = zyCurrentPos(placement);
        int[] zyCR = setupCR(zyPos);
        int[] moveCR = setupCR(move);
        char cardKingdom = ' ';
        ArrayList<Character> check = new ArrayList<>();
        //*********

        //collect all cards that in the same row or column with Zhangyi
        if (zyCR[0] == moveCR[0] && zyCR[1] != moveCR[1]) {
            int min = Math.min(zyCR[1], moveCR[1]);
            int max = Math.max(zyCR[1], moveCR[1]);
            for (int i = min + 1; i < max; i++) {
                char p = CRtoChar(zyCR[0], i);
                check.add(p);
                System.out.println(check.get(check.size() - 1));    //debug
            }
        } else if (zyCR[0] != moveCR[0] && zyCR[1] == moveCR[1]) {
            int min = Math.min(zyCR[0], moveCR[0]);
            int max = Math.max(zyCR[0], moveCR[0]);
            for (int i = min + 1; i < max; i++) {
                char p = CRtoChar(i, zyCR[1]);
                check.add(p);
                System.out.println(check.get(check.size() - 1));    //debug
            }
        } else {
            return null;
        }

        // define aim position's cardKingdom, and delete card at 'move'
        for (int j = 0; j < placement.length(); j += 3) {
            if (placement.charAt(j + 2) == move) {
                cardKingdom = placement.charAt(j);
                collection.add(placement.substring(j, j + 2));          //*******
                placement = placement.replace(placement.substring(j, j + 3), ""); // delete the furthest card
                j -= 3;
            }

        }
        // collect cards that belong to same Kingdom, and delete them
        for (int i = 0; i < placement.length(); i += 3) {
            if (check.contains(placement.charAt(i + 2))) {
                if (placement.charAt(i) == cardKingdom) {
                    collection.add(placement.substring(i, i + 2));
                    placement = placement.replace(placement.substring(i, i + 3), "");
                    i -= 3;
                }
            }
        }
        //replace card at 'move' to Zhangyi
        for (int i = 0; i < placement.length(); i += 3) {
            if (placement.charAt(i) == 'z') {
                placement = placement.replace(placement.substring(i, i + 3), "z9" + move);
            }
        }
        return placement;
    }

//    public static void main(String[] args) {
//        System.out.println(removeCards("b07b6Ga18e29c5Xb1Lb4Vc0Cz9Eg0Ib5Ja64d4Ff23a5Ub2Ra7Ka2Wc20a4Hb36",'8',new ArrayList<>()));
//        System.out.println(isMoveLegal("a0Bf1Cc5Ee2Ic2Kd0Ld4Oc3Qe0Rc1Td1Ub0Xb10z9Fb33g16c09", 'E'));
//    }       //debug

    static int[] setupCR(char locationChar) {
        int pos;
        int[] cardCR = new int[2];
        if (locationChar <= 'Z' && locationChar >= 'A') {
            pos = locationChar - 'A';
        } else {
            pos = locationChar - '0' + 26;
        }
        cardCR[0] = pos / 6;
        cardCR[1] = pos % 6;
        return cardCR;
    }

    static char CRtoChar(int R, int C) {
        int pos = R * 6 + C;
        if (pos >= 26) {
            return (char) (pos - 26 + '0');
        } else {
            return (char) (pos + 'A');
        }
    }

    /**
     * Get the list of supporters for the chosen player, given the provided
     * setup and move sequence.
     * The list of supporters is a sequence of two-character card IDs, representing
     * the cards that the chosen player collected by moving Zhang Yi.
     *
     * @param setup        A placement string representing the board setup
     * @param moveSequence a string of location characters representing moves
     * @param numPlayers   the number of players in the game, must be in the range [2..4]
     * @param playerId     the player number for which to get the list of supporters, [0..(numPlayers-1)]
     * @return the list of supporters for the given player
     */
    public static String getSupporters(String setup, String moveSequence, int numPlayers, int playerId) {
//        System.out.println(setup);
//        System.out.println(moveSequence);
//        System.out.println(numPlayers);
//        System.out.println(playerId);

        // FIXME Task 7: get the list of supporters for a given player after a sequence of moves

        ArrayList<String> cardsCollected = new ArrayList<>();
        for (int i = 0; i < moveSequence.length(); i++) {
            char move = moveSequence.charAt(i);
            if (i % numPlayers == playerId) {
                setup = removeCards(setup, move, cardsCollected);
            } else {
                setup = removeCards(setup, move, new ArrayList<>());
            }
            System.out.println(setup);
        }
        String supporters = "";
        Collections.sort(cardsCollected);
        for (String s : cardsCollected) {
            supporters += s;
        }
        return supporters;
    }


    /**
     * Given a setup and move sequence, determine which player controls the flag of each kingdom
     * after all the moves in the sequence have been played.
     *
     * @param setup        A placement string representing the board setup
     * @param moveSequence a string of location characters representing a sequence of moves
     * @param numPlayers   the number of players in the game, must be in the range [2..4]
     * @return an array containing the player ID who controls each kingdom, where
     * - element 0 contains the player ID of the player who controls the flag of Qin
     * - element 1 contains the player ID of the player who controls the flag of Qi
     * - element 2 contains the player ID of the player who controls the flag of Chu
     * - element 3 contains the player ID of the player who controls the flag of Zhao
     * - element 4 contains the player ID of the player who controls the flag of Han
     * - element 5 contains the player ID of the player who controls the flag of Wei
     * - element 6 contains the player ID of the player who controls the flag of Yan
     * If no player controls a particular house, the element for that house will have the value -1.
     */
    public static int[] getFlags(String setup, String moveSequence, int numPlayers) {
        // FIXME Task 8: determine which player controls the flag of each kingdom after a given sequence of moves
        return null;
    }

    /**
     * Generate a legal move, given the provided placement string.
     * A move is valid if:
     * - the location char is in the range A .. Z or 0..9
     * - there is a card at the chosen location;
     * - the destination location is different to Zhang Yi's current location;
     * - the destination is in the same row or column of the grid as Zhang Yi's current location; and
     * - drawing a line from Zhang Yi's current location through the card at the chosen location,
     * there are no other cards along the line from the same kingdom as the chosen card
     * that are further away from Zhang Yi.
     * If there is no legal move available, return the null character '\0'.
     *
     * @param placement the current placement string
     * @return a location character representing Zhang Yi's destination for the move
     */
    public static char generateMove(String placement) {
        // FIXME Task 10: generate a legal move
        return '\0';
    }

}

