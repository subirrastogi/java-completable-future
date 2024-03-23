package example.modernjavainaction.ch16.v0;

import java.util.List;
import java.util.function.Supplier;

public class BestPriceFinderMain {

    public static void main(String[] args) {
        BestPriceFinder bestPriceFinder = new BestPriceFinder();

        execute("Version-0 1. Sequentially", () -> bestPriceFinder.findPricesSequentially("my favorite product"));
        execute("Version-0 2. Using Future & Executor", () -> bestPriceFinder.findPricesFuture("my favorite product"));
    }

    private static void execute(String msg, Supplier<List<String >> s) {
        long start = System.nanoTime();
        System.out.println(s.get());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(msg + " done in " + duration + " msecs");
    }
}
