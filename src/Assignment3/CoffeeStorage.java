package Assignment3;

import java.util.Vector;

public class CoffeeStorage {
    private Vector<Coffee> coffeeStorage = new Vector();

    CoffeeStorage(){
    }

    // Fill the coffeeStorage with int random cups of coffee.
    public void fillCoffeeStorage(int cupsToAdd){
        for(int i = cupsToAdd; i > 0; i--){
            addOneRandomCoffee();
        }
    }

    // Get one random cup of coffee from the storage.
    public Coffee getOneCup(){
        int random = (int)(Math.random() * coffeeStorage.size());
        return coffeeStorage.get(random);
    }

    // Add one random cup of coffee to the storage.
    private void addOneRandomCoffee(){
        int random = (int)(Math.random() * 3) + 1;
        if(random == 1) {
            coffeeStorage.add(new BlackCoffee());
        } else if(random == 2) {
            coffeeStorage.add(new LatteCoffee());
        } else coffeeStorage.add(new CappucinoCoffee());
    }
}

