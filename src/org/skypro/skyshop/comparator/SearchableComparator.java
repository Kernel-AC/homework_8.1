package org.skypro.skyshop.comparator;

import org.skypro.skyshop.search.Searchable;

import java.util.Comparator;

public class SearchableComparator implements Comparator<Searchable> {
    @Override
    public int compare(Searchable s1, Searchable s2) {
        int lengthComparison = Integer.compare(s2.getTitle().length(), s1.getTitle().length());
        if (lengthComparison != 0) {
            return lengthComparison;
        } else {
            return s1.getTitle().compareTo(s2.getTitle());
        }
    }
}