package Assignment3;

public class Person implements Runnable{
    String name;
    int energy;
    CoffeeStorage coffeeMachine = new CoffeeStorage();

    public Person(String name){
        this.name = name;
        this.energy = (((int) (Math.random() * (90 - 30))) + 30);
    }

    @Override
    public void run() {
        while (energy > 0 && energy < 100 ) {
            if (energy <= 30){
                System.out.println(name + "has " + energy + " and goes to coffee-room..." );
                Coffee newCup = coffeeMachine.getOneCup();
                setEnergy(newCup);
                // thread.sleep(1000)
            }
        }
        if(this.energy <= 0){
            // GO HOME
            // System.out.println(name + "has " + energy + " and goes to coffee-room...");
        }
        else if(this.energy >= 100){
            // TO THE OFFICE
            // System.out.println(name + " " + "Goes to the Office");
        }

    }
    public void setEnergy(Coffee newCup){  // Kommer vi nog behöva ändra att den tar emot ett objekt av typen KAFFEKOPP.
        System.out.println(name + " consumes a " + newCup.getCoffeeType() + " with " + newCup.getEnergyValue() + " and now has " + (energy += newCup.getEnergyValue()));
        energy += newCup.getEnergyValue();
    }
}

