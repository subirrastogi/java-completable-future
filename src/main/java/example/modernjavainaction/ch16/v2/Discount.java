package example.modernjavainaction.ch16.v2;

import example.modernjavainaction.ch16.Util;

public class Discount {
        public enum Code {
            NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

            private final int percentage;

            Code(int percentage) {
                this.percentage = percentage;
            }
        }

        public static String applyDiscount(Quote quote) {
            double discountPrice = Discount.apply(quote.getPrice(), quote.getDiscountCode());

            return String.format("%s price is %.2f", quote.getShopName(), discountPrice);
        }

        private static double apply(double price, Code code) {
            Util.delay();
            return (price * (100 - code.percentage) / 100);
        }
}
