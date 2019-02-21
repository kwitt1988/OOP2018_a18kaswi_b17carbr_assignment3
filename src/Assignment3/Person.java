package Assignment3;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class Person implements Runnable {
    String name;
    int energy;
    Timer timer = new Timer();

    CoffeeStorage coffeeMachine = new CoffeeStorage();

    public Person(String name) {
        coffeeMachine.fillCoffeeStorage(20);
        this.name = name;
        this.energy = (((int) (Math.random() * 90) + 30));
    }

    @Override
    public void run() {
        if (energy > 0 && energy < 100) {
            System.out.println(name + " has " + energy + " energy and goes to coffee room");
        }
        while (energy > 0 && energy < 100) {
            useCoffeeMachine();
            timer.scheduleAtFixedRate(energyDrained, 1000, 1000);

        }
    }

    public void EnergyDrainedTimer() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(call, 1000, 1000);
    synchronized TimerTask call = new TimerTask() {
        @Override
        public void run() {
            energy++;

        }
    };
}


    public synchronized void useCoffeeMachine(){
        setEnergy(coffeeMachine.getOneCup());
    }

    public void setEnergy(Coffee newCup){
        energy += newCup.getEnergyValue();
        if(energy > 0 && energy < 100){
            System.out.println(name + " consumes a " + newCup.getCoffeeType() + " with " + newCup.getEnergyValue() + " and now has " + (energy));
        } else if(energy >= 100){
            System.out.println(name + " consumes a " + newCup.getCoffeeType() + " with " + newCup.getEnergyValue() + " and now has " + (energy) + " and goes to office");
        }
    }
}

