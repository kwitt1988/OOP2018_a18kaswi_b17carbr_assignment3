package Assignment3;

import java.util.Vector;

// CoffeeStorage is a class which provides the storing of coffee and methods connected to it.
// We use a Vector because several threads will try to access it. Even though -
// our locks should prevent race-conditions from happening this is an extra layer of protection to prevent -
// the data from being corrupt.

public class CoffeeStorage {
    private Vector<Coffee> coffeeStorage = new Vector<>();

    CoffeeStorage(){
        fillCoffeeStorage(20);
    }

    // Get one random cup of coffee from the storage.   Added * And removes that cup
    public Coffee getOneCup(){
        int random = (int)(Math.random() * coffeeStorage.size());
        Coffee newCoffee = coffeeStorage.get(random);
        coffeeStorage.remove(random);  // <-- Removes
        chanceOnFiveCups(); // <-- Chance to add
        return newCoffee;
    }

    // Fill the coffeeStorage with int random cups of coffee.
    private void fillCoffeeStorage(int cupsToAdd){
        for(int i = cupsToAdd; i > 0; i--){
            addOneRandomCoffee();
        }
    }

    // Chance to add five cups to the coffeeStorage when a cup has been consumed
    private void chanceOnFiveCups(){
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
            coffeeStorage.addElement(new BlackCoffee());
        } else if(random == 2) {
            coffeeStorage.addElement(new LatteCoffee());
        } else coffeeStorage.addElement(new CappucinoCoffee());
    }
}

