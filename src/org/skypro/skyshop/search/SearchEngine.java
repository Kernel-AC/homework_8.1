package org.skypro.skyshop.search;

import java.sql.SQLOutput;

public final class SearchEngine {
    private final Searchable[] searchables;

    private static final int MAX_SEARCH_RESULTS = 5;
    private static final int NOT_FOUND = -1;
    private static final int DEFAULT_SIZE = 50;

    public SearchEngine(int size) {
        this.searchables = new Searchable[size];
    }

    public SearchEngine() {
        this.searchables = new Searchable[DEFAULT_SIZE];
    }

    public Searchable[] search(String query) {
        Searchable[] results = new Searchable[MAX_SEARCH_RESULTS];

        int i = 0;
        for (Searchable searchable : searchables) {
            if (searchable != null && searchable.getSearchTerm().contains(query)) {
                results[i++] = searchable;
                if (i >= MAX_SEARCH_RESULTS) {
                    break;
                }
            }
        }
        return results;
    }

    public void add(Searchable searchable) {
        int freeIndex = getFreeIndex();
        if (freeIndex == NOT_FOUND){
            System.out.println("Невозможно добавить элемент для поиска");
            return;
        }
        searchables[freeIndex]=searchable;
    }

    public void addAll(Searchable... searchables){
        for (Searchable searchable: searchables){
            add(searchable);
        }
    }

    private int getFreeIndex(){
        for (int i=0;i<searchables.length;i++){
            if (searchables[i]==null){
                return i;
            }
        }
        return 0;
    }


}
