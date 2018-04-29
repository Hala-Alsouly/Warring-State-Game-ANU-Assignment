package comp1110.ass2;
//created by Hala
//store a collected kingdom cards information for one player
public class CollectedCardsInfo {
    char c;
    int count;//how many cards collected from this kingdom
    int lastMove;// last move to won the last card
    int playernum;

    public CollectedCardsInfo( char c){
        this.c=c;
    }

}
