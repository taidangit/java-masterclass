package com.timbuchalka;

public class StockItem implements Comparable<StockItem> {
    private final String name;
    private double price;
    private int quatityStock;
    private int reserved = 0;

    public StockItem(String name, double price, int quatityStock) {
        this.name = name;
        this.price = price;
        this.quatityStock = quatityStock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int availableQuantity() {
        return quatityStock - reserved;
    }

    public void setPrice(double price) {
        if (price > 0.0) {
            this.price = price;
        }

    }

    public void adjustStock(int quantity) {
        int newQuantity = this.quatityStock + quantity;
        if (newQuantity >= 0) {
            this.quatityStock = newQuantity;
        }
    }

    public int reserveStock(int quantity) {
        if (quantity <= availableQuantity()) {
            this.reserved += quantity;
            return quantity;
        }
        return 0;
    }

    public int unreserveStock(int quantity) {
        if (quantity <= this.reserved) {
            this.reserved -= quantity;
            return quantity;
        }
        return 0;
    }

    public int finaliseStock(int quantity) {
        if (quantity <= this.reserved) {
            this.quatityStock -= quantity;
            this.reserved -= quantity;
            return quantity;
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if ((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }

        String objName = ((StockItem) obj).getName();
        return this.name.equals(objName);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + 31;
    }

    @Override
    public int compareTo(StockItem o) {
        if (this == o) {
            return 0;
        }
        if (o != null) {
            return this.name.compareTo(o.getName());
        }
        throw new NullPointerException();
    }

    @Override
    public String toString() {
        return "StockItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quatityStock=" + quatityStock +
                ", reserved=" + reserved +
                '}';
    }
}
