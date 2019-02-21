package Assignment3;

public class Main {

    public static void main(String[] args) {

        // Example use of the coffee-machine.
        CoffeeStorage Machine = new CoffeeStorage();
        Machine.fillCoffeeStorage(20);
        Machine.getOneCup().getEnergyValue();
        Machine.getOneCup().getCoffeeType();

        //ThreadHandler person = new ThreadHandler();
        //person.startThreads();
    }
}
