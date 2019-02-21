package Assignment3;

import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Person implements Runnable, MachineInterface{
    private String name;
    private int energy;
    private static Lock lock = new ReentrantLock();

    public Person(String name){
        this.name = name;
        this.energy = (((int) (Math.random() *  60) + 30));
        decrementEnergyLevel();   // <-- När personen skapas så börjar den dra energy direkt
    }

    @Override
    public void run() {
        while(energy > 0){
            if(lock.tryLock()) {
                useCoffeeMachine();
                lock.unlock();
                try {
                    Thread.sleep(1000);    // <--  ingen bra lösning på " Similarly, you may assume a person spends 1 second drinking a given cup. " 
                    System.out.println(name + " sleep");
                } catch (InterruptedException ex) {
                    System.out.print(ex);
                }
            }
            //if(energy > 100) officeLoop(); // <-- Denna får man fixa
        }
        System.out.println(name + "Tired going home");
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
        if(energy > 0) {
            new java.util.Timer().schedule(new TimerTask() {     // <-- Hela tiden i bakgrunden tills att personen energy är helt slut
                @Override
                public void run() {
                    energy -= 10;
                }
            }, 1000, 1000);
        }
    }

    private void officeLoop(){
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