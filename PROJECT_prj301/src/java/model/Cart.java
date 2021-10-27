package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cart {

    private List<Item> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public Cart(List<Item> items) {
        this.items = items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public double getTotalMoney() {
        double t = 0;
        for (Item i : items) {
            t += (i.getQuantity() * i.getPrice());
        }
        return t;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        Cart c = (Cart) obj;
        if (c.items.size() != this.items.size()) return false;
        for (int i = 0; i < items.size(); i++) {
            if (this.getItems().get(i).getId() != c.getItems().get(i).getId() || this.getItems().get(i).getQuantity()!= c.getItems().get(i).getQuantity()) {
                return false;
            }
        }
        return true;
    }
}