package Assignment3;

public class CappucinoCoffee extends Coffee {
    private String coffeeType = "Cappucino";
    private int energyValue = randomEnergyValue(20, 30);

    @Override
    public int getEnergyValue() {
        return energyValue;
    }

    @Override
    public String getCoffeeType() {
        return coffeeType;
    }
}
