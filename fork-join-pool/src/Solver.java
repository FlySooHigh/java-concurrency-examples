import java.util.concurrent.RecursiveAction;

public class Solver extends RecursiveAction{
    int start, end, result;
    int[] array;

    public Solver(int[] array, int start, int end) {
        this.start = start;
        this.end = end;
        this.array = array;
    }

    @Override
    protected void compute() {
        if(end - start == 1){
            result = array[start];
        }
        else {
            int mid = (start + end) / 2;
            Solver solver1 = new Solver(array, start, mid);
            Solver solver2 = new Solver(array, mid, end);
            invokeAll(solver1, solver2);
            result = Math.max(solver1.result, solver2.result);
        }
    }
}
