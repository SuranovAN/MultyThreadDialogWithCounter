import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
    Integer count = 0;

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
