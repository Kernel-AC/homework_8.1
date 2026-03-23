package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;

public class App {
    public static void main(String[] args) {
        Product product1 = new Product("Яблоко", 50);
        Product product2 = new Product("Банан", 150);
        Product product3 = new Product("Груша", 30);
        Product product4 = new Product("Ананас", 200);
        Product product5 = new Product("Слива", 500);
        Product product6 = new Product("Мандарин", 350);

        ProductBasket basket = new ProductBasket();

        // 1.добавление продукта в корзину
        basket.addProduct(product1);
        basket.addProduct(product2);
        basket.addProduct(product3);
        basket.addProduct(product4);
        basket.addProduct(product6);
        System.out.println();

        //2.добавление в заполненную корзину, в которой нет места
        for (int i = 0; i < 2; i++) {
            basket.addProduct(product5);
        }

        //3.печать содержимого корзины с несколькими товарами
        basket.printContents();

        // 4.получение стоимости корзины с несколькими товарами
        System.out.println("Стоимость корзины: " + basket.getTotalPrice());

        // 5.поиск товара, который есть в корзине
        System.out.println("Есть ли «Яблоко» в корзине? " + basket.containsProduct("Яблоко"));

        // 6.поиск товара, которого нет в корзине
        System.out.println("Есть ли «Морковь» в корзине? " + basket.containsProduct("Морковь"));

        // 7.очистка корзины
        basket.clearBasket();

        // 8.печать содержимого пустой корзины
        basket.printContents();

        // 9.получение стоимости пустой корзины
        System.out.println("Стоимость пустой корзины: " + basket.getTotalPrice());

        // 10.поиск товара по имени в пустой корзине
        System.out.println("Есть ли «Груша» в пустой корзине? " + basket.containsProduct("Груша"));

    }
}