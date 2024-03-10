package example.modernjavainaction.ch16.v0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BestPriceFinder {
    private final List<Shop> shops = Arrays.asList(
            new Shop("Best Price"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll")/*,
            new Shop("ShopEasy")*/
    );

    private final ExecutorService executorService = Executors.newFixedThreadPool(shops.size());

    public List<String> findPrices(String product) {

        List<String> priceList = new ArrayList<>();

        List<Future<Double>> futuresList = new ArrayList<>();

        for (Shop shop : shops) {
            Future<Double> future =  executorService.submit(() -> shop.getPrice(product));
            futuresList.add(future);
        }

        for (int i = 0; i < shops.size(); i++) {
            try {
                Shop shop = shops.get(i);
                Future<Double> price = futuresList.get(i);

                String str = String.format("%s price is %.2f", shop.getName(), price.get());
                priceList.add(str);

            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        executorService.shutdown();
        return  priceList;
    }
}
