package comp1110.ass2.gui;

import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
//created by Hala
// create 36 card placement
public class Placement {
    private Card []cards= new Card[36];
    private static String start="g0Aa0Bf1Ca1Dc5Ee1Fa4Ge3He2Ia2Jc2Kd0Lf0Mb4Nd4Oa6Pc3Qe0Ra5Sc1Td1Uc4Vb5Wb0Xa7Yf2Zb10a31z92b33b64d35g16b27d28c09";
    public Placement(){
        int j=0;
        for (int i =0; i<36;i++,j+=3)
            cards[i]=new Card(start.charAt(j),start.charAt(j+1),start.charAt(j+2));
        //ShuffleCards ();
    }
    public Placement(String start){
        int j=0;
        for (int i =0; i<36;i++,j+=3)
            cards[i]=new Card(start.charAt(j),start.charAt(j+1),start.charAt(j+2));
    }
    //to shuffle the cards in the beginning of the game or when restart
    public void ShuffleCards (){
        List<Card> list=Arrays.asList(cards);
        Collections.shuffle(list);
        int i=0;
        for (Card c:list)
        {
            c.setCardPos(Card.posChars.charAt(i));
            cards[i++]=c;
        }


    }
// convert from array of objects to string
    @Override
    public String toString() {
        String s="";
        for (Card c:cards)
            s+=c;
        return s;
    }

    public String getKingdomName(int i) {
        return cards[i].getKingdomName();
    }

    public String getCharacter(int i) {
        return cards[i].getCharacter();
    }

    public Color getColor(int i) {
        return cards[i].getCardColor();
    }

}
