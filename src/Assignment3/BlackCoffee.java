package Assignment3;

public class BlackCoffee extends Coffee {
    String coffeeType = "Black Coffee";
    int energyValue = randomEnergyValue(20, 40);

    @Override
    public int getEnergyValue() {
        return energyValue;
    }

    @Override
    public String getCoffeeType() {
        return coffeeType;
    }
}
