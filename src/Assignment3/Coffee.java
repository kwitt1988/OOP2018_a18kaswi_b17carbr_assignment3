package Assignment3;
import java.lang.Math;

// Superclass for the three coffee-classes.
// Implements an random-generator which generates energyValue of the coffee.

public class Coffee {
    int randomEnergyValue(int min, int max){
        int interval = (max-min) + 1;
        return (int)(Math.random() * interval) + min;
    }
}
