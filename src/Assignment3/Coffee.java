package Assignment3;
import java.lang.Math;
import java.lang.Double;

public class Coffee {
    int randomEnergyValue(int min, int max){
        int interval = (max-min) + 1;
        return (int)(Math.random() * interval) + min;
    }
}
