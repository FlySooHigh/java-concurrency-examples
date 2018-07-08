import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by rajeevkumarsingh on 11/05/17.
 */
class ReadWriteCounter {
    ReadWriteLock lock = new ReentrantReadWriteLock();

    private int count = 0;

    public int incrementAndGetCount() {
        lock.writeLock().lock();

        try {
            count = count + 1;
            return count;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int getCount() {
        lock.readLock().lock();
        try {
            return count;
        } finally {
            lock.readLock().unlock();
        }
    }
}

public class ReadWriteLockExample {

    private static final int numberOfThreads = 10;
    private static final int numberOfReadTasks = 10;
    private static final int numberOfWriteTasks = 10;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        ReadWriteCounter counter = new ReadWriteCounter();

        Runnable readTask = () -> {
          System.out.println(Thread.currentThread().getName() +
                  " Read Task : " + counter.getCount());
        };

        Runnable writeTask = () -> {
            System.out.println(Thread.currentThread().getName() +
                    " Write Task : " + counter.incrementAndGetCount());
        };


        for (int i = 0; i < numberOfWriteTasks; i++) {
            executorService.submit(writeTask);
            executorService.submit(readTask);
        }

        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);
    }
}
