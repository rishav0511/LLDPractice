package org.example.filter;

import org.example.fileStructure.File;

public class ExtensionFilter implements SearchFilter {
    private String targetExtension;

    public ExtensionFilter(String extension) {
        this.targetExtension = extension;
    }

    @Override
    public boolean isValid(File file) {
        return file.getExtension().equals(targetExtension);
    }
}
