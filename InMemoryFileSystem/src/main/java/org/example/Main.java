package org.example;

import org.example.fileStructure.Directory;
import org.example.fileStructure.File;
import org.example.filter.SearchFilter;
import org.example.filter.SizeFilter;

import java.sql.Array;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //make a directory structure and search for files based on a filter
        Directory root = new Directory("root");
        Directory subDir1 = new Directory("subDir1");
        Directory subDir2 = new Directory("subDir2");
        File file1 = new File("file1", "txt", 100);
        File file2 = new File("file2", "txt", 200);
        File file3 = new File("file3", "doc", 300);
        subDir1.addChildren(file1);
        subDir1.addChildren(file2);
        subDir2.addChildren(file3);
        root.addChildren(subDir1);
        root.addChildren(subDir2);

        root.print("");
        System.out.println("Root size: " + root.getSize());

        SearchFilter filter = new SizeFilter(SizeFilter.OPERATION.GREATER_THAN, 150);
        FileSearcher fileSearcher = new FileSearcher();
        System.out.println(fileSearcher.search(root, filter).stream().map(File::getName).toList());
    }
}