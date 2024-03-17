package example.modernjavainaction.ch16.v1;

import java.util.List;
import java.util.function.Supplier;

public class BestPriceFinderMain
{
    public static void main(String[] args) {
        BestPriceFinder bestPriceFinder = new BestPriceFinder();

        execute("Version-1 1. sequentially using streams", () -> bestPriceFinder.findPricesSequential("my favorite product"));
        execute("Version-1 2. parallel using streams.parallelStream()", () -> bestPriceFinder.findPricesParallel("my favorite product"));
        execute("Version-1 3. completable future using streams", () -> bestPriceFinder.findPricesFuture("my favorite product"));
        execute("Version-1 4. completable future using custom executor", () -> bestPriceFinder.findPricesFutureWithCustomExecutor("my favorite product"));

    }

    private static void execute(String msg, Supplier<List<String >> s) {
        long start = System.nanoTime();
        System.out.println(s.get());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(msg + " done in " + duration + " msecs");
    }
}
