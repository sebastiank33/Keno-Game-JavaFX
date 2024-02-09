import static org.junit.jupiter.api.Assertions.*;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


public class MyTest {
    JavaFXTemplate app = new JavaFXTemplate();

    @Test
    void testMoney1() {
        // Test for selectedSpot[0] == 10
        app.selectedSpot[0] = 10;
        assertEquals(5, app.money_earned(0));
        assertEquals(0, app.money_earned(1));
        assertEquals(0, app.money_earned(2));
        assertEquals(0, app.money_earned(3));
        assertEquals(0, app.money_earned(4));
        assertEquals(2, app.money_earned(5));
        assertEquals(15, app.money_earned(6));
        assertEquals(40, app.money_earned(7));
        assertEquals(450, app.money_earned(8));
        assertEquals(4250, app.money_earned(9));
        assertEquals(100000, app.money_earned(10));
    }

    @Test
        //test money earned for 8 spot game
    void testMoney2() {
        app.selectedSpot[0] = 8;
        assertEquals(0, app.money_earned(0));
        assertEquals(0, app.money_earned(1));
        assertEquals(0, app.money_earned(2));
        assertEquals(0, app.money_earned(3));
        assertEquals(2, app.money_earned(4));
        assertEquals(12, app.money_earned(5));
        assertEquals(50, app.money_earned(6));
        assertEquals(750, app.money_earned(7));
        assertEquals(10000, app.money_earned(8));
    }

    @Test
    void testMoney3() {
        // Test for selectedSpot[0] == 4
        app.selectedSpot[0] = 4;
        assertEquals(0, app.money_earned(0));
        assertEquals(0, app.money_earned(1));
        assertEquals(1, app.money_earned(2));
        assertEquals(5, app.money_earned(3));
        assertEquals(75, app.money_earned(4));
        app.selectedSpot[0] = 2;
        assertEquals(2, app.money_earned(1));
        app.selectedSpot[0] = 20;
        assertEquals(2, app.money_earned(1));
    }

    @Test
    void testMoney4() {
        //when numbers hit is 0
        app.selectedSpot[0] = 10;
        assertEquals(5, app.money_earned(0));

        app.selectedSpot[0] = 8;
        assertEquals(0, app.money_earned(0));

        app.selectedSpot[0] = 4;
        assertEquals(0, app.money_earned(0));

        app.selectedSpot[0] = 2;
        assertEquals(0, app.money_earned(0));
    }

    @Test
    void testMoney5() {
        app.selectedSpot[0] = 2;
        assertEquals(2, app.money_earned(1));
    }

    @Test
    void testLogicforNumbersHit() {
        int numbersHit = 0;
        ArrayList<Integer> drawnNumbersFromGrid = new ArrayList<Integer>();
        drawnNumbersFromGrid.add(1);
        drawnNumbersFromGrid.add(2);
        drawnNumbersFromGrid.add(3);
        drawnNumbersFromGrid.add(4);

        ArrayList<Integer> drawnNumbersFromGrid2 = new ArrayList<Integer>();
        drawnNumbersFromGrid2.add(1);
        drawnNumbersFromGrid2.add(2);
        drawnNumbersFromGrid2.add(3);
        drawnNumbersFromGrid2.add(4);

        for (int i = 0; i < drawnNumbersFromGrid.size(); i++) {
            for (int j = 0; j < drawnNumbersFromGrid2.size(); j++) {
                if (drawnNumbersFromGrid.get(i) == drawnNumbersFromGrid2.get(j)) {
                    numbersHit++;
                }
            }
        }
        assertEquals(4, numbersHit);
        //test numbersHit for a 4 spot game
        app.selectedSpot[0] = 4;
        //money earned should be 75
        assertEquals(75, app.money_earned(numbersHit));
    }

    @Test
    void testLogicforNumbersHit2() {
        int numbersHit = 0;
        ArrayList<Integer> drawnNumbersFromGrid = new ArrayList<Integer>();
        for (int i = 0; i < 8; i++) {
            drawnNumbersFromGrid.add(i);
        }
        ArrayList<Integer> drawnNumbersFromGrid2 = new ArrayList<Integer>();
        //put 8 random numbers in the array using a for loop
        for (int i = 0; i < 8; i++) {
            drawnNumbersFromGrid2.add(i);
        }
        for (int i = 0; i < drawnNumbersFromGrid.size(); i++) {
            for (int j = 0; j < drawnNumbersFromGrid2.size(); j++) {
                if (drawnNumbersFromGrid.get(i) == drawnNumbersFromGrid2.get(j)) {
                    numbersHit++;
                }
            }
        }
        app.selectedSpot[0] = 8;
        assertEquals(10000, app.money_earned(numbersHit));
    }

