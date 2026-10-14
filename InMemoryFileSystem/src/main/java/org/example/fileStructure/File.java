package org.example.fileStructure;

public class File extends FileSystemNode {
    private Integer size;
    private String extension;
    private byte[] content;

    public File(String name, String extension, Integer size) {
        super(name);
        this.extension = extension;
        this.size = size;
    }

    public String getExtension() {
        return extension;
    }

    @Override
    public Integer getSize() {
        return size;
    }

    @Override
    public void print(String indentation) {
        System.out.println(indentation + "File: " + getName() + getExtension() + " [" + size + " bytes]");
    }
}
