package Assignment3;

import java.util.Vector;

public class CoffeeStorage {
    Vector<Coffee> coffeeStorage = new Vector(20);

    CoffeeStorage(){
        fillCoffeeStorage();
    }

    void fillCoffeeStorage(){
        coffeeStorage.add(new BlackCoffee());
        coffeeStorage.add(new LatteCoffee());
        System.out.println(coffeeStorage.get(0).getEnergyValue());
        System.out.println(coffeeStorage.get(1).getCoffeeType());
    }
}
