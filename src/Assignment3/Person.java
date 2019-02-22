package Assignment3;

import java.util.TimerTask;
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
        //decrementEnergyLevel();  // Check further down
    }

    @Override
    public void run() {
        while(true){
            //theGreateFilter(); // Check further down
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

    //private void theGreateFilter(){                    <------- // Filter for threads, not proper solution but better than nothing
    //        if(energy <= 30 && energy > 0){            <------- // if we cannot find a way to notify threads with low values when
    //            try{                                   <------- // loop is ready to be used. Or throw away if you wish.
    //                Thread.sleep(1000);                <------- //
    //                                                   <------- //
    //            } catch(InterruptedException ex){               // Uncomment theGreateFilter and decrementEnergyLevel and play with
    //                System.out.println(ex);                     // the values of each of them to get a satisfying amount of flow and
                                                                  // and filtering.
    //            }

    //       }

    //        else if (energy <= 60 && energy > 30){
    //            try{
    //                Thread.sleep(2000);
    //
    //            } catch(InterruptedException ex){
    //                System.out.println(ex);
    //            }

    //        }

    //        else if(energy <= 100 && energy > 60){
    //            try{
    //                Thread.sleep(3000);
    //
    //            } catch(InterruptedException ex){
    //                System.out.println(ex);
    //            }

    //        }

    //        }

      // USE THIS TO SPEED UP FOR TESTING 
    //public void decrementEnergyLevel() {
    //    if (energy > 0) {
    //        new java.util.Timer().schedule(new TimerTask() {
    //            @Override
    //            public void run() {
    //                energy -= 10;
    //            }
    //        }, 1000, 1000);
    //    }
    //}


    private void coffeeRoom(){
        while(energy > 0 && energy < 100)
        if (lock.tryLock()) {
            useCoffeeMachine();
            lock.unlock();
        } threadUpdate();
    }

    private void exitRoom(){
        this.quit = null;
        System.out.println(name + "GOING HOME");
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