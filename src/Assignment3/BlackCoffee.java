package Assignment3;

public class BlackCoffee extends Coffee {
    private String coffeeType = "Black Coffee";
    private int energyValue = randomEnergyValue(20, 40);

    public int getEnergyValue() {
        return energyValue;
    }

    public String getCoffeeType() {
        return coffeeType;
    }
}
