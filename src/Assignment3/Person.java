package Assignment3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Person implements Runnable{
    private String name;
    private int energy;
    CoffeeStorage coffeeMachine = new CoffeeStorage();
    private static Lock lock = new ReentrantLock();

    public Person(String name){
        this.name = name;
        this.energy = (((int) (Math.random() *  60) + 30));
    }

    private void decrementEnergyLevel(){
        try{
            energy -=10;
            Thread.sleep(1000);
        } catch(InterruptedException ex){
            System.out.println(ex);
        }
    }


    @Override
    public void run() {
        while(energy > 0){
            useCoffeeMachine();
            decrementEnergyLevel();
            if(energy > 100){
                while(energy > 30){
                    decrementEnergyLevel();
                }
            }
        }
        System.out.println("Tired going home");
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

