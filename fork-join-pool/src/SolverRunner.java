import java.util.concurrent.ForkJoinPool;

public class SolverRunner {

    private static final int numberOfThreads = 8;

    public static void main(String[] args) {
        int[] testArray = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
        Solver solver = new Solver(testArray, 0, testArray.length);
        ForkJoinPool pool = new ForkJoinPool(numberOfThreads);
        pool.invoke(solver);
        System.out.println(solver.result);
    }
}
