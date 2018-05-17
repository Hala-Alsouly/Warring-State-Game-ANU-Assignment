package comp1110.ass2;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {

    boolean isRobot;
    int flagNum = 0;
    boolean[] flags={false,false,false,false,false,false,false};
    int ID;
    HashMap<String,Integer> cardNumbers = new HashMap<>();

    public Player(int ID){
        this.ID=ID;
        cardNumbers.put("a",0);
        cardNumbers.put("b",0);
        cardNumbers.put("c",0);
        cardNumbers.put("d",0);
        cardNumbers.put("e",0);
        cardNumbers.put("f",0);
        cardNumbers.put("g",0);
    }

    //this player, get each country's card number
    public HashMap<String, Integer> getCardNumbers() {
        return cardNumbers;
    }

    //this player, get each the array's flag to show this player get whihc country's flag
    public int getFlagNumbers() {
        return flagNum;
    }
    public boolean[] getFlags(){
        return flags;
    }

    //use array 'flags' to represent which country's flag is gotten
    public void addFlags(String country){
        if(country.equals("a")) flags[0]=true;
        if(country.equals("b"))  flags[1]=true;
        if(country.equals("c" ))flags[2]=true;
        if(country.equals("d"))flags[3]=true;
        if(country.equals("e")) flags[4]=true;
        if(country.equals("f")) flags[5]=true;
        if(country.equals("g")) flags[6]=true;
        for (boolean b:flags){
            System.out.println(b);
        }
        System.out.println();
    }

    public void deleteFlags(String country){
        if(country.equals("a")) flags[0]=false;
        if(country.equals("b"))  flags[1]=false;
        if(country.equals("c" ))flags[2]=false;
        if(country.equals("d"))flags[3]=false;
        if(country.equals("e")) flags[4]=false;
        if(country.equals("f")) flags[5]=false;
        if(country.equals("g")) flags[6]=false;
    }


    public void addCardsNum(String country, int num){
        cardNumbers.put(country,cardNumbers.get(country)+ num);
    }

    public void  addFlagsNum(int num){
        this.flagNum += num;
    }

}
