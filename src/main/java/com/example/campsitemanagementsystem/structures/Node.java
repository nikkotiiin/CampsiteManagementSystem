package com.example.campsitemanagementsystem.structures;

public class Node<E> {
    private E contents;
    public Node<E> next;

    public Node(E contents) {
        this.contents = contents;
    }
    public E getContents() {
        return contents;
    }
    public void setContents(E contents) {
        this.contents = contents;
    }
}
