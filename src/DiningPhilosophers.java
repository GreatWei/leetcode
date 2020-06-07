import java.util.concurrent.Semaphore;

public class DiningPhilosophers {

    public DiningPhilosophers() {

    }
    Semaphore semaphoreForkLeft = new Semaphore(3);
    Semaphore semaphoreForkRight = new Semaphore(2);

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {

        semaphoreForkLeft.acquire();
        pickLeftFork.run();
        semaphoreForkRight.acquire();
        pickRightFork.run();
        eat.run();
        semaphoreForkLeft.release();
        putLeftFork.run();
        semaphoreForkRight.release();
        putRightFork.run();
    }

    public static void main(String args[]){

    }
}