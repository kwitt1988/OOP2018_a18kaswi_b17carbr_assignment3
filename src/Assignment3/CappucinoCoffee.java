package Assignment3;

public class CappucinoCoffee extends Coffee {
    private String coffeeType = "Cappucino";
    private int energyValue = randomEnergyValue(20, 30);

    public int getEnergyValue() {
        return energyValue;
    }

    public String getCoffeeType() {
        return coffeeType;
    }
}