    @Test
    void testLogicforNumbersHit3() {
        int numbersHit = 0;
        ArrayList<Integer> drawnNumbersFromGrid = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            drawnNumbersFromGrid.add(i);
        }
        ArrayList<Integer> drawnNumbersFromGrid2 = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            drawnNumbersFromGrid2.add(i);
        }
        for (int i = 0; i < drawnNumbersFromGrid.size(); i++) {
            for (int j = 0; j < drawnNumbersFromGrid2.size(); j++) {
                if (drawnNumbersFromGrid.get(i) == drawnNumbersFromGrid2.get(j)) {
                    numbersHit++;
                }
            }
        }
        app.selectedSpot[0] = 10;
        assertEquals(100000, app.money_earned(numbersHit));
    }

    @Test
        //test on empty array
    void testEmpty() {
        int numbersHit = 0;
        ArrayList<Integer> drawnNumbersFromGrid = new ArrayList<Integer>();
        ArrayList<Integer> drawnNumbersFromGrid2 = new ArrayList<Integer>();
        for (int i = 0; i < drawnNumbersFromGrid.size(); i++) {
            for (int j = 0; j < drawnNumbersFromGrid2.size(); j++) {
                if (drawnNumbersFromGrid.get(i) == drawnNumbersFromGrid2.get(j)) {
                    numbersHit++;
                }
            }
        }
        app.selectedSpot[0] = 10;
        assertEquals(5, app.money_earned(numbersHit));

        app.selectedSpot[0] = 8;
        assertEquals(0, app.money_earned(numbersHit));

        app.selectedSpot[0] = 4;
        assertEquals(0, app.money_earned(numbersHit));

        app.selectedSpot[0] = 2;
        assertEquals(0, app.money_earned(numbersHit));
    }

    @Test
    void testLogicfor20UniqueRandomNumbers() {
        ArrayList<Integer> temp = new ArrayList<>();
        // populate winning numbers with unique random numbers
        for (int i = 0; i < 20; i++) {
            int number = (int) (Math.random() * 80) + 1;
            while (temp.contains((Object) number)) {
                number = (int) (Math.random() * 80) + 1;
            }
            temp.add(number);
        }
        //make sure the numbers are unique
        for (int i = 0; i < temp.size(); i++) {
            for (int j = i + 1; j < temp.size(); j++) {
                if (temp.get(i) == temp.get(j)) {
                    fail("numbers are not unique");
                }
            }
        }
        //check size
        assertEquals(20, temp.size());
    }

    @Test
    void TwoDraws4Spots() {
        int numbersHit = 0;
        ArrayList<Integer> drawnNumbersFromGrid = new ArrayList<Integer>();
        drawnNumbersFromGrid.add(1);
        drawnNumbersFromGrid.add(2);
        drawnNumbersFromGrid.add(3);
        drawnNumbersFromGrid.add(4);
        ArrayList<Integer> drawnNumbersFromGrid2 = new ArrayList<Integer>();

        drawnNumbersFromGrid2.add(1);
        drawnNumbersFromGrid2.add(2);
        drawnNumbersFromGrid2.add(3);
        drawnNumbersFromGrid2.add(4);

        app.selectedSpot[0] = 4;

        int totalNumbersHit = 0; // new variable to store total numbers hit
        int temp = 0;
        for (int a = 0; a < 2; a++) { //multiple draws
            for (int i = 0; i < drawnNumbersFromGrid.size(); i++) {
                for (int j = 0; j < drawnNumbersFromGrid2.size(); j++) {
                    if (drawnNumbersFromGrid.get(i) == drawnNumbersFromGrid2.get(j)) {
                        numbersHit++;
                    }
                }
            }
            totalNumbersHit += numbersHit; // add current numbers hit to total
            temp += app.money_earned(numbersHit);
            numbersHit = 0;
        }
        assertEquals(8, totalNumbersHit); // use totalNumbersHit instead of numbersHit
//test numbersHit for a 4 spot game
//total money should be 150

        assertEquals(150, temp);
    }

    //test multipledrawPayout logic for 4 spot 3 draws
    @Test
    void ThreeDraws4Spots() {
        int numbersHit = 0;
        ArrayList<Integer> drawnNumbersFromGrid = new ArrayList<Integer>();
        drawnNumbersFromGrid.add(1);
        drawnNumbersFromGrid.add(2);
        drawnNumbersFromGrid.add(3);
        drawnNumbersFromGrid.add(4);
        ArrayList<Integer> drawnNumbersFromGrid2 = new ArrayList<Integer>();

        drawnNumbersFromGrid2.add(1);
        drawnNumbersFromGrid2.add(2);
        drawnNumbersFromGrid2.add(3);
        drawnNumbersFromGrid2.add(4);

        app.selectedSpot[0] = 4;

        int totalNumbersHit = 0; // new variable to store total numbers hit
        int temp = 0;
        for (int a = 0; a < 3; a++) { //multiple draws
            for (int i = 0; i < drawnNumbersFromGrid.size(); i++) {
                for (int j = 0; j < drawnNumbersFromGrid2.size(); j++) {
                    if (drawnNumbersFromGrid.get(i) == drawnNumbersFromGrid2.get(j)) {
                        numbersHit++;
                    }
                }
            }
            totalNumbersHit += numbersHit; // add current numbers hit to total
            temp += app.money_earned(numbersHit);
            numbersHit = 0;
        }
        assertEquals(12, totalNumbersHit); // use totalNumbersHit instead of numbersHit
//test numbersHit for a 4 spot game


        assertEquals(225, temp);
    }

    @Test
    void FourDraws4Spots() {
        int numbersHit = 0;
        ArrayList<Integer> drawnNumbersFromGrid = new ArrayList<Integer>();
        drawnNumbersFromGrid.add(1);
        drawnNumbersFromGrid.add(2);
        drawnNumbersFromGrid.add(3);
        drawnNumbersFromGrid.add(4);
        ArrayList<Integer> drawnNumbersFromGrid2 = new ArrayList<Integer>();

        drawnNumbersFromGrid2.add(1);
        drawnNumbersFromGrid2.add(2);
        drawnNumbersFromGrid2.add(3);
        drawnNumbersFromGrid2.add(4);

        app.selectedSpot[0] = 4;

        int totalNumbersHit = 0; // new variable to store total numbers hit
        int temp = 0;
        for (int a = 0; a < 4; a++) { //multiple draws
            for (int i = 0; i < drawnNumbersFromGrid.size(); i++) {
                for (int j = 0; j < drawnNumbersFromGrid2.size(); j++) {
                    if (drawnNumbersFromGrid.get(i) == drawnNumbersFromGrid2.get(j)) {
                        numbersHit++;
                    }
                }
            }
            totalNumbersHit += numbersHit; // add current numbers hit to total
            temp += app.money_earned(numbersHit);
            numbersHit = 0;
        }
        assertEquals(16, totalNumbersHit); // use totalNumbersHit instead of numbersHit
//test numbersHit for a 4 spot game


        assertEquals(300, temp);
    }

    @Test
    void TwoDraws8Spot() {
        int numbersHit = 0;
        ArrayList<Integer> drawnNumbersFromGrid = new ArrayList<Integer>();
        drawnNumbersFromGrid.add(1);
        drawnNumbersFromGrid.add(2);
        drawnNumbersFromGrid.add(3);
        drawnNumbersFromGrid.add(4);
        drawnNumbersFromGrid.add(5);
        drawnNumbersFromGrid.add(6);
        drawnNumbersFromGrid.add(7);
        drawnNumbersFromGrid.add(8);
        ArrayList<Integer> drawnNumbersFromGrid2 = new ArrayList<Integer>();

        drawnNumbersFromGrid2.add(1);
        drawnNumbersFromGrid2.add(2);
        drawnNumbersFromGrid2.add(3);
        drawnNumbersFromGrid2.add(4);
        drawnNumbersFromGrid2.add(5);
        drawnNumbersFromGrid2.add(6);
        drawnNumbersFromGrid2.add(7);
        drawnNumbersFromGrid2.add(8);

        app.selectedSpot[0] = 8;

        int totalNumbersHit = 0; // new variable to store total numbers hit
        int temp = 0;
        for (int a = 0; a < 2; a++) { //multiple draws
            for (int i = 0; i < drawnNumbersFromGrid.size(); i++) {
                for (int j = 0; j < drawnNumbersFromGrid2.size(); j++) {
                    if (drawnNumbersFromGrid.get(i) == drawnNumbersFromGrid2.get(j)) {
                        numbersHit++;
                    }
                }
            }
            totalNumbersHit += numbersHit; // add current numbers hit to total
            temp += app.money_earned(numbersHit);
            numbersHit = 0;
        }
        assertEquals(16, totalNumbersHit); // use totalNumbersHit instead of numbersHit

        assertEquals(20000, temp);
    }

    @Test
    void ThreeDraws8Spot() {
        int numbersHit = 0;
        ArrayList<Integer> drawnNumbersFromGrid = new ArrayList<Integer>();
        drawnNumbersFromGrid.add(1);
        drawnNumbersFromGrid.add(2);
        drawnNumbersFromGrid.add(3);
        drawnNumbersFromGrid.add(4);
        drawnNumbersFromGrid.add(5);
        drawnNumbersFromGrid.add(6);
        drawnNumbersFromGrid.add(7);
        drawnNumbersFromGrid.add(8);
        ArrayList<Integer> drawnNumbersFromGrid2 = new ArrayList<Integer>();
        drawnNumbersFromGrid2.add(1);
        drawnNumbersFromGrid2.add(2);
        drawnNumbersFromGrid2.add(3);
        drawnNumbersFromGrid2.add(4);
        drawnNumbersFromGrid2.add(5);
        drawnNumbersFromGrid2.add(6);
        drawnNumbersFromGrid2.add(7);
        drawnNumbersFromGrid2.add(8);

        app.selectedSpot[0] = 8;

        int totalNumbersHit = 0; // new variable to store total numbers hit
        int temp = 0;
        for (int a = 0; a < 3; a++) { //multiple draws
            for (int i = 0; i < drawnNumbersFromGrid.size(); i++) {
                for (int j = 0; j < drawnNumbersFromGrid2.size(); j++) {
                    if (drawnNumbersFromGrid.get(i) == drawnNumbersFromGrid2.get(j)) {
                        numbersHit++;
                    }
                }
            }
            totalNumbersHit += numbersHit; // add current numbers hit to total
            temp += app.money_earned(numbersHit);
            numbersHit = 0;
        }
        assertEquals(24, totalNumbersHit); // use totalNumbersHit instead of numbersHit

        assertEquals(30000, temp);
    }

    @Test
    void FourDraws8Spot() {
        int numbersHit = 0;
        ArrayList<Integer> drawnNumbersFromGrid = new ArrayList<Integer>();
        drawnNumbersFromGrid.add(1);
        drawnNumbersFromGrid.add(2);
        drawnNumbersFromGrid.add(3);
        drawnNumbersFromGrid.add(4);
        drawnNumbersFromGrid.add(5);
        drawnNumbersFromGrid.add(6);
        drawnNumbersFromGrid.add(7);
        drawnNumbersFromGrid.add(8);
        ArrayList<Integer> drawnNumbersFromGrid2 = new ArrayList<Integer>();
        drawnNumbersFromGrid2.add(1);
        drawnNumbersFromGrid2.add(2);
        drawnNumbersFromGrid2.add(3);
        drawnNumbersFromGrid2.add(4);
        drawnNumbersFromGrid2.add(5);
        drawnNumbersFromGrid2.add(6);
        drawnNumbersFromGrid2.add(7);
        drawnNumbersFromGrid2.add(8);

        app.selectedSpot[0] = 8;

        int totalNumbersHit = 0; // new variable to store total numbers hit
        int temp = 0;
        for (int a = 0; a < 4; a++) { //multiple draws
            for (int i = 0; i < drawnNumbersFromGrid.size(); i++) {
                for (int j = 0; j < drawnNumbersFromGrid2.size(); j++) {
                    if (drawnNumbersFromGrid.get(i) == drawnNumbersFromGrid2.get(j)) {
                        numbersHit++;
                    }
                }
            }
            totalNumbersHit += numbersHit; // add current numbers hit to total
            temp += app.money_earned(numbersHit);
            numbersHit = 0;
        }
        assertEquals(32, totalNumbersHit); // use totalNumbersHit instead of numbersHit

        assertEquals(40000, temp);
    }

    @Test
    void TwoDraws10Spot() {
        int numbersHit = 0;
        ArrayList<Integer> drawnNumbersFromGrid = new ArrayList<Integer>();
        drawnNumbersFromGrid.add(1);
        drawnNumbersFromGrid.add(2);
        drawnNumbersFromGrid.add(3);
        drawnNumbersFromGrid.add(4);
        drawnNumbersFromGrid.add(5);
        drawnNumbersFromGrid.add(6);
        drawnNumbersFromGrid.add(7);
        drawnNumbersFromGrid.add(8);
        drawnNumbersFromGrid.add(9);
        drawnNumbersFromGrid.add(10);
        ArrayList<Integer> drawnNumbersFromGrid2 = new ArrayList<Integer>();

        drawnNumbersFromGrid2.add(1);
        drawnNumbersFromGrid2.add(2);
        drawnNumbersFromGrid2.add(3);
        drawnNumbersFromGrid2.add(4);
        drawnNumbersFromGrid2.add(5);
        drawnNumbersFromGrid2.add(6);
        drawnNumbersFromGrid2.add(7);
        drawnNumbersFromGrid2.add(8);
        drawnNumbersFromGrid2.add(9);
        drawnNumbersFromGrid2.add(10);

        app.selectedSpot[0] = 10;

        int totalNumbersHit = 0; // new variable to store total numbers hit
        int temp = 0;
        for (int a = 0; a < 2; a++) { //multiple draws
            for (int i = 0; i < drawnNumbersFromGrid.size(); i++) {
                for (int j = 0; j < drawnNumbersFromGrid2.size(); j++) {
                    if (drawnNumbersFromGrid.get(i) == drawnNumbersFromGrid2.get(j)) {
                        numbersHit++;
                    }
                }
            }
            totalNumbersHit += numbersHit; // add current numbers hit to total
            temp += app.money_earned(numbersHit);
            numbersHit = 0;
        }
        assertEquals(20, totalNumbersHit); // use totalNumbersHit instead of numbersHit

        assertEquals(200000, temp);
    }

    @Test
    void ThreeDraws10Spot() {
        int numbersHit = 0;
        ArrayList<Integer> drawnNumbersFromGrid = new ArrayList<Integer>();
        drawnNumbersFromGrid.add(1);
        drawnNumbersFromGrid.add(2);
        drawnNumbersFromGrid.add(3);
        drawnNumbersFromGrid.add(4);
        drawnNumbersFromGrid.add(5);
        drawnNumbersFromGrid.add(6);
        drawnNumbersFromGrid.add(7);
        drawnNumbersFromGrid.add(8);
        drawnNumbersFromGrid.add(9);
        drawnNumbersFromGrid.add(10);
        ArrayList<Integer> drawnNumbersFromGrid2 = new ArrayList<Integer>();

        drawnNumbersFromGrid2.add(1);
        drawnNumbersFromGrid2.add(2);
        drawnNumbersFromGrid2.add(3);
        drawnNumbersFromGrid2.add(4);
        drawnNumbersFromGrid2.add(5);
        drawnNumbersFromGrid2.add(6);
        drawnNumbersFromGrid2.add(7);
        drawnNumbersFromGrid2.add(8);
        drawnNumbersFromGrid2.add(9);
        drawnNumbersFromGrid2.add(10);

        app.selectedSpot[0] = 10;

        int totalNumbersHit = 0; // new variable to store total numbers hit
        int temp = 0;
        for (int a = 0; a < 3; a++) { //multiple draws
            for (int i = 0; i < drawnNumbersFromGrid.size(); i++) {
                for (int j = 0; j < drawnNumbersFromGrid2.size(); j++) {
                    if (drawnNumbersFromGrid.get(i) == drawnNumbersFromGrid2.get(j)) {
                        numbersHit++;
                    }
                }
            }
            totalNumbersHit += numbersHit; // add current numbers hit to total
            temp += app.money_earned(numbersHit);
            numbersHit = 0;
        }
        assertEquals(30, totalNumbersHit); // use totalNumbersHit instead of numbersHit

        assertEquals(300000, temp);
    }

    @Test
    void FourDraws10Spot() {
        int numbersHit = 0;
        ArrayList<Integer> drawnNumbersFromGrid = new ArrayList<Integer>();
        drawnNumbersFromGrid.add(1);
        drawnNumbersFromGrid.add(2);
        drawnNumbersFromGrid.add(3);
        drawnNumbersFromGrid.add(4);
        drawnNumbersFromGrid.add(5);
        drawnNumbersFromGrid.add(6);
        drawnNumbersFromGrid.add(7);
        drawnNumbersFromGrid.add(8);
        drawnNumbersFromGrid.add(9);
        drawnNumbersFromGrid.add(10);
        ArrayList<Integer> drawnNumbersFromGrid2 = new ArrayList<Integer>();

        drawnNumbersFromGrid2.add(1);
        drawnNumbersFromGrid2.add(2);
        drawnNumbersFromGrid2.add(3);
        drawnNumbersFromGrid2.add(4);
        drawnNumbersFromGrid2.add(5);
        drawnNumbersFromGrid2.add(6);
        drawnNumbersFromGrid2.add(7);
        drawnNumbersFromGrid2.add(8);
        drawnNumbersFromGrid2.add(9);
        drawnNumbersFromGrid2.add(10);

        app.selectedSpot[0] = 10;

        int totalNumbersHit = 0; // new variable to store total numbers hit
        int temp = 0;
        for (int a = 0; a < 4; a++) { //multiple draws
            for (int i = 0; i < drawnNumbersFromGrid.size(); i++) {
                for (int j = 0; j < drawnNumbersFromGrid2.size(); j++) {
                    if (drawnNumbersFromGrid.get(i) == drawnNumbersFromGrid2.get(j)) {
                        numbersHit++;
                    }
                }
            }
            totalNumbersHit += numbersHit; // add current numbers hit to total
            temp += app.money_earned(numbersHit);
            numbersHit = 0;
        }
        assertEquals(40, totalNumbersHit); // use totalNumbersHit instead of numbersHit

        assertEquals(400000, temp);
    }

    @Test
    void testDrawingsSize() {
        app.numDrawings[0] = 4;
        assertEquals(4, app.numDrawings[0]);

        app.numDrawings[0] = 2;
        assertEquals(2, app.numDrawings[0]);

        app.numDrawings[0] = 1;
        assertEquals(1, app.numDrawings[0]);

        app.numDrawings[0] = 0;
        assertEquals(0, app.numDrawings[0]);
    }

    @Test
        //test selectedSpot
    void testSelectedSpot() {
        app.selectedSpot[0] = 3;
        assertEquals(3, app.selectedSpot[0]);

        app.selectedSpot[0] = 2;
        assertEquals(2, app.selectedSpot[0]);

        app.selectedSpot[0] = 1;
        assertEquals(1, app.selectedSpot[0]);

        app.selectedSpot[0] = 0;
        assertEquals(0, app.selectedSpot[0]);
    }

    @Test
        //test for numbersHit == 1
    void testNumbersHit1() {
        //when numbers hit is 0
        app.selectedSpot[0] = 10;
        assertEquals(0, app.money_earned(1));

        app.selectedSpot[0] = 8;
        assertEquals(0, app.money_earned(1));

        app.selectedSpot[0] = 4;
        assertEquals(0, app.money_earned(1));

        app.selectedSpot[0] = 2;
        assertEquals(2, app.money_earned(1));

    }

    @Test
        //test for numbersHit == 2
    void testNumbersHit2() {
        //when numbers hit is 0
        app.selectedSpot[0] = 10;
        assertEquals(0, app.money_earned(2));

        app.selectedSpot[0] = 8;
        assertEquals(0, app.money_earned(2));

        app.selectedSpot[0] = 4;
        assertEquals(1, app.money_earned(2));
    }

    @Test
        //test for numbersHit == 3
    void testNumbersHit3() {
        //when numbers hit is 0
        app.selectedSpot[0] = 10;
        assertEquals(0, app.money_earned(3));

        app.selectedSpot[0] = 8;
        assertEquals(0, app.money_earned(3));

        app.selectedSpot[0] = 4;
        assertEquals(5, app.money_earned(3));
    }

    @Test
        //test for numbersHit == 4
    void testNumbersHit4() {
        //when numbers hit is 0
        app.selectedSpot[0] = 10;
        assertEquals(0, app.money_earned(4));

        app.selectedSpot[0] = 8;
        assertEquals(2, app.money_earned(4));

        app.selectedSpot[0] = 4;
        assertEquals(75, app.money_earned(4));
    }
}
