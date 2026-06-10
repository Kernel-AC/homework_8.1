package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.exceptions.BestResultNotFound;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

import java.util.List;
import java.util.Map;


public class App {
    public static void main(String[] args) {
        Product apples = new SimpleProduct("Яблоки", 150);
        Product pears = new SimpleProduct("Груши", 200);
        Product oranges = new FixPriceProduct("Апельсины");
        Product bananas = new FixPriceProduct("Бананы");
        Product lemons = new DiscountedProduct("Лимоны", 150, 20);
        Product kiwi = new DiscountedProduct("Киви", 250, 35);

        System.out.println("\n--- Демонстрация некорректных данных ---");
        // Демонстрация некорректных данных
        try {
            Product invalidProduct = new SimpleProduct("", 150);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            Product invalidPrice = new SimpleProduct("Яблоки", 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            Product invalidDiscount = new DiscountedProduct("Лимоны", 150, 150);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        ProductBasket basket = new ProductBasket();

        // добавление разных типов товаров в корзину
        basket.addProduct(apples);
        basket.addProduct(pears);
        basket.addProduct(oranges);
        basket.addProduct(bananas);
        basket.addProduct(lemons);

        // Демонстрация удаления продукта
        System.out.println("\n--- Удаление продукта из корзины ---");
        List<Product> removedApples = basket.removeProductsByName("Яблоки");
        System.out.println("Удалённые продукты:");
        for (Product product : removedApples) {
            System.out.println("- " + product.getTitle());
        }
        System.out.println("Содержимое корзины после удаления:");
        basket.printContents();

        System.out.println("\n--- Попытка удалить несуществующий продукт ---");
        List<Product> removedOranges = basket.removeProductsByName("Киви");
        if (removedOranges.isEmpty()) {
            System.out.println("Список пуст");
        } else {
            System.out.println("Удалено: " + removedOranges.size() + " товаров");
        }
        System.out.println("Содержимое корзины:");
        basket.printContents();

        System.out.println("\n--- Добавление продукта в заполненную корзину ---");
        // добавление продукта в заполненную корзину
        basket.addProduct(kiwi);

        // печать содержимого корзины с несколькими товарами
        basket.printContents();

        // получение стоимости корзины с несколькими товарами
        System.out.println("Стоимость корзины: " + basket.getTotalCost());

        System.out.println("\n--- Поиск товара который есть в корзине ---");
        // поиск товара, который есть в корзине
        System.out.println("Есть ли «Яблоки» в корзине? " + basket.containsProduct("Яблоки"));

        // 6.поиск товара, которого нет в корзине
        System.out.println("Есть ли «Киви» в корзине? " + basket.containsProduct("Киви"));

        System.out.println("\n--- Очистка корзины ---");
        // 7.очистка корзины
        basket.clearBasket();

        // 8.печать содержимого пустой корзины
        basket.printContents();

        System.out.println("\n--- Стоимость пустой корзины ---");
        // 9.получение стоимости пустой корзины
        System.out.println("Стоимость пустой корзины: " + basket.getTotalCost());

        System.out.println("\n--- Поиск товаров по имени в пустой корзине ---");
        // 10.поиск товара по имени в пустой корзине
        System.out.println("Есть ли «Груши» в пустой корзине? " + basket.containsProduct("Груша"));

        System.out.println("\n--- Создаем и добавляем товары ---");
        // 11.создаем один объект типа SearchEngine и добавляем товары
        SearchEngine searchEngine = new SearchEngine();
        searchEngine.addAll(apples, pears, oranges, lemons, bananas);

        // 12.создаем несколько объектов типа Article и добавляем их в SearchEngine
        Article applesArticle = new Article("Статья о яблоках", "Яблоки- это плод, имеет округлую форму, тонкую кожицу обычно зелёного, жёлтого или красного цвета и сочную светлую мякоть белого, розоватого или жёлтого оттенков с сердцевиной, содержащей мелкие тёмно-коричневые или чёрные семечки.");

        Article lemonsArticle = new Article("Статья о лимонах", "Лимоны- это небольшое вечнозелёное плодовое дерево высотой до 5—8 м, с раскидистой или пирамидальной кроной. Встречаются деревья в возрасте 45 лет.");

        searchEngine.addAll(applesArticle, lemonsArticle);

        // 13.функционал поиска с помощью SearchEngine
        String searchQuery1 = "Яблоки";
        Map<String, Searchable> searchResults1 = searchEngine.search(searchQuery1);
        System.out.println("Поиск \"" + searchQuery1 + "\": " + searchResults1.values());

        String searchQuery2 = "Лимоны";
        Map<String, Searchable> searchResults2 = searchEngine.search(searchQuery2);
        System.out.println("Поиск \"" + searchQuery2 + "\": " + searchResults2.values());

        String searchQuery3 = "Бумага";
        Map<String, Searchable> searchResults3 = searchEngine.search(searchQuery3);
        System.out.println("Поиск \"" + searchQuery3 + "\": " + searchResults3.values());

        System.out.println("\n--- Новый метод поиска ---");
        // новый метод поиска
        // 1-объект найден
        try {
            Searchable result = searchEngine.findBestMatch("Яблоки");
            System.out.println("Найден лучший результат: " + result.getTitle());
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка поиска: " + e.getMessage());
        }

        // 2-объект не найден
        try {
            Searchable result = searchEngine.findBestMatch("Бумага");
            System.out.println("Найден лучший результат: " + result.getTitle());
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка поиска: " + e.getMessage());
        }
    }

}