package Assignment3;

import java.sql.SQLOutput;
import java.util.ArrayList;

// CoffeeStorage is a class which provides the storing of coffee and methods connected to it.
// We use a Vector because several threads will try to access it. Even though -
// our locks should prevent race-conditions from happening this is an extra layer of protection to prevent -
// the data from being corrupt.

public class CoffeeStorage {
    private ArrayList<Coffee> coffeeStorage = new ArrayList<>();

    private static CoffeeStorage obj;

    private CoffeeStorage(){
        fillCoffeeStorage(20);
    }

    public static synchronized CoffeeStorage getInstance(){
        if (obj==null)
            obj = new CoffeeStorage();
        return obj;
    }

    // Get one random cup of coffee from the storage.   Added * And removes that cup
    public synchronized Coffee getOneCup(){
        if(coffeeStorage.isEmpty()){
            System.out.println("All coffee is gone, game over.");
            System.exit(0);
        }
        int random = (int)(Math.random() * coffeeStorage.size());
        Coffee newCoffee = coffeeStorage.get(random);
        coffeeStorage.remove(random);
        return newCoffee;
    }

    // Fill the coffeeStorage with int random cups of coffee.
    private synchronized void fillCoffeeStorage(int cupsToAdd){
        System.out.println("------ " + cupsToAdd + " CUPS SPAWNED ------");
        for(int i = cupsToAdd; i > 0; i--){
            addOneRandomCoffee();
        }
        System.out.println("--------------------------------------------");
    }

    // Method used to check cups left.
    public int getCupsLeft(){
        return coffeeStorage.size();
    }

    // Chance to add five cups to the coffeeStorage when a cup has been consumed
    public void chanceOnFiveCups(){

        int random = (int)(Math.random() * 5) + 1;
        if(random == 1){
            fillCoffeeStorage(5);
        }
    }

    // Add one random cup of coffee to the storage.
    // We get a random number from 1-3. Based on that number one beverage will be added to the storage.
    private void addOneRandomCoffee(){
        int random = (int)(Math.random() * 3) + 1;
        if(random == 1) {
            Coffee newBlack = new BlackCoffee();
            coffeeStorage.add(newBlack);
            System.out.println("- " + newBlack.getCoffeeType() + " created with " + newBlack.getEnergyValue() + " energy.");
        } else if(random == 2) {
            Coffee newLatte = new LatteCoffee();
            coffeeStorage.add(newLatte);
            System.out.println("- " + newLatte.getCoffeeType() + " created with " + newLatte.getEnergyValue() + " energy.");
        } else if(random == 3) {
            Coffee newCappucino = new CappucinoCoffee();
            coffeeStorage.add(newCappucino);
            System.out.println("- " + newCappucino.getCoffeeType() + " created with " + newCappucino.getEnergyValue() + " energy.");
        }
    }
}