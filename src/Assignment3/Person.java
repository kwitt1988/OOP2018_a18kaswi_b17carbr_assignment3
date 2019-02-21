package Assignment3;

import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Person implements Runnable{
    private String name;
    private int energy;
    private static Lock lock = new ReentrantLock();
    static CoffeeStorage coffeeMachine = new CoffeeStorage();

    public Person(String name){
        this.name = name;
        this.energy = (((int) (Math.random() *  60) + 30));
        threadSleep();
    }

    @Override
    public void run() {
        while(energy > 0) {
            if (lock.tryLock()) {
                useCoffeeMachine();
                lock.unlock();
            }
            threadSleep();
        }
    }


    private void useCoffeeMachine(){
        while(energy > 0 && energy < 100)
            setEnergy(coffeeMachine.getOneCup());
    }

    private void setEnergy(Coffee newCup){
        energy += newCup.getEnergyValue();
        if(energy > 100){
            System.out.println(name + " consumes a " + newCup.getCoffeeType() + " with " + newCup.getEnergyValue() + " and now has " + (energy) + " and goes to office");
        } else System.out.println(name + " consumes a " + newCup.getCoffeeType() + " with " + newCup.getEnergyValue() + " and now has " + (energy));
        System.out.println(coffeeMachine.getCupsLeft() + " cups left.");
    }

    private void threadSleep(){
        energy -= 10;
        try{
            Thread.sleep(1000);

        } catch(InterruptedException ex){
            System.out.println(ex);
        }
    }
}