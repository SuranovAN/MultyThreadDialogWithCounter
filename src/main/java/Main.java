import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("Создаю потоки");

        final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        Set<Callable<Integer>> callables = new HashSet<>();
        callables.add(new MyThread());
        callables.add(new MyThread());
        callables.add(new MyThread());
        callables.add(new MyThread());

        List<Future<Integer>> futures = threadPool.invokeAll(callables);
        Integer futureAny = threadPool.invokeAny(callables);

        futures.forEach(f -> {
            try {
                System.out.println(f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        Thread.sleep(15000);
        System.out.println("Result ANY " + futureAny);
        System.out.println("Завершаю все потоки");
        threadPool.shutdown();
    }
}
