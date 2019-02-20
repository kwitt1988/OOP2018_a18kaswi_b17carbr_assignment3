package Assignment3;

import java.util.Vector;

public class CoffeeStorage {
    private Vector<Coffee> coffeeStorage = new Vector(20);

    CoffeeStorage(){
    }

    public void fillCoffeeStorage(int cupsToAdd){
        for(int i = cupsToAdd; i > 0; i--){
            addOneRandomCoffee();
        }
    }

    public Coffee getOneCup(){
        int random = (int)(Math.random() * coffeeStorage.size());
        return coffeeStorage.get(random);
    }

    private void addOneRandomCoffee(){
        int random = (int)(Math.random() * 3) + 1;
        if(random == 1) {
            coffeeStorage.add(new BlackCoffee());
        } else if(random == 2) {
            coffeeStorage.add(new LatteCoffee());
        } else coffeeStorage.add(new CappucinoCoffee());
    }
}

