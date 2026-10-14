package org.example.fileStructure;

public abstract class FileSystemNode {
    private String name;
    public FileSystemNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract Integer getSize();
    public abstract void print(String indentation);

}
