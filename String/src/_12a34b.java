import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ab 12a34b 打印方法
 */
public class _12a34b {

    private static final AtomicInteger a = new AtomicInteger(1);

    private static final AtomicInteger b = new AtomicInteger(1);

    private static final AtomicInteger add = new AtomicInteger(0);

    private static final ReentrantLock lock = new ReentrantLock();

    private static final Condition ca = lock.newCondition();

    private static final Condition cb = lock.newCondition();

    private static boolean flag = true;

    private static final String[] ss = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    private static void a() {
        lock.lock();
        try {
            while (a.get() <= 26) {
                if (!flag) {
                    ca.await();
                }
                System.out.println(add.incrementAndGet());
                System.out.println(add.incrementAndGet());
                a.incrementAndGet();
                cb.signal();
                flag = false;
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
            while (b.get() <= 26) {
                if (flag) {
                    cb.await();
                }
                System.out.println(ss[b.get() - 1]);
                b.incrementAndGet();
                ca.signal();
                flag = true;
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
