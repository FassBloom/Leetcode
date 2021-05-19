public class ThreadSync {
    public static void syn1() {
        System.out.println("static is running");
    }

    public void syn2() {
        System.out.println("syn2 is running");
    }

    public void rec(){
        rec();
    }

    public static void main(String[] args) {
        ThreadSync threadSync = new ThreadSync();

        Thread thread1 = new Thread(() -> ThreadSync.syn1());
        Thread thread2 = new Thread(threadSync::syn2);
        thread1.start();
        thread2.start();
        //threadSync.rec();
    }
}
