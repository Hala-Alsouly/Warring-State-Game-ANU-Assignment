package comp1110.ass2;
//created by Hala
//store a collected kingdom cards information for one player
public class CollectedCardsInfo {
    public char c;
    public int count=-1;//how many cards collected from this kingdom
    public int lastMove;// last move to won the last card
    public int playernum=-1;
    public int []playerscollection={0,0,0,0};

    public CollectedCardsInfo( char c){
        this.c=c;
    }

    public CollectedCardsInfo(){
    }

}
