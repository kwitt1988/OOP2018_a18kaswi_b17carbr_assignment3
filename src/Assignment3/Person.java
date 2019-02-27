package Assignment3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Person implements Runnable{
    private boolean active = true;
    private String name;
    private int energy;
    private static Lock lock = new ReentrantLock();
    CoffeeStorage coffeeMachine = CoffeeStorage.getInstance();

    public Person(String name){
        this.name = name;
        this.energy = (((int) (Math.random() *  60) + 30));
    }

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

    private void coffeeRoom(){
        while(energy > 0 && energy < 100)
            if(lock.tryLock()){
                useCoffeeMachine();
                lock.unlock();
            } else {
                decrementEnergy();
            }
        }


    private void officeRoom(){
        while(energy >= 30){
            decrementEnergy();
        }
        System.out.println(name + " is low on energy so goes to get coffee.");
        decrementEnergy();
    }

    private void exitRoom(){
        System.out.println(name + " is out of energy - going home");
        active = false;
    }

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

    private void setEnergy(Coffee newCup){
        energy += newCup.getEnergyValue();
    }

    private void decrementEnergy(){
        energy -= 10;
        try{
            Thread.sleep(1000);

        } catch(InterruptedException ex){
            System.out.println(ex);
        }
    }

    private void threadSleep(){
        try{
            Thread.sleep(1000);

        } catch(InterruptedException ex){
            System.out.println(ex);
        }
    }
}