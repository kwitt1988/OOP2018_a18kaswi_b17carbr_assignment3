package Assignment3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Person implements Runnable, MachineInterface{
    private String name;
    private int energy;
    private static Lock lock = new ReentrantLock();

    public Person(String name){
        this.name = name;
        this.energy = (((int) (Math.random() *  60) + 30));
    }

    @Override
    public void run() {
        while(energy > 0){
            if(lock.tryLock()){
                useCoffeeMachine();
                lock.unlock();}
            else {
                decrementEnergyLevel();
            }
            if(energy > 100) officeLoop();
        }
        System.out.println(name + " is out of energy. Going home.");
    }

    private synchronized void useCoffeeMachine(){
        while(energy > 0 && energy < 100)
            setEnergy(coffeeMachine.getOneCup());
    }

    private synchronized void setEnergy(Coffee newCup){
        energy += newCup.getEnergyValue();
        if(energy > 100){
            System.out.println(name + " consumes a " + newCup.getCoffeeType() + " with " + newCup.getEnergyValue() + " and now has " + (energy) + " and goes to office");
        } else System.out.println(name + " consumes a " + newCup.getCoffeeType() + " with " + newCup.getEnergyValue() + " and now has " + (energy));
        threadSleep();
    }

    private void decrementEnergyLevel(){
        this.energy -=10;
        threadSleep();
    }

    private void officeLoop(){
        while(energy > 30){
            decrementEnergyLevel();
        }
        System.out.println(name + " is low on coffee, going back to the coffee-room");
    }

    private void threadSleep(){
        try{
            Thread.sleep(1000);
        } catch(InterruptedException ex){
            System.out.println(ex);
        }
    }
}