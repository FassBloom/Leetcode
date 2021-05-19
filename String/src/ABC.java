import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 循环打印ABC
 */
public class ABC {

    private static final ReentrantLock lock = new ReentrantLock();

    private static final Condition conditionA = lock.newCondition();

    private static final Condition conditionB = lock.newCondition();

    private static final Condition conditionC = lock.newCondition();

    private static volatile int flag = 1;

    private static void a() {
        lock.lock();
        try {
            if (flag != 1) {
                conditionA.await();
            }
            System.out.println("A");
            flag = 2;
            conditionB.signal();
        } catch (Exception ign) {
            // ign
        } finally {
            lock.unlock();
        }
    }

    private static void b() {
        lock.lock();
        try {
            if (flag != 2) {
                conditionB.await();
            }
            System.out.println("B");
            flag = 3;
            conditionC.signal();
        } catch (Exception ign) {
            // ign
        } finally {
            lock.unlock();
        }
    }

    private static void c() {
        lock.lock();
        try {
            if (flag != 3) {
                conditionC.await();
            }
            System.out.println("C");
            flag = 1;
            conditionA.signal();
        } catch (Exception ign) {
            // ign
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0;i < 3;i++) {
                a();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0;i < 3;i++) {
                b();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0;i < 3;i++) {
                c();
            }
        }).start();
    }
}
