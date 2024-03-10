package example.modernjavainaction.ch16.v2;

import example.modernjavainaction.ch16.Util;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {
    private final String name;
    private final Random random;

    public Shop(String name) {
        this.name = name;
        random = new Random();
    }

    public String getName() {
        return this.name;
    }

    public String getPrice(String product) {

        double price =  calculatePrices(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return name + ":" + price + ":" + code;
    }

    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();

        new Thread( () -> {
            try {
                double price = calculatePrices(product);
                futurePrice.complete(price);
            } catch (Exception ex) {
                futurePrice.completeExceptionally(ex);
            }
        }).start();
        return  futurePrice;
    }

    public Future<Double> getPriceAsyncWithSupplyAsync(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrices(product));

    }

    private double calculatePrices(String product) {
        Util.delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

}
