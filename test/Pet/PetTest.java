package Pet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Akansha Sen
 */
public class PetTest {
    Pet pet;
    public PetTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        pet = new Dog("Maya");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setFatiguePoints method, of class Pet.
     */
    @Test
    public void testSetFatiguePointsAtZero() {
        System.out.println("Set Fatigue point at 0");
        int fatigue = 0;
        //Pet instance = null;
        int expectedResult = 0;
        pet.setFatiguePoints(fatigue);
        int result = pet.getFatiguePoints();

        assertEquals(expectedResult, result, 0.0);
        System.out.println("Expected Result: "+ expectedResult);
        System.out.println("Result:"+ result);
                
    }
    
    
    @Test
    public void testSetFatiguePointsLessThanZero() {
        System.out.println("Set Fatigue point less than zero");
        int fatigue = -50;
        //Pet instance = null;
        int expectedResult = 0;
        pet.setFatiguePoints(fatigue);
        int result = pet.getFatiguePoints();

        assertEquals(expectedResult, result, 0.0);
        System.out.println("Expected Result: " + expectedResult);
        System.out.println("Result:" + result);
    }
    
    @Test
    public void testSetFatiguePointsMoreThanHundred() {
        System.out.println("Set Fatigue point more than hundred");
        int fatigue = 150;
        //Pet instance = null;
        int expectedResult = 0;
        pet.setFatiguePoints(fatigue);
        int result = pet.getFatiguePoints();

        assertEquals(expectedResult, result, 0.0);
        System.out.println("Expected Result: " + expectedResult);
        System.out.println("Result:" + result);
    }
    
    @Test
    public void testSetFatiguePointsInRange() {
        System.out.println("Set Fatigue in range between 0 and 100");
        int fatigue = 28;
        //Pet instance = null;
        int expectedResult = 28;
        pet.setFatiguePoints(fatigue);
        int result = pet.getFatiguePoints();

        assertEquals(expectedResult, result, 0.0);
        System.out.println("Expected Result: " + expectedResult);
        System.out.println("Result:" + result);
    }
    
    /**
     * Test of setFatiguePoints method, of class Pet.
     */
    @Test
    public void testSetFatiguePointsAtHundred() {
        System.out.println("Set Fatigue at 100");
        int fatigue = 100;
        //Pet instance = null;
        int expectedResult = 100;
        pet.setFatiguePoints(fatigue);
        int result = pet.getFatiguePoints();

        assertEquals(expectedResult, result, 0.0);
        System.out.println("Expected Result: " + expectedResult);
        System.out.println("Result:" + result);
    }
    
}
