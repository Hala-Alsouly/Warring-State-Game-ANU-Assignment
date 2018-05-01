package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

public class CardTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(2000);
    private String []s= new String[]{"a0","a1","a2","a3","a4","a5","a6","a7","b0","b1","b2","b3","b4","b5","b6",
            "c0","c1","c2","c3","c4","c5","d0","d1","d2","d3","d4","e0","e1","e2","e3","f0","f1","f2","g0","g1"};
    private String pos ="456789YZ0123STUVWXMNOPQRGHIJKLABCDEF";
    private Random r= new Random();

   /* private String setCard(){
        String sub=s[r.nextInt(36)];
        char position=pos.charAt(r.nextInt(36));
        Card c= new Card(sub.charAt(0),sub.charAt(1),position);
        return sub+position;

    }*/

    private void testString(char k, char c, char p, String reference) {
        String out = (new Card(k, c, p)).toString();
        assertTrue("Incorrect output for Card toString(): '" + k + c +p + "', expected: '" + reference + "' but got: '" + out + "'", out.equals(reference));
    }
   private boolean getKingdom(Card c, char k){
       switch (k){
           case 'a': return (c.getKingdomName()=="秦");

           case 'b': return (c.getKingdomName()=="齊");
           case 'c': return (c.getKingdomName()=="楚");
           case 'd': return (c.getKingdomName()=="趙");
           case 'e': return  (c.getKingdomName()=="韓");
           case 'f': return (c.getKingdomName()=="魏");
           case 'g': return (c.getKingdomName()=="燕");
           default: return false;
       }
   }

   private boolean getCharachter(Card c,char k, char ch){
       switch (k){
           case 'a':
               switch (ch) {
                   case '0': return c.getCharacter()== "Duke Xiao";
                   case '1': return c.getCharacter()== "Shang Yang";
                   case '2': return c.getCharacter()== "King Hui";
                   case '3': return c.getCharacter()== "King Zhaoxiang";
                   case '4': return c.getCharacter()== "Lady Mi";
                   case '5': return c.getCharacter()== "Bai Qi";
                   case '6': return c.getCharacter()== "Lady Zhao";
                   case '7': return c.getCharacter()== "King Zheng";
                   default: return false;
               }
           case 'b':
               switch (ch) {
                   case '0': return c.getCharacter()== "King Xuan";
                   case '1': return c.getCharacter()== "Zhong Wuyan";
                   case '2': return c.getCharacter()== "Lord Menchang";
                   case '3': return c.getCharacter()== "King Xiang";
                   case '4': return c.getCharacter()== "Queen Junwang";
                   case '5': return c.getCharacter()== "King Jian";
                   case '6': return c.getCharacter()== "Sun Bin";
                   default:return false;
               }
           case 'c':
               switch (ch) {
                   case '0': return c.getCharacter()== "Wu Qi";
                   case '1': return c.getCharacter()== "King Kaolie";
                   case '2': return c.getCharacter()== "King You";
                   case '3': return c.getCharacter()== "Qu Yuan";
                   case '4': return c.getCharacter()== "Fuchu";
                   case '5': return c.getCharacter()== "Lord Chunshen";
                   default:return false;
               }
           case 'd':
               switch (ch) {
                   case '0': return c.getCharacter()== "King Wuling";
                   case '1': return c.getCharacter()== "Lord Pingyuan";
                   case '2': return c.getCharacter()== "King Xiaocheng";
                   case '3': return c.getCharacter()== "Li Mu";
                   case '4': return c.getCharacter()== "Lian Po";
                   default: return false;
               }
           case 'e':
               switch (ch) {
                   case '0': return c.getCharacter()== "Marquess Ai";
                   case '1': return c.getCharacter()== "King Huanhui";
                   case '2': return c.getCharacter()== "King An";
                   case '3': return c.getCharacter()== "Han Fei";
                   default:return false;
               }
           case 'f':
               switch (ch) {
                   case '0': return c.getCharacter()== "Marquess Wen";
                   case '1': return c.getCharacter()== "Lord Xinling";
                   case '2': return c.getCharacter()== "King Anxi";
                   default:return false;
               }
           case 'g':
               switch (ch) {
                   case '0': return c.getCharacter()== "King Xi";
                   case '1': return c.getCharacter()== "Prince Dan";
                   default:return false;
               }
           case 'z': return c.getCharacter()== "Zhang Yi";

               default:return false;
       }
   }

    @Test
    public void getKingdomName() {
        String sub=s[r.nextInt(35)];
        char position=pos.charAt(r.nextInt(36));
        Card c= new Card(sub.charAt(0),sub.charAt(1),position);
        switch (sub.charAt(0)){
            case 'a': assertTrue(" Should get 秦, but got "+c.getKingdomName(),getKingdom(c,sub.charAt(0)));
                break;
            case 'b': assertTrue(" Should get 齊, but got "+c.getKingdomName(),getKingdom(c,sub.charAt(0)));
                break;
            case 'c': assertTrue(" Should get 楚, but got "+c.getKingdomName(),getKingdom(c,sub.charAt(0)));
                break;
            case 'd': assertTrue(" Should get 趙, but got "+c.getKingdomName(),getKingdom(c,sub.charAt(0)));
                break;
            case 'e': assertTrue(" Should get 韓, but got "+c.getKingdomName(),getKingdom(c,sub.charAt(0)));
                break;
            case 'f': assertTrue(" Should get 魏, but got "+c.getKingdomName(),getKingdom(c,sub.charAt(0)));
                break;
            case 'g': assertTrue(" Should get 燕, but got "+c.getKingdomName(),getKingdom(c,sub.charAt(0)));
                break;
        }

   }

    @Test
    public void getCharacter() {
        String sub=s[r.nextInt(35)];
        char position=pos.charAt(r.nextInt(36));
        Card c= new Card(sub.charAt(0),sub.charAt(1),position);
        switch (sub.charAt(0)){
            case 'a':
                switch (sub.charAt(1)) {
                    case '0': assertTrue(" Should get Duke Xiao, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                    break;
                    case '1': assertTrue(" Should get Shang Yang, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                    break;
                    case '2': assertTrue(" Should get King Hui, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                    break;
                    case '3': assertTrue(" Should get King Zhaoxiang, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                    break;
                    case '4': assertTrue(" Should get Lady Mi, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                    break;
                    case '5': assertTrue(" Should get Bai Qi, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                    break;
                    case '6': assertTrue(" Should get Lady Zhao, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                    break;
                    case '7': assertTrue(" Should get King Zheng, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                    break;
                }break;
            case 'b':
                switch (sub.charAt(1)) {
                    case '0': assertTrue(" Should get King Xuan, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                    break;
                    case '1': assertTrue(" Should get Zhong Wuyan, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                    break;
                    case '2': assertTrue(" Should get Lord Menchang, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '3': assertTrue(" Should get King Xiang, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '4': assertTrue(" Should get Queen Junwang, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '5': assertTrue(" Should get King Jian, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '6': assertTrue(" Should get Sun Binn, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                }break;
            case 'c':
                switch (sub.charAt(1)) {
                    case '0': assertTrue(" Should get Wu Qi, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '1': assertTrue(" Should get King Kaolien, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '2': assertTrue(" Should get King You, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '3': assertTrue(" Should get Qu Yuann, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '4': assertTrue(" Should get King Fuchu, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '5': assertTrue(" Should get Lord Chunshen, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                }break;
            case 'd':
                switch (sub.charAt(1)) {
                    case '0': assertTrue(" Should get King Wuling, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '1': assertTrue(" Should get Lord Pingyuan, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '2': assertTrue(" Should get King Xiaocheng, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '3': assertTrue(" Should get Li Mu, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '4': assertTrue(" Should get Lian Po, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                }break;
            case 'e':
                switch (sub.charAt(1)) {
                    case '0': assertTrue(" Should get Marquess Ai, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '1': assertTrue(" Should get King Huanhuin, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '2': assertTrue(" Should get King An, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '3': assertTrue(" Should get Han Fei, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                }break;
            case 'f':
                switch (sub.charAt(1)) {
                    case '0': assertTrue(" Should get Marquess Wen, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '1': assertTrue(" Should get Lord Xinling, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '2': assertTrue(" Should get King Anxi, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                }break;
            case 'g':
                switch (sub.charAt(1)) {
                    case '0': assertTrue(" Should get King Xi, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '1': assertTrue(" Should get Prince Dan, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                }break;
            case 'z': assertTrue(" Should get Zhang Yi, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                break;
        }

    }


    @Test
    public void getCardPos() {
        String sub=s[r.nextInt(35)];
        char position=pos.charAt(r.nextInt(36));
        Card c= new Card(sub.charAt(0),sub.charAt(1),position);
        assertTrue("Wrong position, should return "+position+", but got "+c.getCardPos(),position==c.getCardPos());
    }

    @Test
    public void getPosInArray() {
        String sub=s[r.nextInt(35)];
        char position=pos.charAt(r.nextInt(36));
        String posChars="456789YZ0123STUVWXMNOPQRGHIJKLABCDEF";
        Card c= new Card(sub.charAt(0),sub.charAt(1),position);
        int index= posChars.indexOf(position);
        assertTrue("Wrong position, should return "+position+", but got "+c.getCardPos(),index==c.getPosInArray(position));
    }

    @Test
    public void testString() {
        String sub=s[r.nextInt(35)];
        char position=pos.charAt(r.nextInt(36));
        testString(sub.charAt(0),sub.charAt(1),position,sub+position);
    }
}