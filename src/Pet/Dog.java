package Pet;

/**
 * This is the subclass of the Super class Pet, it has all the methods that 
 * the Super class Pet does, however, in addition to the Pet class, it consists
 * of two methods of its own, these are the unLucky and the Lucky events, as
 * well as a method that chooses these events at random.
 * 
 * @author AkanshaSen (17994377) and KellyLuo (17985065)
 * @author Group ID: 14
 */
public class Dog extends Pet{

     /**
     * Constructor of the dog class
     * @param petName, takes the name of the pet as the input parameter
     */
    public Dog(String petName) {
        super(petName);

    }

     /**
     * This is the method that is responsible for handling the unlucky events
     * that, at random, occurs, it will let the owner know if their pet has
     * lost a toy while playing outside or if it just comes back without losing
     * toys, if they loose a toy, this method will decrease the happy points
     * of the pet.
     * 
     */
    @Override
    public String unLuckyEvents() {
        String str = super.getName() + " was playing outside...\r\n";

        int chances = rand.nextInt(2) + 1;

        switch (chances) {
            case 1:
                str += "Unfortunately " + super.getName() + " lost their favourite pet toy\r\n";
                str += super.getName() + "'s happiness points has decreased by 10.\r\n";
                super.setHappinessPoints(super.getHappinessPoints() - 10);
                break;
            case 2:
                str += "and came back inside after a long run.\r\n";
                break;
        }
        return str;

    }

     /**
     * This is the method responsible for handling the unlucky events that,
     * also at random, occurs, it will let the owner know if their pet found
     * a new toy while playing outside, this in turn increases the happy points
     * of the pet, else otherwise, no changes made to the happy points.
     * 
     */
    @Override
    public String luckyEvents() {

        String str = super.getName() + " was playing outside...";

        int chances = rand.nextInt(2) + 1;

        switch (chances) {
            case 1:
                str += super.getName() + "found a new toy and is very excited.\r\n";
                str += super.getName() + "'s happiness points has increased by 10.";
                super.setHappinessPoints(super.getHappinessPoints() + 10);
                break;
            case 2:
                str += "and came back inside after a long run.\r\n";
                break;
        }
        
        return str;

    }

     /**
     * This is the method that chooses at random which of the two lucky or 
     * unlucky even to be chosen.
     * 
     */
    @Override
    public String pickEvent() {
        int event = rand.nextInt(2) + 1;
        String str = "";
        switch(event){
            case 1: 
                str = this.luckyEvents();
                break;
                
            case 2:
                str = this.unLuckyEvents();
                break;
        }
        return str;
    }
}
