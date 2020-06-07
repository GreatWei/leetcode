import java.util.concurrent.CountDownLatch;
import java.util.function.IntConsumer;

public class ZeroEvenOdd {
    private int n;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    private CountDownLatch countzero = new CountDownLatch(1);
    private CountDownLatch counteven = new CountDownLatch(1);
    private CountDownLatch countodd = new CountDownLatch(1);


    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i=1;i<=n;i++){
            printNumber.accept(0);
            if(i%2==0){
                counteven.countDown();
            }else {
                countodd.countDown();
            }
            countzero = new CountDownLatch(1);
            countzero.await();
        }
    }

        public void even(IntConsumer printNumber) throws InterruptedException {
            for(int i=2;i<=n;i=i+2){
                counteven.await();
                printNumber.accept(i);
                counteven = new CountDownLatch(1);
               countzero.countDown();
            }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int j=1;j<=n;j=j+2){
            countodd.await();
            printNumber.accept(j);
            countodd =  new CountDownLatch(1);
            countzero.countDown();
        }
    }

    public  static void  main(String[] args) throws Exception{
        IntConsumer intConsumer = new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.println(value);
            }
        };

      //  intConsumer.accept(1);

        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(6);
       new Thread(new Runnable() {
           @Override
           public void run() {
               try {

                   zeroEvenOdd.zero(new IntConsumer() {
                       @Override
                       public void accept(int value) {
                           System.out.println(value);
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

                    zeroEvenOdd.even(new IntConsumer() {
                        @Override
                        public void accept(int value) {
                            System.out.println(value);
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

                    zeroEvenOdd.odd(new IntConsumer() {
                        @Override
                        public void accept(int value) {
                            System.out.println(value);
                        }
                    });
                }catch (Exception e){

                }
            }
        }).start();
    }
}