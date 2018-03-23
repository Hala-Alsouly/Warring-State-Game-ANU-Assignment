package comp1110.ass2.gui;

import java.util.Random;

public class Placement {
    private String []cards= new String[36];

    //create 36 card placement
    public void setPlacement(){
        int x;
        for (int i=0;i<8;i++){
            cards[i]="a"+i;
        }
        x=8;
        for ( int i=0;i<7;i++){
            cards[x]="b"+i;
            x++;
        }
        for (int i=0;i<6;i++){
            cards[x]="c"+i;
            x++;
        }
        for ( int i=0;i<5;i++){
            cards[x]="d"+i;
            x++;
        }
        for ( int i=0;i<4;i++){
            cards[x]="e"+i;
            x++;
        }
        for ( int i=0;i<3;i++){
            cards[x]="f"+i;
            x++;
        }
        for (int i=0;i<2;i++){
            cards[x]="g"+i;
            x++;
        }
        cards[x]="z9";
        //for(int i=0;i<36;i++)
        //System.out.println(cards[i]);

    }
    public String ShuffleCards (){
        //to shuffle the cards in the beginning of the game or when restart
        String[] shuffeldCards= new String[36];
        String sc="";
        // shufle
        boolean[]usedcards=new boolean[cards.length];
        Random rand =new Random();
        for (int i=0; i<cards.length;i++){
            int r=rand.nextInt(cards.length);
            while (usedcards[r]){
                r=(r+1)%cards.length;
            }
            usedcards[r]=true;
            if (i<=25){
                shuffeldCards[i]=cards[r]+(char)(i+65);
            }else{
                shuffeldCards[i]=cards[r]+(char)(i+22);
            }

        }
        //return the shuffeld card as a string
        for (int i= 0 ; i<shuffeldCards.length;i++){
            sc=sc+shuffeldCards[i];
        }
        return sc;
    }

    /*just to test the shuffle methode and class if it is work correctly
    public static void main(String[] args) {
        Placement s= new Placement();
        String sc;
        s.setPlacement();
        sc=s.ShuffleCards();
        System.out.println(sc.length());
            System.out.println(sc);


    }*/


}
