import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class H2O {

    public H2O() {

    }

    private volatile int h = 0;

    CountDownLatch H = new CountDownLatch(1);
    CountDownLatch O = new CountDownLatch(1);
    Semaphore semaphore = new Semaphore(1);
    Semaphore semaphore2 = new Semaphore(1);

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        semaphore.acquire();
        //   System.out.print(h);
        switch (h) {
            case 0:
                releaseHydrogen.run();
                h++;
                break;
            case 1:
                releaseHydrogen.run();
                O.countDown();
                H.await();
                H = new CountDownLatch(1);
                break;
        }

        semaphore.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        semaphore2.acquire();
        O.await();
        releaseOxygen.run();
        O = new CountDownLatch(1);
        h = 0;
        H.countDown();
        semaphore2.release();
    }

    public static void main(String[] args) throws Exception {
        String tmp = "HHHHHHHHHHOHHOHHHHOOHHHOOOOHHOOHOHHHHHOOHOHHHOOOOOOHHHHHHHHH";
     //   tmp = "OOHHHH";
        H2O h2O = new H2O();
        ExecutorService cachedThreadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < tmp.length(); i++) {
            char s = tmp.charAt(i);
            final int j = i;

            if (s == 'H') {

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    h2O.hydrogen(new Runnable() {
                                        @Override
                                        public void run() {
                                            System.out.print("H");

                                            // System.out.println("H"+j);
                                        }
                                    });
                                } catch (Exception e) {

                                }
                            }
                        }).start();
            } else {
               new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            h2O.oxygen(new Runnable() {
                                @Override
                                public void run() {
                                    System.out.print("O");
                                    //  System.out.println("O"+j);
                                }
                            });
                        } catch (Exception e) {

                        }
                    }
                }).start();
            }
        }
    }

}
