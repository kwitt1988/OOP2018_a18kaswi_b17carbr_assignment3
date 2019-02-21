package Assignment3;

import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class CoffeeStorage {
    private Vector<Coffee> coffeeStorage = new Vector<>();

    CoffeeStorage(){
        fillCoffeeStorage(20);
    }

    // Fill the coffeeStorage with int random cups of coffee.
    public  void fillCoffeeStorage(int cupsToAdd){
        for(int i = cupsToAdd; i > 0; i--){
            addOneRandomCoffee();
        }
    }

    // Get one random cup of coffee from the storage.   Added * And removes that cup
    public Coffee getOneCup(){
        int random = (int)(Math.random() * coffeeStorage.size());
        Coffee newCoffee = coffeeStorage.get(random);
        coffeeStorage.remove(random);  // <-- Removes
        chanceOnFiveCups(); // <-- Chance to add
        return newCoffee;
    }

     // Chance to add five cups to the coffeeStorage when a cup has been consumed
     public void chanceOnFiveCups(){
        int rand = ThreadLocalRandom.current().nextInt(1,5);
        if(rand != 1){
            fillCoffeeStorage(5);
        }
    }

    // Add one random cup of coffee to the storage.
    private void addOneRandomCoffee(){
        int random = (int)(Math.random() * 3) + 1;
        if(random == 1) {
            coffeeStorage.addElement(new BlackCoffee());
        } else if(random == 2) {
            coffeeStorage.addElement(new LatteCoffee());
        } else coffeeStorage.addElement(new CappucinoCoffee());
    }
}

