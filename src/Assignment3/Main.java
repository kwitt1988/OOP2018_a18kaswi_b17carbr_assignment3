package Assignment3;

public class Main {
    // calls upon the ThreadHandler to create a new thread pool with instantiated person-objects.
    public static void main(String[] args) {
        ThreadHandler person = new ThreadHandler();
        person.startThreads();
    }
}