public class PrintABCUsingWaitNotify {

    private int state;
    private int times;
    private static final Object LOCK = new Object();

    public PrintABCUsingWaitNotify(int times) {
        this.times = times;
    }

    public static void main(String[] args) {
        PrintABCUsingWaitNotify printABC = new PrintABCUsingWaitNotify(10);
        new Thread(() -> {
            printABC.printLetter("A", 0);
        }, "A").start();
        new Thread(() -> {
            printABC.printLetter("B", 1);
        }, "B").start();
        new Thread(() -> {
            printABC.printLetter("C", 2);
        }, "C").start();
    }

    private void printLetter(String name, int targetState) {
        for (int i = 0; i < times; i++) {
            System.out.println("i=: " + i + "    thread:" + Thread.currentThread().getName());
            synchronized (LOCK) {
                System.out.println(Thread.currentThread().getName()+"getLock");
                while (state % 3 != targetState) {
                    try {
                        System.out.println(Thread.currentThread().getName()+"wait");
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                state++;
                System.out.println(name);
                System.out.println("notifyAll");
                LOCK.notifyAll();
            }
        }
    }
}