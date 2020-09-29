import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("Создаю потоки");

        final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Callable<Integer>> futureList = new ArrayList<>();
        futureList.add(new MyCallable());
        futureList.add(new MyCallable());
        futureList.add(new MyCallable());
        futureList.add(new MyCallable());

        threadPool.invokeAll(futureList);

        Integer futureAny = threadPool.invokeAny(futureList);

        futureList.forEach(f -> {
            try {
                System.out.println(f.call());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }

        System.out.println("Result ANY " + futureAny);
        System.out.println("Завершаю все потоки");
        threadPool.shutdown();
    }
}
