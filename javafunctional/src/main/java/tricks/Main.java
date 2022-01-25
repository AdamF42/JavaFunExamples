package tricks;

import java.util.function.Function;


public class Main {
    Double longCalculation(Integer x) {
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException ignored) {
        }
        return (double) (x * 2);
    }

    Integer longCalculation2(Integer x, Integer y) {
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException ignored) {
        }
        return x * y;
    }

    Function<Integer, Double> f = this::longCalculation;
    Function<Integer, Double> g = Memoizer.memoize(f);
    Function<Integer, Function<Integer, Integer>> e = Memoizer.memoize(x -> Memoizer.memoize(y -> longCalculation2(x, y)));

    public void automaticMemoizationExample() {
        long startTime = System.currentTimeMillis();
        Double result1 = g.apply(1);
        long time1 = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        Double result2 = g.apply(1);
        long time2 = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        Integer withCurrying1 = e.apply(2).apply(3);
        long time3 = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        Integer withCurrying2 = e.apply(2).apply(3);
        long time4 = System.currentTimeMillis() - startTime;

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(withCurrying1);
        System.out.println(withCurrying2);
        System.out.println(time1);
        System.out.println(time2);
        System.out.println(time3);
        System.out.println(time4);

    }

    public static void main(String[] args) {

        new Main().automaticMemoizationExample();

    }
}
