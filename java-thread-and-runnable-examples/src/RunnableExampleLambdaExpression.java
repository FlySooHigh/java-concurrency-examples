/**
 * Created by rajeevkumarsingh on 08/05/17.
 */
public class RunnableExampleLambdaExpression {

    public static void main(String[] args) {
        System.out.println("Inside : " + Thread.currentThread().getName());

        System.out.println("Creating Runnables...");
        Runnable runnable = () -> {
            System.out.println("Inside : " + Thread.currentThread().getName());
        };

        Runnable runnable1 = () -> {
            System.out.println("Inside : " + Thread.currentThread().getName());
        };

        System.out.println("Creating Threads...");
        Thread thread = new Thread(runnable);
        Thread thread1 = new Thread(runnable1);

        System.out.println("Starting Threads...");
        thread.start();
        thread1.start();

    }
}
