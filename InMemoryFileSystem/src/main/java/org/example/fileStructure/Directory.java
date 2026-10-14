package org.example.fileStructure;

import java.util.ArrayList;
import java.util.List;

public class Directory extends FileSystemNode {

    private List<FileSystemNode> children;

    public Directory(String name) {
        super(name);
        this.children = new ArrayList<>();
    }

    public List<FileSystemNode> getChildren() {
        return children;
    }

    public List<FileSystemNode> addChildren(FileSystemNode node) {
        children.add(node);
        return children;
    }

    public List<FileSystemNode> removeChildren(FileSystemNode node) {
        children.remove(node);
        return children;
    }

    @Override
    public Integer getSize() {
        int size = 0;
        for (FileSystemNode child : children) {
            size += child.getSize();
        }
        return size;
    }

    @Override
    public void print(String indentation) {
        System.out.println(indentation + "Directory: " + getName());
        for(FileSystemNode child : children) {
            child.print(indentation + "  ");
        }
    }
}
