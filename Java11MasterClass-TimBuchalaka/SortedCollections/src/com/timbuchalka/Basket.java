package com.timbuchalka;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Basket {
    private final String name;
    private final Map<StockItem, Integer> map;

    public Basket(String name) {
        this.name = name;
        this.map = new HashMap<>();
    }

    public int addToBasket(StockItem item, int quantity) {
        if ((item != null) && (quantity > 0)) {
            int inBasket = map.getOrDefault(item, 0);
            map.put(item, inBasket + quantity);
            return inBasket;
        }
        return 0;
    }

    public int removeFromBasket(StockItem item, int quantity) {
        if ((item != null) && (quantity > 0)) {
            int inBasket = map.getOrDefault(item, 0);
            int newQuantity = inBasket - quantity;

            if (newQuantity > 0) {
                map.put(item, newQuantity);
                return quantity;
            } else if (newQuantity == 0) {
                map.remove(item);
                return quantity;
            }
        }
        return 0;
    }

    public void clearBasket() {
        map.clear();
    }

    public Map<StockItem, Integer> items() {
        return Collections.unmodifiableMap(map);
    }

    @Override
    public String toString() {
        String s = "\nShopping basket " + name + " contains " + map.size() + (map.size() == 1 ? " item" : " items") + "\n";
        double totalCost = 0.0;
        for (Map.Entry<StockItem, Integer> item : map.entrySet()) {
            s += item.getKey() + "." + item.getValue() + " purchased\n";
            totalCost += item.getKey().getPrice() * item.getValue();
        }
        return s + "Total cost " + totalCost;
    }
}
