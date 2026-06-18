package org.skypro.skyshop.search;

import org.skypro.skyshop.comparator.SearchableComparator;
import org.skypro.skyshop.exceptions.BestResultNotFound;

import java.util.*;


public final class SearchEngine {
    private final Set<Searchable> searchables;

    private static final int DEFAULT_SIZE = 50;

    public SearchEngine(int size) {
        this.searchables = new HashSet<>(size);
    }

    public SearchEngine() {
        this.searchables = new HashSet<>(DEFAULT_SIZE);
    }

    public TreeSet<Searchable> search(String query) {
        TreeSet<Searchable> results = new TreeSet<>(new SearchableComparator());

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

    public void addAll(Searchable... searchables) {
        for (Searchable searchable : searchables) {
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
