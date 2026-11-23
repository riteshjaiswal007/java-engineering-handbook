package designPattern.structural;

import java.util.*;

/*5. Composite Pattern
Purpose: Treat individual objects and groups of objects uniformly using a tree structure.
Real-world example: A file system containing files and directories.*/
interface FileSystemItem {
    void show();
}

class FileItem implements FileSystemItem {
    private final String name;

    FileItem(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println("File: " + name);
    }
}

class Directory implements FileSystemItem {
    private final String name;
    private final List<FileSystemItem> children = new ArrayList<>();

    Directory(String name) {
        this.name = name;
    }

    void add(FileSystemItem item) {
        children.add(item);
    }

    public void show() {
        System.out.println("Directory: " + name);
        children.forEach(FileSystemItem::show);
    }
}

public class CompositeDemo {
    public static void main(String[] args) {
        Directory root = new Directory("root");

        root.add(new FileItem("a.txt"));
        root.add(new FileItem("b.txt"));

        Directory sub = new Directory("documents");
        sub.add(new FileItem("resume.pdf"));

        root.add(sub);
        root.show();
    }
}