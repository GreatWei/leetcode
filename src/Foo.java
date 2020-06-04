import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Foo {

    public Foo() {

    }

    private  AtomicInteger i = new AtomicInteger(0);

    CountDownLatch countDownLatch1= new CountDownLatch(1);
    CountDownLatch countDownLatch2= new CountDownLatch(1);


    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        countDownLatch1.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
                countDownLatch1.await();
                printSecond.run();
                countDownLatch2.countDown();

    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
                countDownLatch2.await();
                printThird.run();
    }
}
