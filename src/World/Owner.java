package World;


import Food.Food;
import Food.PetFood;
import Food.Treat;
import Pet.Pet;

/**
 * This is the Owner class, it consists of fields, methods and
 * getters and setters to hold appropriate details of the user.
 * 
 * The class has appropriate methods of functionalities that can be conducted
 * by the Owner, such as petting and taking pet on a walk. As well as this,
 * the class consists of a constructor that initializes the values of its
 * instance variables.
 * 
 * @author AkanshaSen (17994377) and KellyLuo (17985065)
 * @author Group ID: 14
 */
public class Owner
{
    public Pet pet;
    public Food[] foodBox = new Food[]{};
    private String ownerName;
    private String gender;


    /**
     * @return the ownerName
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * @param ownerName the ownerName to set
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    /**
     * 
     * @param petName, sets the pet to the owner
     */
    public void setPet(Pet pet)
    {
        this.pet = pet;
    }
    
     /**
     * This is the constructor of the class that initializes its appropriate
     * instance variables.
     * 
     * @param ownerName, takes input of player (owner) name 
     * @param gender, takes input of the player's gender
     */
    public Owner(String ownerName, String gender)
    {
        this.foodBox = new Food[]{new PetFood(), new Treat()};
        this.ownerName = ownerName;
        this.gender = gender;
    }
    
     /**
     * Petting increases the pets happiness points by 5, player gets informed
     * about the update.
     *
     */
    public String petting() 
    {
        String str = "";
        str += this.ownerName + " has petted " + this.pet.getName() + "\r\n";
        str += this.pet.getName() + " has gained 5 happiness points\r\n";
        pet.setHappinessPoints(pet.getHappinessPoints() + 5);
        
        return str;
    }
    
     /**
     * Taking the pet for a walk makes the pet happy hence the happiness points
     * rise, however, it also in turns makes the pet tired therefore, the
     * fatigue also rises.
     *
     */
    public String walk() 
    {
        String str = "";
        str += this.ownerName + " is talking " + this.pet.getName() + " on a walk\r\n";
        str += this.pet.getName() + " has gained 5 happiness points\r\n";

        //fatigue points increase as the walk makes the pet tired but pet gets happier
        pet.setHappinessPoints(pet.getHappinessPoints() + 5);
        pet.setFatiguePoints(pet.getFatiguePoints() + 5);
        
        return str;
    }    
}
