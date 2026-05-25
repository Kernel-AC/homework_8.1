package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;

public abstract class Product implements Searchable {
    private final String title;

    public Product(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public abstract int getPrice();

    public abstract boolean isSpecial();

    @Override
    public String getSearchTerm(){
        return title;
    }
    @Override
    public String getSearchContentType(){
        return "PRODUCT";
    }
    @Override
    public String toString(){
        return title;
    }

}
