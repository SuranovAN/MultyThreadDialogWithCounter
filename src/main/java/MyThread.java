import java.util.concurrent.Callable;

public class MyThread extends Thread implements Callable<Integer> {
    static Integer count = 0;


    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                sleep(2500);
                System.out.printf("Я поток %s. Всем привет!\n", getName());
            }
        } catch (InterruptedException e) {

        } finally {
            System.out.printf("%s звершён\n", getName());
        }
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
    }

    public MyThread() {
    }

    @Override
    public Integer call() {
        while (count < 10) {
            System.out.println("итерация " + count);
            count++;
        }
        System.out.println(Thread.currentThread().getName());
        return count;
    }
}
