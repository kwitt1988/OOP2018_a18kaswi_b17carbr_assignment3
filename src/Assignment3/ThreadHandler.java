package Assignment3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// ThreadHandler is a class that creates a "thread pool" consisting of five threads.
// Each row instantiate a Person-object with a name and execute the class's run method.

public class ThreadHandler {
    void startThreads(){
        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.execute(new Person("Anders"));
        executor.execute(new Person("Lisa"));
        executor.execute(new Person("Kasper"));
        executor.execute(new Person("Simon"));
        executor.execute(new Person("Jonathan"));
        executor.shutdown();
    }
}
