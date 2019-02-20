package Assignment3;

import java.util.Vector;

public class CoffeeStorage {
    Vector<Coffee> coffeeStorage = new Vector(20);

    CoffeeStorage(){
        fillCoffeeStorage();
    }

    void fillCoffeeStorage(){
        for(int i = 0; i < 20; i++){
            addOneRandomCoffee();
            System.out.println(coffeeStorage.get(i).getCoffeeType());
        }
    }

    void addOneRandomCoffee(){
        int random = (int)(Math.random() * 3) + 1;
        if(random == 1) {
            coffeeStorage.add(new BlackCoffee());
        } else if(random == 2) {
            coffeeStorage.add(new LatteCoffee());
        } else coffeeStorage.add(new CappucinoCoffee());

    }
}

