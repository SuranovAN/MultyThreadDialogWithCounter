import java.util.concurrent.Callable;

public class MyThread extends Thread implements Callable<Integer> {
    Integer count = 0;


    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                sleep(2500);
                System.out.printf("Я поток %s. Всем привет!\n", getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.printf("%s звершён\n", getName());
        }
    }

    public MyThread() {
    }

    @Override
    public Integer call() {
        while (count < 5) {
            System.out.println(Thread.currentThread().getName() + " итерация " + count);
            count++;
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Thread.currentThread().interrupt();
        return count;
    }
}
