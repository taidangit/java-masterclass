package com.timbuchalka;

import java.util.Map;

public class Main {

    private static StockList stockList = new StockList();

    public static void main(String[] args) {
        StockItem temp = new StockItem("bread", 0.86, 100);
        stockList.addStock(temp);

        temp = new StockItem("cake", 1.10, 7);
        stockList.addStock(temp);

        temp = new StockItem("cake", 2.12, 5);
        stockList.addStock(temp);


        temp = new StockItem("car", 12.50, 2);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.50, 200);
        stockList.addStock(temp);

        temp = new StockItem("door", 72.95, 4);
        stockList.addStock(temp);

        temp = new StockItem("juice", 2.50, 36);
        stockList.addStock(temp);

        temp = new StockItem("phone", 96.99, 35);
        stockList.addStock(temp);

        temp = new StockItem("towel", 2.40, 80);
        stockList.addStock(temp);

        temp = new StockItem("vase", 8.76, 40);
        stockList.addStock(temp);

        System.out.println(stockList);

        System.out.println("Key List:");

        for (String s : stockList.items().keySet()) {
            System.out.println(s);
        }

        Basket taiBasket = new Basket("Tai");

        sellItem(taiBasket, "car", 1);
        System.out.println(taiBasket);

        sellItem(taiBasket, "car", 1);
        System.out.println(taiBasket);

        sellItem(taiBasket, "spanner", 5);

        sellItem(taiBasket, "juice", 14);
        sellItem(taiBasket, "cup", 12);
        sellItem(taiBasket, "bread", 1);

        System.out.println(taiBasket);

        Basket basket = new Basket("customer");

        sellItem(basket, "cup", 100);
        sellItem(basket, "juice", 5);
        System.out.println(basket);

        removeItem(basket, "cup", 1);
        System.out.println(basket);

        removeItem(taiBasket, "car", 1);
        removeItem(taiBasket, "cup", 9);
        removeItem(taiBasket, "car", 1);

        System.out.println("cars removed: " + removeItem(taiBasket, "car", 1));

        System.out.println(stockList);

        removeItem(taiBasket, "bread", 1);
        removeItem(taiBasket, "cup", 3);
        removeItem(taiBasket, "juice", 4);
        removeItem(taiBasket, "cup", 3);
        System.out.println(taiBasket);

        System.out.println("\nDisplay stock list before and after checkout");
        System.out.println(basket);
        System.out.println(stockList);
        checkout(basket);
        System.out.println(basket);
        System.out.println(stockList);

       /* StockItem car=stockList.items.get("car");
        if(car!=null) {
            car.adjustStock(2000);
        }

        if(car!=null) {
            stockList.get("car").adjustStock(-1000);
        }
*/

        /*System.out.println("==========================================");

        for (Map.Entry<String, Double> price:stockList.priceList().entrySet()) {
            System.out.println(price.getKey()+" costs "+price.getValue());
        }
*/
        checkout(taiBasket);
        System.out.println(taiBasket);
    }

    public static int sellItem(Basket basket, String item, int quantity) {
        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {
            System.out.println("We do not sell " + item);
            return 0;
        }
        if (stockList.reserveStock(item, quantity) != 0) {
            return basket.addToBasket(stockItem, quantity);
        }
        return 0;
    }

    public static int removeItem(Basket basket, String item, int quantity) {
        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {
            System.out.println("We do not sell " + item);
            return 0;
        }
        if (basket.removeFromBasket(stockItem, quantity) == quantity) {
            return stockList.unreserveStock(item, quantity);
        }
        return 0;
    }

    public static void checkout(Basket basket) {
        for (Map.Entry<StockItem, Integer> item : basket.items().entrySet()) {
            stockList.sellStock(item.getKey().getName(), item.getValue());
        }
        basket.clearBasket();
    }
}
