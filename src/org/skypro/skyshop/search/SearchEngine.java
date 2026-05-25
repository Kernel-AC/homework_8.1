package org.skypro.skyshop.search;

import org.skypro.skyshop.exceptions.BestResultNotFound;

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

    public Searchable findBestMatch(String search) throws BestResultNotFound {
        if (search == null || search.isBlank()) {
            throw new BestResultNotFound(search);
        }

        Searchable bestMatch = null;
        int maxCount = -1;

        for (Searchable searchable : searchables) {
            if (searchable == null) {
                continue;
            }

            String searchTerm = searchable.getSearchTerm();
            if (searchTerm == null) {
                continue;
            }

            int count = countOccurrences(searchTerm, search);

            if (count > maxCount) {
                maxCount = count;
                bestMatch = searchable;
            }
        }

        if (bestMatch == null || maxCount == 0) {
            throw new BestResultNotFound(search);
        }

        return bestMatch;
    }

    private int countOccurrences(String str, String substring) {
        if (substring == null || substring.isEmpty() || str == null || str.isEmpty()) {
            return 0;
        }

        int count = 0;
        int index = 0;
        int substringLength = substring.length();

        while (true) {
            int indexOfSubstring = str.indexOf(substring, index);
            if (indexOfSubstring == -1) {
                break;
            }
            count++;
            index = indexOfSubstring + substringLength;
        }
        return count;
    }
}
