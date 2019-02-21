package Assignment3;

public class Person implements Runnable{
    String name;
    int energy;
    CoffeeStorage coffeeMachine = new CoffeeStorage();

    public Person(String name){
        coffeeMachine.fillCoffeeStorage(20);
        this.name = name;
        this.energy = (((int) (Math.random() *  30) + 1));
    }

    @Override
    public void run() {
        if (energy > 0 && energy < 100) {
            System.out.println(name + " has " + energy + " energy and goes to coffee room");
        }
        while(energy > 0 && energy < 100) {
                useCoffeeMachine();
        }
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

