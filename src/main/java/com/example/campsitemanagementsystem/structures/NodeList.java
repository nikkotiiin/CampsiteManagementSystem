package com.example.campsitemanagementsystem.structures;

public class NodeList<E> {

    private Node<E> head;

    public void add(E element) {
        Node<E> newNode = new Node<E>(element);
        newNode.next = head;
        head = newNode;
    }
    public boolean remove(E element) {
        if (head == null) {
            return false;
        }
        if(head.getContents().equals(element)){
            head = head.next;
            return true;
        }
        Node<E> previous = head;
        Node<E> current = head.next;

        while(current!=null){
            if(current.getContents().equals(element)){
                previous.next=current.next;

                return true;
            }
            previous=previous.next;
            current=current.next;

        }


        return false;
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
    public boolean isEmpty() {
        return head == null;

    }

    public void clear() {
        head = null;

    }

}
