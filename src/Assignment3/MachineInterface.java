package Assignment3;

// A very simple interface for holding a shared coffeeMachine for all threads.

public interface MachineInterface {
    CoffeeStorage coffeeMachine = new CoffeeStorage();
}
