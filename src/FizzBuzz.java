import java.util.concurrent.CountDownLatch;
import java.util.function.IntConsumer;

public class FizzBuzz {
    private int n;

    public FizzBuzz(int n) {
        this.n = n;
    }

    volatile  int tmp=1;
    CountDownLatch fizzbuzz = new CountDownLatch(1);
    CountDownLatch buzz = new CountDownLatch(1);
    CountDownLatch fizz = new CountDownLatch(1);
    CountDownLatch number = new CountDownLatch(1);
    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for(;tmp<=n;){
            fizz.await();
            if(tmp>n){
                break;
            }
            printFizz.run();
            fizz = new CountDownLatch(1);
            fizzbuzz.countDown();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for(;tmp<=n;){
                buzz.await();
            if(tmp>n){
                break;
            }
                printBuzz.run();
                buzz = new CountDownLatch(1);
                fizzbuzz.countDown();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for(;tmp<=n;){
            if(tmp%15==0){
                printFizzBuzz.run();
            }else if(tmp%3==0){
                fizz.countDown();
                fizzbuzz.await();
                fizzbuzz = new CountDownLatch(1);
            }else if(tmp%5==0){
                buzz.countDown();
                fizzbuzz.await();
                fizzbuzz = new CountDownLatch(1);
            }else {
                number.countDown();
                fizzbuzz.await();
                fizzbuzz = new CountDownLatch(1);
            }
            tmp++;
        }
        fizz.countDown();
        buzz.countDown();
        number.countDown();
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for(;tmp<=n;){
            number.await();
            if(tmp>n){
                break;
            }
            printNumber.accept(tmp);
            number = new CountDownLatch(1);
            fizzbuzz.countDown();
        }
    }

    public static  void main(String args[]){
        FizzBuzz fizzBuzzs =new FizzBuzz(15);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                fizzBuzzs.number(new IntConsumer() {
                    @Override
                    public void accept(int value) {
                        System.out.print(value+",");
                    }
                });
                }catch (Exception e){

                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    fizzBuzzs.buzz(new Runnable() {
                        @Override
                        public void run() {
                            System.out.print("buzz,");
                        }
                    });
                }catch (Exception e){

                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    fizzBuzzs.fizz(new Runnable() {
                        @Override
                        public void run() {
                            System.out.print("fizz,");
                        }
                    });
                }catch (Exception e){

                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    fizzBuzzs.fizzbuzz(new Runnable() {
                        @Override
                        public void run() {
                            System.out.print("fizzbuzz,");
                        }
                    });
                }catch (Exception e){

                }
            }
        }).start();
    }
}