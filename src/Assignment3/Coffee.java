package Assignment3;
import java.lang.Math;

// Superclass for Coffee which includes an random-number generator and abstract getters.
// We use abstract methods instead of implementing here because the methods will use primitive datatypes -
// which cannot be inherited.

public class Coffee {
    int energyValue = 0;
    String coffeeType = "";

    public int getEnergyValue(){
        return energyValue;
    }

    public String getCoffeeType(){
        return coffeeType;
    }

    public int randomEnergyValue(int min, int max){
        int interval = (max-min) + 1;
        return (int)(Math.random() * interval) + min;
    }
}