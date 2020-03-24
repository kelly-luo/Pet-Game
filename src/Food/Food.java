package Food;

/**
 * This is an abstract Food class, it consists of happy and hungry points, as 
 * well as a constructor that initializes the values of the instance variables
 * to zero (to initialize them at the beginning of the game), the class also
 * consists of getter method for these variables.
 * 
 * @author AkanshaSen (17994377) and KellyLuo (17985065)
 * @author Group ID: 14
 */
public abstract class Food {
    //instance variables of the class
    public int happinessPoints;
    public int hungryPoints;
    
    /**
     * The constructor class to initialize the instance variables.
     * 
     */
    public Food(){
        this.happinessPoints = 0;
        this.hungryPoints = 0;
    }
    
     /**
     * Getter method for the happiness
     *
     * @return, the happiness points
     */
    public int getHappiness(){
        return this.happinessPoints;
    }
    
     /**
     * Getter method for the hungry
     *
     * @return, the hungry points
     */
    public int getHungry(){
        return this.hungryPoints;
    }
    
}
