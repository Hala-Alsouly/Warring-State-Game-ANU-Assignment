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
           case 'a': return (c.getKingdomName().equals("秦"));
           case 'b': return (c.getKingdomName().equals("齊"));
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
            case 'a': assertTrue(" Expected: 秦, but got "+c.getKingdomName(),getKingdom(c,sub.charAt(0)));
                break;
            case 'b': assertTrue(" Expected: 齊, but got "+c.getKingdomName(),getKingdom(c,sub.charAt(0)));
                break;
            case 'c': assertTrue(" Expected: 楚, but got "+c.getKingdomName(),getKingdom(c,sub.charAt(0)));
                break;
            case 'd': assertTrue(" Expected: 趙, but got "+c.getKingdomName(),getKingdom(c,sub.charAt(0)));
                break;
            case 'e': assertTrue(" Expected: 韓, but got "+c.getKingdomName(),getKingdom(c,sub.charAt(0)));
                break;
            case 'f': assertTrue(" Expected: 魏, but got "+c.getKingdomName(),getKingdom(c,sub.charAt(0)));
                break;
            case 'g': assertTrue(" Expected: 燕, but got "+c.getKingdomName(),getKingdom(c,sub.charAt(0)));
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
                    case '0': assertTrue(" Expected: Duke Xiao, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                    break;
                    case '1': assertTrue(" Expected: Shang Yang, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                    break;
                    case '2': assertTrue(" Expected: King Hui, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                    break;
                    case '3': assertTrue(" Expected: King Zhaoxiang, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                    break;
                    case '4': assertTrue(" Expected: Lady Mi, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                    break;
                    case '5': assertTrue(" Expected: Bai Qi, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                    break;
                    case '6': assertTrue(" Expected: Lady Zhao, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                    break;
                    case '7': assertTrue(" Expected: King Zheng, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                    break;
                }break;
            case 'b':
                switch (sub.charAt(1)) {
                    case '0': assertTrue(" Expected: King Xuan, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                    break;
                    case '1': assertTrue(" Expected: Zhong Wuyan, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                    break;
                    case '2': assertTrue(" Expected: Lord Menchang, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '3': assertTrue(" Expected: King Xiang, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '4': assertTrue(" Expected: Queen Junwang, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '5': assertTrue(" Expected: King Jian, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '6': assertTrue(" Expected: Sun Binn, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                }break;
            case 'c':
                switch (sub.charAt(1)) {
                    case '0': assertTrue(" Expected: Wu Qi, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '1': assertTrue(" Expected: King Kaolien, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '2': assertTrue(" Expected: King You, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '3': assertTrue(" Expected: Qu Yuann, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '4': assertTrue(" Expected: King Fuchu, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '5': assertTrue(" Expected: Lord Chunshen, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                }break;
            case 'd':
                switch (sub.charAt(1)) {
                    case '0': assertTrue(" Expected: King Wuling, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '1': assertTrue(" Expected: Lord Pingyuan, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '2': assertTrue(" Expected: King Xiaocheng, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '3': assertTrue(" Expected: Li Mu, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '4': assertTrue(" Expected: Lian Po, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                }break;
            case 'e':
                switch (sub.charAt(1)) {
                    case '0': assertTrue(" Expected: Marquess Ai, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '1': assertTrue(" Expected: King Huanhuin, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '2': assertTrue(" Expected: King An, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '3': assertTrue(" Expected: Han Fei, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                }break;
            case 'f':
                switch (sub.charAt(1)) {
                    case '0': assertTrue(" Expected: Marquess Wen, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '1': assertTrue(" Expected: Lord Xinling, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '2': assertTrue(" Expected: King Anxi, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                }break;
            case 'g':
                switch (sub.charAt(1)) {
                    case '0': assertTrue(" Expected: King Xi, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                    case '1': assertTrue(" Expected: Prince Dan, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                        break;
                }break;
            case 'z': assertTrue(" Expected: Zhang Yi, but got "+c.getCharacter(),getCharachter(c,sub.charAt(0),sub.charAt(1)));
                break;
        }

    }


    @Test
    public void getCardPos() {
        String sub=s[r.nextInt(35)];
        char position=pos.charAt(r.nextInt(36));
        Card c= new Card(sub.charAt(0),sub.charAt(1),position);
        assertTrue("Wrong position, expected: "+position+", but got "+c.getCardPos(),position==c.getCardPos());
    }

    @Test
    public void getPosInArray() {
        String sub=s[r.nextInt(35)];
        char position=pos.charAt(r.nextInt(36));
        String posChars="456789YZ0123STUVWXMNOPQRGHIJKLABCDEF";
        Card c= new Card(sub.charAt(0),sub.charAt(1),position);
        int index= posChars.indexOf(position);
        assertTrue("invalid index, expected "+position+", but got "+c.getCardPos(),index==c.getPosInArray(position));
    }

    @Test
    public void testString() {
        String sub=s[r.nextInt(35)];
        char position=pos.charAt(r.nextInt(36));
        testString(sub.charAt(0),sub.charAt(1),position,sub+position);
    }
}