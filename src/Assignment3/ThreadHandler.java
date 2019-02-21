package Assignment3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
