package comp1110.ass2.gui;

import javafx.scene.paint.Color;

public class Card {
    private String kingdomName, character;
    private Color cardColor;
    private char kingdom, ch, cardPos;
    static String posChars="456789YZ0123STUVWXMNOPQRGHIJKLABCDEF";


    public Card(){}
    public Card(char k, char c, char pos){
        kingdom=k;
        ch=c;
        initCard(k,c);
        cardPos=pos;

    }

    public String getKingdomName() {
        return kingdomName;
    }

    public String getCharacter() {
        return character;
    }

    public Color getCardColor() {
        return cardColor;
    }

    public char getCardPos() {
        return cardPos;
    }

    public void setCardPos(char cardPos) {
        this.cardPos = cardPos;
    }

// this method used to get the index of the position, it is start from upper left to bottom
    public static int getPosInArray(char cardPos){
        int index=posChars.indexOf(cardPos);
        return index;
    }

    @Override
    public String toString() {
        return kingdom+ch+cardPos+"";
    }

    //initialise card 'kingdom name, card color and the character name'
    private void initCard(char k, char c){
        switch (k){
            case 'a':
                kingdomName="秦";
                cardColor=Color.LIGHTYELLOW;
                switch (c) {
                    case '0':
                        character= "Duke Xiao";
                        break;
                    case '1':
                        character= "Shang Yang";
                        break;
                    case '2':
                        character= "King Hui";
                        break;
                    case '3':
                        character= "King Zhaoxiang";
                        break;
                    case '4':
                        character= "Lady Mi";
                        break;
                    case '5':
                        character= "Bai Qi";
                        break;
                    case '6':
                        character= "Lady Zhao";
                        break;
                    case '7':
                        character= "King Zheng";
                        break;
                }

                break;
            case 'b':
                kingdomName="齊";
                cardColor=Color.LIGHTBLUE;
                switch (c) {
                    case '0':
                        character= "King Xuan";
                        break;
                    case '1':
                        character= "Zhong Wuyan";
                        break;
                    case '2':
                        character= "Lord Menchang";
                        break;
                    case '3':
                        character= "King Xiang";
                        break;
                    case '4':
                        character= "Queen Junwang";
                        break;
                    case '5':
                        character= "King Jian";
                        break;
                    case '6':
                        character= "Sun Bin";
                        break;
                }
                break;
            case 'c':
                kingdomName="楚";
                cardColor=Color.PINK;
                switch (c) {
                    case '0':
                        character= "Wu Qi";
                        break;
                    case '1':
                        character= "King Kaolie";
                        break;
                    case '2':
                        character= "King You";
                        break;
                    case '3':
                        character= "Qu Yuan";
                        break;
                    case '4':
                        character= "Fuchu";
                        break;
                    case '5':
                        character= "Lord Chunshen";
                        break;
                }
                break;
            case 'd':
                kingdomName="趙";
                cardColor=Color.LIGHTGREEN;
                switch (c) {
                    case '0':
                        character= "King Wuling";
                        break;
                    case '1':
                        character= "Lord Pingyuan";
                        break;
                    case '2':
                        character= "King Xiaocheng";
                        break;
                    case '3':
                        character= "Li Mu";
                        break;
                    case '4':
                        character= "Lian Po";
                        break;
                }
                break;
            case 'e':
                kingdomName="韓";
                cardColor=Color.LIGHTSALMON;
                switch (c) {
                    case '0':
                        character= "Marquess Ai";
                        break;
                    case '1':
                        character= "King Huanhui";
                        break;
                    case '2':
                        character= "King An";
                        break;
                    case '3':
                        character= "Han Fei";
                        break;
                }
                break;
            case 'f':
                kingdomName="魏";
                cardColor=Color.LAVENDERBLUSH;
                switch (c) {
                    case '0':
                        character= "Marquess Wen";
                        break;
                    case '1':
                        character= "Lord Xinling";
                        break;
                    case '2':
                        character= "King Anxi";
                        break;
                }
                break;
            case 'g':
                kingdomName="燕";
                cardColor=Color.LIGHTCORAL;
                switch (c) {
                    case '0':
                        character= "King Xi";
                        break;
                    case '1':
                        character= "Prince Dan";
                        break;
                }
                break;
            case 'z':
                kingdomName="";
                cardColor=Color.LIGHTGRAY;
                character= "Zhang Yi";
                break;

        }
    }

}
