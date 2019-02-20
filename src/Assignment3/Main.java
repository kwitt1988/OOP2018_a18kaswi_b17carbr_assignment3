package Assignment3;

public class Main {

    public static void main(String[] args) {
        CoffeeStorage Machine = new CoffeeStorage();
        Machine.fillCoffeeStorage(20);
        Machine.getOneCup().getEnergyValue();
        Machine.getOneCup().getCoffeeType();
    }
}
