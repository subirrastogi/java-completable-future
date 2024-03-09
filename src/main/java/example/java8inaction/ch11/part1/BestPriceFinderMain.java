package example.java8inaction.ch11.part1;

import java.util.List;
import java.util.function.Supplier;

public class BestPriceFinderMain
{
    public static void main(String[] args) {
        BestPriceFinder bestPriceFinder = new BestPriceFinder();

        execute("1. sequential", () -> bestPriceFinder.findPricesSequential("my favorite product"));
        execute("2. parallel", () -> bestPriceFinder.findPricesParallel("my favorite product"));
        execute("3. completable future", () -> bestPriceFinder.findPricesFuture("my favorite product"));
        execute("4. completable future using custom executor", () -> bestPriceFinder.findPricesFutureWithCustomExecutor("my favorite product"));

    }

    private static void execute(String msg, Supplier<List<String >> s) {
        long start = System.nanoTime();
        System.out.println(s.get());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(msg + " done in " + duration + " msecs");
    }
}
