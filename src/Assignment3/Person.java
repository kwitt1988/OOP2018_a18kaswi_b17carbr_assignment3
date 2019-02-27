package Assignment3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Person is a class which provides the initialization person-object in association with a thread.
// Holds the loop which the threads adhere to.
// Maintain the connect to the source of coffee and controls the access to the coffee.
// General control of each person thread during it lifespan. with sleeping, decrementing and ending threads.

public class Person implements Runnable{
    private boolean active = true;
    private String name;
    private int energy;
    private static Lock lock = new ReentrantLock();
    CoffeeStorage coffeeMachine = CoffeeStorage.getInstance();

    // Constructor initialized with name passed in as an argument during the creation of thread pool, also sets the energy of the person during creation to a random value in the interval between 30 - 60.
    public Person(String name){
        this.name = name;
        this.energy = (((int) (Math.random() *  60) + 30));
    }

    // Run method for the threads
    // Prints out the name and energy of each thread with a instantiated person-object, the name is passed in as an argument to the person constructor at creation of the thread pool.
    // First condition checks whether the energy value of each person thread has gone beyond 100, if so then the thread is sent to the office room to be held and decremented in energy before returning
    // Second condition checks whether the energy value is above 0, if so then sends the thread to the coffeeRom to consume coffee and regain energy
    // Third condition checks whether the energy value is bellow 0, if so then send the thread home, i.e. ends that thread.
    @Override
    public void run() {
        System.out.println(name + " entered the building with " + energy + " energy.");
        Thread.yield();
        while(active){
            Thread.yield();
            if(energy >= 100){
                officeRoom();
            } else if(energy > 0){
                coffeeRoom();
            } else if(energy <= 0){
                exitRoom();
            }
        }
    }

    // Method responsible handling access to the useCoffeeMachine method by locking via the entering of each thread and then releasing the lock once the entering thread has retrieved and consumed the coffee
    private void coffeeRoom(){
        while(energy > 0 && energy < 100)
            if(lock.tryLock()){
                useCoffeeMachine();
                lock.unlock();
            } else {
                decrementEnergy();
            }
    }

    // Method responsible for holding threads that gone beyond 100 in energy.
    // Holds them until their energy is bellow or equal to 30.
    // does this by calling the method decrementEnergy which sleeps the the thread and decrement its energy by -10 each times its called.
    private void officeRoom(){
        while(energy >= 30){
            decrementEnergy();
        }
        System.out.println(name + " is low on energy so goes to get coffee.");
        decrementEnergy();
    }

    //
    private void exitRoom(){
        System.out.println(name + " is out of energy - going home");
        active = false;
    }

    // Method responsible for the retrieval and consumption  of coffee, calls upon the method getOneCup in the CoffeeStorage to retrieve a random cup of coffee
    // Consume the coffee by setting the current energy value += to the retrieved cup of coffee via the method set energy
    // Check whether the threads energy has gone beyond 100, then prints out a message with the value of the recently consumed coffee with type and value ending with the sum total energy
    // and a message that the thread goes to the office. If condition is not met then prints the same line without the office part and the remaining cups of coffee.
    // End by calling method that gives a chance of 20 to fill the coffee machine with an additional 5 cups of coffee and then afterwards sleeps the thread for 1 second.
    private void useCoffeeMachine(){
        while(energy > 0 && energy < 100) {
            Coffee newCup = coffeeMachine.getOneCup();
            setEnergy(newCup);
            if(energy > 100){
                System.out.println(name + " consumes a " + newCup.getCoffeeType() + " with " + newCup.getEnergyValue() + " and now has " + (energy) + " and goes to office");
            } else {
                System.out.println(name + " consumes a " + newCup.getCoffeeType() + " with " + newCup.getEnergyValue() + " and now has " + (energy));
                System.out.println(coffeeMachine.getCupsLeft() + " cups left.");
            }
            coffeeMachine.chanceOnFiveCups();
            threadSleep();
        }

    }

    // Method responsible for setting the energy value += to the retrieved cup of coffee
    private void setEnergy(Coffee newCup){
        energy += newCup.getEnergyValue();
    }

    // Method responsible for decrementing and sleeping person threads
    private void decrementEnergy(){
        energy -= 10;
        try{
            Thread.sleep(1000);

        } catch(InterruptedException ex){
            System.out.println(ex);
        }
    }

    // Method only responsible for sleeping threads
    private void threadSleep(){
        try{
            Thread.sleep(1000);

        } catch(InterruptedException ex){
            System.out.println(ex);
        }
    }
}