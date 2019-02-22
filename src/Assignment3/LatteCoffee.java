package Assignment3;

public class LatteCoffee extends Coffee {
    private String coffeeType = "Latte";
    private int energyValue = randomEnergyValue(25, 35);

    public int getEnergyValue() {
        return energyValue;
    }

    public String getCoffeeType() {
        return coffeeType;
    }
}
