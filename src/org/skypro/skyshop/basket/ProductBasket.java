package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.*;

public class ProductBasket {
    private final Map<String, List<Product>> products = new HashMap<>();

    public void addProduct(Product product) {
        products.computeIfAbsent(product.getTitle(), k -> new ArrayList<>()).add(product);
    }

    public int getTotalCost() {
        int total = 0;
        for (List<Product> productList : products.values()) {
            for (Product product : productList) {
                if (product != null) {
                    total += product.getPrice();
                }
            }
        }
        return total;
    }

    public void printContents() {
        if (products.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }

        int specialCount = 0;
        for (List<Product> productList : products.values()) {
            for (Product product : productList) {
                System.out.println(product.toString());
                if (product.isSpecial()) {
                    specialCount++;
                }
            }
        }

        System.out.println("Итого: " + getTotalCost());
        System.out.println("Специальных товаров: " + specialCount);
    }

    public boolean containsProduct(String name) {
        List<Product> productList = products.get(name);
        return productList != null && !productList.isEmpty();
    }

    public void clearBasket() {
        products.clear();
    }

    public List<Product> removeProductsByName(String name) {
        List<Product> removedProducts = products.remove(name);
        if (removedProducts == null) {
            return new ArrayList<>();
        }
        return removedProducts;
    }
}
