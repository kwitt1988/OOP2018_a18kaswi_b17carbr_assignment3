package Assignment3;

public class Main {

    public static void main(String[] args) {
        
        // SATT MED SKITEN FÖR LÄNGE. VAR INTE BRA. DE GÅR KÖRA SKITEN ... FAST FÅR MASSA ERRORS, HAR MED VECTOR ATT GÖRA, TROR EJ RIKTIGT SYNKAD,
        // KAN VARA BRA ATT BÖRJA DÄR!


        // Example use of the coffee-machine.
        CoffeeStorage Machine = new CoffeeStorage();
        Machine.fillCoffeeStorage(20);

        ThreadHandler person = new ThreadHandler();
        person.startThreads();
    }
}
