import java.util.concurrent.CountDownLatch;


public class FooBar {
    private int n;

    public FooBar(int n) {
        this.n = n;
    }

    CountDownLatch countDownLatch1= new CountDownLatch(1);
    CountDownLatch countDownLatch2= new CountDownLatch(1);
    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printFoo.run() outputs "foo". Do not change or remove this line.

            printFoo.run();
            countDownLatch2.countDown();
            countDownLatch1=new CountDownLatch(1);
            countDownLatch1.await();
          //  countDownLatch2.await();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {


        for (int i = 0; i < n; i++) {
            // printBar.run() outputs "bar". Do not change or remove this line.
            countDownLatch2.await();
            countDownLatch2 = new CountDownLatch(1);
            printBar.run();
            countDownLatch1.countDown();
        }
    }
}