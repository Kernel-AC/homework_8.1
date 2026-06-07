package org.skypro.skyshop.search;

import org.skypro.skyshop.exceptions.BestResultNotFound;

import java.util.List;
import java.util.ArrayList;


public final class SearchEngine {
    private final List<Searchable> searchables;

    private static final int DEFAULT_SIZE = 50;

    public SearchEngine(int size) {
        this.searchables = new ArrayList<>(size);
    }

    public SearchEngine() {
        this.searchables = new ArrayList<>(DEFAULT_SIZE);
    }

    public List<Searchable> search(String query) {
        List<Searchable> results = new ArrayList<>();

        for (Searchable searchable : searchables) {
            if (searchable != null && searchable.getSearchTerm().contains(query)) {
                results.add(searchable);
            }
        }
        return results;
    }

    public void add(Searchable searchable) {
        searchables.add(searchable);
    }

    public void addAll(Searchable... searchables){
        for (Searchable searchable: searchables){
            add(searchable);
        }
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
