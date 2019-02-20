package Assignment3;

public class LatteCoffee extends Coffee {
    String coffeeType = "Latte";
    int energyValue = randomEnergyValue(25, 35);

    @Override
    public int getEnergyValue() {
        return energyValue;
    }

    @Override
    public String getCoffeeType() {
        return coffeeType;
    }
}
