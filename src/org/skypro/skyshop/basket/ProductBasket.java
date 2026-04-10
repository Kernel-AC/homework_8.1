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

    public int getTotalCost() {
        int total = 0;
        for (Product product : products) {
            if (product != null) {
                total += product.getPrice();
            }
        }
        return total;
    }

    public void printContents() {
        boolean isEmpty = true;
        int specialCount = 0;

        for (Product product : products) {
            if (product != null) {
                System.out.println(product.toString());
                isEmpty = false;
                if (product.isSpecial()) {
                    specialCount++;
                }
            }
        }

        if (isEmpty) {
            System.out.println("в корзине пусто");
        } else {
            System.out.println("Итого: " + getTotalCost());
            System.out.println("Специальных товаров: " + specialCount);
        }
    }

    public boolean containsProduct(String name) {
        for (Product product : products) {
            if (product != null && product.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void clearBasket() {
        for (int i = 0; i < products.length; i++) {
            products[i] = null;
        }
    }
}
