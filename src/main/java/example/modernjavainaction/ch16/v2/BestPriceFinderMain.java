package example.modernjavainaction.ch16.v2;

import java.util.List;
import java.util.function.Supplier;

public class BestPriceFinderMain
{
    public static void main(String[] args) {
        BestPriceFinder bestPriceFinder = new BestPriceFinder();

        execute("Version-2 1. sequentially using streams", () -> bestPriceFinder.findPricesSequential("my favorite product"));
        execute("Version-2 2. parallel using streams.parallelStream()", () -> bestPriceFinder.findPricesParallel("my favorite product"));
        execute("Version-2 4. completable future with custom executor", () -> bestPriceFinder.findPricesFutureWithCustomExecutor("my favorite product"));
        /*execute("4. completable future using custom executor", () -> bestPriceFinder.findPricesFutureWithCustomExecutor("my favorite product"));*/

    }

    private static void execute(String msg, Supplier<List<String >> s) {
        long start = System.nanoTime();
        System.out.println(s.get());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(msg + " done in " + duration + " msecs");
    }
}
