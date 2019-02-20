package Assignment3;

public class LatteCoffee extends Coffee {
    private String coffeeType = "Latte";
    private int energyValue = randomEnergyValue(25, 35);

    @Override
    public int getEnergyValue() {
        return energyValue;
    }

    @Override
    public String getCoffeeType() {
        return coffeeType;
    }
}
