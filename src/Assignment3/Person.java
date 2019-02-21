package Assignment3;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Person implements Runnable{
    private String name;
    private int energy;
    CoffeeStorage coffeeMachine = new CoffeeStorage();
    private static Lock lock = new ReentrantLock();

    public Person(String name){
        this.name = name;
        this.energy = (((int) (Math.random() *  30) + 1));
    }

    private void decrementEnergyLevel(){
        if (energy > 0){
            try{
                energy = energy - 10;
                Thread.sleep(1000);
            } catch(InterruptedException ex){
                System.out.println(ex);
            }
        }
        else if(energy <= 0){
            System.out.println("Tired - going home");
        }
    }

    @Override
    public void run() {
        if (energy > 0 && energy < 100) {
            System.out.println(name + " has " + energy + " energy and goes to coffee room");
            decrementEnergyLevel();
        }
        do {useCoffeeMachine();
        }
        while(energy > 0 && energy < 100);
        if (energy <= 0){
            System.out.println("Tired going home");
        }
    }

    private void useCoffeeMachine(){
    lock.lock();
        while(energy > 0 && energy < 100)
            setEnergy(coffeeMachine.getOneCup());

    lock.unlock();
    }

    private  void setEnergy(Coffee newCup){
        energy += newCup.getEnergyValue();
        if(energy > 0 && energy < 100){
            System.out.println(name + " consumes a " + newCup.getCoffeeType() + " with " + newCup.getEnergyValue() + " and now has " + (energy));
        } else if(energy >= 100){
            System.out.println(name + " consumes a " + newCup.getCoffeeType() + " with " + newCup.getEnergyValue() + " and now has " + (energy) + " and goes to office");
        } else System.out.println("Goes home");
        try{
            Thread.sleep(1000);
        } catch(InterruptedException ex){
            System.out.println(ex);
        }
    }
}

