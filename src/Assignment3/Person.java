package Assignment3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Person implements Runnable{
    private volatile Thread quit;
    private String name;
    private int energy;
    private static Lock lock = new ReentrantLock();
    static CoffeeStorage coffeeMachine = new CoffeeStorage();

    public Person(String name){
        this.name = name;
        this.energy = (((int) (Math.random() *  60) + 30));
    }

    @Override
    public void run() {
        while(true){
            if(energy > 100){
                officeRoom();
            } else if(energy > 0){
                coffeeRoom();
            } else if(energy <= 0){
                exitRoom();
            }
        }
    }

    private void officeRoom(){
        while(energy >= 30){
            threadUpdate();
        }
        System.out.println(name + " is low on energy so goes to get coffee.");
    }

    private void coffeeRoom(){
        while(energy > 0 && energy < 100)
        if (lock.tryLock()) {
            useCoffeeMachine();
            lock.unlock();
        } threadUpdate();
    }

    private void exitRoom(){
        quit = null;
    }

    private synchronized void useCoffeeMachine(){
        while(energy > 0 && energy < 100)
            setEnergy(coffeeMachine.getOneCup());
            threadUpdate();
    }

    private synchronized void setEnergy(Coffee newCup){
        energy += newCup.getEnergyValue();
        if(energy > 100){
            System.out.println(name + " consumes a " + newCup.getCoffeeType() + " with " + newCup.getEnergyValue() + " and now has " + (energy) + " and goes to office");
        } else System.out.println(name + " consumes a " + newCup.getCoffeeType() + " with " + newCup.getEnergyValue() + " and now has " + (energy));
        System.out.println(coffeeMachine.getCupsLeft() + " cups left.");
    }

    private void threadUpdate(){
        energy -= 10;
        try{
            Thread.sleep(1000);

        } catch(InterruptedException ex){
            System.out.println(ex);
        }
    }
}