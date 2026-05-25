package org.skypro.skyshop.exceptions;

public class BestResultNotFound extends Exception {
    public BestResultNotFound(String search) {
        super("Не найдено подходящего объекта для поискового запроса: " + search);
    }
}
