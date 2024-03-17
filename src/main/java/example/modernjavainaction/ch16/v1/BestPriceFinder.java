package example.modernjavainaction.ch16.v1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

public class BestPriceFinder {
    private final List<Shop> shops = Arrays.asList(
            new Shop("Best Price"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("ShopEasy"),
            new Shop("ShopSmart-1"),
            new Shop("ShopSmart-2"),
            new Shop("ShopSmart-3"),
            new Shop("ShopSmart-4")
    );

    private final Executor executor = Executors.newFixedThreadPool(shops.size(), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });

    public List<String> findPricesSequential(String product) {
        return shops.stream()
                    .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                    .collect(Collectors.toList());
    }

    public List<String> findPricesParallel(String product) {
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    public List<String> findPricesFuture(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> String.format("%s price is %.2f", shop.getName(),
                                shop.getPrice(product))))
                .collect(Collectors.toList());

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public List<String> findPricesFutureWithCustomExecutor(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                                                            .map(shop -> CompletableFuture.supplyAsync(
                                                                    () -> String.format("%s price is %.2f", shop.getName(),
                                                                            shop.getPrice(product))))
                                                            .collect(Collectors.toList());

        return priceFutures.stream()
                           .map(CompletableFuture::join)
                           .collect(Collectors.toList());
    }
}
