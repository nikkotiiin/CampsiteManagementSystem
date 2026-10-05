package com.example.campsitemanagementsystem.structures;

public class NodeList<E> {

    private Node<E> head;

    public void add(E element) {
        Node<E> newNode = new Node<E>(element);
        newNode.next = head;
        head = newNode;
            }


    public int size(){
        int count = 0;
        Node<E> temp = head;

        while (temp !=null){
            count ++;
            temp=temp.next;
        }
        return count;
    }
    public E getElement(int index) {
        Node<E> temp = head;
        int currentIndex = 0;
        while (temp!=null){
            if (currentIndex == index) {
                return temp.getContents();
            }
            temp = temp.next;
            currentIndex++;

        }
        return null;

    }

}
