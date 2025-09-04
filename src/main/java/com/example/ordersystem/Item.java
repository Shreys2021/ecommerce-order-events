package com.example.ordersystem;

import java.util.Objects;

public class Item {
    private String itemId;
    private int qty;

    public Item() { }

    public Item(String itemId, int qty) {
        this.itemId = itemId;
        this.qty = qty;
    }

    public String getItemId() { return itemId; }
    public int getQty() { return qty; }

    public void setItemId(String itemId) { this.itemId = itemId; }
    public void setQty(int qty) { this.qty = qty; }

    @Override
    public String toString() {
        return "Item{itemId='" + itemId + "', qty=" + qty + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return qty == item.qty && Objects.equals(itemId, item.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, qty);
    }
}
