package org.skypro.skyshop.product;

public class FixPriceProduct extends Product {
    private final static int FIXED_PRICE = 1000;

    public FixPriceProduct(String name) {
        super(name);
    }

    @Override
    public int getPrice() {
        return FIXED_PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getTitle() + ": Фиксированная цена " + FIXED_PRICE;
    }
}
