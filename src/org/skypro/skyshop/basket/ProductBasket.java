package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

public class ProductBasket {
    private final Product[] products = new Product[5];
    private int productCount = 0;

    public void addProduct(Product product) {
        if (productCount >= products.length) {
            System.out.println("Невозможно добавить продукт");
            return;
        }
        products[productCount++] = product;
    }

    public int getTotalPrice() {
        int total = 0;
        for (int i = 0; i < productCount; i++) {
            total += products[i].getPrice();
        }
        return total;
    }

    public void printContents() {
        if (productCount == 0) {
            System.out.println("в корзине пусто");
            return;
        }

        for (int i = 0; i < productCount; i++) {
            Product product = products[i];
            System.out.println(product.getName() + ": " + product.getPrice());
        }
        System.out.println("Итого: " + getTotalPrice());
    }

    public boolean containsProduct(String name) {
        for (int i = 0; i < productCount; i++) {
            if (products[i].getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void clearBasket() {
        for (int i = 0; i < productCount; i++) {
            products[i] = null;
        }
        productCount = 0;
    }
}
