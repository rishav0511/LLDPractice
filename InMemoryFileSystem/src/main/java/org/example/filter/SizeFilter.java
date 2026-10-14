package org.example.filter;

import org.example.fileStructure.File;

public class SizeFilter implements SearchFilter {
    public enum OPERATION {
        LESS_THAN,
        GREATER_THAN,
        EQUAL_TO
    }
    private OPERATION operation;
    private Integer threshold;

    public SizeFilter(OPERATION operation, Integer threshold) {
        this.operation = operation;
        this.threshold = threshold;
    }

    @Override
    public boolean isValid(File file) {
        int fileSize = file.getSize();
        switch (operation) {
            case LESS_THAN:
                return fileSize < threshold;
            case GREATER_THAN:
                return fileSize > threshold;
            case EQUAL_TO:
                return fileSize == threshold;
            default:
                return false;
        }
    }
}
