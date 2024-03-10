package example.modernjavainaction.ch16.v0;

import example.modernjavainaction.ch16.Util;

import java.util.Random;

public class Shop {
    private final String name;
    private final Random random;

    public Shop(String name) {
        this.name = name;
        random = new Random();
    }

    public String getName() {
        return name;
    }

    public double getPrice(String product) {
        return calculatePrices(product);
    }
    private double calculatePrices(String product) {
        Util.delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }
}
