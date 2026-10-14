package org.example.filter;

import org.example.fileStructure.File;

public class AndFilter implements SearchFilter {

    private SearchFilter filter1;
    private SearchFilter filter2;

    public AndFilter(SearchFilter filter1, SearchFilter filter2) {
        this.filter1 = filter1;
        this.filter2 = filter2;
    }

    @Override
    public boolean isValid(File file) {
        return filter1.isValid(file) && filter2.isValid(file);
    }
}
