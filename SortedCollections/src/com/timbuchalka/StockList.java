package com.timbuchalka;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockList {
    private final Map<String, StockItem> map;

    public StockList() {
        this.map = new HashMap<>();
    }

    public int addStock(StockItem item) {
        if (item != null) {
            StockItem inStock = map.get(item.getName());

            if (inStock != null) {
                item.adjustStock(inStock.availableQuantity());
            }

            map.put(item.getName(), item);
            return item.availableQuantity();
        }
        return 0;
    }

    public int sellStock(String key, int quantity) {
        StockItem inStock = map.get(key);

        if ((inStock != null) && (quantity > 0)) {
            return inStock.finaliseStock(quantity);
        }
        return 0;

    }

    public int reserveStock(String item, int quantity) {
        StockItem inStock = map.get(item);

        if ((inStock != null) && (quantity > 0)) {
            return inStock.reserveStock(quantity);
        }
        return 0;
    }

    public int unreserveStock(String item, int quantity) {
        StockItem inStock = map.get(item);

        if ((inStock != null) && (quantity > 0)) {
            return inStock.unreserveStock(quantity);
        }
        return 0;
    }

    public StockItem get(String key) {
        return map.get(key);
    }

    public Map<String, Double> priceList() {
        Map<String, Double> prices = new HashMap<>();
        for (Map.Entry<String, StockItem> item : map.entrySet()) {
            prices.put(item.getKey(), item.getValue().getPrice());
        }
        return Collections.unmodifiableMap(prices);
    }

    public Map<String, StockItem> items() {
        return Collections.unmodifiableMap(map);
    }

    @Override
    public String toString() {
        String s = "\nStock List\n";
        double totalCost = 0.0;
        for (Map.Entry<String, StockItem> item : map.entrySet()) {
            StockItem stockItem = item.getValue();

            double itemValue = stockItem.getPrice() * stockItem.availableQuantity();

            s += stockItem + ". There are " + stockItem.availableQuantity() + " in stock. Value of items: ";
            s += itemValue + "\n";
            totalCost += itemValue;
        }

        return s + " Total stock value " + totalCost;
    }
}
