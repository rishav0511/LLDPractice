package org.example.filter;

import org.example.fileStructure.File;

public interface SearchFilter {
    boolean isValid(File file);
}
