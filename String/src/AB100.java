import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程轮流打到100
 */
public class AB100 {

    private static final AtomicInteger res = new AtomicInteger(0);

    private static final ReentrantLock lock = new ReentrantLock();

    private static final Condition ca = lock.newCondition();

    private static final Condition cb = lock.newCondition();

    private static void a() {
        lock.lock();
        try {
            while (res.get() % 2 == 1 && res.get() < 100) {
                System.out.println("A " + res.incrementAndGet());
                cb.signal();
                ca.await();
            }
            cb.signal();
        } catch (Exception e) {
            // ign
        } finally {
            lock.unlock();
        }
    }

    private static void b() {
        lock.lock();
        try {
            while (res.get() % 2 == 0 && res.get() < 100) {
                System.out.println("B " + res.incrementAndGet());
                ca.signal();
                cb.await();
            }
            ca.signal();
        } catch (Exception e) {
            // ign
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> b()).start();
        new Thread(() -> a()).start();
    }
}