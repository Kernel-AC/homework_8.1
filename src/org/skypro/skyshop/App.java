package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.SearchEngine;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        Product apples = new SimpleProduct("Яблоки", 150);
        Product pears = new SimpleProduct("Груши", 200);
        Product oranges = new FixPriceProduct("Апельсины");
        Product bananas = new FixPriceProduct("Бананы");
        Product lemons = new DiscountedProduct("Лимоны", 150, 20);
        Product kiwi = new DiscountedProduct("Киви", 250, 35);


        ProductBasket basket = new ProductBasket();

        // добавление разных типов товаров в корзину
        basket.addProduct(apples);
        basket.addProduct(pears);
        basket.addProduct(oranges);
        basket.addProduct(bananas);
        basket.addProduct(lemons);

        // добавление продукта в заполненую корзину
        basket.addProduct(kiwi);

        // печать содержимого корзины с несколькими товарами
        basket.printContents();

        // получение стоимости корзины с несколькими товарами
        System.out.println("Стоимость корзины: " + basket.getTotalCost());

        // поиск товара, который есть в корзине
        System.out.println("Есть ли «Яблоки» в корзине? " + basket.containsProduct("Яблоки"));

        // 6.поиск товара, которого нет в корзине
        System.out.println("Есть ли «Киви» в корзине? " + basket.containsProduct("Киви"));

        // 7.очистка корзины
        basket.clearBasket();

        // 8.печать содержимого пустой корзины
        basket.printContents();

        // 9.получение стоимости пустой корзины
        System.out.println("Стоимость пустой корзины: " + basket.getTotalCost());

        // 10.поиск товара по имени в пустой корзине
        System.out.println("Есть ли «Груши» в пустой корзине? " + basket.containsProduct("Груша"));

        // 11.создаем один объект типа SearchEngine и добавляем товары
        SearchEngine searchEngine = new SearchEngine();
        searchEngine.addAll(apples, pears, oranges, lemons, bananas);

        // 12.создаем несколько объектов типа Article и добавляем их в SearchEngine
        Article applesArticle = new Article("Статья о яблоках", "Яблоки- это плод, имеет округлую форму, тонкую кожицу обычно зелёного, жёлтого или красного цвета и сочную светлую мякоть белого, розоватого или жёлтого оттенков с сердцевиной, содержащей мелкие тёмно-коричневые или чёрные семечки.");

        Article lemonsArticle = new Article("Статья о лимонах", "Лимоны- это небольшое вечнозелёное плодовое дерево высотой до 5—8 м, с раскидистой или пирамидальной кроной. Встречаются деревья в возрасте 45 лет.");

        searchEngine.addAll(applesArticle, lemonsArticle);

        // 13.функционал поиска с помощью SearchEngine
        String searchQuery1 = "Яблоки";
        System.out.println("Поиск \""+ searchQuery1+"\": "+ Arrays.toString(searchEngine.search(searchQuery1)));

        String searchQuery2= "Лимоны";
        System.out.println("Поиск \""+ searchQuery2+"\": "+ Arrays.toString(searchEngine.search(searchQuery2)));

        String searchQuery3= "Бумага";
        System.out.println("Поиск \""+ searchQuery3+"\": "+ Arrays.toString(searchEngine.search(searchQuery3)));
    }
}