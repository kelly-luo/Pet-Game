package World;

import java.lang.Thread;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This output class consists of relevant instance variables, it consist of
 * methods such as updateStatus(), askUserInput(), help() and processInput() 
 * methods.
 * 
 * @author AkanshaSen (17994377) and KellyLuo (17985065)
 * @author Group ID: 14
 */
public class Output {

    public boolean notEndOfGame = true;
    public Scanner scan = new Scanner(System.in);
    public static Owner owner;
    
    public boolean input;
    public int seconds;

    public String inputCmd;
    
     /**
     * This is the update status method of the output class, this updates the 
     * status of the pet depending on the inputs done by the owner of the pet,
     * this update happens every (shown below) seconds, for example, every one 
     * second the hungry points of the pet goes up,
     * 
     * finally the method uses the toString method to print out all the
     * updates information about the pet.
     */
    public Output(Owner owner) {
        this.owner = owner;
    }

     /**
     * This is the update status method of the output class, this updates the 
     * status of the pet depending on the inputs done by the owner of the pet,
     * this update happens every (shown below) seconds, for example, every one 
     * second the hungry points of the pet goes up,
     * 
     * finally the method uses the toString method to print out all the
     * updates information about the pet.
     */
    public synchronized void updateStatus() {
        if (this.input) { // true means inputting
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Output.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        while (this.notEndOfGame) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Output.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            this.seconds++;
            if (this.seconds % 5 == 0) { // 5 seconds
                break;
            }
            if (this.seconds % 1 == 0) { // 1 second
                this.owner.pet.setHungryPoints(this.owner.pet.getHungryPoints() + 1);

                if (this.owner.pet.getHungryPoints() > 10) {
                    System.out.println(this.owner.pet.getName() + " is hungry so has increased 1 fatigue point\r\n");
                    this.owner.pet.setFatiguePoints(this.owner.pet.getFatiguePoints() + 1);
                } else if (this.owner.pet.getHungryPoints() <= 40) {
                    System.out.println(owner.pet.getName() + " is happy so has increased 1 happiness point\r\n");
                    this.owner.pet.setHappinessPoints(this.owner.pet.getHappinessPoints() + 1);
                }
            }
            if (this.seconds % 9 == 0) { // lucky or unlucky event
                this.owner.pet.pickEvent();

            }
            if(this.seconds % 9 == 0){ 
                this.owner.pet.setAge(this.owner.pet.getAge() + 1);
                System.out.println(this.owner.pet.getName() + " has increased in age by 1\r\n");
            }
        }
        System.out.println(owner.pet.toString());

        this.input = true;
        notify();
    }

     /**
     * This method will wait if the owner is not inputting anything,
     * if not, then it will run the process input method, and set the input
     * boolean to false, notifies all other threads.
     */
    public synchronized void askUserToInput(){
        if (!this.input) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Output.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Put the method for aksing the user to input.
        this.processInput();
        this.input = false;
        notify();
    }

    public String feedTreat(){
        this.owner.pet.eat(this.owner.foodBox[1]);
        return this.owner.getOwnerName() + " has fed " + this.owner.pet.getName() + "\r\n";
    }
    
    public String feedFood() {
        this.owner.pet.eat(this.owner.foodBox[0]);
        return this.owner.getOwnerName() + " has fed " + this.owner.pet.getName() + "\r\n";
    }
    
    public String pet(){
        return this.owner.petting();
    }
    
    public String sleep(){
        this.owner.pet.sleep();
        return this.owner.pet.getName() + " has slept and now has no fatigue.\r\n";
    }
    
    public String takeWalk(){
        return this.owner.walk();
    }
    
    /**
     * This is the help method that the user can use during the game if they
     * need assistance with how to play and what instructions are there for
     * them (read from LifeOfPetOwner.txt)
     */
    public String help() throws IOException, FileNotFoundException{

        String line = "";
        BufferedReader br = null;
        try {
            StringBuilder builder = new StringBuilder();
            br = new BufferedReader(new FileReader("LifeOfPetOwner.txt")); // Initialize the BufferedReader, which is used to read contents from scores.txt.
            line = br.readLine();
            while (line != null) {
                builder.append(line);
                builder.append("\r\n");
                line = br.readLine();
            }
            return builder.toString();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(Output.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
    
    /**
     * This method processes the player input, in the duration of the game the
     * user can input commands such as 'feed', 'pet' etc to interact with their
     * pet.
     *
     */
    public void processInput(){
        System.out.println("Do a command from: feed treat, feed food, pet, sleep, take walk, help, quit");
        System.out.println(">: ");

        inputCmd = scan.nextLine();

        String[] token = inputCmd.toLowerCase().split(" ");

        switch (token[0]) {

            case "take":
                if(token[1].equals("walk"))
                {
                    this.owner.walk();
                }
                else {
                    System.out.println("Invalid input");
                    this.processInput();
                }
                
                break;
                
            case "feed":
                if (token[1].equals("food")) {
                    System.out.println(this.owner.getOwnerName() + " has fed " + this.owner.pet.getName());
                    this.owner.pet.eat(this.owner.foodBox[0]);
                }
                else if(token[1].equals("treat"))
                {
                    System.out.println(this.owner.getOwnerName() + " has fed " + this.owner.pet.getName());
                    this.owner.pet.eat(this.owner.foodBox[1]);
                }
                break;
            case "pet":
                this.owner.petting();
                break;

            case "sleep":
                this.owner.pet.sleep();
                System.out.println(this.owner.pet.getName() + " has slept and now has no fatigue.");
                break;

            case "help": {
                    try {
                        help();
                    } catch (IOException ex) {
                        Logger.getLogger(Output.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                
            case "quit":
                System.exit(0);
                scan.close();
            default:
                System.out.println("Unknown command of " + this.inputCmd 
                        + ". Please re-enter a proper command: feed treat, "
                                + "feed food, pet, sleep, take walk, help, quit.");
                this.inputCmd = scan.nextLine();
                this.processInput();

        }
    }

    /**
     * A method to check if the game has end by winning or losing
     * @return boolean for if the game has ended
     */
    public boolean checkEndOfGame() {
        boolean gameInProgress = true;
        
        if (this.owner.pet.getFatiguePoints() >= 50) {
                System.out.println("Awwwww noooo.... You lost the game... Your pet got too tired");
                gameInProgress = false;

            } else if (this.owner.pet.getHappinessPoints() >= 20) {
                System.out.println("YOU WON! YOU KEPT YOUR PET HAPPY! YOU ARE AMAZING!! :D");
                gameInProgress = false;
            }
        
        return gameInProgress;
        
    }
}
