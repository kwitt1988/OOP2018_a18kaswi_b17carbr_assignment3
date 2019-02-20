package Assignment3;
        import java.lang.Math;

public abstract class Coffee {


    int randomEnergyValue(int min, int max){
        int interval = (max-min) + 1;
        return (int)(Math.random() * interval) + min;
    }

    abstract int getEnergyValue();
    abstract String getCoffeeType();
}