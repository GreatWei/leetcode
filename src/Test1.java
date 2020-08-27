import java.util.concurrent.atomic.AtomicInteger;

public class Test1 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger =new  AtomicInteger(1);
//        atomicInteger.set(1);
//        atomicInteger.addAndGet(1);
        System.out.println(atomicInteger.incrementAndGet());
        System.out.println(atomicInteger.get());
    }
}
