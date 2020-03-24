package Pet;

import Food.Food;
import Food.PetFood;
import Food.Treat;
import java.util.Random;

/**
 * This is the Pet class, it consists of details and functionalities that 
 * are common in pets. It consists of getter and setter methods for 
 * appropriate instance variables as well as methods for basic functionality
 * of a pet, such as sleep, eat food and treat
 *
 * @author AkanshaSen (17994377) and KellyLuo (17985065)
 * @author Group ID: 14
 */
public abstract class Pet{

    private String petName;
    private int petAge; 
    private int happinessPoints; 
    private int hungryPoints; 
    private int fatiguePoints; 

    Random rand = new Random();

    /**
     * Getter method for pet name
     * @return, the name of the pet
     */
    public String getName() {
        return this.petName;
    }

    /**
     * Getter method for pet happiness points
     * @return, the happiness points the pet has
     */
    public int getHappinessPoints() {
        return happinessPoints;
    }

    /**
     * Setter method for the pet happiness points, as the pet is given treats, 
     * pet, taken on a walk will increase the happiness points as it makes the 
     * pet happier
     * @param happiness, takes input of the happiness value 
     */
    public void setHappinessPoints(int happiness) {
        if (happiness <= 100 && happiness >= 0) {
            this.happinessPoints = happiness;
        }
            
    }

    /**
     * Getter method for the fatigue points, this represents how tired
     * the pet is
     * @return, the fatigue points of the pet 
     */
    public int getFatiguePoints() {
        return fatiguePoints;
    }

    /**
     * Setter method for the fatigue points, it ensures the pet can only have
     * fatigue points between 0 and 100
     * @param fatigue, takes input fatigue points 
     */
    public void setFatiguePoints(int fatigue) {
        if (fatigue <= 100 && fatigue >= 0) {
            this.fatiguePoints = fatigue;
        }
    }

    /**
     * Getter method for hungry points
     * @return, returns the hungry points of the pet
     */
    public int getHungryPoints() {
        return hungryPoints;
    }

    /**
     * Setter method for the hungry points, just like the fatigue points, this
     * method also ensures that the fatigue points stays between 0 and 100
     * @param fatigue, takes input hungry points. 
     */
    public void setHungryPoints(int hungry) {
        if (hungry <= 100 && hungry >= 0) {
            this.hungryPoints = hungry;
        }
    }

    /**
     * Getter method for the age of the pet
     * @return, the pet age 
     */
    public int getAge() {
        return this.petAge;
    }

    /**
     * Setter method for the age of the pet
     * @param age, takes input an integer value for the age
     */
    public void setAge(int age) {
        this.petAge = age;
    }

    //Constructor and methods
    
    /**
     * This is the constructor of the Pet class, it initializes the values of 
     * the instance variables to their suitable values.
     * @param petName, takes the name of the pet as its input parameter 
     */
    public Pet(String petName) {
        this.petName = petName; //read from the database file
        this.setHappinessPoints(0);
        this.setFatiguePoints(0);
        this.setHungryPoints(0);
        this.setAge(1);

    }

    /**
     * This is the sleep functionality of the pet, by using this functionality
     * the owner can help their pet reduce fatigue points, in other words, the
     * pet is put to sleep.
     */
    public void sleep() {
        this.setFatiguePoints(0);
    }

    /**
     * This is the eat functionality of the pet, this functionality will be used
     * by the owner when they are required to feed their pet
     * @param food, takes input a food parameter
     */
    public void eat(Food food) {
        
        if(food instanceof PetFood)
        {
            if(this.hungryPoints - food.getHungry() < 0){
                this.setHungryPoints(0);
            }else{
                this.setHungryPoints(this.hungryPoints - food.getHungry());
            }
            
        }
        else if (food instanceof Treat)
        {
            this.setHappinessPoints(this.happinessPoints + food.getHappiness());
        }
        
        System.out.println(this.toString());
    }

    
    /**
     * This toString method is used to output the details of the pet.  
     * @return, string description of the pet status 
     */
    @Override
    public String toString() {
        return "[Name: " + this.getName() + " Age: " + this.getAge() + " Happiness: " 
                + this.getHappinessPoints() + " Hungry: " + this.getHungryPoints() 
                + " Fatigue: " + this.getFatiguePoints() + "]";
    }

    abstract public String unLuckyEvents();

    abstract public String luckyEvents();
    
    abstract public String pickEvent();
}
/**
 * We override equals() aiming to compare the instance of Question with other
 * instances. Only if they are both instances of Question, and they have same
 * attributes (i.e., num1, num2 and operation), then we can regard them are the
 * same question.
 *
 * @param object
 * @return True or False
 */
//    @Override
//    public boolean equals(Object object) {
//        return object != null 
//                && object instanceof Pet
//                && ((Pet) object).num1 == this.num1
//                && ((Pet) object).num2 == this.num2
//                && ((Pet) object).operation.equals(this.operation);
//    }
//    
//    /**
//     * Basically, we also need to override hashCode() method once we override equals(),
//     * to avoid the conflict of hashcode happened in hash-based collections, e.g., HashMap, HashSet and HashTable.
//     * 
//     * @return hashCode
//     */
//    @Override
//    public int hashCode() {
//        this.petID = (this.num1 + this.operation + this.num2).hashCode();
//        return petID;
//    }
