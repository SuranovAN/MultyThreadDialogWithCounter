import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("Создаю потоки");
        ThreadGroup mainGroup = new ThreadGroup("main group");
        Thread thread1 = new MyThread(mainGroup, "1");
        Thread thread2 = new MyThread(mainGroup, "2");
        Thread thread3 = new MyThread(mainGroup, "3");
        Thread thread4 = new MyThread(mainGroup, "4");

        final Callable<Integer> myCallable1 = new MyThread();
        final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        Future<Integer> task = threadPool.submit(myCallable1);
        Set<Callable<Integer>> callables = new HashSet<>();
        callables.add(new MyThread());
        callables.add(new MyThread());
        callables.add(new MyThread());

        List<Future<Integer>> futures = threadPool.invokeAll(callables);
        Integer futureAny = threadPool.invokeAny(callables);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        futures.forEach(f -> {
            try {
                System.out.println(f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        Integer tasksResult = task.get();
        Thread.sleep(15000);

        System.out.println("Result ANY" + futureAny);
        System.out.println("Result: " + tasksResult);
        System.out.println("Завершаю все потоки");
        mainGroup.interrupt();
        threadPool.shutdown();
    }
}
