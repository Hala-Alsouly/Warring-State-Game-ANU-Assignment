package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.ArrayList;
import java.util.Collections;
import static org.junit.Assert.*;

public class removeCardsTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(2000);

    @Test
    public void testRemove() {
        String placement1 = "g0Aa0Bf1Ca1Dc5Ee1Fa4Ge3He2Ia2Jc2Kd0Lf0Mb4Nd4Oa6Pc3Qe0Ra5Sc1Td1Uc4Vb5Wb0Xa7Yf2Zb10a31z92b33b64d35g16b27d28c09";
        ArrayList<String> cardsPos1 = new ArrayList<>();
        for (int i = 0; i < placement1.length(); i += 3) {
            cardsPos1.add(placement1.substring(i, i + 3));
        }
        Collections.sort(cardsPos1);
        ArrayList<String> collection = new ArrayList<>();
        String placement2 = WarringStatesGame.removeCards(placement1, '8', collection);
        ArrayList<String> cardsPos2 = new ArrayList<>();
        for (int i = 0; i < placement2.length(); i += 3) {
            cardsPos2.add(placement2.substring(i, i + 3));
        }
        cardsPos1.remove("d28");
        cardsPos1.remove("z92");
        cardsPos1.add("z98");

        Collections.sort(cardsPos1);
        Collections.sort(cardsPos2);
        System.out.println(placement2.length());
        assertEquals(cardsPos1.size(), cardsPos2.size());
        for (int i = 0; i < cardsPos1.size(); i++) {
            assertEquals(cardsPos1.get(i), cardsPos2.get(i));
        }
    }

    @Test
    public void testRemoveSameKinCards() {
        String placement1 = "g0Aa0Bf1Ca1Dc5Ee1Fa4Ge3He2Ia2Jc2Kd0Lf0Mb4Nd4Oa6Pc3Qe0Ra5Sc1Td1Uc4Vb5Wb0Xa7Yf2Zb10a31z92b33b64d35g16b27d28c09";
        ArrayList<String> cardsPos1 = new ArrayList<>();
        for (int i = 0; i < placement1.length(); i += 3) {
            cardsPos1.add(placement1.substring(i, i + 3));
        }
        Collections.sort(cardsPos1);
        ArrayList<String> collection1 = new ArrayList<>();
        String placement2 = WarringStatesGame.removeCards(placement1, 'E', collection1);
        ArrayList<String> cardsPos2 = new ArrayList<>();
        for (int i = 0; i < placement2.length(); i += 3) {
            cardsPos2.add(placement2.substring(i, i + 3));
        }
        cardsPos1.remove("c5E");
        cardsPos1.remove("c2K");
        cardsPos1.remove("c3Q");
        cardsPos1.remove("z92");
        cardsPos1.add("z9E");

        Collections.sort(cardsPos1);
        Collections.sort(cardsPos2);
        System.out.println(placement2.length());
        assertEquals(cardsPos1.size(), cardsPos2.size());
        for (int i = 0; i < cardsPos1.size(); i++) {
            assertEquals(cardsPos1.get(i), cardsPos2.get(i));
        }
    }

    @Test
    public void testCardCollection() {
        String placement1 = "g0Aa0Bf1Ca1Dc5Ee1Fa4Ge3He2Ia2Jc2Kd0Lf0Mb4Nd4Oa6Pc3Qe0Ra5Sc1Td1Uc4Vb5Wb0Xa7Yf2Zb10a31z92b33b64d35g16b27d28c09";
        ArrayList<String> cardsPos1 = new ArrayList<>();
        for (int i = 0; i < placement1.length(); i += 3) {
            cardsPos1.add(placement1.substring(i, i + 3));
        }
        Collections.sort(cardsPos1);
        ArrayList<String> collection = new ArrayList<>();
        String placement2 = WarringStatesGame.removeCards(placement1, 'E', collection);
        ArrayList<String> cardsPos2 = new ArrayList<>();
        for (int i = 0; i < placement2.length(); i += 3) {
            cardsPos2.add(placement2.substring(i, i + 3));
        }
        cardsPos1.remove("c5");
        cardsPos1.remove("c2");
        cardsPos1.remove("c3");

        Collections.sort(cardsPos1);
        Collections.sort(cardsPos2);
        System.out.println(placement2.length());

        ArrayList<String> collectedCard = new ArrayList<>();
        collectedCard.add("c5");
        collectedCard.add("c2");
        collectedCard.add("c3");

        Collections.sort(collectedCard);
        Collections.sort(collection);
        assertEquals(collectedCard, collection);
    }
}