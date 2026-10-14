package org.example;

import org.example.fileStructure.Directory;
import org.example.fileStructure.File;
import org.example.fileStructure.FileSystemNode;
import org.example.filter.SearchFilter;

import java.util.ArrayList;
import java.util.List;

public class FileSearcher {
    public List<File> search(Directory directory, SearchFilter filter) {
        List<File> result = new ArrayList<>();
        searchHelper(directory, filter, result);
        return result;
    }

    private void searchHelper(Directory directory, SearchFilter filter, List<File> result) {
        for (FileSystemNode node : directory.getChildren()) {
            if (node instanceof File) {
                File file = (File) node;
                if (filter.isValid(file)) {
                    result.add(file);
                }
            } else if (node instanceof Directory) {
                // Recursive call to search child directories
                searchHelper((Directory) node, filter, result);
            }
        }
    }
}
